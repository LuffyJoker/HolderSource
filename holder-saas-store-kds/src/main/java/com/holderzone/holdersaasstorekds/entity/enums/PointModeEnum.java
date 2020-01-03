package com.holderzone.holdersaasstorekds.entity.enums;

import com.holderzone.framework.exception.runtime.BusinessException;

/**
 * kds 设备点位模式
 */
public enum PointModeEnum {

    PRODUCTION(0, "制作点"),

    DISTRIBUTE(1, "出堂口"),

    ;

    private Integer pointMode;

    private String pointDesc;

    PointModeEnum(Integer pointMode, String pointDesc) {
        this.pointMode = pointMode;
        this.pointDesc = pointDesc;
    }

    public String getPointDesc() {
        return pointDesc;
    }

    public static PointModeEnum ofMode(Integer pointMode) {
        for (PointModeEnum pointModeEnum : PointModeEnum.values()) {
            if (pointModeEnum.pointMode.equals(pointMode)) {
                return pointModeEnum;
            }
        }
        throw new BusinessException("不支持的PointMode：" + pointMode);
    }

    public static String getDescByMode(Integer pointMode) {
        return ofMode(pointMode).getPointDesc();
    }
}

