package com.holderzone.framework.rocketmq.starter.common;

import java.util.List;

/**
 * @author Mr.Q
 * @date 2019/12/24 23:36
 * desc：
 */
public class RocketMqConsumerMBean {
    private List<AbstractRocketMqConsumer> consumers;

    public RocketMqConsumerMBean() {
    }

    public List<AbstractRocketMqConsumer> getConsumers() {
        return this.consumers;
    }

    public void setConsumers(List<AbstractRocketMqConsumer> consumers) {
        this.consumers = consumers;
    }
}
