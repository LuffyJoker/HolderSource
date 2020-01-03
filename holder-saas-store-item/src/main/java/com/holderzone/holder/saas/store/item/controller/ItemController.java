package com.holderzone.holder.saas.store.item.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mr.Q
 * @date 2019/12/29 21:36
 * desc：
 */
@RestController
@RequestMapping("/item")
@Slf4j
public class ItemController {

    private final IItemService itemService;
    private final ISkuService skuService;
    private final ItemHelper itemHelper;

    @Autowired
    public ItemController(IItemService itemService, ISkuService skuService, ItemHelper itemHelper) {
        this.itemService = itemService;
        this.skuService = skuService;
        this.itemHelper = itemHelper;
    }

    /**
     * @param itemReqDTO 保存商品
     * @return desc 1:success  2:fail  3:save itme success , allot store fail.
     */

    @PostMapping("/save_item")
    public Integer saveItem(@RequestBody @Valid ItemReqDTO itemReqDTO) {
        log.info("商品新增接口入参,itemReqDTO={}", JacksonUtils.writeValueAsString(itemReqDTO));
        itemReqDTO = itemHelper.change(itemReqDTO);
        List<String> insertSkuGuidList = itemService.saveItem(itemReqDTO);
        if (!CollectionUtils.isEmpty(itemReqDTO.getDataList())) {
            Integer push = itemHelper.distributeItems2Stores(itemReqDTO, insertSkuGuidList);
            return Integer.valueOf(1).equals(push) ? 1 : 3;
        }
        return 1;
    }

    /**
     * @param itemReqDTO 更新商品
     * @return
     */
    @PostMapping("/update_item")
    public Integer updateItem(@RequestBody @Valid ItemReqDTO itemReqDTO) {
        log.info("商品update接口入参,itemReqDTO={}", JacksonUtils.writeValueAsString(itemReqDTO));
        itemReqDTO = itemHelper.change(itemReqDTO);
        if (StringUtils.isEmpty(itemReqDTO.getItemGuid())) {
            throw new ParameterException(WRONG_PARAMS);
        }
        // 入库的规格GUID集合
        List<String> saveOrUpdateSkuGuidList = itemService.updateItem(itemReqDTO);
        if (!CollectionUtils.isEmpty(itemReqDTO.getDataList())) {
            // 推送
            Integer push = itemHelper.distributeItems2Stores(itemReqDTO, saveOrUpdateSkuGuidList);
            return Integer.valueOf(1).equals(push) ? 1 : 3;
        }
        return 1;
    }

    @PostMapping("/delete_item")
    public Integer deleteItem(@RequestBody @Valid ItemSingleDTO itemSingleDTO) {
        log.info("商品delete接口入参,itemSingleDTO={}", JacksonUtils.writeValueAsString(itemSingleDTO));
        ItemStringListDTO itemStringListDTO = MapStructUtils.INSTANCE.itemSingleDTO2itemStringListDTO(itemSingleDTO);
        itemStringListDTO.setDataList(Arrays.asList(itemSingleDTO.getData()));
        return itemService.batchDelete(itemStringListDTO);
    }

    /**
     * 安卓同步商品及分类接口
     *
     * @param baseDTO
     * @return
     */
    @ApiOperation(value = "安卓端以及微信端同步商品接口，必填参数：storeGuid")
    @PostMapping("/query_for_synchronize")
    public ItemAndTypeForAndroidRespDTO selectItemAndTypeForSyn(@RequestBody BaseDTO baseDTO) {
        log.info("正餐商品同步接口入参,baseDTO={}", JacksonUtils.writeValueAsString(baseDTO));
        long startTime=System.currentTimeMillis();
        if (StringUtils.isEmpty(baseDTO.getStoreGuid())) {
            throw new ParameterException("门店唯一标识不能为空");
        }
        ItemAndTypeForAndroidRespDTO itemAndTypeForAndroidRespDTO = itemService.selectItemDetailAndTypeForSyn(baseDTO.getStoreGuid());
        log.warn("程序运行时间： {}",(System.currentTimeMillis() - startTime)+"ms");
        return itemAndTypeForAndroidRespDTO;
    }

    @PostMapping("/select_item_for_web")
    public Page<ItemWebRespDTO> selectItemForWeb(@RequestBody @Valid ItemQueryReqDTO itemQueryReqDTO) {
        log.info("商品列表接口入参,itemQueryReqDTO={}", JacksonUtils.writeValueAsString(itemQueryReqDTO));
        return itemService.selectItemListForWeb(itemQueryReqDTO);
    }

