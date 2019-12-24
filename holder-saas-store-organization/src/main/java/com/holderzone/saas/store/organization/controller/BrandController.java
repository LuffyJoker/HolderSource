package com.holderzone.saas.store.organization.controller;

import com.holderzone.frameworks.slf4j.starter.anno.LogAfter;
import com.holderzone.frameworks.slf4j.starter.anno.LogBefore;
import com.holderzone.frameworks.slf4j.starter.enums.LogLevel;
import com.holderzone.holdersaasstoredto.dto.organization.BrandDTO;
import com.holderzone.saas.store.organization.service.BrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "品牌相关接口")
@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @ApiOperation("创建品牌")
    @PostMapping("/create")
    @LogBefore(value = "创建品牌", logLevel = LogLevel.INFO)
    @LogAfter(value = "创建品牌", logLevel = LogLevel.DEBUG)
    public BrandDTO createBrand(@RequestBody @Validated BrandDTO brandDTO) {
        return brandService.createBrand(brandDTO);
    }
}
