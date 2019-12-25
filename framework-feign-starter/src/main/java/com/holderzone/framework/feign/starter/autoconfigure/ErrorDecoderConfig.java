package com.holderzone.framework.feign.starter.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnMissingBean(ErrorDecoder.class)
public class ErrorDecoderConfig {

    @Bean("errorDecoder")
    @ConditionalOnClass(HystrixBadRequestException.class)
    @ConditionalOnProperty(prefix = "feign.hystrix", name = "enabled", havingValue = "true")
    public ErrorDecoder errorDecoderWithHystrix() {
        return new ErrorDecoderWithHystrix();
    }

    @Bean("errorDecoder")
    @ConditionalOnMissingBean(name = "errorDecoder")
    public ErrorDecoder errorDecoderWithoutHystrix() {
        return new ErrorDecoderWithoutHystrix();
    }
}

