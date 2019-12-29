package com.holderzone.holdersaasstoreitem.entity.bo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Mr.Q
 * @date 2019/12/29 23:20
 * desc：
 */
@Data
public class GroupMealSubItemBO  implements Serializable {
    /**
     *  关联guid
     */
    private String  guid ;

    /**
     * 团餐主项商品guid
     */
    private  String  itemGuid;
    /**
     * 类型名称
     */
    private String  typeName;

    /**
     * 类型guid
     */
    private String  typeGuid;
    /**
     * 类型下排序 越小越靠前
     */
    private Integer  sort ;
    /**
     *子菜的guid
     */
    private String  subItemGuid ;

    /**
     *子菜的商品类型
     */
    private Integer  subItemType ;
    /**
     *子菜的规格guid
     */
    private String  subSkuGuid ;
    /**
     *子菜名称
     */
    private String  itemName ;
    /**子菜规格名称
     *
     */
    private String  skuName;
    /**
     * 子菜总销售价
     */
    private BigDecimal salePrice;
    /**
     *子菜总成本价
     */
    private BigDecimal  costPrice;
    /**
     * 子菜销售价
     */
    private BigDecimal  subSkuSalePrice;
    /**
     *子菜成本价
     */
    private BigDecimal  subSkuCostPrice;

    /**
     *子菜数量
     */
    private Integer num;
    /**
     *子菜的规格单位
     */
    private String  unit;

}

