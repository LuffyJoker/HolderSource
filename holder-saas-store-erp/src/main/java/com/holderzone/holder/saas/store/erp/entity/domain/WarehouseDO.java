package com.holderzone.holder.saas.store.erp.entity.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WarehouseDO {
    private String guid;
    private String name;
    private String code;
    private String addr;
    private String pic;
    private String tel;
    private String remark;
    private Integer type;
    private Integer status;
    private Integer deleted;
    private Integer enabled;
    private String foreignKey;
    private String enterpriseGuid;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
}
