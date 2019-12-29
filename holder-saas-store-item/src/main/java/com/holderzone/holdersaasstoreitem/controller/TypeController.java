package com.holderzone.holdersaasstoreitem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Mr.Q
 * @date 2019/12/29 21:37
 * desc：
 */
@RestController
@RequestMapping("/type")
@Slf4j
public class TypeController {
    @Autowired
    private ITypeService typeService;
    @Autowired
    private ItemHelper itemHelper;
    @Autowired
    private IRetailItemService retailItemService;

    /**
     * 正餐获取分类排序接口
     *
     * @param itemSingleDTO
     * @return
     */
    @PostMapping("/get_sort")
    public Integer getSort(@RequestBody ItemSingleDTO itemSingleDTO) {
        log.info("分类获取分类排序接口入参,dishSingleDTO={}", JacksonUtils.writeValueAsString(itemSingleDTO));
        return typeService.getSort(itemSingleDTO);
    }

    @PostMapping("/save")
    public Integer save(@RequestBody @Valid TypeReqDTO typeReqDTO) {
        log.info("分类新增接口入参,typeReqDTO={}", JacksonUtils.writeValueAsString(typeReqDTO));
        return typeService.saveType(typeReqDTO);
    }

    @PostMapping("/update")
    public Integer update(@RequestBody @Valid TypeReqDTO typeReqDTO) {
        log.info("分类修改接口入参,typeReqDTO={}", JacksonUtils.writeValueAsString(typeReqDTO));
        return typeService.updateType(typeReqDTO);
    }

    @PostMapping("/delete")
    public Integer delete(@RequestBody @Valid ItemSingleDTO itemSingleDTO) {
        log.info("分类修改接口入参,itemSingleDTO={}", JacksonUtils.writeValueAsString(itemSingleDTO));
        return typeService.deleteType(itemSingleDTO);
    }

    @PostMapping("/quick_save")
    public Integer quickSave(@RequestBody TypeReqDTO typeReqDTO) {
        log.info("分类新增接口入参,typeReqDTO={}", JacksonUtils.writeValueAsString(typeReqDTO));
        validateQuickSaveTypeReqDTO(typeReqDTO);
        return typeService.saveType(typeReqDTO);
    }

    private void validateQuickSaveTypeReqDTO(TypeReqDTO typeReqDTO) {
        if (StringUtils.isEmpty(typeReqDTO.getName())) {
            throw new ParameterException("名称必填");
        }
    }

    @PostMapping("/query_type")
    public List<TypeWebRespDTO> queryType(@RequestBody ItemSingleDTO itemSingleDTO) {
        log.info("分类接口入参,itemSingleDTO={}", JacksonUtils.writeValueAsString(itemSingleDTO));
        return typeService.queryType(itemSingleDTO);
    }

    @PostMapping("/query_type_by_stores")
    public List<TypeWebRespDTO> queryTypeByStoreGuidList(@RequestBody ItemStringListDTO itemStringListDTO) {
        log.info("根据门店列表查询分类接口入参,itemStringListDTO={}", JacksonUtils.writeValueAsString(itemStringListDTO));
        itemHelper.validateItemStringListDTO(itemStringListDTO);
        return typeService.queryTypeByStoreGuidList(itemStringListDTO);
    }


    @PostMapping("/query_journaling_item_type")
    public List<JournalingItemRespDTO> queryJournalingItemType(@RequestBody @Valid BaseDTO request) {
        log.info("报表大屏商品分类支持->查询企业下门店所有商品分类（包含推送分类）, request={}", JacksonUtils.writeValueAsString(request));
        return typeService.queryJournalingItemType();
    }


    /**
     * 判断商品类型下面是否存在商品  存在1/2
     *
     * @param request
     * @return
     */
    @PostMapping("/verify_item_exists_for_type")
    public Integer verifyThatitemExistsForType(@RequestBody @Valid SingleDataDTO request) {
        log.info("判断商品类型下面是否存在商品,request = {}", JacksonUtils.writeValueAsString(request));
        return retailItemService.verifyThatitemExistsForType(request) ? 1 : 2;
    }

    /**
     * 修改团餐功能当前状态
     *
     * @param request
     * @return
     */
    @PostMapping("/set_group_meal_status")
    public String setGroupMealStatus(@RequestBody  SingleDataDTO request) {
        log.info("设置团餐当前状态, 入参 request = {}", JacksonUtils.writeValueAsString(request));
        return typeService.setGroupMealStatus(request) ? request.getData() :"-1";
    }


    /**
     * 查询团餐当前状态
     * @param request
     * @return
     */
    @PostMapping("/select_group_meal_status")
    public String  selectGroupMealStatus(@RequestBody  SingleDataDTO request){
        log.info("查询团餐当前状态，入参 request = {}",JacksonUtils.writeValueAsString(request));
        return  typeService.selectGroupMealStatus(request.getData());
    }

}

