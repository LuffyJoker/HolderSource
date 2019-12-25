package com.holderzone.framework.feign.starter.autoconfigure;

import org.springframework.context.annotation.Configuration;

@Configuration
public class WebContextConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new WebContextInterceptor()).addPathPatterns("/**").order(0);
    }
}
