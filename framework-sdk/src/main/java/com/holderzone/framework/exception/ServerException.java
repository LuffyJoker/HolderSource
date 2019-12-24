package com.holderzone.framework.exception;

/**
 * @author Mr.Q
 * @date 2019/12/24 21:55
 * desc：
 */
public class ServerException extends RuntimeException {
    private static final long serialVersionUID = -9135489452969531238L;

    public ServerException() {
        super("服务器错误");
    }

    public ServerException(String message) {
        super(message);
    }

    public ServerException(String message, Throwable cause) {
        super(message, cause);
    }
}
