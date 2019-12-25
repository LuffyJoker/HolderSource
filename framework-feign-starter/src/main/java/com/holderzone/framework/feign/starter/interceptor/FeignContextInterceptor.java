package com.holderzone.framework.feign.starter.interceptor;

import com.holderzone.framework.feign.starter.constants.Constants;
import com.holderzone.framework.feign.starter.util.UserContextUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author Mr.Q
 * @date 2019/12/25 20:30
 * desc：
 */
@Slf4j
@Configuration
@ConditionalOnClass(RequestInterceptor.class)
@ConditionalOnProperty(prefix = "hystrix.command.default.execution.isolation", name = "strategy", havingValue = "SEMAPHORE")
public class FeignContextInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        try {
            String jsonStr = UserContextUtils.getJsonStr();
            if (StringUtils.hasText(jsonStr)) {
                template.header(Constants.USER_INFO, URLEncoder.encode(jsonStr, "utf-8"));
            }
        } catch (UnsupportedEncodingException e) {
            log.error("添加userInfo头出错", e);
        }
    }
}
