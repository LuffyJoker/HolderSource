package com.holderzone.holdersaasstorekds.entity.read;

import com.holderzone.holdersaasstorekds.entity.domain.BaseDO;
import com.holderzone.holdersaasstorekds.entity.domain.DeviceConfigDO;
import com.holderzone.holdersaasstorekds.entity.domain.ItemConfigDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class KitchenItemReadDO extends BaseDO implements ItemComparable {

    /**
     * 门店Guid
     */
    private String storeGuid;

    /**
     * 堂口Gui
     */
    private String pointGuid;

    /**
     * 制作点设备Guid
     */
    private String prdDeviceId;

    /**
     * 出堂口设备Guid
     */
    private String dstDeviceId;

    /**
     * 屏幕显示分类
     * 0b00001111
     * 低0位 外卖 1
     * 低1位 快餐 2
     * 低2位 正餐 4
     * 低3位 全部 8
     * <p>
     * tradeMode
     * 0：正餐
     * 1：快餐
     * 2：外卖
     * <p>
     * 映射关系如下
     * tradeMode 0 -> displayType 4
     * tradeMode 1 -> displayType 2
     * tradeMode 2 -> displayType 1
     */
    private Integer displayType;

    /**
     * 订单Guid
     * 并台情况下，订单拆单显示
     */
    private String orderGuid;

    /**
     * 正餐：正餐
     * 快餐：快餐
     * 外卖：饿了么|美团
     */
    private String orderDesc;

    /**
     * 正餐：订单号
     * 快餐：订单号
     * 外卖：订单号
     */
    private String orderNumber;

    /**
     * 正餐：桌台
     * 快餐：牌号
     * 外卖：日流水号
     */
    private String orderSerialNo;

    /**
     * 整单备注
     */
    private String orderRemark;

    /**
     * 区域Guid
     */
    private String areaGuid;

    /**
     * 桌台Guid
     */
    private String tableGuid;

    /**
     * 订单下商品唯一Guid，
     * 不同于guid
     * 不同于itemGuid
     * 该字段标识唯一的一次菜品(批量)操作
     * <p>
     * 比如：白菜x10，则
     * guid表示这个1份的标识
     * itemGuid表示这个item类型的标识
     * orderItemGuid表示这个10份的标识
     */
    private String orderItemGuid;

    /**
     * 商品Guid
     */
    private String itemGuid;

    /**
     * 商品名称
     */
    private String itemName;

    /**
     * skuGuid
     */
    private String skuGuid;

    /**
     * sku名称
     */
    private String skuName;

    /**
     * sku编码
     */
    private String skuCode;

    /**
     * sku单位
     */
    private String skuUnit;

    /**
     * 是否是称重商品
     */
    private Boolean isWeight;

    /**
     * 当前商品数量
     * 称重       具体重量
     * 非称重     具体份数
     */
    private BigDecimal currentCount;

    /**
     * 已退商品数量
     */
    @Deprecated
    private BigDecimal returnCount;

    /**
     * 商品属性MD5，ItemAttrMd5BO转Json转Md5
     */
    private String itemAttrMd5;

    /**
     * 菜品备注
     */
    private String itemRemark;

    /**
     * 超时阈值
     * 单位：分钟
     */
    private Integer timeout;

    /**
     * 催促次数
     */
    @Deprecated
    private Integer urgedTimes;

    /**
     * 商品状态
     * 1.即起
     * 2.挂起
     * 3.叫起
     * 8.已上菜(已划菜)
     * 9.预定
     * 99.退菜
     */
    private Integer itemState;

    /**
     * 厨房商品状态
     * 4.待制作
     * 5.制作中
     * 6.待出堂
     * 7.已出堂
     */
    private Integer kitchenState;

    /**
     * 新订单自动打印
     * 开启后，点击制作不会再次打印；关闭则需要手动点击制作后打印
     */
    private Boolean isPrintAutomatic;

    /**
     * 准备时间
     */
    private LocalDateTime prepareTime;

    /**
     * 挂起时间
     */
    private LocalDateTime hangUpTime;

    /**
     * 叫起时间
     */
    private LocalDateTime callUpTime;

    /**
     * 叫起时间
     */
    private LocalDateTime urgedTime;

    /**
     * 制作人Guid
     */
    private String cookStaffGuid;

    /**
     * 制作人姓名
     */
    private String cookStaffName;

    /**
     * 制作时间
     */
    private LocalDateTime cookTime;

    /**
     * 完成人Guid
     */
    private String completeStaffGuid;

    /**
     * 完成人姓名
     */
    private String completeStaffName;

    /**
     * 完成时间
     */
    private LocalDateTime completeTime;

    /**
     * 出堂人Guid
     */
    private String distributeStaffGuid;

    /**
     * 出堂人姓名
     */
    private String distributeStaffName;

    /**
     * 出堂时间
     */
    private LocalDateTime distributeTime;

    /**
     * 撤销出堂人Guid
     */
    private String cancelDstStaffGuid;

    /**
     * 撤销出堂人姓名
     */
    private String cancelDstStaffName;

    /**
     * 撤销出堂时间
     */
    private LocalDateTime cancelDstTime;

    /**
     * 制作点配置
     */
    private DeviceConfigDO prdDeviceConfig;

    /**
     * 出堂口配置
     */
    private DeviceConfigDO dstDeviceConfig;

    /**
     * 菜品配置
     */
    private ItemConfigDO itemConfig;

    /**
     * 属性列表
     */
    private List<KitchenItemAttrDO> attrs;

    @Override
    public LocalDateTime getUrgedTimeForSort() {
        return urgedTime;
    }

    @Override
    public LocalDateTime getCallUpTimeForSort() {
        return callUpTime;
    }

    @Override
    public LocalDateTime getPrepareTimeForSort() {
        return prepareTime;
    }

    @Override
    public LocalDateTime getHangUpTimeForSort() {
        return hangUpTime;
    }

    @Override
    public Integer getSplitIndexForSort() {
        return null;
    }

    public boolean isTypeMatched(boolean isAllType, boolean isOrderMode) {
        int itemDisplayType = Optional.ofNullable(itemConfig)
                .map(ItemConfigDO::getDisplayType)
                .orElse(Constants.ITEM_DISPLAY_TYPE);
        return isAllType || (isOrderMode && 0 == itemDisplayType) || (!isOrderMode && 1 == itemDisplayType);
    }

    /**
     * make sure that all those items are un refund.
     * always be true generally unless that the query of sql makes mistakes.
     *
     * @return
     */
    public boolean isUnRefund() {
        return itemState < 4;
    }

    public boolean isNormalState() {
        return isUnRefund() && (1 == itemState || 3 == itemState);
    }

    public boolean isCompleted() {
        return isUnRefund() && 6 == kitchenState;
    }

    public boolean isCooking() {
        return isUnRefund() && (5 == kitchenState || 6 == kitchenState);
    }

    public boolean isCookingIncludeNormalState() {
        return isUnRefund() && ((4 == kitchenState && isNormalState()) || 5 == kitchenState || 6 == kitchenState);
    }

    public boolean isCookingIncludeAllTheState() {
        return isUnRefund() && (4 == kitchenState || 5 == kitchenState || 6 == kitchenState);
    }
}

