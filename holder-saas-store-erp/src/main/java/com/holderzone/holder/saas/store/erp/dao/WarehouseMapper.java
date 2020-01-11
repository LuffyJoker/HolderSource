package com.holderzone.holder.saas.store.erp.dao;

import com.holderzone.holder.saas.store.erp.entity.domain.WarehouseDO;
import feign.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseMapper {
    int checkCodeRepeat(@Param("code") String code);

    int checkNameRepeat(@Param("name") String name, @Param("guid") String guid);

    void createWarehouse(WarehouseDO warehouseDO);

    String getMaximumCode();
}
