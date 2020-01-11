package com.holderzone.holder.saas.store.erp.mapstruct;

import com.holderzone.holder.saas.store.erp.entity.domain.WarehouseDO;
import com.holderzone.holder.saas.store.dto.dto.erp.WarehouseReqDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ErpMapStruct {
    WarehouseDO mapToWarehouseDO(WarehouseReqDTO reqDTO);
}
