package com.holderzone.holder.saas.store.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mr.Q
 * @date 2019/12/29 21:36
 * desc：
 */
@Slf4j
@RestController
@RequestMapping("/default_data")
public class DefaultDataController {
    @Autowired
    private IDefaultDataService defaultDataService;

    /**
     * 新建品牌时添加默认属性、属性组
     *
     * @param itemSingleDTO 品牌guid
     */
    @PostMapping("/add_data")
    public void addDefaultData(@RequestBody ItemSingleDTO itemSingleDTO) {
        log.info("创建品牌[{}]时添加默认属性、属性组;", itemSingleDTO.getData());
        defaultDataService.addAttr(itemSingleDTO);
    }
}

