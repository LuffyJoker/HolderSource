package com.holderzone.saas.store.organization.controller;

import com.holderzone.frameworks.slf4j.starter.anno.LogAfter;
import com.holderzone.frameworks.slf4j.starter.anno.LogBefore;
import com.holderzone.frameworks.slf4j.starter.enums.LogLevel;
import com.holderzone.holdersaasstoredto.dto.organization.BrandDTO;
import com.holderzone.holdersaasstoredto.dto.organization.QueryBrandDTO;
import com.holderzone.saas.store.organization.service.BrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    /**
     * 更新品牌
     *
     * @param brandDTO DTO
     * @return true-成功，false-失败
     */
    @ApiOperation("更新品牌")
    @PostMapping("/update")
    @LogBefore(value = "更新品牌", logLevel = LogLevel.INFO)
    @LogAfter(value = "更新品牌", logLevel = LogLevel.DEBUG)
    public boolean updateBrand(@RequestBody @Validated(BrandDTO.Update.class) BrandDTO brandDTO) {
        return brandService.updateBrand(brandDTO);
    }


    /**
     * 删除品牌
     *
     * @param brandGuid 品牌guid
     * @return true-成功，false-失败
     */
    @ApiOperation("删除品牌")
    @PostMapping("/delete")
    @LogBefore(value = "删除品牌", logLevel = LogLevel.INFO)
    @LogAfter(value = "删除品牌", logLevel = LogLevel.DEBUG)
    public boolean deleteBrand(@RequestParam("brandGuid") String brandGuid) {
        return brandService.deleteBrand(brandGuid);
    }

    /**
     * 根据品牌guid查询品牌信息
     *
     * @param brandGuid 品牌guid
     * @return 品牌信息
     */
    @ApiOperation("根据品牌guid查询品牌信息")
    @PostMapping("/query_brand_by_guid")
    @LogBefore(value = "根据品牌guid查询品牌信息", logLevel = LogLevel.INFO)
    @LogAfter(value = "根据品牌guid查询品牌信息", logLevel = LogLevel.DEBUG)
    public BrandDTO queryBrandByGuid(@RequestParam("brandGuid") String brandGuid) {
        return brandService.queryBrandByGuid(brandGuid);
    }

    /**
     * 根据传入的品牌guid集合查询品牌集合（只包含guid与name两列）
     *
     * @param guidList 品牌guid集合
     * @return 品牌集合（只包含guid与name两列）
     */
    @ApiOperation("根据传入的品牌guid集合查询品牌")
    @PostMapping("/query_brand_by_idlist")
    @LogBefore(value = "根据传入的品牌guid集合查询品牌", logLevel = LogLevel.INFO)
    @LogAfter(value = "根据传入的品牌guid集合查询品牌", logLevel = LogLevel.DEBUG)
    public List<BrandDTO> queryBrandByIdList(@RequestBody List<String> guidList) {
        return brandService.queryBrandByIdList(guidList);
    }

    /**
     * 查询企业下所有品牌
     *
     * @return 品牌列表
     */
    @ApiOperation("查询企业下所有品牌")
    @PostMapping("/query_list")
    @LogBefore(value = "查询企业下所有品牌", logLevel = LogLevel.INFO)
    @LogAfter(value = "查询企业下所有品牌", logLevel = LogLevel.DEBUG)
    public List<BrandDTO> queryList(@RequestBody(required = false) QueryBrandDTO queryBrandDTO) {
        return brandService.queryAllList(queryBrandDTO);
    }

    /**
     * 判断品牌下是否存在门店、商品、商品分类
     *
     * @param brandGuid 品牌guid
     * @return true-存在，false-不存在
     */
    @ApiOperation("根据品牌guid判断品牌下是否存在门店、商品、商品分类，true-存在，false-不存在")
    @PostMapping("/query_exist_store_account/{brandGuid}")
    @LogBefore(value = "根据品牌guid判断品牌下是否存在门店、商品、商品分类", logLevel = LogLevel.INFO)
    @LogAfter(value = "根据品牌guid判断品牌下是否存在门店、商品、商品分类", logLevel = LogLevel.DEBUG)
    public boolean queryExistStoreAccount(@PathVariable("brandGuid") String brandGuid) {
        return brandService.queryExistStoreAccount(brandGuid);
    }

    @PostMapping("/query_store_guid_list_by_brand_guid")
    @LogBefore(value = "根据品牌guid查询门店列表", logLevel = LogLevel.INFO)
    @LogAfter(value = "根据品牌guid查询门店列表", logLevel = LogLevel.DEBUG)
    public List<String> queryStoreGuidListByBrandGui(@RequestBody String brandGuid) {
        return brandService.queryStoreGuidListByBrandGuid(brandGuid);
    }
}

