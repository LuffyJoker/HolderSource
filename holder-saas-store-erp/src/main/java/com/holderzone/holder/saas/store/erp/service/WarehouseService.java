package com.holderzone.holder.saas.store.erp.service;

import com.holderzone.holder.saas.store.dto.dto.erp.WarehouseReqDTO;

public interface WarehouseService {

    /**
     * 创建仓库
     */
    String createWarehouse(WarehouseReqDTO reqDTO);
}
