package com.holderzone.holder.saas.store.erp.controller;

import com.holderzone.framework.util.JacksonUtils;
import com.holderzone.holder.saas.store.erp.service.WarehouseService;
import com.holderzone.holder.saas.store.dto.dto.erp.WarehouseReqDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "仓库管理")
@RestController
public class WarehouseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseController.class);
    private final WarehouseService warehouseService;

    @Autowired
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @ApiOperation(value = "创建仓库")
    @PostMapping("/warehouse")
    public String createWarehouse(@RequestBody @Validated WarehouseReqDTO reqDTO) {
        LOGGER.info("ERP系统(创建仓库)-> WarehouseReqDTO:{}", JacksonUtils.writeValueAsString(reqDTO));
//        WarehouseValidator.validateCreateWarehouse(reqDTO);
        return warehouseService.createWarehouse(reqDTO);
    }
}
