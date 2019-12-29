package com.holderzone.holdersaasstoreitem.controller;

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
@RequestMapping("/retail/item")
@Slf4j
public class RetailItemController {


    private final  IRetailItemService retailItemService;

    public RetailItemController(IRetailItemService retailItemService) {
        this.retailItemService = retailItemService;
    }

    @ApiOperation("获取商品列表")
    @PostMapping("/select_item_for_web")
    public Page<ItemWebRespDTO> selectItemForWeb(@RequestBody @Valid ItemQueryReqDTO itemQueryReqDTO) {
        log.info("零售商品列表接口入参,itemQueryReqDTO={}", JacksonUtils.writeValueAsString(itemQueryReqDTO));
        return retailItemService.selectItemListForWeb(itemQueryReqDTO);
    }


    /**
     * 商超根据分类guid获取商品列表
     *
     * @param request
     * @return
     */
    @PostMapping("/get_item_sort_list")
    public Page<ItemSortRespDTO> getItemSortList(@RequestBody @Valid ItemQueryReqDTO request) {
        log.info("商超排序分类获取商品集合,request = {}", JacksonUtils.writeValueAsString(request));
        return retailItemService.getItemSortList(request);
    }

    /**
     * 商超商品更新排序
     *
     * @param request
     * @return
     */
    @PostMapping("/retail_update_item_sort")
    public Integer retailUpdateItemSort(@RequestBody @Valid ItemRetailSortReqDTO request) {
        log.info("商超排序分类获取商品集合,request = {}", JacksonUtils.writeValueAsString(request));
        return retailItemService.retailUpdateItemSort(request) ? 1 : 2;
    }

    /**
     * 商超获取分类排序和分类下商品集合
     * @param request
     * @return
     */
    @PostMapping("/get_sort_type_and_item")
    public ItemRetailSortRespDTO getSortTypeAndItems(@RequestBody @Valid ItemSingleDTO request) {
        request.setFrom(0); //设置门店来源查询
        log.info("商超获取分类排序和分类下商品集合,request = {}", JacksonUtils.writeValueAsString(request));
        return  retailItemService.getSortTypeAndItems(request);
    }


}

