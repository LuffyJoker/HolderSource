package com.holderzone.framework.exception.runtime;

public class BeanOpreationException extends RuntimeException {
    private String message;

    public BeanOpreationException() {
    }

    public BeanOpreationException(String message) {
        super(message);
    }
}

