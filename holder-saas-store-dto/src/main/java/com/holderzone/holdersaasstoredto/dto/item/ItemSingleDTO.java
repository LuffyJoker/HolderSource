package com.holderzone.holdersaasstoredto.dto.item;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author Mr.Q
 * @date 2019/12/24 23:28
 * desc：
 */
@Data
@ApiModel
public class ItemSingleDTO {
    private static final long serialVersionUID = 1727528918478527413L;
    @ApiModelProperty(value = "最主要的业务请求参数")
    @NotEmpty
    private String data;

    @ApiModelProperty(value = "查询关键字")
    private String keywords;

    @ApiModelProperty(value = "初始化数据时 0：门店  1：品牌")
    private Integer model;
}
