package com.holderzone.holder.saas.store.dto.dto.erp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Data
@Setter
@Getter
@NoArgsConstructor
public class WarehouseReqDTO implements Serializable {
    private static final long serialVersionUID = 3990685863840271598L;
    @ApiModelProperty(value = "主键guid")
    private String guid;
    @ApiModelProperty(value = "仓库名称")
    private String name;
    @ApiModelProperty(value = "仓库编号")
    private String code;
    @ApiModelProperty(value = "仓库地址")
    private String addr;
    @ApiModelProperty(value = "负责人")
    private String pic;
    @ApiModelProperty(value = "电话")
    private String tel;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "类型(0:企业库，1:门店库，2:其他)")
    private Integer type;
    @ApiModelProperty(value = "门店或企业guid")
    private String foreignKey;
    @ApiModelProperty(value = "企业guid")
    private String enterpriseGuid;
}

