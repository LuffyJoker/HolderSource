package com.holderzone.holder.saas.store.erp.service.impl;

import com.holderzone.framework.exception.runtime.BusinessException;
import com.holderzone.framework.feign.starter.util.UserContextUtils;
import com.holderzone.framework.util.IDUtils;
import com.holderzone.holder.saas.store.erp.dao.WarehouseMapper;
import com.holderzone.holder.saas.store.erp.entity.domain.WarehouseDO;
import com.holderzone.holder.saas.store.erp.mapstruct.ErpMapStruct;
import com.holderzone.holder.saas.store.erp.service.WarehouseService;
import com.holderzone.holder.saas.store.dto.dto.erp.WarehouseReqDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private ErpMapStruct erpMapstruct;

    @Autowired
    private WarehouseMapper warehouseMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String createWarehouse(WarehouseReqDTO reqDTO) {
        String guid = IDUtils.nextId();
        WarehouseDO warehouseDO = erpMapstruct.mapToWarehouseDO(reqDTO);
        warehouseDO.setGuid(guid);
        if (StringUtils.isEmpty(warehouseDO.getCode())) {
            log.info("编码为空，自动生成编号");
            warehouseDO.setCode(warehouseCode());
        }
        int count = warehouseMapper.checkCodeRepeat(warehouseDO.getCode());
        log.info("编号重复数量：{}", count);
        if (count > 0) {
            throw new BusinessException("仓库编号已存在，请重新生成");
        }
        checkNameRepeat(warehouseDO.getName(), null);
        String enterpriseGuid = UserContextUtils.getEnterpriseGuid();
        if (warehouseDO.getType() == 0) {
            warehouseDO.setForeignKey(enterpriseGuid);
        }
        warehouseDO.setEnterpriseGuid(enterpriseGuid);
        warehouseMapper.createWarehouse(warehouseDO);
        return guid;
    }

    public String warehouseCode() {
        String enterpriseGuid = UserContextUtils.getEnterpriseGuid();
        String key = "warehouseCode:" + enterpriseGuid;
        Integer warehouseCode = (Integer) redisTemplate.opsForValue().get(key);
        log.info("缓存获取到的code：{}", warehouseCode);
        int currentMax = 0;
        if (warehouseCode == null) {
            String maximumCode = warehouseMapper.getMaximumCode();
            currentMax = StringUtils.isEmpty(maximumCode) ? 0 : Integer.parseInt(maximumCode);
        }
        RedisAtomicInteger id = new RedisAtomicInteger(key, redisTemplate.getConnectionFactory());
        id.set(warehouseCode == null ? currentMax : warehouseCode);
        warehouseCode = id.incrementAndGet();
        if (String.valueOf(warehouseCode).length() > 6) {
            throw new BusinessException("编号超出长度限制");
        }
        return String.format("%06d", warehouseCode);
    }

    private void checkNameRepeat(String name, String guid) {
        int nameRepeat = warehouseMapper.checkNameRepeat(name, guid);
        if (nameRepeat > 0) {
            throw new BusinessException("仓库名称已存在，请重命名");
        }
    }
}
