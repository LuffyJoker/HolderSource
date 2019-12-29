package com.holderzone.holdersaasstoreitem.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Mr.Q
 * @date 2019/12/29 21:36
 * desc：
 */
@RestController
@RequestMapping("/estimate")
@Slf4j
public class EstimateController {

    @Autowired
    private IEstimateService iEstimateService;

    /**
     * 商户后台菜品设置估清
     *
     * @param request
     * @return
     */
    @PostMapping("/save")
    public Integer saveEstimate(@RequestBody @Valid EstimateReqDTO request) {
        log.info("估清新增or更新接口入参,request={}", JacksonUtils.writeValueAsString(request));
        return iEstimateService.saveOrUpdate(request) ? 1 : 0;
    }

    /**
     * 商户后台条件获取估清菜品列表
     *
     * @param request
     * @return
     */
    @PostMapping("/get_estimate_list_store")
    public Page<EstimateMerchantConfigRespDTO> getEstimateListStore(@RequestBody @Valid EstimateMerchantReqDTO request) {
        log.info("商户后台-估清菜品列表接口入参,request={}", JacksonUtils.writeValueAsString(request));
        return iEstimateService.getItmeEstimates(request);
    }

    /**
     * 一体机手动设置菜品估清状态
     *
     * @param request
     * @return
     */
    @PostMapping("/save_sold_out_status")
    public Integer saveSoldOutStatus(@RequestBody @Valid EstimateForManualReqDTO request) {
        log.info("一体机手动设置菜品估清状态接口入参,request={}", JacksonUtils.writeValueAsString(request));
        return iEstimateService.saveSoldOutStatus(request) ? 1 : 0;
    }

    /**
     * 商户后台条件获取估清菜品剩余可售数
     *
     * @param request
     * @return
     */
    @PostMapping("/get_estimate_itme_residue_store")
    public Page<EstimateItemResidueMemchantRespDTO> getEstimateItemResidueStore(@RequestBody @Valid EstimateMerchantReqDTO request) {
        log.info("商户后台-估清菜品剩余可售数接口入参,request={}", JacksonUtils.writeValueAsString(request));
        return iEstimateService.getEstimateItemResidue(request);
    }

    /**
     * @param request
     * @return 客户端下单时验证菜品是否估清，并扣减库存数量
     */
    @PostMapping("/verify_dinein_item_estimate")
    public Boolean verifyDineInItemEstimate(@RequestBody @Valid List<DineInItemDTO> request) {
        log.info("验证订单菜品是否估清接口入参,request={}", JacksonUtils.writeValueAsString(request));
        Boolean result = iEstimateService.verifyDineInItemEstimate(request);
        log.info("verify result = {}", result);
        return result;
    }

    /**
     * @param request
     * @return 订单失败、退菜，加回库存
     */
    @PostMapping("/dinein_fail")
    public Boolean dineinFail(@RequestBody @Valid List<DineInItemDTO> request) {
        log.info("订单支付失败和退菜接口入参,request={}", JacksonUtils.writeValueAsString(request));
        Boolean result = iEstimateService.dineinFail(request);
        return result;
    }

    /**
     * 移动端商品估清同步
     *
     * @param baseDTO
     * @return
     */
    @PostMapping("/query_estimate_for_synchronize")
    public List<ItemEstimateForAndroidRespDTO> queryEstimateForSyn(@RequestBody @Valid BaseDTO baseDTO) {
        log.info("商品估清同步接口入参,baseDTO={}", JacksonUtils.writeValueAsString(baseDTO));
        return iEstimateService.queryEstimateForSyn(baseDTO);
    }

    /**
     * job门店定时置满
     *
     * @param request
     * @return
     */
    @PostMapping("/store_item_estimate_reset")
    public Boolean storeItemEstimateReset(@RequestBody @Valid Map<String, List<String>> request) {
        log.info("job门店定时置满接口入参,request={}", JacksonUtils.writeValueAsString(request));
        return iEstimateService.storeItemEstimateReset(request) > 0 ? Boolean.TRUE : Boolean.FALSE;
    }


}

