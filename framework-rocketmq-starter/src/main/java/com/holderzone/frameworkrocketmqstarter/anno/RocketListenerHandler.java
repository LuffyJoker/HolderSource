package com.holderzone.frameworkrocketmqstarter.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Mr.Q
 * @date 2019/12/24 23:35
 * desc：
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface RocketListenerHandler {
    MessageModel type() default MessageModel.CLUSTERING;

    ConsumeFromWhere consumerFromWhere() default ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET;

    int consumeThreadMin() default 10;

    int consumeThreadMax() default 32;

    ConsumeMode consumeMode() default ConsumeMode.CONCURRENTLY;

    int consumeMsgMaxSize() default 1;

    int reTryConsume() default 16;

    String consumerGroup();

    String topic();

    String[] tags();

    String messageSelector() default "";

    boolean reWriteSubscribe() default false;
}
