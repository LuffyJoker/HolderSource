package com.holderzone.holdersaasstorekds.entity.read;

import com.holderzone.holdersaasstorekds.entity.domain.BaseDO;
import com.holderzone.holdersaasstorekds.entity.domain.ProductionPointDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PointItemReadDO extends BaseDO {

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
    private String pointName;

    /**
     * 商品列表数量
     */
    private Integer itemCount;

    /**
     * 商品列表
     */
    private List<KitchenItemReadDO> items;

    public static PointItemReadDO of(ProductionPointDO productionPointDO) {
        PointItemReadDO pointItemReadDO = new PointItemReadDO();
        pointItemReadDO.setStoreGuid(productionPointDO.getStoreGuid());
        pointItemReadDO.setDeviceId(productionPointDO.getDeviceId());
        pointItemReadDO.setPointName(productionPointDO.getName());
        pointItemReadDO.setItemCount(0);
        pointItemReadDO.setItems(Collections.emptyList());
        pointItemReadDO.setId(productionPointDO.getId());
        pointItemReadDO.setGuid(productionPointDO.getGuid());
        pointItemReadDO.setGmtCreate(productionPointDO.getGmtCreate());
        pointItemReadDO.setGmtModified(productionPointDO.getGmtModified());
        return pointItemReadDO;
    }
}

