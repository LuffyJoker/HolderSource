package com.holderzone.holder.saas.store.dto.dto.organization;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("品牌查询入参")
public class QueryBrandDTO {

    @ApiModelProperty("品牌guid")
    private String brandGuid;

    @ApiModelProperty("品牌名字")
    private String brandName;
}
