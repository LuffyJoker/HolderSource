package com.holderzone.framework.exception;

/**
 * @author Mr.Q
 * @date 2019/12/24 21:53
 * desc：
 */
public class NotFoundException extends Exception {
    private static final long serialVersionUID = -4122227362914191481L;

    public NotFoundException() {
        super("未找到");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
