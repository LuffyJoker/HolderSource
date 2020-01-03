package com.holderzone.holdersaasstorekds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.holderzone.holdersaasstorekds.entity.domain.DistributeAreaDO;

public interface DistributeAreaService extends IService<DistributeAreaDO> {
    void reInitialize(String storeGuid, String deviceId);
}
