package com.holderzone.holdersaasstoredto.dto.organization;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@ApiModel("品牌DTO")
@Data
@Accessors(chain = true)
public class BrandDTO implements Serializable {

    @NotNull(message = "更新时品牌guid不能为空", groups = Update.class)
    @ApiModelProperty(value = "品牌guid（更新时必传）")
    private String guid;

    @ApiModelProperty(value = "MDM 生成的MDM")
    private String uuid;

    @NotNull(message = "品牌名称不能为空")
    @ApiModelProperty(value = "品牌名称", required = true)
    private String name;

    @ApiModelProperty(value = "品牌介绍")
    private String description;

    @ApiModelProperty(value = "品牌logo（oss下载地址）")
    private String logoUrl;

    @ApiModelProperty(value = "是否启用（默认为1-已启用）")
    private Boolean isEnable;

    @ApiModelProperty(value = "是否删除（默认为0-未删除）")
    private Boolean isDeleted;

    @ApiModelProperty(value = "创建人guid")
    private String createUserGuid;

    @ApiModelProperty(value = "修改人guid")
    private String modifiedUserGuid;

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

    public interface Update {
    }
}
