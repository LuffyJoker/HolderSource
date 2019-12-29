package com.holderzone.holdersaasstoredto.dto.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Mr.Q
 * @date 2019/12/29 21:44
 * desc：
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseDTO implements Serializable {

    private static final long serialVersionUID = -2537027369402350110L;

    @ApiModelProperty(value = "0：PC服务端,1：PC平板,2：小店通,3：一体机,4：POS机,5：云平板,6：点菜宝(M1),7：PV1(带刷卡的点菜宝),9：厨房显示系统,10: 取餐屏,12：微信",
            hidden = true)
    private Integer deviceType;

    @ApiModelProperty(value = "设备id", hidden = true)
    private String deviceId;

    @ApiModelProperty(value = "企业guid", hidden = true)
    private String enterpriseGuid;

    @ApiModelProperty(value = "企业名称", hidden = true)
    private String enterpriseName;

    @ApiModelProperty(value = "门店guid", hidden = true)
    private String storeGuid;

    @ApiModelProperty(value = "门店名称", hidden = true)
    private String storeName;

    @ApiModelProperty(value = "用户guid", hidden = true)
    private String userGuid;

    @ApiModelProperty(value = "用户名称", hidden = true)
    private String userName;

    @ApiModelProperty(value = "登录者帐号", hidden = true)
    private String account;

    @ApiModelProperty(value = "发起请求时间戳",hidden = true)
    private Long requestTimestamp;

    @ApiModelProperty(value = "请求过程耗时", hidden = true)
    public Long getElapsedTime() {
        if (requestTimestamp != null) {
            return System.currentTimeMillis() - requestTimestamp;
        }
        return null;

    }
}

