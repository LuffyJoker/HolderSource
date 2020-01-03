package com.holderzone.holdersaasstorekds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.holderzone.holdersaasstorekds.entity.domain.ProductionPointDO;

public interface ProductionPointService extends IService<ProductionPointDO> {
    void reInitialize(String storeGuid, String deviceId);
}
