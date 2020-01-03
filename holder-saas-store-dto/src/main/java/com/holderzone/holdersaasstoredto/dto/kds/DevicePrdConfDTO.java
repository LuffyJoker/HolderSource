package com.holderzone.holdersaasstoredto.dto.kds;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class DevicePrdConfDTO implements Serializable {

    private static final long serialVersionUID = 5268151227383540588L;

    @NotNull(message = "屏幕显示分类不得为空")
    @Min(value = 1, message = "屏幕显示分类：外卖=1，快餐=2，正餐=4，全部=8，多选时相加取和")
    @Max(value = 15, message = "屏幕显示分类：外卖=1，快餐=2，正餐=4，全部=8，多选时相加取和")
    @ApiModelProperty(value = "屏幕显示分类：外卖=1，快餐=2，正餐=4，全部=8，多选时相加取和")
    private Integer displayType;

    @NotNull(message = "菜品排序不得为空")
    @ApiModelProperty(value = "菜品排序")
    private Boolean isItemSort;

    @NotNull(message = "菜品超时显示不得为空")
    @ApiModelProperty(value = "菜品超时显示")
    private Boolean isItemTimeout;

    @NotNull(message = "显示挂起的菜品不得为空")
    @ApiModelProperty(value = "显示挂起的菜品")
    private Boolean isShowHangedItem;

    @NotNull(message = "挂起的菜品可提前制作不得为空")
    @ApiModelProperty(value = "挂起的菜品可提前制作")
    private Boolean isProduceHangedItem;

    @NotNull(message = "显示预约的菜品不得为空")
    @ApiModelProperty(value = "显示预约的菜品")
    private Boolean isShowReserveItem;

    @NotNull(message = "预约的菜品可提前制作不得为空")
    @ApiModelProperty(value = "预约的菜品可提前制作")
    private Boolean isProduceReserveItem;

    @NotNull(message = "菜品汇总模式下，按单次最大制作数拆单显示不得为空")
    @ApiModelProperty(value = "菜品汇总模式下，按单次最大制作数拆单显示")
    private Boolean isDisplayByMaxCopied;

    @NotNull(message = "制作完成需手动确定不得为空")
    @ApiModelProperty(value = "制作完成需手动确定")
    private Boolean isManualConfirm;

    @NotNull(message = "指派制作人不得为空")
    @ApiModelProperty(value = "指派制作人")
    private Boolean isSpecifiedStaff;

    @NotNull(message = "指定桌台制作不得为空")
    @ApiModelProperty(value = "指定桌台制作")
    private Boolean isSpecifiedTable;

    @NotNull(message = "新订单自动打印不得为空")
    @ApiModelProperty(value = "新订单自动打印")
    private Boolean isPrintAutomatic;

    // fixme 兼容旧版本，允许不传值，默认为false
//    @NotNull(message = "是否按订单打印不得为空")
    @Nullable
    @ApiModelProperty(value = "订单模式下是否按订单打印")
    private Boolean isPrintPerOrder;

    @Nullable
    @ApiModelProperty(value = "是否获取外卖订单")
    private Boolean isFilterTakeWay;

    @NotNull(message = "正餐新订单提醒不得为空")
    @ApiModelProperty(value = "正餐新订单提醒")
    private Boolean isDineInOrderNotice;

    @NotNull(message = "快餐新订单提醒不得为空")
    @ApiModelProperty(value = "快餐新订单提醒")
    private Boolean isSnackOrderNotice;

    @NotNull(message = "外卖新订单提醒不得为空")
    @ApiModelProperty(value = "外卖新订单提醒")
    private Boolean isTakeoutOrderNotice;
}

