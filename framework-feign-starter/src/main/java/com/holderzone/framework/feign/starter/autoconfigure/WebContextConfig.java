package com.holderzone.framework.feign.starter.autoconfigure;

import com.holderzone.framework.feign.starter.interceptor.WebContextInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebContextConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new WebContextInterceptor()).addPathPatterns("/**").order(0);
    }
}
