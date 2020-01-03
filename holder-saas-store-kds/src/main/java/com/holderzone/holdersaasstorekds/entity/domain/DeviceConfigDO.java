package com.holderzone.holdersaasstorekds.entity.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.holderzone.holdersaasstorekds.entity.enums.DisplayModeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("hsk_device_config")
public class DeviceConfigDO extends BaseDO {

    /**
     * 门店Guid
     */
    private String storeGuid;

    /**
     * 设备名称
     */
    private String name;

    /**
     * 堂口模式：
     * 0=制作点
     * 1=出堂口
     *
     * @see PointModeEnum
     */
    private Integer pointMode;

    /**
     * 显示模式：
     * <p>
     * 制作点位显示模式：
     * 0=堂口模式       1*4
     * 1=菜品汇总模式   2*4
     * 2=订单模式       1*4
     * 3=订单模式       1*6
     * <p>
     * 出堂点位显示模式：
     * 0=单菜品模式     2*4
     * 1=菜品汇总模式   2*4
     * 2=订单模式       1*4
     * 3=订单模式       1*6
     *
     * @see DisplayModeEnum
     */
    private Integer displayMode;

    /**
     * 屏幕显示分类
     * 0b00001111
     * 低0位 外卖 1
     * 低1位 快餐 2
     * 低2位 正餐 4
     * 低3位 全部 8
     */
    private Integer displayType;

    /*****production point config*****/

    /**
     * 菜品排序
     * 按照 催单->叫起->普通下单->预约/挂起 排序
     * 默认开启，暂不支持更改
     */
    private Boolean isItemSort;

    /**
     * 菜品超时显示
     * 关闭后，堂口仅按下单时间显示菜品或订单，但不会显示等待时长
     */
    private Boolean isItemTimeout;

    /**
     * 显示挂起的菜品
     * 关闭后，将不显示挂起的菜品
     */
    private Boolean isShowHangedItem;

    /**
     * 挂起的菜品可提前制作
     * 关闭后，挂起的菜品不可提前制作
     */
    private Boolean isProduceHangedItem;

    /**
     * 显示预约的菜品
     * 关闭后，将不显示预约的菜品
     */
    private Boolean isShowReserveItem;

    /**
     * 预约的菜品可提前制作
     * 关闭后，预约的菜品不可提前制作
     */
    private Boolean isProduceReserveItem;

    /**
     * 菜品汇总模式下，按单次最大制作数拆单显示
     */
    private Boolean isDisplayByMaxCopied;

    /**
     * 制作完成需手动确定
     */
    private Boolean isManualConfirm;

    /**
     * 指派制作人
     * 默认不指派，暂不支持更改
     */
    private Boolean isSpecifiedStaff;

    /**
     * 指定桌台制作
     * 默认不指定，暂不支持更改
     */
    private Boolean isSpecifiedTable;

    /**
     * 新订单自动打印
     * 开启后，点击制作不会再次打印；关闭则需要手动点击制作后打印
     */
    private Boolean isPrintAutomatic;

    /**
     * 订单模式下是否按订单打印
     * 订单模式包括：订单模式1X4，订单模式1X6，混合模式（汇总模式+订单模式）
     */
    private Boolean isPrintPerOrder;

    /**
     * 是否获取外卖订单
     */
    private Boolean isFilterTakeWay;

    /**
     * 正餐新订单提醒
     * 您有一笔新的订单
     */
    private Boolean isDineInOrderNotice;

    /**
     * 快餐新订单提醒
     * 您有一笔新的快销订单
     */
    private Boolean isSnackOrderNotice;

    /**
     * 外卖新订单提醒
     * 您有一笔新的外卖订单
     */
    private Boolean isTakeoutOrderNotice;

    /*****production point config end*****/

    /*****dispatch point config*****/

    /**
     * 显示未制作菜品
     * 关闭之后，制作口必须点击制作按钮后，出堂口才能看到菜品
     */
    private Boolean isDisplayItemUnProduced;

    /**
     * 菜品超时显示
     * 关闭后，堂口仅按下单时间显示菜品或订单，但不会显示等待时长
     */
    private Boolean isDisplayItemTimeout;

    /**
     * 出堂即打印
     * 默认开启，暂不支持更改
     */
    private Boolean isDispatchAsPrint;

    /**
     * 正餐出堂提醒
     * 暂不支持正餐语音播报
     */
    private Boolean isDineInDispatchNotice;

    /**
     * 快餐出堂提醒
     * 请**（取餐号）前来就餐
     */
    private Boolean isSnackDispatchNotice;

    /**
     * 外卖出堂提醒
     * 请外卖订单***（取餐号）前来就餐
     */
    private Boolean isTakeoutDispatchNotice;

    /*****dispatch point config end*****/

    /**
     * 打印机Guid
     */
    private String printerGuid;

    /**
     * 是否已完成初始化
     */
    private Boolean isInitialized;

    /**
     * 制作点 和 出堂口 的默认配置
     *
     * @return
     */
    public static DeviceConfigDO defaultConfig() {
        DeviceConfigDO deviceConfigDO = new DeviceConfigDO();
        deviceConfigDO.setDisplayType(0);
        deviceConfigDO.setIsItemSort(true);
        deviceConfigDO.setIsItemTimeout(true);
        deviceConfigDO.setIsShowHangedItem(false);
        deviceConfigDO.setIsProduceHangedItem(false);
        deviceConfigDO.setIsShowReserveItem(false);
        deviceConfigDO.setIsProduceReserveItem(false);
        deviceConfigDO.setIsDisplayByMaxCopied(false);
        deviceConfigDO.setIsManualConfirm(false);
        deviceConfigDO.setIsSpecifiedStaff(false);
        deviceConfigDO.setIsSpecifiedTable(false);
        deviceConfigDO.setIsPrintAutomatic(true);
        deviceConfigDO.setIsDineInOrderNotice(true);
        deviceConfigDO.setIsSnackOrderNotice(true);
        deviceConfigDO.setIsTakeoutOrderNotice(true);
        deviceConfigDO.setIsDisplayItemUnProduced(true);
        deviceConfigDO.setIsDisplayItemTimeout(true);
        deviceConfigDO.setIsDispatchAsPrint(false);
        deviceConfigDO.setIsDineInDispatchNotice(false);
        deviceConfigDO.setIsSnackDispatchNotice(false);
        deviceConfigDO.setIsTakeoutDispatchNotice(false);
        return deviceConfigDO;
    }

    public boolean shouldPrintPerOrder() {
        return shouldPrintPerOrder(true);
    }

    public boolean shouldPrintPerOrder(boolean isOrderModeInPoint) {
        return (DisplayModeEnum.PRD_ORDER.getDisplayMode().equals(displayMode) ||
                DisplayModeEnum.PRD_SUMMARY_ORDER.getDisplayMode().equals(displayMode) ||
                (DisplayModeEnum.PRD_POINT.getDisplayMode().equals(displayMode) && isOrderModeInPoint)) && isPrintPerOrder;
    }
}

