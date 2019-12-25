package com.holderzone.framework.feign.starter.exception;

import com.holderzone.framework.exception.BusinessException;
import com.holderzone.framework.exception.NotFoundException;
import com.holderzone.framework.exception.ParamException;
import com.holderzone.framework.feign.starter.core.*;
import com.holderzone.framework.feign.starter.util.FeignJsonUtils;
import com.holderzone.framework.feign.starter.util.FeignMsgUtils;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.rmi.ServerException;
import java.util.stream.Collectors;

/**
 * @author Mr.Q
 * @date 2019/12/25 20:28
 * desc：
 */
@Slf4j
public abstract class AbstractExceptionHandler implements ExceptionConverter {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<String> requestValueError(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> String.format(
                        "%s:%s",
                        fieldError.getField(),
                        fieldError.getDefaultMessage()
                ))
                .collect(Collectors.joining(","));
        log.error("本地参数错误：{}", message);
        return new ResponseEntity<>(requireNonNullMessage(message, "参数错误"), HttpStatus.PRECONDITION_REQUIRED);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<String> illegalArgumentException(IllegalArgumentException e) {
        String message = e.getMessage();
        log.error("本地参数错误：{}", nonNullDetailMessage(e));
        return new ResponseEntity<>(requireNonNullMessage(message, "参数错误"), HttpStatus.PRECONDITION_REQUIRED);
    }

    @ConditionalOnClass(ParamException.class)
    @ExceptionHandler(value = ParamException.class)
    public ResponseEntity<String> parameterException(ParamException e) {
        String message = e.getMessage();
        log.error("本地参数错误：{}", nonNullDetailMessage(e));
        return new ResponseEntity<>(requireNonNullMessage(message, "参数错误"), HttpStatus.PRECONDITION_REQUIRED);
    }

    @ConditionalOnClass(NotFoundException.class)
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<String> notFoundException(NotFoundException e) {
        String message = e.getMessage();
        log.error("本地未能找到：{}", nonNullDetailMessage(e));
        return new ResponseEntity<>(requireNonNullMessage(message, "未找到"), HttpStatus.NOT_FOUND);
    }

    @ConditionalOnClass(BusinessException.class)
    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<String> businessException(BusinessException e) {
        ResponseEntity<String> responseEntity = interceptBusinessException(e);
        if (responseEntity != null) return responseEntity;
        log.error("本地业务异常：{}", nonNullDetailMessage(e));
        return new ResponseEntity<>(nonNullMessage(e), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = GenericException.class)
    public ResponseEntity<String> genericException(GenericException e) {
        ResponseEntity<String> responseEntity = interceptGenericException(e);
        if (responseEntity != null) return responseEntity;
        String source = e.isCausedByServer() ? "服务方" : "本地";
        log.error("{}业务异常：{}", source, nonNullDetailMessage(e));
        return new ResponseEntity<>(nonNullMessage(e.getMessage()), HttpStatus.valueOf(e.getStatus()));
    }

    @ExceptionHandler(value = SpecificException.class)
    public ResponseEntity<String> specificException(SpecificException e) {
        ResponseEntity<String> responseEntity = interceptSpecificException(e);
        if (responseEntity != null) return responseEntity;
        String source = e.isCausedByServer() ? "服务方" : "本地";
        SpecificMessage specificMessage = new SpecificMessage(e.getErrorCode(), e.getMessage());
        String specificMessageJson = FeignJsonUtils.writeValueAsString(specificMessage);
        log.error("{}业务异常：{}", source, specificMessageJson);
        return new ResponseEntity<>(specificMessageJson, HttpStatus.valueOf(e.getStatus()));
    }

    @ExceptionHandler(value = RawJsonException.class)
    public ResponseEntity<String> rawJsonException(RawJsonException e) {
        ResponseEntity<String> responseEntity = interceptRawJsonException(e);
        if (responseEntity != null) return responseEntity;
        log.error("服务方业务异常：{}", nonNullDetailMessage(e));
        return new ResponseEntity<>(nonNullMessage(e.getMessage()), HttpStatus.valueOf(e.getStatus()));
    }

    @ConditionalOnClass(HystrixBadRequestException.class)
    @ExceptionHandler(value = HystrixBadRequestException.class)
    public ResponseEntity<String> hystrixBadRequestException(HystrixBadRequestException e) {
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
        return new ResponseEntity<>(nonNullMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ConditionalOnClass(HystrixRuntimeException.class)
    @ExceptionHandler(value = HystrixRuntimeException.class)
    public ResponseEntity<String> hystrixRuntimeException(HystrixRuntimeException e) {
        Throwable cause = e.getCause();
        if (cause instanceof OpenFeignException) {
            return openFeignException((OpenFeignException) cause);
        }
        log.error("服务方系统异常：{}", nonNullDetailMessage(cause));
        String message = FeignMsgUtils.parseFeignMsgDetailed(cause.getMessage());
        return new ResponseEntity<>(nonNullMessage(message), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = OpenFeignException.class)
    public ResponseEntity<String> openFeignException(OpenFeignException e) {
        ResponseEntity<String> responseEntity = interceptOpenFeignException(e);
        if (responseEntity != null) return responseEntity;
        log.error("服务方系统异常：{}", nonNullDetailMessage(e));
        return new ResponseEntity<>(nonNullMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ConditionalOnClass(FeignException.class)
    @ExceptionHandler(value = FeignException.class)
    public ResponseEntity<String> feignException(FeignException e) {
        log.error("服务方系统异常：{}", nonNullDetailMessage(e));
        String message = FeignMsgUtils.parseFeignMsgDetailed(e.getMessage());
        return new ResponseEntity<>(nonNullMessage(message), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ConditionalOnClass(ServerException.class)
    @ExceptionHandler(value = ServerException.class)
    public ResponseEntity<String> serverException(ServerException e) {
        String message = e.getMessage();
        log.error("本地系统异常：{}", nonNullDetailMessage(e));
        return new ResponseEntity<>(nonNullMessage(message), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> exception(Exception e) {
        ResponseEntity<String> responseEntity = interceptException(e);
        if (responseEntity != null) return responseEntity;
        log.error("本地系统异常：{}", nonNullDetailMessage(e));
        return new ResponseEntity<>(nonNullMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    protected abstract ResponseEntity<String> interceptBusinessException(BusinessException e);

    protected abstract ResponseEntity<String> interceptGenericException(GenericException e);

    protected abstract ResponseEntity<String> interceptSpecificException(SpecificException e);

    protected abstract ResponseEntity<String> interceptRawJsonException(RawJsonException e);

    protected abstract ResponseEntity<String> interceptOpenFeignException(OpenFeignException e);

    protected abstract ResponseEntity<String> interceptException(Exception e);
}

