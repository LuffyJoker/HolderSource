package com.holderzone.holdersaasstorekds.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.holderzone.holdersaasstorekds.entity.domain.ProductionPointDO;
import com.holderzone.holdersaasstorekds.mapper.ProductionPointMapper;
import com.holderzone.holdersaasstorekds.service.PrdPointItemService;
import com.holderzone.holdersaasstorekds.service.ProductionPointService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@SuppressWarnings("unchecked")
public class ProductionPointServiceImpl extends ServiceImpl<ProductionPointMapper, ProductionPointDO> implements ProductionPointService {

    @Autowired
    PrdPointItemService prdPointItemService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reInitialize(String storeGuid, String deviceId) {
        remove(new LambdaQueryWrapper<ProductionPointDO>()
                .eq(ProductionPointDO::getStoreGuid, storeGuid)
                .eq(ProductionPointDO::getDeviceId, deviceId));
        prdPointItemService.reInitialize(storeGuid, deviceId);
    }

}
