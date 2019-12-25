package com.holderzone.framework.feign.starter.exception;

import com.holderzone.framework.exception.BusinessException;
import com.holderzone.framework.exception.NotFoundException;
import com.holderzone.framework.exception.ParamException;
import com.holderzone.framework.feign.starter.core.*;
import com.holderzone.framework.feign.starter.util.FeignJsonUtils;
import com.holderzone.framework.feign.starter.util.FeignMsgUtils;
import com.holderzone.framework.response.Result;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.rmi.ServerException;
import java.util.stream.Collectors;

/**
 * @author Mr.Q
 * @date 2019/12/25 20:29
 * desc：
 */
@Slf4j
public abstract class AbstractResultExceptionHandler implements ExceptionConverter {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result requestValueError(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> String.format(
                        "%s:%s",
                        fieldError.getField(),
                        fieldError.getDefaultMessage()
                ))
                .collect(Collectors.joining(","));
        log.error("本地参数错误：{}", message);
        return Result.buildIllegalArgumentResult(requireNonNullMessage(message, "参数错误"));
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public Result illegalArgumentException(IllegalArgumentException e) {
        String message = e.getMessage();
        log.error("本地参数错误：{}", nonNullDetailMessage(e));
        if (StringUtils.isEmpty(message)) {
            return Result.buildIllegalArgumentResult("参数错误");
        }
        return Result.buildIllegalArgumentResult(requireNonNullMessage(message, "参数错误"));
    }

    @ConditionalOnClass(ParamException.class)
    @ExceptionHandler(value = ParamException.class)
    public Result parameterException(ParamException e) {
        String message = e.getMessage();
        log.error("本地参数错误：{}", nonNullDetailMessage(e));
        if (StringUtils.isEmpty(message)) {
            return Result.buildIllegalArgumentResult("参数错误");
        }
        return Result.buildIllegalArgumentResult(requireNonNullMessage(message, "参数错误"));
    }

    @ConditionalOnClass(NotFoundException.class)
    @ExceptionHandler(value = NotFoundException.class)
    public Result notFoundException(NotFoundException e) {
        String message = e.getMessage();
        log.error("本地未能找到：{}", nonNullDetailMessage(e));
        return Result.buildIllegalArgumentResult(requireNonNullMessage(message, "未找到"));
    }

    @ConditionalOnClass(BusinessException.class)
    @ExceptionHandler(value = BusinessException.class)
    public Result businessException(BusinessException e) {
        Result result = interceptBusinessException(e);
        if (result != null) return result;
        log.error("本地业务异常：{}", nonNullDetailMessage(e));
        return Result.buildOpFailedResult(nonNullMessage(e));
    }

    @ExceptionHandler(value = GenericException.class)
    public Result genericException(GenericException e) {
        Result responseEntity = interceptGenericException(e);
        if (responseEntity != null) return responseEntity;
        String source = e.isCausedByServer() ? "服务方" : "本地";
        log.error("{}业务异常：{}", source, nonNullDetailMessage(e));
        return Result.buildOpFailedResult(nonNullMessage(e.getMessage()));
    }

    @ExceptionHandler(value = SpecificException.class)
    public Result specificException(SpecificException e) {
        Result responseEntity = interceptSpecificException(e);
        if (responseEntity != null) return responseEntity;
        String source = e.isCausedByServer() ? "服务方" : "本地";
        SpecificMessage specificMessage = new SpecificMessage(e.getErrorCode(), e.getMessage());
        String specificMessageJson = FeignJsonUtils.writeValueAsString(specificMessage);
        log.error("{}业务异常：{}", source, specificMessageJson);
        // fixme 这里应该把 SpecificMessage.errorCode 设置进 Result.resultCode 中
        // fixme 但是贸然改动可能对以前的业务造成影响，以后再看看
        return Result.buildOpFailedResult(nonNullMessage(e.getMessage()));
    }

    @ExceptionHandler(value = RawJsonException.class)
    public Result rawJsonException(RawJsonException e) {
        Result responseEntity = interceptRawJsonException(e);
        if (responseEntity != null) return responseEntity;
        log.error("服务方业务异常：{}", e.getMessage());
        return Result.buildOpFailedResult(nonNullMessage(e.getMessage()), HttpStatus.valueOf(e.getStatus()));
    }

    @ConditionalOnClass(HystrixBadRequestException.class)
    @ExceptionHandler(value = HystrixBadRequestException.class)
    public Result hystrixBadRequestException(HystrixBadRequestException e) {
        Throwable cause = e.getCause();
        if (cause instanceof GenericException) {
            return genericException((GenericException) cause);
        }
        if (cause instanceof SpecificException) {
            return specificException((SpecificException) cause);
        }
        if (cause instanceof RawJsonException) {
            return rawJsonException((RawJsonException) cause);
        }
        log.error("服务方业务异常：{}", nonNullDetailMessage(e));
        return Result.buildOpFailedResult(nonNullMessage(e.getMessage()));
    }

    @ConditionalOnClass(HystrixRuntimeException.class)
    @ExceptionHandler(value = HystrixRuntimeException.class)
    public Result hystrixRuntimeException(HystrixRuntimeException e) {
        Throwable cause = e.getCause();
        if (cause instanceof OpenFeignException) {
            return openFeignException((OpenFeignException) cause);
        }
        log.error("服务方系统异常：{}", nonNullDetailMessage(cause));
        String message = FeignMsgUtils.parseFeignMsgDetailed(cause.getMessage());
        return Result.buildOpFailedResult(nonNullMessage(message));
    }

    @ExceptionHandler(value = OpenFeignException.class)
    public Result openFeignException(OpenFeignException e) {
        Result result = interceptOpenFeignException(e);
        if (result != null) return result;
        log.error("服务方系统异常：{}", nonNullDetailMessage(e));
        return Result.buildOpFailedResult(nonNullMessage(e.getMessage()));
    }

    @ConditionalOnClass(FeignException.class)
    @ExceptionHandler(value = FeignException.class)
    public Result feignException(FeignException e) {
        log.error("服务方系统异常：{}", nonNullDetailMessage(e));
        String message = FeignMsgUtils.parseFeignMsgDetailed(e.getMessage());
        return Result.buildOpFailedResult(nonNullMessage(message));
    }

    @ConditionalOnClass(ServerException.class)
    @ExceptionHandler(value = ServerException.class)
    public Result serverException(ServerException e) {
        String message = e.getMessage();
        log.error("本地系统异常：{}", nonNullDetailMessage(e));
        return Result.buildOpFailedResult(nonNullMessage(message));
    }

    @ExceptionHandler(value = Exception.class)
    public Result exception(Exception e) {
        Result result = interceptException(e);
        if (result != null) return result;
        log.error("本地系统异常：{}", nonNullDetailMessage(e));
        return Result.buildOpFailedResult(nonNullMessage(e));
    }

    protected abstract Result interceptBusinessException(BusinessException e);

    protected abstract Result interceptGenericException(GenericException e);

    protected abstract Result interceptSpecificException(SpecificException e);

    protected abstract Result interceptRawJsonException(RawJsonException e);

    protected abstract Result interceptOpenFeignException(OpenFeignException e);

    protected abstract Result interceptException(Exception e);
}

