package com.holderzone.holdersaasstoredto.dto.organization;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.holderzone.holdersaasstoredto.store.StoreProductDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * @author Mr.Q
 * @date 2019/12/29 22:24
 * desc：
 */
@ApiModel("门店DTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class StoreDTO implements Serializable {

    private static final long serialVersionUID = 1372192434495873269L;

    @NotNull(message = "更新时门店guid不能为空", groups = Update.class)
    @ApiModelProperty(value = "门店guid（更新时必传）")
    private String guid;

    @NotNull(message = "更新时门店编码不能为空", groups = Update.class)
    @ApiModelProperty(value = "门店编码（更新时必传）")
    private String code;

    @ApiModelProperty(value = "门店名称（创建、更新时不能为空）", required = true)
    private String name;

    @ApiModelProperty(value = "所属品牌的guid")
    private String belongBrandGuid;

    @ApiModelProperty(value = "所属组织的guid（从企业到上级组织的id数组，逗号分隔）", required = true)
    private String parentIds;

    @ApiModelProperty(value = "营业开始时间", required = true)
    @JsonFormat(pattern = "HH:mm:ss")
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime businessStart;

    @ApiModelProperty(value = "营业结束时间", required = true)
    @JsonFormat(pattern = "HH:mm:ss")
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime businessEnd;

    @ApiModelProperty(value = "联系人姓名")
    private String contactName;

    @ApiModelProperty(value = "联系人电话")
    private String contactTel;

    @ApiModelProperty(value = "省份编码")
    private String provinceCode;

    @ApiModelProperty(value = "省份名称")
    private String provinceName;

    @ApiModelProperty(value = "城市编码")
    private String cityCode;

    @ApiModelProperty(value = "城市名称")
    private String cityName;

    @ApiModelProperty(value = "区县编码")
    private String countyCode;

    @ApiModelProperty(value = "区县名称")
    private String countyName;

    @ApiModelProperty(value = "详细地址")
    private String addressDetail;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    private String latitude;

    @ApiModelProperty(value = "是否启用（默认为1-启用）", required = true)
    private Boolean isEnable;

    @ApiModelProperty(value = "是否删除（默认为0-未删除）", required = true)
    private Boolean isDeleted;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime gmtModified;

    @ApiModelProperty(value = "创建人guid")
    private String createUserGuid;

    @ApiModelProperty(value = "修改人guid")
    private String modifiedUserGuid;

    @ApiModelProperty(value = "门店使用产品集合")
    private List<StoreProductDTO> productDTOList;

    @ApiModelProperty(value = "门店所属品牌集合（包括品牌id、名称等）")
    private List<BrandDTO> brandDTOList;

    @ApiModelProperty(value = "门店经营类型")
    private String mchntTypeCode;

    interface Update {
    }

    @JsonIgnore
    public String getParentId() {
        return this.parentIds.substring(this.parentIds.lastIndexOf(",") + 1);
    }
}

