package com.holderzone.holdersaasstoreitem.service.impl;

import com.holderzone.framework.exception.BusinessException;
import com.holderzone.holdersaasstoredto.dto.item.ItemSingleDTO;
import com.holderzone.holdersaasstoreitem.entity.domain.AttrDO;
import com.holderzone.holdersaasstoreitem.entity.domain.AttrGroupDO;
import com.holderzone.holdersaasstoreitem.entity.domain.TypeDO;
import com.holderzone.holdersaasstoreitem.service.IDefaultDataService;
import com.holderzone.sdk.util.BatchIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author Mr.Q
 * @date 2019/12/29 22:27
 * desc：
 */
@Service
public class DefaultDataServiceImpl implements IDefaultDataService {
    private final IAttrService attrService;
    private final IAttrGroupService attrGroupService;
    private final DynamicHelper dynamicHelper;
    private final RedisTemplate redisTemplate;
    private final ITypeService typeService;
    private final IItemService itemService;
    private final ISkuService skuService;

    @Autowired
    public DefaultDataServiceImpl(IAttrService attrService, IAttrGroupService attrGroupService, DynamicHelper dynamicHelper, RedisTemplate redisTemplate, ITypeService typeService, IItemService itemService, ISkuService skuService) {
        this.attrService = attrService;
        this.attrGroupService = attrGroupService;
        this.dynamicHelper = dynamicHelper;
        this.redisTemplate = redisTemplate;
        this.typeService = typeService;
        this.itemService = itemService;
        this.skuService = skuService;
    }

    @Override
    public void addAttr(ItemSingleDTO itemSingleDTO) {
        Map<String, String[]> defaultAttrGroup = new HashMap<>(3);
        defaultAttrGroup.put("温度", new String[]{"去冰", "少冰", "多冰", "常温", "热"});
        defaultAttrGroup.put("口味", new String[]{"不辣", "微辣", "中辣", "特辣"});
        defaultAttrGroup.put("做法", new String[]{"不要葱", "不要蒜", "不要香菜"});
        List<AttrGroupDO> attrGroupDOList = new ArrayList<>(3);
        List<AttrDO> attrDOList = new ArrayList<>();
        defaultAttrGroup.forEach((key, value) -> {
            try {
                String attrGroupGuid = String.valueOf(BatchIdGenerator.getGuid(redisTemplate, "item"));
                AttrGroupDO attrGroupDO = new AttrGroupDO()
                        .setIsEnable(TRUE)
                        .setGuid(attrGroupGuid);
                if(Objects.equals(itemSingleDTO.getModel(),0)) {
                    attrGroupDO.setAttrGroupFrom(ModuleEntranceEnum.STORE.code());
                    attrGroupDO.setStoreGuid(itemSingleDTO.getData());
                }else{
                    attrGroupDO.setAttrGroupFrom(ModuleEntranceEnum.BRAND.code());
                    attrGroupDO.setBrandGuid(itemSingleDTO.getData());
                }
                attrGroupDO.setName(key);
                for (int i = 0; i < value.length; i++) {
                    AttrDO attrDO = new AttrDO()
                            .setAttrFrom(Objects.equals(itemSingleDTO.getModel(), 0) ? ModuleEntranceEnum.STORE.code() : ModuleEntranceEnum.BRAND.code())
                            .setGuid(String.valueOf(BatchIdGenerator.getGuid(redisTemplate, "item")))
                            .setAttrGroupGuid(attrGroupGuid)
                            .setIsDefault(FALSE);
                    if (Objects.equals(itemSingleDTO.getModel(), 0)) {
                        attrDO.setStoreGuid(itemSingleDTO.getData());
                    } else {
                        attrDO.setBrandGuid(itemSingleDTO.getData());
                    }
                    attrDO.setName(value[i]);
                    attrDOList.add(attrDO);
                }
                attrGroupDOList.add(attrGroupDO);
            } catch (IOException e) {
                throw new BusinessException("BatchIdGenerator生成商品guid失败");
            }
        });
        // 排序
        for (int i = 0; i < attrGroupDOList.size(); i++) {
            attrGroupDOList.get(i).setSort(i + 1);
        }
        if (!CollectionUtils.isEmpty(attrGroupDOList)) {
            attrGroupService.saveBatch(attrGroupDOList, attrGroupDOList.size());
        }
        if (!CollectionUtils.isEmpty(attrDOList)) {
            attrService.saveBatch(attrDOList, attrDOList.size());
        }
    }

    @Override
    @Transactional
    public void addInitTypeAndItem(ItemSingleDTO itemSingleDTO) {
        TypeDO typeDO = new TypeDO();
        try {
            String typeGuid = String.valueOf(BatchIdGenerator.getGuid(redisTemplate, "type"));
            typeDO.setGuid(typeGuid);
            typeDO.setItemNum(1);
            typeDO.setName("默认分类");
            typeDO.setSort(1);
            typeDO.setIsEnable(1);
            typeDO.setTypeFrom(Objects.equals(itemSingleDTO.getModel(), 0) ? ModuleEntranceEnum.STORE.code() : ModuleEntranceEnum.BRAND.code());
            if(Objects.equals(itemSingleDTO.getModel(),0)) {
                typeDO.setStoreGuid(itemSingleDTO.getData());
            }else{
                typeDO.setBrandGuid(itemSingleDTO.getData());
            }
            typeService.save(typeDO); //初始化默认门店分类

            ItemDO itemDO = new ItemDO();
            String itemGuid = String.valueOf(BatchIdGenerator.getGuid(redisTemplate, "item"));
            itemDO.setGuid(itemGuid);
            itemDO.setTypeGuid(typeGuid);
            itemDO.setItemType(4);
            itemDO.setHasAttr(0);
            itemDO.setName("默认商品");
            itemDO.setPinyin("MRSP");
            itemDO.setSort(1);
            itemDO.setIsNew(1);
            itemDO.setIsBestseller(1);
            itemDO.setIsSign(1);
            itemDO.setItemFrom(Objects.equals(itemSingleDTO.getModel(), 0) ? ModuleEntranceEnum.STORE.code() : ModuleEntranceEnum.BRAND.code());
            if(Objects.equals(itemSingleDTO.getModel(),0)) {
                itemDO.setStoreGuid(itemSingleDTO.getData());
            }else{
                itemDO.setBrandGuid(itemSingleDTO.getData());
            }
            itemService.save(itemDO);  //初始化默认门店商品

            SkuDO skuDO = new SkuDO();
            String skuGuid = String.valueOf(BatchIdGenerator.getGuid(redisTemplate, "sku"));
            skuDO.setItemGuid(itemGuid);
            skuDO.setGuid(skuGuid);
            skuDO.setName("");
            skuDO.setSalePrice(BigDecimal.ONE);
            skuDO.setUnit("份");
            skuDO.setMinOrderNum(BigDecimal.ONE);
            skuDO.setIsRack(1);
            skuDO.setIsJoinWeChat(1);
            skuDO.setSkuFrom(Objects.equals(itemSingleDTO.getModel(), 0) ? ModuleEntranceEnum.STORE.code() : ModuleEntranceEnum.BRAND.code());
            if(Objects.equals(itemSingleDTO.getModel(),0)) {
                skuDO.setStoreGuid(itemSingleDTO.getData());
            }else{
                skuDO.setBrandGuid(itemSingleDTO.getData());
            }
            skuService.save(skuDO);  //初始化默认单品规格
        } catch (Exception e) {
            throw  new BusinessException("初始化数据失败");
        }
    }
}

