package com.holderzone.holder.saas.store.item.service;

import com.holderzone.holder.saas.store.dto.dto.item.ItemSingleDTO;

/**
 * @author Mr.Q
 * @date 2019/12/29 22:26
 * desc：
 */
public interface IDefaultDataService {

    /**
     * 添加默认属性、属性组
     *
     * @param itemSingleDTO data: 品牌guid或者门店guid  model: 0：门店初始化  1：品牌初始化
     */
    void addAttr(ItemSingleDTO itemSingleDTO);

    /**
     * 添加默认属性、属性组
     *
     * @param itemSingleDTO data: 品牌guid或者门店guid  model: 0：门店初始化  1：品牌初始化
     */
    void addInitTypeAndItem(ItemSingleDTO itemSingleDTO);
}
