package com.holderzone.holdersaasstorekds.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.holderzone.holdersaasstorekds.entity.domain.DistributeAreaDO;
import com.holderzone.holdersaasstorekds.mapper.DistributeAreaMapper;
import com.holderzone.holdersaasstorekds.service.DistributeAreaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DistributeAreaServiceImpl extends ServiceImpl<DistributeAreaMapper, DistributeAreaDO> implements DistributeAreaService {
    @Override
    public void reInitialize(String storeGuid, String deviceId) {
        remove(new LambdaQueryWrapper<DistributeAreaDO>()
                .eq(DistributeAreaDO::getStoreGuid, storeGuid)
                .eq(DistributeAreaDO::getDeviceId, deviceId));
    }
}
