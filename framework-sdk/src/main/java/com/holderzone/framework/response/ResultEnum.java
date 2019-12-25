package com.holderzone.framework.response;

/**
 * @author Mr.Q
 * @date 2019/12/25 23:01
 * desc：
 */
public enum ResultEnum {
    SUCCESS(0, "成功"),
    UNKNOWN_ERROR(1, "未知错误"),
    SERVICE_TEMPORARILY_UNAVAILABLE(2, "服务暂不可用"),
    UNSUPPORTED_OPENAPI_METHOD(3, "未知的方法"),
    OPEN_API_REQUEST_LIMIT_REACHED(4, "接口调用次数已达到设定的上限"),
    UNAUTHORIZED_CLIENT_IP_ADDRESS(5, "请求来自未经授权的IP地址"),
    NO_PERMISSION_TO_ACCESS_DATA(6, "无权限访问该用户数据"),
    NO_PERMISSION_TO_ACCESS_DATA_FOR_THIS_REFERER(7, "来自该refer的请求无访问权限"),
    INVALID_PARAMETER(100, "请求参数无效"),
    INVALID_API_KEY(101, "api key无效"),
    SESSION_KEY_INVALID_OR_NO_LONGER_VALID(102, "session key无效"),
    INCORRECT_SIGNATURE(104, "无效签名"),
    USER_NOT_VISIBLE(210, "用户不可见"),
    UNSUPPORTED_PERMISSION(211, "获取未授权的字段"),
    UNKNOWN_DATA_STORE_API_ERROR(800, "未知的存储操作错误"),
    INVALID_OPERATION(801, "无效的操作方法"),
    SPECIFIED_OBJECT_CANNOT_BE_FOUND(803, "指定的对象不存在"),
    A_DATABASE_ERROR_OCCURRED_PLEASE_TRY_AGAIN(805, "数据库操作出错，请重试"),
    NO_SUCH_APPLICATION_EXISTS(900, "访问的应用不存在");

    private int resultCode;
    private String resultMsg;

    private ResultEnum(int resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return this.resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}

