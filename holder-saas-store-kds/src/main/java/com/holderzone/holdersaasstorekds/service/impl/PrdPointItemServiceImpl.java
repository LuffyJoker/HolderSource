package com.holderzone.holdersaasstorekds.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.holderzone.holdersaasstorekds.entity.domain.PrdPointItemDO;
import com.holderzone.holdersaasstorekds.mapper.PrdPointItemMapper;
import com.holderzone.holdersaasstorekds.service.PrdPointItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PrdPointItemServiceImpl extends ServiceImpl<PrdPointItemMapper, PrdPointItemDO> implements PrdPointItemService {

    @Override
    public void reInitialize(String storeGuid, String deviceId) {
        remove(new LambdaQueryWrapper<PrdPointItemDO>()
                .eq(PrdPointItemDO::getStoreGuid, storeGuid)
                .eq(PrdPointItemDO::getDeviceId, deviceId));
    }

}
