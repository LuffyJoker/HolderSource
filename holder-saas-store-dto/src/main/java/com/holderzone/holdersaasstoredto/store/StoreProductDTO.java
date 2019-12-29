package com.holderzone.holdersaasstoredto.store;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Mr.Q
 * @date 2019/12/29 22:25
 * desc：
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class StoreProductDTO {

    @ApiModelProperty("产品guid")
    private String productGuid;

    @ApiModelProperty("产品名")
    private String productName;

    @ApiModelProperty("产品类型：0=时间，1=数量")
    private Integer productType;

    @ApiModelProperty("产品授权起始日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate gmtProductStart;

    @ApiModelProperty("产品授权截止日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate gmtProductEnd;

    @ApiModelProperty("数据创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "门店使用产品是否到期")
    private Boolean overdue;
}

