package com.holderzone.framework.feign.starter.core;

import org.springframework.http.HttpStatus;

import java.util.Optional;

/**
 * @author Mr.Q
 * @date 2019/12/25 20:27
 * desc：
 */
public final class SpecificException extends RuntimeException {

    private int status;

    private int errorCode;

    private boolean causedByServer;

    private SpecificException(int errorCode, String message) {
        super(Optional.ofNullable(message).orElse("默认错误"));
        this.errorCode = errorCode;
        this.status = HttpStatus.CONFLICT.value();
    }

    public static SpecificException local(int errorCode, String message) {
        SpecificException specificException = new SpecificException(errorCode, message);
        specificException.causedByServer = false;
        return specificException;
    }

    public static SpecificException remote(int errorCode, String message) {
        SpecificException specificException = new SpecificException(errorCode, message);
        specificException.causedByServer = true;
        return specificException;
    }

    public int getStatus() {
        return status;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public boolean isCausedByServer() {
        return causedByServer;
    }

    public Checked checked() {
        return new Checked(getMessage(), getStatus(), getErrorCode());
    }

    public static class Checked extends Exception {

        private int status;

        private int errorCode;

        private Checked(String message, int status, int errorCode) {
            super(message);
            this.status = status;
            this.errorCode = errorCode;
        }

        public int getStatus() {
            return status;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public String getErrorMessage() {
            return getMessage();
        }
    }
}
