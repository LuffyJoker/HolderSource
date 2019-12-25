package com.holderzone.framework.feign.starter.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Mr.Q
 * @date 2019/12/25 20:30
 * desc：
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserContext implements Serializable {

    /**
     * 企业GUID
     */
    private String enterpriseGuid;

    /**
     * 企业名称
     */
    private String enterpriseName;

    /**
     * 企业编码
     */
    private String enterpriseNo;

    /**
     * 门店GUID
     */
    private String storeGuid;

    /**
     * 门店名称
     */
    private String storeName;

    /**
     * 门店编码
     */
    private String storeNo;

    /**
     * 用户GUID
     */
    private String userGuid;

    /**
     * 用户名称
     */
    @JsonProperty("name")
    private String userName;

    /**
     * 用户帐号
     */
    private String account;

    /**
     * 用户电话
     */
    private String tel;

    /**
     * 联盟id
     */
    private String allianceId;

    /**
     * 来源设备类型
     */
    private String source;
}

