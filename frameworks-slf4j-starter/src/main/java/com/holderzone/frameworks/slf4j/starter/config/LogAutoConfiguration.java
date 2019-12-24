package com.holderzone.frameworks.slf4j.starter.config;

import com.holderzone.frameworks.slf4j.starter.anno.LogProcessor;
import com.holderzone.frameworks.slf4j.starter.serializer.DefaultLogSerializer;
import com.holderzone.frameworks.slf4j.starter.serializer.LogSerializer;
import org.slf4j.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass({Logger.class})
public class LogAutoConfiguration {
    public LogAutoConfiguration() {
    }

    @Bean
    @ConditionalOnMissingBean(
            name = {"logSerializer"}
    )
    public LogSerializer logSerializer() {
        return new DefaultLogSerializer();
    }

    @Bean
    public LogProcessor logProcessor(LogSerializer logSerializer) {
        return new LogProcessor(logSerializer);
    }
}

