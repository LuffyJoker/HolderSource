package com.holderzone.holder.saas.store.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Mr.Q
 * @date 2019/12/29 21:36
 * desc：
 */
@RestController
@RequestMapping("/attr")
@Slf4j
public class AttrController {
    @Autowired
    private IAttrGroupService attrGroupService;
    @Autowired
    private IAttrService attrService;

    /**
     * 属性组列表
     *
     * @param itemSingleDTO 门店guid   keywords: 0:查询自建属性  2：推送属性
     * @return 属性组list（包括属性组下的属性值）
     */
    @PostMapping("/list_attr_group")
    public List<AttrGroupAttrRespDTO> listAttrGroup(@RequestBody ItemSingleDTO itemSingleDTO) {
        log.info("属性组列表接口入参,storeGuid：{}", JacksonUtils.writeValueAsString(itemSingleDTO));
        checkFrom(itemSingleDTO);
        return attrGroupService.listAttrGroupByOrganization(itemSingleDTO);
    }

    /**
     * 新增商品获取属性组列表
     *
     * @param itemSingleDTO 门店guid or 品牌guid
     * @return 属性组list（只含启用的属性组），包括属性值
     */
    @PostMapping("/list_attr_for_save_item")
    public List<AttrGroupAttrRespDTO> listAttrForSaveItem(@RequestBody ItemSingleDTO itemSingleDTO) {
        log.info("新增商品获取属性组列表接口入参,storeGuid：{}", JacksonUtils.writeValueAsString(itemSingleDTO));
        checkFrom(itemSingleDTO);
        return attrGroupService.listAttrForSaveItem(itemSingleDTO);
    }

    /**
     * 获取某个属性组下属性值列表
     *
     * @param itemSingleDTO 属性组guid
     * @return 属性值list
     */
    @PostMapping("/list_attr_by_group")
    public List<AttrRespDTO> listAttrByGroup(@RequestBody ItemSingleDTO itemSingleDTO) {
        log.info("获取某个属性组下属性值列表入参,属性组guid：{}", JacksonUtils.writeValueAsString(itemSingleDTO));
        checkFrom(itemSingleDTO);
        return attrService.listAttrByGroup(itemSingleDTO);
    }

    /**
     * 新建属性组
     *
     * @param attrGroupReqDTO attrGroupReqDTO
     * @return Integer
     */
    @PostMapping("/save_attr_group")
    public boolean addAttrGroup(@RequestBody @Valid AttrGroupReqDTO attrGroupReqDTO) {
        log.info("save_attr_group接口入参,attrGroupReqDTO={}", JacksonUtils.writeValueAsString(attrGroupReqDTO));
        checkFrom(attrGroupReqDTO);
        return attrGroupService.addAttrGroup(attrGroupReqDTO);
    }

    /**
     * 修改属性组
     *
     * @param attrGroupUpdateReqDTO attrGroupUpdateReqDTO
     * @return Integer
     */
    @PostMapping("/set_attr_group")
    public boolean updateAttrGroup(@RequestBody AttrGroupUpdateReqDTO attrGroupUpdateReqDTO) {
        log.info("set_attr_group,attrGroupUpdateReqDTO={}", JacksonUtils.writeValueAsString(attrGroupUpdateReqDTO));
        checkFrom(attrGroupUpdateReqDTO);
        return attrGroupService.updateAttrGroup(attrGroupUpdateReqDTO);
    }

    /**
     * 新建属性值
     *
     * @param attrReqDTO attrReqDTO
     * @return boolean
     */
    @PostMapping("/save_attr")
    public boolean saveAttrValue(@RequestBody @Valid AttrReqDTO attrReqDTO) {
        log.info("新建属性值接口入参,attrReqDTO={}", JacksonUtils.writeValueAsString(attrReqDTO));
        checkFrom(attrReqDTO);
        return attrService.saveAttrValue(attrReqDTO);
    }

    /**
     * 修改属性值
     *
     * @param attrSaveReqDTO attrSaveReqDTO
     * @return Integer
     */
    @PostMapping("/update_attr")
    public boolean updateAttrValue(@RequestBody @Valid AttrSaveReqDTO attrSaveReqDTO) {
        log.info("修改属性值入参:{}", JacksonUtils.writeValueAsString(attrSaveReqDTO));
        checkFrom(attrSaveReqDTO);
        return attrService.update(attrSaveReqDTO);
    }

    /**
     * 删除属性值
     *
     * @param itemSingleDTO 属性guid
     * @return boolean
     */
    @PostMapping("/delete_attr")
    public boolean deleteAttrValue(@RequestBody ItemSingleDTO itemSingleDTO) {
        log.info("删除属性值入参(属性值guid)：{}", JacksonUtils.writeValueAsString(itemSingleDTO));
        checkFrom(itemSingleDTO);
        return attrService.deleteByGuid(itemSingleDTO.getData());
    }

    /**
     * 删除属性组
     *
     * @param itemSingleDTO 属性组guid
     * @return Boolean
     */
    @PostMapping("/delete_attr_group")
    public boolean deleteAttrGroup(@RequestBody ItemSingleDTO itemSingleDTO) {
        log.info("删除属性组入参，属性组guid：{}", JacksonUtils.writeValueAsString(itemSingleDTO));
        checkFrom(itemSingleDTO);
        return attrGroupService.deleteByGuid(itemSingleDTO);
    }

    /**
     * 检查模块入口参数是否填写
     *
     * @param mchntItemBaseDTO mchntItemBaseDTO
     */
    private void checkFrom(MchntItemBaseDTO mchntItemBaseDTO) {
        if (ObjectUtils.isEmpty(mchntItemBaseDTO.getFrom())) {
            throw new ParameterException("调用内部服务请必填模块入口参数：form");
        }
    }
}

