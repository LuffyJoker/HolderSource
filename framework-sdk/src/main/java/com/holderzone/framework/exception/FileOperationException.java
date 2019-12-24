package com.holderzone.framework.exception;

/**
 * @author Mr.Q
 * @date 2019/12/24 21:53
 * desc：
 */
public class FileOperationException extends RuntimeException {
    private String message;

    public FileOperationException() {
    }

    public FileOperationException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
