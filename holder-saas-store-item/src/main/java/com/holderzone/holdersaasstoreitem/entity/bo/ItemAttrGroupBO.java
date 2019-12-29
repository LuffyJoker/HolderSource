package com.holderzone.holdersaasstoreitem.entity.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Mr.Q
 * @date 2019/12/29 23:21
 * desc：
 */
@Data
@ApiModel
public class ItemAttrGroupBO implements Serializable {

    @ApiModelProperty(value = "商品GUID")
    private String itemGuid;
    @ApiModelProperty(value = "商品与属性组关联实体的唯一标识")
    private String itemAttrGroupGuid;
    @ApiModelProperty(value = "商品属性组GUID",required = true)
    private String attrGroupGuid;
    @ApiModelProperty(value = "属性组名称",required = true)
    private String name;
    @ApiModelProperty(value = "是否必选:0 否 1 是",required = true)
    private Integer isRequired;

    @ApiModelProperty(value = "是否多选:0 否 1 是",required = true)
    private Integer isMultiChoice;
    @ApiModelProperty(value = "maybe null,图标地址")
    private String iconUrl;
    @ApiModelProperty(value = "是否有默认属性，1：是，0,否",required = true)
    private Integer withDefault;
    @ApiModelProperty(value = "属性详情集合",required = true)
    private List<ItemAttrBO> attrList;
}

