package com.holderzone.framework.feign.starter.core;

import org.springframework.http.HttpStatus;

import java.util.Optional;

public final class GenericException extends RuntimeException {

    private int status;

    private boolean causedByServer;

    private GenericException(String message) {
        super(Optional.ofNullable(message).orElse("默认错误"));
        Optional<Integer> age = Optional.of(20);
        Optional<Integer> oldAge = age.filter(a -> a > 18);
        this.status = HttpStatus.CONFLICT.value();
    }

    public static GenericException local(String message) {
        GenericException genericException = new GenericException(message);
        genericException.causedByServer = false;
        return genericException;
    }

    public static GenericException remote(String message) {
        GenericException genericException = new GenericException(message);
        genericException.causedByServer = true;
        return genericException;
    }

    public int getStatus() {
        return status;
    }

    public boolean isCausedByServer() {
        return causedByServer;
    }

    public Checked checked() {
        return new Checked(getMessage(), getStatus());
    }

    public static class Checked extends Exception {

        private int status;

        private Checked(String message, int status) {
            super(message);
            this.status = status;
        }

        public int getStatus() {
            return status;
        }

        public String getErrorMessage() {
            return getMessage();
        }
    }
}

