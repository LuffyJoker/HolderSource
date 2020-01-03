package com.holderzone.holdersaasstorekds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.holderzone.holdersaasstorekds.entity.domain.DistributeItemDO;

public interface DistributeItemService extends IService<DistributeItemDO> {

    void reInitialize(String storeGuid, String deviceId);

}
