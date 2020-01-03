package com.holderzone.holdersaasstorekds.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.holderzone.framework.exception.runtime.BusinessException;
import com.holderzone.holdersaasstoredto.dto.kds.req.DeviceCreateReqDTO;
import com.holderzone.holdersaasstoredto.dto.kds.resp.DeviceStatusRespDTO;
import com.holderzone.holdersaasstorekds.entity.domain.DeviceConfigDO;
import com.holderzone.holdersaasstorekds.entity.enums.PointModeEnum;
import com.holderzone.holdersaasstorekds.mapper.DeviceConfigMapper;
import com.holderzone.holdersaasstorekds.mapstruct.DeviceConfigMapstruct;
import com.holderzone.holdersaasstorekds.service.DeviceConfigService;
import com.holderzone.holdersaasstorekds.service.DistributeService;
import com.holderzone.holdersaasstorekds.service.ProductionPointService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class DeviceConfigServiceImpl extends ServiceImpl<DeviceConfigMapper, DeviceConfigDO> implements DeviceConfigService {

    @Autowired
    private DeviceConfigMapstruct deviceConfigMapstruct;

    @Autowired
    ProductionPointService productionPointService;

    @Autowired
    DistributeService distributeService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DeviceStatusRespDTO create(DeviceCreateReqDTO deviceCreateReqDTO) {
        DeviceConfigDO deviceConfigDO = deviceConfigMapstruct.fromCreateReq(deviceCreateReqDTO);
        assertDeviceNameAvailable(deviceConfigDO);
        String storeGuid = deviceCreateReqDTO.getStoreGuid();
        String deviceId = deviceCreateReqDTO.getDeviceId();
        Wrapper<DeviceConfigDO> wrapper = deviceConfigWrapper(deviceConfigDO.getStoreGuid(), deviceConfigDO.getGuid());
        DeviceConfigDO deviceConfigInSql = getOne(wrapper);

        // 创建设备时，设备不存在
        if (deviceConfigInSql == null) {
            save(deviceConfigDO);
        } else {
            PointModeEnum pointModeInSql = PointModeEnum.ofMode(deviceConfigInSql.getPointMode());
            PointModeEnum pointModeNewReq = PointModeEnum.ofMode(deviceConfigDO.getPointMode());
            // 点位模式相同，即设备的 制作点/出堂口 未切换
            if (pointModeInSql == pointModeNewReq) {
                update(deviceConfigDO, wrapper);
            } else {
                if (pointModeInSql == PointModeEnum.PRODUCTION
                        && pointModeNewReq == PointModeEnum.DISTRIBUTE) {

                    // 删除设备下所有制作点、设备下的所有菜品
                    productionPointService.reInitialize(storeGuid, deviceId);
                }
                if (pointModeInSql == PointModeEnum.DISTRIBUTE
                        && pointModeNewReq == PointModeEnum.PRODUCTION) {

                    // 删除堂口、堂口绑定的区域
                    distributeService.reInitialize(storeGuid, deviceId);
                }
                // 最后删除该设备信息
                remove(wrapper);
                save(deviceConfigDO);
            }
        }
        return query(new DeviceQueryReqDTO(storeGuid, deviceId));
    }


    /**
     * 校验设备重名
     *
     * @param deviceConfigDO
     */
    private void assertDeviceNameAvailable(DeviceConfigDO deviceConfigDO) {
        if (count(new LambdaQueryWrapper<DeviceConfigDO>()
                .ne(DeviceConfigDO::getGuid, deviceConfigDO.getGuid())
                .eq(DeviceConfigDO::getName, deviceConfigDO.getName())
                .eq(DeviceConfigDO::getStoreGuid, deviceConfigDO.getStoreGuid())) > 0) {
            throw new BusinessException("设备名称[" + deviceConfigDO.getName() + "]重复");
        }
    }

    /**
     * 设备配置信息查询条件
     *
     * @param storeGuid
     * @param deviceId
     * @return
     */
    private Wrapper<DeviceConfigDO> deviceConfigWrapper(String storeGuid, String deviceId) {
        return new LambdaQueryWrapper<DeviceConfigDO>()
                .eq(DeviceConfigDO::getStoreGuid, storeGuid)
                .eq(DeviceConfigDO::getGuid, deviceId);
    }

}
