package com.holderzone.framework.exception.runtime;

public class ParameterException extends RuntimeException {
    private static final long serialVersionUID = -8092102461482267120L;

    public ParameterException() {
        super("参数错误");
    }

    public ParameterException(String message) {
        super(message);
    }

    public ParameterException(String message, Throwable cause) {
        super(message, cause);
    }
}