    @PostMapping("/get_item_info")
    public ItemInfoRespDTO selectItemInfo(@RequestBody ItemSingleDTO itemSingleDTO) {
        log.info("获取一个商品详情接口入参,itemSingleDTO={}", JacksonUtils.writeValueAsString(itemSingleDTO));
        return itemService.getItemInfo(itemSingleDTO.getData());
    }

    @ApiOperation("获取导出菜品数据")
    @PostMapping("/download_data")
    public List<ItemExcelTemplateRespDTO> getDownloadItemData(@RequestBody ItemSingleDTO itemSingleDTO) {
        log.info("获取导出菜品数据入参:{}", JacksonUtils.writeValueAsString(itemSingleDTO));
        return skuService.listDownloadData(itemSingleDTO);
    }

    /**
     * 查询当前品牌是否有绑定的商品分类或商品
     *
     * @param brandGuid 当前品牌GUID
     * @return true:count>0,即品牌下有商品分类或商品;false:count=0，即品牌下无商品分类或商品
     */
    @PostMapping("/count_type_or_item")
    public boolean countTypeOrItem(@RequestParam String brandGuid) {
        return itemService.countTypeOrItem(brandGuid);
    }

    /**
     * 更新菜品排序
     *
     * @return boolean
     */
    @PostMapping("update_item_sort")
    public boolean updateItemSort(@RequestBody @Valid ItemSortUpdateReqDTO itemSortUpdateReqDTO) {

        log.info("更新菜品排序入参:{}", JacksonUtils.writeValueAsString(itemSortUpdateReqDTO));
        return itemService.updateItemSort(itemSortUpdateReqDTO);
    }

    /**
     * 推送商品
     *
     * @return boolean
     */
    @PostMapping("push_item")
    public Integer pushItem(@RequestBody @Valid PushItemReqDTO pushItemReqDTO) {

        log.info("推送商品入参:{}", JacksonUtils.writeValueAsString(pushItemReqDTO));
        return itemService.pushItems(pushItemReqDTO);
    }

    /**
     * 商品批量导入
     *
     * @param itemDoubleParamDTO itemDoubleParamDTO
     * @return 导入的数量
     */
    @PostMapping("/batch_import")
    public int batchImport(@RequestBody ItemDoubleParamDTO<List<ItemExcelTemplateReqDTO>, String> itemDoubleParamDTO) {
        log.info("商品批量导入入参：{}", JacksonUtils.writeValueAsString(itemDoubleParamDTO));
        if (ObjectUtils.isEmpty(itemDoubleParamDTO.getFrom())) {
            throw new ParameterException("调用内部服务请必填模块入口参数：from");
        }
        CreateRepertoryReqDTO createRepertoryReqDTO = itemService.batchImport(itemDoubleParamDTO);
        if (Optional.ofNullable(createRepertoryReqDTO).isPresent()) {
            itemService.batchOpenStack(createRepertoryReqDTO, itemDoubleParamDTO.getSecondData());
        }
        return 1;
    }

    /**
     * 上下架商品
     *
     * @return boolean
     */
    @PostMapping("rack_item")
    public Integer rackItem(@RequestBody @Valid ItemRackDTO itemRackDTO) {
        log.info("上下架商品入参:{}", JacksonUtils.writeValueAsString(itemRackDTO));
        return skuService.rack(itemRackDTO);
    }


    /**
     * 整单折扣商品
     *
     * @return boolean
     */
    @PostMapping("whole_discount_item")
    public Integer wholeDiscountItem(@RequestBody @Valid ItemWholeDiscountDTO itemWholeDiscountDTO) {
        log.info("整单折扣商品入参:{}", JacksonUtils.writeValueAsString(itemWholeDiscountDTO));
        return skuService.wholeDiscount(itemWholeDiscountDTO);
    }


    @PostMapping("/batch_delete")
    public Integer batchDelete(@RequestBody ItemStringListDTO itemStringListDTO) {
        log.info("商品batch_delete接口入参,itemSingleDTO={}", JacksonUtils.writeValueAsString(itemStringListDTO));
        itemHelper.validateItemStringListDTO(itemStringListDTO);
        return itemService.batchDelete(itemStringListDTO);
    }

    /**
     * 美团获取商品映射接口
     *
     * @param itemSingleDTO
     * @return
     */
    @PostMapping("/mapping")
    public List<MappingRespDTO> mapping(@RequestBody @Valid ItemSingleDTO itemSingleDTO) {
        log.info("商品mapping接口入参,itemSingleDTO={}", JacksonUtils.writeValueAsString(itemSingleDTO));
        return skuService.mapping(itemSingleDTO.getData());
    }

