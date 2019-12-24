package com.holderzone.framework.exception;

public class BeanOpreationException extends RuntimeException {
    private String message;

    public BeanOpreationException() {
    }

    public BeanOpreationException(String message) {
        super(message);
    }
}

