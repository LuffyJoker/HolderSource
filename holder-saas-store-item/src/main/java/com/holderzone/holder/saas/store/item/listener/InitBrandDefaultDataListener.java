package com.holderzone.holder.saas.store.item.listener;

import com.holderzone.framework.exception.runtime.BusinessException;
import com.holderzone.framework.feign.starter.util.UserContextUtils;
import com.holderzone.framework.util.EnterpriseIdentifier;
import com.holderzone.framework.rocketmq.starter.anno.RocketListenerHandler;
import com.holderzone.framework.rocketmq.starter.common.AbstractRocketMqConsumer;
import com.holderzone.framework.rocketmq.starter.constants.RocketMqTopic;
import com.holderzone.holder.saas.store.item.service.IDefaultDataService;
import com.holderzone.holder.saas.store.dto.dto.item.ItemSingleDTO;
import com.holderzone.holder.saas.store.dto.dto.organization.BrandDTO;
import com.holderzone.holder.saas.store.dto.enums.MchntTypeEnum;
import com.holderzone.holder.saas.store.item.constant.MqConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author Mr.Q
 * @date 2019/12/29 21:23
 * desc：
 */
@Slf4j
@Component
@RocketListenerHandler(
        topic = MqConstant.DOWNSTREAM_BRAND_CREATE_TOPIC,
        tags = {
                MqConstant.DOWNSTREAM_BRAND_CREATE_TAG
        },
        consumerGroup = MqConstant.DOWNSTREAM_INIT_BRAND_ITEM_GROUP
)
public class InitBrandDefaultDataListener extends AbstractRocketMqConsumer<RocketMqTopic, BrandDTO> {

    private final IDefaultDataService iDefaultDataService;

    @Autowired
    public InitBrandDefaultDataListener(IDefaultDataService iDefaultDataService) {
        this.iDefaultDataService = iDefaultDataService;
    }

    @Override
    public boolean consumeMsg(BrandDTO brandDTO, MessageExt messageExt) {
        if (Objects.equals(brandDTO.getMchntTypeCode(), MchntTypeEnum.CATERING.getCode())) {
            UserContextUtils.put(messageExt.getProperty(MqConstant.DOWNSTREAM_CONTEXT));
            //将Enterprise存入threadLocal中
            EnterpriseIdentifier.setEnterpriseGuid(UserContextUtils.getEnterpriseGuid());
            try {
                ItemSingleDTO itemSingleDTO = new ItemSingleDTO();
                itemSingleDTO.setModel(1);
                itemSingleDTO.setData(brandDTO.getGuid());
                iDefaultDataService.addAttr(itemSingleDTO); //初始化品牌属性组
                iDefaultDataService.addInitTypeAndItem(itemSingleDTO); //初始化品牌商品 分类 规格
                log.error("品牌：{} 初始化品牌商品数据成功", brandDTO.getName());
            } catch (BusinessException e) {
                log.error("品牌：{} 初始化品牌商品数据发生错误：{}，初始化消息已消费", brandDTO.getName(), e.getMessage());
                return true;
            } catch (Exception e) {
                log.error("品牌：{} 初始化品牌商品数据发生错误：{}", brandDTO.getName(), e.getMessage());
                return false;
            } finally {
                EnterpriseIdentifier.remove();
            }
        }
        return true;
    }
}
