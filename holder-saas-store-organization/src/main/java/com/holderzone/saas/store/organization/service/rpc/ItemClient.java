package com.holderzone.saas.store.organization.service.rpc;

import com.holderzone.framework.util.JacksonUtils;
import com.holderzone.framework.util.ThrowableUtils;
import com.holderzone.holdersaasstoredto.dto.item.ItemSingleDTO;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Mr.Q
 * @date 2019/12/24 23:26
 * desc：
 */
@Component
@FeignClient(value = "holder-saas-store-item", fallbackFactory = ItemClient.ServiceFallBack.class)
public interface ItemClient {

    /**
     * 查询当前品牌是否有绑定的商品分类或商品
     *
     * @param brandGuid 当前品牌GUID
     * @return true-品牌下有商品分类或商品；false-品牌下无商品分类或商品
     */
    @PostMapping("/item/count_type_or_item")
    boolean countTypeOrItem(@RequestParam("brandGuid") String brandGuid);

    @PostMapping("/default_data/add_data")
    void addDefaultAttr(@RequestBody ItemSingleDTO itemSingleDTO);


    @Slf4j
    @Component
    class ServiceFallBack implements FallbackFactory<ItemClient> {

        private static final String HYSTRIX_PATTERN = "服务间调用{}熔断，入参{}，异常{}";

        @Override
        public ItemClient create(Throwable cause) {
            return new ItemClient() {
                @Override
                public boolean countTypeOrItem(String brandGuid) {
                    log.error(HYSTRIX_PATTERN, "countTypeOrItem", "入参brandGuid为：" + brandGuid, ThrowableUtils.asString(cause));
                    return true;
                }

                @Override
                public void addDefaultAttr(ItemSingleDTO itemSingleDTO) {
                    log.error(HYSTRIX_PATTERN, "addDefaultAttr", "itemSingleDTO：" + JacksonUtils.writeValueAsString(itemSingleDTO),
                            ThrowableUtils.asString(cause));
                }
            };
        }
    }
}

