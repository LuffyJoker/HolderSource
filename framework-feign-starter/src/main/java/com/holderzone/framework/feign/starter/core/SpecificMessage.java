package com.holderzone.framework.feign.starter.core;

/**
 * @author Mr.Q
 * @date 2019/12/25 20:27
 * desc：
 * Throwable类的detailMessage是private的，且未提供set方法。
 * 所以，无法直接使用SpecificException.class反序列化errorJson。
 * 所以，提供了改类，用于放序列化errorJson；然后，手动地设置到SpecificException中。
 */
public class SpecificMessage {

    private int errorCode;

    private String detailMessage;

    public SpecificMessage() {
    }

    public SpecificMessage(int errorCode, String detailMessage) {
        this.errorCode = errorCode;
        this.detailMessage = detailMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    public void setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
    }
}
