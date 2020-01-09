package com.holderzone.holdersaasstorekds.controller;

import com.holderzone.framework.util.JacksonUtils;
import com.holderzone.holdersaasstoredto.dto.kds.req.DeviceCreateReqDTO;
import com.holderzone.holdersaasstoredto.dto.kds.req.DeviceQueryReqDTO;
import com.holderzone.holdersaasstoredto.dto.kds.resp.DeviceStatusRespDTO;
import com.holderzone.holdersaasstorekds.service.DeviceConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api("KDS设备接口")
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private DeviceConfigService deviceConfigService;

    @PostMapping("/create")
    @ApiOperation(value = "创建KDS设备")
    public DeviceStatusRespDTO create(@RequestBody @Validated DeviceCreateReqDTO deviceCreateReqDTO) {
        if (log.isInfoEnabled()) {
            log.info("创建KDS设备入参:{}", JacksonUtils.writeValueAsString(deviceCreateReqDTO));
        }
        return deviceConfigService.create(deviceCreateReqDTO);
    }

    @PostMapping("/query")
    @ApiOperation(value = "查询KDS设备")
    public DeviceStatusRespDTO query(@RequestBody @Validated DeviceQueryReqDTO deviceQueryReqDTO) {
        if (log.isInfoEnabled()) {
            log.info("查询KDS设备入参:{}", JacksonUtils.writeValueAsString(deviceQueryReqDTO));
        }
        return deviceConfigService.query(deviceQueryReqDTO);
    }

    @PostMapping("/initialize")
    @ApiOperation(value = "初始化KDS设备")
    public void initialize(@RequestBody @Validated DeviceQueryReqDTO deviceQueryReqDTO) {
        if (log.isInfoEnabled()) {
            log.info("初始化KDS设备入参:{}", JacksonUtils.writeValueAsString(deviceQueryReqDTO));
        }
        deviceConfigService.initialize(deviceQueryReqDTO);
    }

}
