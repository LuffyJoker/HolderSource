package com.holderzone.holdersaasstoreitem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Mr.Q
 * @date 2019/12/29 21:36
 * desc：
 */
@Slf4j
@RestController
@RequestMapping("/erp")
@Api(value = "ERP商品相关接口")
public class ErpController {

    private final ErpService erpService;

    @Autowired
    public ErpController(ErpService erpService) {
        this.erpService = erpService;
    }

    @PostMapping("/list_type_of_store")
    @ApiOperation("ERP获取分类列表：data值为storeGuid")
    public List<ErpTypeDTO> listType(@RequestBody @Valid SingleDataDTO singleDataDTO) {
        log.info("ERP获取分类列表，入参：{}", JacksonUtils.writeValueAsString(singleDataDTO));
        return erpService.listType(singleDataDTO.getData());
    }

    @PostMapping("/page_item_of_store")
    @ApiOperation("ERP获取商品列表：data值为storeGuid")
    public Page<ErpItemDTO> pageItem(@RequestBody @Valid SingleDataPageDTO singleDataPageDTO) {
        log.info("ERP获取商品列表，入参：{}", JacksonUtils.writeValueAsString(singleDataPageDTO));
        return erpService.pageItem(singleDataPageDTO);
    }

    @PostMapping("/page_item_of_type")
    @ApiOperation("ERP获取分类下商品列表：data值为typeGuid")
    public Page<ErpItemDTO> pageItemOfType(@RequestBody @Valid SingleDataPageDTO singleDataPageDTO) {
        log.info("ERP获取分类下商品列表，入参：{}", JacksonUtils.writeValueAsString(singleDataPageDTO));
        return erpService.pageItemOfType(singleDataPageDTO);
    }

    @PostMapping("/page_item_by_name")
    @ApiOperation("ERP获取指定名称下商品列表：data1值为storeGuid, data2值为itemName")
    public Page<ErpItemDTO> pageItemByName(@RequestBody @Valid DoubleDataPageDTO doubleDataPageDTO) {
        log.info("ERP获取指定名称下商品列表，入参：{}", JacksonUtils.writeValueAsString(doubleDataPageDTO));
        return erpService.pageItemByName(doubleDataPageDTO);
    }
}

