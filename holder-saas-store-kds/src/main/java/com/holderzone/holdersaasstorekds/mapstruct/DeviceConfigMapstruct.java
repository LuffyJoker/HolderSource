package com.holderzone.holdersaasstorekds.mapstruct;

import com.holderzone.holdersaasstoredto.dto.kds.req.DeviceCreateReqDTO;
import com.holderzone.holdersaasstorekds.entity.domain.DeviceConfigDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface DeviceConfigMapstruct {

    @Mapping(source = "deviceId", target = "guid")
    DeviceConfigDO fromCreateReq(DeviceCreateReqDTO deviceCreateReqDTO);

}
