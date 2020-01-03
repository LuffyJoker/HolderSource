package com.holderzone.holder.saas.store.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Mr.Q
 * @date 2019/12/29 21:37
 * desc：
 */
@RestController
@RequestMapping("/item_template")
@Slf4j
public class ItemTemplateController {

    @Autowired
    private IItemTemplateService  iItemTemplateService;

    @Autowired
    private IItemTMenuService iItemTMenuService;

    @Autowired
    private IItmeTMenuValidityService itmeTMenuValidityService;

    @Autowired
    private  IItemTMenuSubitemService itemTMenuSubitemService;



    /**
     * 保存、更新商品模板
     * @param request
     * @return
     */
    @PostMapping("/save")
    public  Integer  saveItmeTemplate(@RequestBody @Valid ItemTemplateReqDTO request){
        log.info("商品模板新增or更新接口入参,request={}", JacksonUtils.writeValueAsString(request));
        return  iItemTemplateService.saveOrUpdate(request) ? 1: 0;
    }

    /**
     * 新增菜单及其菜单子项菜品、执行时间
     * @param request
     * @return
     */
    @PostMapping("/save_menu")
    public Integer saveItemMenu(@RequestBody @Valid ItemTemplateMenuSubitemReqDTO request){
        log.info("新增or更新菜单及其菜单子项菜品接口入参,request={}", JacksonUtils.writeValueAsString(request));
        return iItemTMenuService.saveItemMenu(request)? 1:0;
    }

    @PostMapping("/get_store_item_templates")
    public ItemTemplatesRespDTO getStoreItemTemplates(@RequestBody @Valid ItemTemplateSearchReqDTO request){
        log.info("条件查询门店商品模板接口入参,request={}", JacksonUtils.writeValueAsString(request));
        return iItemTemplateService.getStoreItemTemplates(request);
    }

    @PostMapping("/get_item_template_menus")
    public List<ItemTemplateMenusRespDTO>  getItemTemplateMenus(@RequestBody @Valid SingleDataDTO request){
        log.info("查询门店商品模板菜单列表接口入参,request={}", JacksonUtils.writeValueAsString(request));
        return iItemTMenuService.getItemTemplateMenus(request);
    }

    @PostMapping("/get_item_template_execute_time")
    public List<Long> getTimeAndTypeForSyn(@RequestBody @Valid SingleDataDTO request){
        log.info("安卓同步商品模板执行时间接口入参,request={}", JacksonUtils.writeValueAsString(request));
        return  itmeTMenuValidityService.getTimeAndTypeForSyn(request);
    }



    @PostMapping("/get_item_template_menu_detail")
    public ItemTemplateMenuDetailRespDTO getItemTemplateMenuDetail(@RequestBody @Valid ItemTemplateMenuDetailsReqDTO request){
        log.info("获取商品模板菜单详情接口入参,request={}", JacksonUtils.writeValueAsString(request));
        ItemTemplateMenuDetailRespDTO result = itemTMenuSubitemService.getItemTemplateMenuDetail(request);
        return  result;
    }


    @PostMapping("/batch_remove")
    public  Integer menuSubItemBatchRemove(@RequestBody @Valid SingleDataDTO request){
        log.info("（批量）移除菜单下菜品接口入参,request={}", JacksonUtils.writeValueAsString(request));
        return  itemTMenuSubitemService.menuSubItemBatchRemove(request)?1:0;
    }

}

