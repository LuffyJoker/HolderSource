package com.holderzone.holdersaasstorekds.entity.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("hsk_point_item")
public class PrdPointItemDO extends BaseDO {

    /**
     * 门店Guid
     */
    private String storeGuid;

    /**
     * 设备Guid
     */
    private String deviceId;

    /**
     * 堂口Guid
     */
    private String pointGuid;

    /**
     * 商品Guid
     */
    private String itemGuid;

    /**
     * 规格Id
     */
    private String skuGuid;

    /**
     * 规格编码
     */
    private String skuCode;
}

