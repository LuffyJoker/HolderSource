package com.holderzone.holdersaasstorekds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.holderzone.holdersaasstoredto.dto.kds.req.DeviceCreateReqDTO;
import com.holderzone.holdersaasstoredto.dto.kds.req.DeviceQueryReqDTO;
import com.holderzone.holdersaasstoredto.dto.kds.resp.DeviceStatusRespDTO;
import com.holderzone.holdersaasstorekds.entity.domain.DeviceConfigDO;

public interface DeviceConfigService extends IService<DeviceConfigDO> {

    DeviceStatusRespDTO create(DeviceCreateReqDTO deviceCreateReqDTO);

    DeviceStatusRespDTO query(DeviceQueryReqDTO deviceQueryReqDTO);

    void initialize(DeviceQueryReqDTO deviceQueryReqDTO);

    DeviceConfigDO queryDeviceByGuid(String storeGuid, String deviceId);
}
