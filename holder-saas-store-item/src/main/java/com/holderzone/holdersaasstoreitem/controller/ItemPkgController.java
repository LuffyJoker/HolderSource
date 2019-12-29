package com.holderzone.holdersaasstoreitem.controller;

import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Mr.Q
 * @date 2019/12/29 21:37
 * desc：
 */
@RestController
@RequestMapping("/item_pkg")
@Slf4j
public class ItemPkgController {
    @Autowired
    private IItemPkgService itemPkgService;
    @Autowired
    private ItemHelper itemHelper;

    @PostMapping("/select_sku_list")
    public List<TypeSkuRespDTO> selectSkuListForPkg(@RequestBody ItemSingleDTO itemSingleDTO) {
        log.info("套餐或推送获取规格列表接口入参,itemSingleDTO={}", JacksonUtils.writeValueAsString(itemSingleDTO));
        return itemPkgService.selectSkuListForPkg(itemSingleDTO);
    }

    @PostMapping("/save_pkg")
    public Integer savePkg(@RequestBody @Valid ItemPkgSaveReqDTO itemPkgSaveReqDTO) {
        log.info("套餐新增接口入参,itemPkgSaveReqDTO={}", JacksonUtils.writeValueAsString(itemPkgSaveReqDTO));
        List<String> insertSkuGuidList = itemPkgService.saveItemPkg(itemPkgSaveReqDTO);
        if (!CollectionUtils.isEmpty(itemPkgSaveReqDTO.getDataList())) {
            // 直接推送至安卓端或者推送至门店
            Integer push = itemHelper.distributeItems2Stores(itemPkgSaveReqDTO, insertSkuGuidList);
            return Integer.valueOf(1).equals(push) ? 1 : 3;
        }
        return 1;
    }

    @PostMapping("/update_pkg")
    public Integer updatePkg(@RequestBody @Valid ItemPkgSaveReqDTO itemPkgSaveReqDTO) {
        log.info("套餐update接口入参,itemPkgSaveReqDTO={}", JacksonUtils.writeValueAsString(itemPkgSaveReqDTO));
        List<String> insertSkuGuidList = itemPkgService.updateItemPkg(itemPkgSaveReqDTO);
        if (!CollectionUtils.isEmpty(itemPkgSaveReqDTO.getDataList())) {
            // 直接推送至安卓端或者推送至门店
            Integer push = itemHelper.distributeItems2Stores(itemPkgSaveReqDTO, insertSkuGuidList);
            return Integer.valueOf(1).equals(push) ? 1 : 3;
        }
        return 1;
    }

    /**
     * 保存/更新团餐
     * @param request
     * @return
     */
    @PostMapping("/save_group_meal_pkg")
    public Integer saveOrUpdateGroupMealPkg(@RequestBody @Valid ItemGroupMealSaveReqDTO request){
        log.info("门店团餐save or update 接口入参，request = {}",JacksonUtils.writeValueAsString(request));
        return itemPkgService.saveOrUpdateGroupMealPkg(request);
    }

}

