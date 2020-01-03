package com.holderzone.holdersaasstorekds.entity.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("hsk_distribute_area")
public class DistributeAreaDO extends BaseDO {

    /**
     * 门店Guid
     */
    private String storeGuid;

    /**
     * 设备Guid
     */
    private String deviceId;

    /**
     * 区域GUID
     * 快餐 mockAreaGuid: 2
     * 外卖 mockAreaGuid: 1
     */
    private String areaGuid;

    public static final String SNACK_AREA_GUID = "snack_area_guid";

    public static final String SNACK_AREA_NAME = "快餐";

    public static final String SNACK_TABLE_GUID = "snack_table_guid";

    public static final String TAKEOUT_AREA_GUID = "takeout_area_guid";

    public static final String TAKEOUT_AREA_NAME = "外卖";

    public static final String TAKEOUT_TABLE_GUID = "takeout_table_guid";

    public static DistributeAreaDO snack(String deviceId) {
        DistributeAreaDO distributeAreaDO = new DistributeAreaDO();
        distributeAreaDO.setGuid("snack_guid");
        distributeAreaDO.setDeviceId(deviceId);
        distributeAreaDO.setAreaGuid(SNACK_AREA_GUID);
        return distributeAreaDO;
    }

    public static DistributeAreaDO takeout(String deviceId) {
        DistributeAreaDO distributeAreaDO = new DistributeAreaDO();
        distributeAreaDO.setGuid("takeout_guid");
        distributeAreaDO.setDeviceId(deviceId);
        distributeAreaDO.setAreaGuid(TAKEOUT_AREA_GUID);
        return distributeAreaDO;
    }
}

