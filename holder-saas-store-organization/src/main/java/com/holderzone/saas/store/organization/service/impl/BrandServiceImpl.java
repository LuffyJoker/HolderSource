package com.holderzone.saas.store.organization.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.holderzone.framework.exception.unchecked.BusinessException;
import com.holderzone.holdersaasstoredto.dto.organization.BrandDTO;
import com.holderzone.holdersaasstoredto.dto.organization.QueryBrandDTO;
import com.holderzone.saas.store.organization.domain.BrandDO;
import com.holderzone.saas.store.organization.mapper.BrandMapper;
import com.holderzone.saas.store.organization.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, BrandDO> implements BrandService {

    private final BrandMapper brandMapper;

//    private final StoreBrandMapper storeBrandMapper;
//
//    private final BrandMapstruct brandMapstruct;
//
//    private final RedisTemplate redisTemplate;
//
//    private final ItemClient itemClient;
//
//    private final DefaultRocketMqProducer defaultRocketMqProducer;
//
//    private final DistributedIdService distributedIdService;

    @Autowired
    public BrandServiceImpl(BrandMapper brandMapper
//                            StoreBrandMapper storeBrandMapper,
//                            BrandMapstruct brandMapstruct,
//                            RedisTemplate redisTemplate,
//                            ItemClient itemClient,
//                            DefaultRocketMqProducer defaultRocketMqProducer,
//                            DistributedIdService distributedIdService
    ) {
        this.brandMapper = brandMapper;
//        this.storeBrandMapper = storeBrandMapper;
//        this.brandMapstruct = brandMapstruct;
//        this.redisTemplate = redisTemplate;
//        this.itemClient = itemClient;
//        this.defaultRocketMqProducer = defaultRocketMqProducer;
//        this.distributedIdService = distributedIdService;
    }

    @Override
    public BrandDTO createBrand(BrandDTO brandDTO) {
        if (this.validateBrandName(brandDTO.getName())) {
            throw new BusinessException("品牌名称重复");
        }

        String guid;
        try {
            guid = String.valueOf(BatchIdGenerator.getGuid(redisTemplate, "brand"));
        } catch (IOException e) {
            throw new BusinessException("BatchIdGenerator生成品牌guid失败");
        }
        BrandDO brandDO = brandMapstruct.brandDTO2DO(brandDTO)
                .setGuid(guid)
                .setCreateUserGuid(UserContextUtils.getUserGuid())
                .setModifiedUserGuid(UserContextUtils.getUserGuid())
                .setGmtCreate(DateTimeUtils.now())
                .setGmtModified(DateTimeUtils.now());

        // 初始化品牌下的商品信息
        ItemSingleDTO itemSingleDTO = new ItemSingleDTO();
        itemSingleDTO.setData(guid);
        //itemClient.addDefaultAttr(itemSingleDTO);

        if (!this.save(brandDO)) {
            return null;
        }

        BrandDTO brandDTO1 = brandMapstruct.brandDO2DTO(this.getOne(
                new LambdaQueryWrapper<BrandDO>().eq(BrandDO::getGuid, guid)));
        // 给下游：品牌创建初始化菜品数据等
        Message message = new Message(MqConstant.DOWNSTREAM_BRAND_CREATE_TOPIC
                , MqConstant.DOWNSTREAM_BRAND_CREATE_TAG, JacksonUtils.toJsonByte(brandDTO1));
        message.getProperties().put(MqConstant.DOWNSTREAM_CONTEXT, UserContextUtils.getJsonStr());
        defaultRocketMqProducer.sendMessage(message);
        return brandDTO1;

    }

    @Override
    public boolean updateBrand(BrandDTO brandDTO) {
        BrandDO brandDO = brandMapstruct.brandDTO2DO(brandDTO)
                .setModifiedUserGuid(UserContextUtils.getUserGuid())
                .setGmtModified(DateTimeUtils.now());

        BrandDO originalDO = this.getOne(new LambdaQueryWrapper<BrandDO>().eq(BrandDO::getGuid, brandDO.getGuid()));
        // 若更新了品牌名称字段，则验证同企业下是否重复
        if (!originalDO.getName().equals(brandDO.getName()) && this.validateBrandName(brandDO.getName())) {
            throw new BusinessException("品牌名称重复");
        }
        if (!this.update(brandDO, new LambdaQueryWrapper<BrandDO>().eq(BrandDO::getGuid, brandDO.getGuid()))) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteBrand(String brandGuid) {
        if (this.queryExistStoreAccount(brandGuid)) {
            throw new BusinessException("该品牌下已存在门店，请清理后删除");
        }
        if (!this.remove(new LambdaQueryWrapper<BrandDO>().eq(BrandDO::getGuid, brandGuid))) {
            return false;
        }
        return true;
    }

    @Override
    public BrandDTO queryBrandByGuid(String brandGuid) {
        BrandDO brandDO = this.getOne(new LambdaQueryWrapper<BrandDO>().eq(BrandDO::getGuid, brandGuid));
        if (null == brandDO) return null;
        return brandMapstruct.brandDO2DTO(brandDO);
    }

    @Override
    public List<BrandDTO> queryBrandByIdList(List<String> guidList) {
        if (CollectionUtils.isEmpty(guidList)) {
            return Collections.emptyList();
        }
        List<BrandDO> list = this.list(new LambdaQueryWrapper<BrandDO>()
                .select(BrandDO::getGuid, BrandDO::getName)
                .in(BrandDO::getGuid, guidList));
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        return brandMapstruct.brandDOList2DTOList(list);
    }

    @Override
    public List<BrandDTO> queryAllList(QueryBrandDTO queryBrandDTO) {
        if (!ObjectUtils.isEmpty(queryBrandDTO) && StringUtils.hasText(queryBrandDTO.getBrandName())) {
            return brandMapstruct.brandDOList2DTOList(this.list(new LambdaQueryWrapper<BrandDO>()
                    .like(BrandDO::getName, queryBrandDTO.getBrandName())));
        }
        return brandMapstruct.brandDOList2DTOList(this.list(null));
    }

    @Override
    public boolean queryExistStoreAccount(String brandGuid) {
        Wrapper<StoreBrandDO> wrapper = new LambdaQueryWrapper<StoreBrandDO>().eq(StoreBrandDO::getBrandGuid, brandGuid);
        return storeBrandMapper.selectCount(wrapper) > 0 || itemClient.countTypeOrItem(brandGuid);
    }

    @Override
    public List<String> queryStoreGuidListByBrandGuid(String brandGuid) {
        log.info("获取门店guid列表请求参数：{}", brandGuid);
        List<String> list = null;
        List<StoreBrandDO> storeBrandDOS = storeBrandMapper.selectList(new LambdaQueryWrapper<StoreBrandDO>()
                .eq(StoreBrandDO::getBrandGuid, brandGuid));
        if (!ObjectUtils.isEmpty(storeBrandDOS))
            list = storeBrandDOS.stream().map(StoreBrandDO::getStoreGuid).collect(Collectors.toList());
        return list;
    }

    @Override
    public BrandDTO createDefaultBrand() {
        BrandDO brandDO = new BrandDO();
        brandDO.setName("我的品牌");
        brandDO.setDescription("默认品牌");
        brandDO.setLogoUrl(null);
        brandDO.setIsEnable(true);
        brandDO.setIsDeleted(false);
        brandDO.setCreateUserGuid("未定义");
        brandDO.setModifiedUserGuid("未定义");
        brandDO.setGmtCreate(DateTimeUtils.now());
        brandDO.setGmtModified(DateTimeUtils.now());
        save(brandDO.setGuid(distributedIdService.nextBrandGuid()));
        BrandDTO brandDTO = brandMapstruct.brandDO2DTO(brandDO);

        // 给默认品牌添加默认商品属性组
        ItemSingleDTO itemSingleDTO = new ItemSingleDTO();
        itemSingleDTO.setData(brandDTO.getGuid());
        itemClient.addDefaultAttr(itemSingleDTO);

        log.info("send  msg to Mq ----------------------------- 创建默认品牌{}", JacksonUtils.writeValueAsString(brandDO));
        Message message = new Message(MemberSyncConstant.MEMBER_BRAND_SYNC_TOPIC,
                MemberSyncConstant.MEMBER_BRAND_MODIFY_TAG, JacksonUtils.toJsonByte(brandDTO));
        message.getProperties().put(MemberSyncConstant.MEMBER_SYNC_PROPERTY, UserContextUtils.getJsonStr());
        defaultRocketMqProducer.sendMessage(message);
        // 给下游：品牌创建初始化菜品数据等
        Message message1 = new Message(MqConstant.DOWNSTREAM_BRAND_CREATE_TOPIC
                , MqConstant.DOWNSTREAM_BRAND_CREATE_TAG, JacksonUtils.toJsonByte(brandDTO));
        message1.getProperties().put(MqConstant.DOWNSTREAM_CONTEXT, UserContextUtils.getJsonStr());
        defaultRocketMqProducer.sendMessage(message1);
        return brandDTO;
    }

    @Override
    public String queryDefaultBrand() {
        List<BrandDO> list = list(new LambdaQueryWrapper<BrandDO>()
                .orderByAsc(BrandDO::getGmtCreate)
                .last("limit 1"));
        if (CollectionUtils.isEmpty(list)) {
            return createDefaultBrand().getGuid();
        }
        return list.get(0).getGuid();
    }

    /**
     * 判断新增或修改时同企业下品牌名称是否重复
     *
     * @param brandName 品牌名称
     * @return 重复-true，未重复-false
     */
    private boolean validateBrandName(String brandName) {
        Wrapper<BrandDO> wrapper = new LambdaQueryWrapper<BrandDO>().eq(BrandDO::getName, brandName);
        return brandMapper.selectCount(wrapper) > 0;
    }
}

