package com.holderzone.framework.feign.starter.exception;

import com.holderzone.framework.feign.starter.util.FeignErrorUtils;
import org.springframework.util.StringUtils;

/**
 * @author Mr.Q
 * @date 2019/12/25 20:29
 * desc：
 */
public interface ExceptionConverter {

    default String nonNullMessage(Throwable e) {
        return nonNullMessage(e.getMessage());
    }

    default String nonNullMessage(String message) {
        return requireNonNullMessage(message, "未知异常");
    }

    default String requireNonNullMessage(String message, String defaultMessage) {
        if (StringUtils.hasText(message)) {
            return message;
        }
        return defaultMessage;
    }

    default String nonNullDetailMessage(Throwable e) {
        return FeignErrorUtils.asString(e);
    }
}

