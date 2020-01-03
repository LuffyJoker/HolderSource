package com.holderzone.holder.saas.store.dto.dto.kds;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class DeviceDstConfDTO implements Serializable {

    private static final long serialVersionUID = 5268151227383540588L;

    @NotNull(message = "屏幕显示分类不得为空")
    @Min(value = 1, message = "屏幕显示分类：外卖=1，快餐=2，正餐=4，全部=8，多选时相加取和")
    @Max(value = 15, message = "屏幕显示分类：外卖=1，快餐=2，正餐=4，全部=8，多选时相加取和")
    @ApiModelProperty(value = "屏幕显示分类：外卖=1，快餐=2，正餐=4，全部=8，多选时相加取和")
    private Integer displayType;

    @NotNull(message = "显示未制作菜品不得为空")
    @ApiModelProperty(value = "显示未制作菜品")
    private Boolean isDisplayItemUnProduced;

    @NotNull(message = "菜品超时显示不得为空")
    @ApiModelProperty(value = "菜品超时显示")
    private Boolean isDisplayItemTimeout;

    @NotNull(message = "出堂即打印不得为空")
    @ApiModelProperty(value = "出堂即打印")
    private Boolean isDispatchAsPrint;

    @NotNull(message = "正餐出堂提醒不得为空")
    @ApiModelProperty(value = "正餐出堂提醒")
    private Boolean isDineInDispatchNotice;

    @NotNull(message = "快餐出堂提醒不得为空")
    @ApiModelProperty(value = "快餐出堂提醒")
    private Boolean isSnackDispatchNotice;

    @NotNull(message = "外卖出堂提醒不得为空")
    @ApiModelProperty(value = "外卖出堂提醒")
    private Boolean isTakeoutDispatchNotice;
}

