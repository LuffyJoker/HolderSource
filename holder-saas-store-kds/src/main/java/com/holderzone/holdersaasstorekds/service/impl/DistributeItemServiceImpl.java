package com.holderzone.holdersaasstorekds.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.holderzone.holdersaasstorekds.entity.domain.DistributeItemDO;
import com.holderzone.holdersaasstorekds.mapper.DistributeItemMapper;
import com.holderzone.holdersaasstorekds.service.DistributeItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DistributeItemServiceImpl extends ServiceImpl<DistributeItemMapper, DistributeItemDO> implements DistributeItemService {

    @Override
    public void reInitialize(String storeGuid, String deviceId) {
        remove(new LambdaQueryWrapper<DistributeItemDO>()
                .eq(DistributeItemDO::getDeviceId, deviceId)
                .eq(DistributeItemDO::getStoreGuid, storeGuid));
    }
}
