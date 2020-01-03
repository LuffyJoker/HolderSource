package com.holderzone.holder.saas.store.item.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Mr.Q
 * @date 2019/12/29 21:32
 * desc：
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 仅开发阶段使用，提测以及上线后去掉
//        registry.addInterceptor(new FakeInterceptor()).order(DdsInterceptor.ORDER - 1);
    }
}
