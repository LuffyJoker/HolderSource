package com.holderzone.framework.feign.starter.interceptor;

import com.holderzone.framework.feign.starter.constants.Constants;
import com.holderzone.framework.feign.starter.util.UserContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;

/**
 * @author Mr.Q
 * @date 2019/12/25 20:30
 * desc：
 */
@Slf4j
@Configuration
@ConditionalOnClass(HandlerInterceptor.class)
public class WebContextInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userInfoJson = request.getHeader(Constants.USER_INFO);
        if (StringUtils.hasText(userInfoJson)) {
            String userInfo = URLDecoder.decode(userInfoJson, "utf-8");
            UserContextUtils.put(userInfo);
            String source = request.getHeader(Constants.SOURCE);
            if (source != null) {
                UserContextUtils.get().setSource(source);
            }
            log.debug("{}：{}", Constants.USER_INFO, UserContextUtils.getJsonStr());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContextUtils.remove();
    }
}
