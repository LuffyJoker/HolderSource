package com.holderzone.holder.saas.store.item.listener;

import com.holderzone.framework.exception.runtime.BusinessException;
import com.holderzone.framework.feign.starter.util.UserContextUtils;
import com.holderzone.framework.rocketmq.starter.anno.RocketListenerHandler;
import com.holderzone.framework.rocketmq.starter.common.AbstractRocketMqConsumer;
import com.holderzone.framework.rocketmq.starter.constants.RocketMqTopic;
import com.holderzone.framework.util.EnterpriseIdentifier;
import com.holderzone.holder.saas.store.dto.dto.item.ItemSingleDTO;
import com.holderzone.holder.saas.store.dto.dto.organization.StoreDTO;
import com.holderzone.holder.saas.store.dto.enums.MchntTypeEnum;
import com.holderzone.holder.saas.store.item.constant.MqConstant;
import com.holderzone.holder.saas.store.item.service.IDefaultDataService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author Mr.Q
 * @date 2019/12/29 21:27
 * desc：
 */
@Slf4j
@Component
@RocketListenerHandler(
        topic = MqConstant.DOWNSTREAM_STORE_TOPIC,
        tags = {
                MqConstant.DOWNSTREAM_STORE_CREATE_TAG
        },
        consumerGroup = MqConstant.DOWNSTREAM_INIT_STORE_ITEM_GROUP
)
public class InitStoreDefaultDataListener extends AbstractRocketMqConsumer<RocketMqTopic, StoreDTO> {

    private final IDefaultDataService iDefaultDataService;

    @Autowired
    public InitStoreDefaultDataListener(IDefaultDataService iDefaultDataService) {
        this.iDefaultDataService = iDefaultDataService;
    }

    @Override
    public boolean consumeMsg(StoreDTO storeDTO, MessageExt messageExt) {
        if(Objects.equals(storeDTO.getMchntTypeCode(), MchntTypeEnum.CATERING.getCode())) {
            UserContextUtils.put(messageExt.getProperty(MqConstant.DOWNSTREAM_CONTEXT));
            //将Enterprise存入threadLocal中
            EnterpriseIdentifier.setEnterpriseGuid(UserContextUtils.getEnterpriseGuid());
            try {
                ItemSingleDTO itemSingleDTO = new ItemSingleDTO();
                itemSingleDTO.setModel(0);
                itemSingleDTO.setData(storeDTO.getGuid());
                iDefaultDataService.addAttr(itemSingleDTO); //初始化门店属性组
                iDefaultDataService.addInitTypeAndItem(itemSingleDTO); //初始化门店商品 分类 规格
                log.error("门店：{} 初始化门店商品数据成功", storeDTO.getName());
            } catch (BusinessException e) {
                log.error("门店：{} 初始化门店商品数据发生错误：{}，初始化消息已消费", storeDTO.getName(), e.getMessage());
                return true;
            } catch (Exception e) {
                log.error("门店：{} 初始化门店商品数据发生错误：{}", storeDTO.getName(), e.getMessage());
                return false;
            } finally {
                EnterpriseIdentifier.remove();
            }
        }
        return true;
    }
}
