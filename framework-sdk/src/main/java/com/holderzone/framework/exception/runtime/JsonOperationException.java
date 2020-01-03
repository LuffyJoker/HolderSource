package com.holderzone.framework.exception.runtime;

/**
 * @author Mr.Q
 * @date 2019/12/24 21:53
 * desc：
 */
public class JsonOperationException extends RuntimeException {
    private String message;

    public JsonOperationException(String message) {
        this.message = message;
    }

    public JsonOperationException() {
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
