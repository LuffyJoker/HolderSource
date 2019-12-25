package com.holderzone.framework.feign.starter.core;

public class OpenFeignException extends FeignException {

    public OpenFeignException(int status, String message) {
        super(status, message);
    }
}