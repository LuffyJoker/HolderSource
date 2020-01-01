package com.holderzone.framework.feign.starter.exception;

import com.holderzone.framework.exception.runtime.BusinessException;
import com.holderzone.framework.feign.starter.core.GenericException;
import com.holderzone.framework.feign.starter.core.OpenFeignException;
import com.holderzone.framework.feign.starter.core.RawJsonException;
import com.holderzone.framework.feign.starter.core.SpecificException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

/**
 * @author Mr.Q
 * @date 2019/12/25 20:29
 * desc：
 */
@Slf4j
@Configuration
@ConditionalOnMissingBean()
public abstract class ExceptionHandlerAdapter extends AbstractExceptionHandler {

    @Override
    protected ResponseEntity<String> interceptBusinessException(BusinessException e) {
        return null;
    }

    @Override
    protected ResponseEntity<String> interceptGenericException(GenericException e) {
        return null;
    }

    @Override
    protected ResponseEntity<String> interceptSpecificException(SpecificException e) {
        return null;
    }

    @Override
    protected ResponseEntity<String> interceptRawJsonException(RawJsonException e) {
        return null;
    }

    @Override
    protected ResponseEntity<String> interceptOpenFeignException(OpenFeignException e) {
        return null;
    }

    @Override
    protected ResponseEntity<String> interceptException(Exception e) {
        return null;
    }
}

