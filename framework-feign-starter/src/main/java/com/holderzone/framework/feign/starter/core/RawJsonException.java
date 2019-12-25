package com.holderzone.framework.feign.starter.core;

public final class RawJsonException extends RuntimeException {

    private int status;

    public RawJsonException(String message) {
        super(message);
        this.status = HttpStatus.CONFLICT.value();
    }

    public int getStatus() {
        return status;
    }
}