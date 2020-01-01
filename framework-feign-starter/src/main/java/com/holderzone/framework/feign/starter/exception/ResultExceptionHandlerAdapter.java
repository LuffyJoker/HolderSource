package com.holderzone.framework.feign.starter.exception;

import com.holderzone.framework.exception.runtime.BusinessException;
import com.holderzone.framework.feign.starter.core.GenericException;
import com.holderzone.framework.feign.starter.core.OpenFeignException;
import com.holderzone.framework.feign.starter.core.RawJsonException;
import com.holderzone.framework.feign.starter.core.SpecificException;
import com.holderzone.framework.response.Result;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Mr.Q
 * @date 2019/12/25 20:29
 * desc：
 */
@Slf4j
public abstract class ResultExceptionHandlerAdapter extends AbstractResultExceptionHandler {

    @Override
    protected Result interceptBusinessException(BusinessException e) {
        return null;
    }

    @Override
    protected Result interceptGenericException(GenericException e) {
        return null;
    }

    @Override
    protected Result interceptSpecificException(SpecificException e) {
        return null;
    }

    @Override
    protected Result interceptRawJsonException(RawJsonException e) {
        return null;
    }

    @Override
    protected Result interceptOpenFeignException(OpenFeignException e) {
        return null;
    }

    @Override
    protected Result interceptException(Exception e) {
        return null;
    }
}
