package com.holderzone.holdersaasstorekds.entity.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 一个制作点可以有多个堂口，这个就是堂口数据表
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("hsk_production_point")
public class ProductionPointDO extends BaseDO {

    public static final int MAX_POINT = 4;

    /**
     * 门店Guid
     */
    private String storeGuid;

    /**
     * 设备Guid
     */
    private String deviceId;

    /**
     * 堂口名称
     */
    private String name;
}

