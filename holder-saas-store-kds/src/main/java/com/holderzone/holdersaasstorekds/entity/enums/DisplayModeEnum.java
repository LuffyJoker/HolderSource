package com.holderzone.holdersaasstorekds.entity.enums;

import com.holderzone.framework.exception.runtime.BusinessException;

/**
 * kds 设备显示模式
 */
public enum DisplayModeEnum {

    PRD_POINT(0, 0, "制作点", "堂口模式"),

    PRD_SUMMARY_ITEM(0, 1, "制作点", "菜品汇总模式"),

    PRD_ORDER(0, 2, "制作点", "订单模式"),

    PRD_SUMMARY_ORDER(0, 3, "制作点", "菜品汇总+订单模式"),

    DST_SINGLE_ITEM(1, 0, "出堂口", "单菜品模式"),

    DST_SUMMARY_ITEM(1, 1, "出堂口", "菜品汇总模式"),

    DST_ORDER(1, 2, "出堂口", "订单模式"),

    DST_SUMMARY_ORDER(1, 3, "出堂口", "菜品汇总+订单模式"),

    ;

    private Integer pointMode;

    private Integer displayMode;

    private String pointDesc;

    private String displayDesc;

    DisplayModeEnum(Integer pointMode, Integer displayMode, String pointDesc, String displayDesc) {
        this.pointMode = pointMode;
        this.displayMode = displayMode;
        this.pointDesc = pointDesc;
        this.displayDesc = displayDesc;
    }

    public Integer getPointMode() {
        return pointMode;
    }

    public Integer getDisplayMode() {
        return displayMode;
    }

    public String getPointDesc() {
        return pointDesc;
    }

    public String getDisplayDesc() {
        return displayDesc;
    }

    public static DisplayModeEnum ofMode(Integer pointMode, Integer displayMode) {
        for (DisplayModeEnum displayModeEnum : DisplayModeEnum.values()) {
            if (displayModeEnum.pointMode.equals(pointMode)
                    && displayModeEnum.displayMode.equals(displayMode)) {
                return displayModeEnum;
            }
        }
        throw new BusinessException("不支持的类型，pointMode：" + pointMode + "displayMode：" + displayMode);
    }

}

