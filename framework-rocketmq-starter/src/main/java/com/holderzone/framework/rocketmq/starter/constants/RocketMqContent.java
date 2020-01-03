package com.holderzone.framework.rocketmq.starter.constants;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.Serializable;

/**
 * @author Mr.Q
 * @date 2019/12/24 23:39
 * desc：
 */
public class RocketMqContent implements Serializable {
    private static final long serialVersionUID = 1L;

    public RocketMqContent() {
    }

    public String toString() {
        return JSON.toJSONString(this, new SerializerFeature[]{SerializerFeature.NotWriteDefaultValue});
    }
}