package com.holderzone.framework.exception;

/**
 * @author Mr.Q
 * @date 2019/12/24 21:54
 * desc：
 * 参数异常
 */
public class ParamException extends Exception {
    private static final long serialVersionUID = -8092102461482267120L;

    public ParamException() {
        super("参数错误");
    }

    public ParamException(String message) {
        super(message);
    }

    public ParamException(String message, Throwable cause) {
        super(message, cause);
    }
}
