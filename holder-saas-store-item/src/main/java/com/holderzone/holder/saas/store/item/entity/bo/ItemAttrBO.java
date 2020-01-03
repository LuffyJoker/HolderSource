package com.holderzone.holder.saas.store.item.entity.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Mr.Q
 * @date 2019/12/29 23:21
 * desc：
 */
@Data
@ApiModel
public class ItemAttrBO implements Serializable {
    private static final long serialVersionUID = 5589200904023786169L;
    @ApiModelProperty(value = "商品与属性组关联实体的唯一标识")
    private String itemAttrGroupGuid;
    @ApiModelProperty(value = "属性详情与商品和属性组关联实体的关联实体GUID",required = true)
    private String attrItemAttrGroupGuid;
    @ApiModelProperty(value = "商品属性详情GUID",required = true)
    private String attrGuid;

    @ApiModelProperty(value = "属性详情名称",required = true)
    private String name;

    @ApiModelProperty(value = "价格",required = true)
    private BigDecimal price;

    @ApiModelProperty(value = "是否默认，1：是，0,否",required = true)
    private Integer isDefault;
}