    @PostMapping("/kds_mapping")
    public List<MappingRespDTO> kdsMapping(@RequestBody @Valid ItemSingleDTO itemSingleDTO) {
        log.info("商品mapping接口入参,itemSingleDTO={}", JacksonUtils.writeValueAsString(itemSingleDTO));
        return skuService.kdsMapping(itemSingleDTO.getData());
    }

    @PostMapping("/selectSkuInfo")
    public List<SkuTakeawayInfoRespDTO> selectSkuTakeawayInfoRespDTOList(@RequestBody ItemStringListDTO itemStringListDTO) {
        log.info("外卖下单获取规格详情入参,itemStringListDTO={}", JacksonUtils.writeValueAsString(itemStringListDTO));
        itemHelper.validateItemStringListDTO(itemStringListDTO);
        return skuService.selectSkuTakeawayInfoRespDTOList(itemStringListDTO);
    }

    @PostMapping("/selectTypeItemList")
    public List<TypeItemListDTO> selectTypeItemList(@RequestBody BaseDTO baseDTO) {
        log.info("获取打印商品列表入参,baseDTO={}", JacksonUtils.writeValueAsString(baseDTO));
        itemHelper.validateStoreGuid(baseDTO);
        return itemService.selectTypeItemList(baseDTO);
    }

    /**
     * 组织模块变更门店所属品牌时，需要调用的接口，以清除当前门店中存在的之前绑定品牌推送过来的商品，分类，属性数据。
     * 且调用此接口时，门店与品牌绑定关系必须为一个门店只能绑定一个品牌，如果一个门店能绑定多个品牌，则接口内容需要变更。
     *
     * @param storeGuid
     * @return
     */
    @PostMapping("/remove_push")
    public Integer removePush(@RequestParam String storeGuid) {
        log.info("移除指定门店被推实体入参,storeGuid={}", JacksonUtils.writeValueAsString(storeGuid));
        return itemService.removePush(storeGuid);
    }

    /**
     * 按分类guid 和 商品名称分页获取sku item列表
     *
     * @param request
     * @return
     */
    @PostMapping("/get_sku_item_list")
    public Page<ItemTemplateSubItemRespDTO> selectSkuItemList(@RequestBody @Valid ItemTemplateMenuAllSubItemReqDTO request) {
        log.info("按条件分页获取sku商品列表入参,request={}", JacksonUtils.writeValueAsString(request));
        Page<ItemTemplateSubItemRespDTO> skuItemList = itemService.selectSkuItemList(request);
        return skuItemList;

    }

    /**
     * 商品批量导入之前验证 获取商品集合 flag 0：查询品牌商品  1：查询门店商品
     *
     * @param request
     * @return
     */
    @PostMapping("/get_items_before_import")
    public List<ItemBatchImportTempRespDTO> selectItemByNameBeforeImportThenInBatches(@RequestBody @Valid BatchImportGetItemsReqDTO request) {
        log.info("根据flag 查询商品集合,request={}", JacksonUtils.writeValueAsString(request));
        return itemService.selectItemsBeforeImport(request);
    }

    /**
     * 报表大屏菜品支持
     *
     * @param request
     * @return
     */
    @PostMapping("/get_journaling_item")
    public List<JournalingItemRespDTO> selectJournalingItem(@RequestBody @Valid BaseDTO request) {
        log.info("报表大屏菜品支持->查询企业下门店所有商品（包含推送商品）, request={}", JacksonUtils.writeValueAsString(request));
        return itemService.selectJournalingItem();
    }

    /**
     * 根据商品guid获取商品图片集合
     *
     * @param request
     */
    @PostMapping("/get_item_picture_urls")
    public List<ItemImgDTO> selectItemPictureUrls(@RequestBody SingleDataDTO request) {
        log.info("根据商品guid获取商品图片集合, request={}", JacksonUtils.writeValueAsString(request));
        return itemService.selectItemPictureUrls(request);
    }


    /**
     * mdm 对接 erp扣减订单下品牌商品的bom -> 查询品牌下商品guid
     *
     * @param request
     * @return
     */
    @PostMapping("/get_erp_sku_guid")
    public Map<String, String> selectErpSkuGuidBySkuGuid(@RequestBody SingleDataDTO request) {
        log.info("订单扣减ERP库存根据门店sku查询父级品牌guid,request = {}", JacksonUtils.writeValueAsString(request));
        return skuService.selectErpSkuGuidBySkuGuid(request);
    }


    /**
     * 获取团餐详情
     *
     * @param request
     * @return
     */
    @PostMapping("/select_group_meal_detail")
    public GroupMealItemDetailRespDTO selectGroupMealDetail(@RequestBody SingleDataDTO request) {
        log.info("获取团餐详情 入参， request = {}", JacksonUtils.writeValueAsString(request));
        return itemService.selectGroupMealDetail(request.getData());
    }


}

