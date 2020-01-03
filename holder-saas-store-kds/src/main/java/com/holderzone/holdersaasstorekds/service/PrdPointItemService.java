package com.holderzone.holdersaasstorekds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.holderzone.holdersaasstorekds.entity.domain.PrdPointItemDO;

public interface PrdPointItemService extends IService<PrdPointItemDO> {
    void reInitialize(String storeGuid, String deviceId);
}
