package com.holderzone.holdersaasstorekds.service.impl;

import com.holderzone.holdersaasstorekds.service.DistributeAreaService;
import com.holderzone.holdersaasstorekds.service.DistributeItemService;
import com.holderzone.holdersaasstorekds.service.DistributeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class DistributeServiceImpl implements DistributeService {

    @Autowired
    DistributeAreaService distributeAreaService;
    @Autowired
    DistributeItemService distributeItemService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reInitialize(String storeGuid, String deviceId) {
        distributeAreaService.reInitialize(storeGuid, deviceId);
        distributeItemService.reInitialize(storeGuid, deviceId);
    }
}
