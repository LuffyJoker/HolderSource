package com.holderzone.holder.saas.store.dto.enums;

/**
 * @author Mr.Q
 * @date 2019/12/29 22:31
 * desc：
 */
public enum MchntTypeEnum {

    CATERING("10001", "餐饮"),

    RETAIL("10002", "零售"),

    ;

    private String code;

    private String desc;

    MchntTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
