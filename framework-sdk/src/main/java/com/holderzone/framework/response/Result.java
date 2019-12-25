package com.holderzone.framework.response;

/**
 * @author Mr.Q
 * @date 2019/12/25 23:01
 * desc：
 */
public class Result<TData> {
    private int code;
    private String message;
    private TData TData;
    private long now;

    public Result() {
    }

    public static <TData> Result<TData> buildSuccessResult(TData data) {
        return (new Result()).setCode(ResultEnum.SUCCESS.getResultCode()).setTData(data).setMessage(ResultEnum.SUCCESS.getResultMsg());
    }

    public static <TData> Result<TData> buildEmptySuccess() {
        return (new Result()).setCode(ResultEnum.SUCCESS.getResultCode()).setMessage(ResultEnum.SUCCESS.getResultMsg());
    }

    public static <TData> Result<TData> buildSuccessMsg(String message) {
        return (new Result()).setCode(ResultEnum.SUCCESS.getResultCode()).setMessage(message);
    }

    public static <TData> Result<TData> buildSuccessResult(String message, TData data) {
        return (new Result()).setCode(ResultEnum.SUCCESS.getResultCode()).setTData(data).setMessage(message);
    }

    public static <TData> Result<TData> buildIllegalArgumentResult(String message) {
        if (message == null) {
            message = ResultEnum.INVALID_PARAMETER.getResultMsg();
        }

        return (new Result()).setCode(ResultEnum.INVALID_PARAMETER.getResultCode()).setMessage(message);
    }

    public static <TData> Result<TData> buildNotExistedResult(String message) {
        if (message == null) {
            message = ResultEnum.SPECIFIED_OBJECT_CANNOT_BE_FOUND.getResultMsg();
        }

        return (new Result()).setCode(ResultEnum.SPECIFIED_OBJECT_CANNOT_BE_FOUND.getResultCode()).setMessage(message);
    }

    public static <TData> Result<TData> buildOpFailedResult(String message) {
        if (message == null) {
            message = ResultEnum.INVALID_OPERATION.getResultMsg();
        }

        return (new Result()).setCode(ResultEnum.INVALID_OPERATION.getResultCode()).setMessage(message);
    }

    public static <TData> Result<TData> buildOpFailedResult(Throwable t) {
        return (new Result()).setCode(ResultEnum.INVALID_OPERATION.getResultCode()).setMessage(t != null ? t.getClass().getName() + ":" + t.getMessage() : null);
    }

    public static <TData> Result<TData> buildOpFailedResult(String message, TData data) {
        if (message == null) {
            message = ResultEnum.INVALID_OPERATION.getResultMsg();
        }

        return (new Result()).setCode(ResultEnum.INVALID_OPERATION.getResultCode()).setMessage(message).setTData(data);
    }

    public static <TData> Result<TData> buildFailResult(int code, String message) {
        return (new Result()).setCode(code).setMessage(message);
    }

    public static <TData> Result<TData> buildSystemErrResult(String message) {
        if (message == null) {
            message = ResultEnum.UNKNOWN_ERROR.getResultMsg();
        }

        return (new Result()).setCode(ResultEnum.UNKNOWN_ERROR.getResultCode()).setMessage(message);
    }

    public Result<TData> copyFrom(Result<TData> result) {
        return this.setCode(result.getCode()).setMessage(result.getMessage()).setTData(result.getTData());
    }

    public int getCode() {
        return this.code;
    }

    public Result<TData> setCode(int code) {
        this.code = code;
        return this;
    }

    public TData getTData() {
        return this.TData;
    }

    public Result<TData> setTData(TData TData) {
        this.TData = TData;
        return this;
    }

    public long getNow() {
        return this.now;
    }

    public Result<TData> setNow(long now) {
        this.now = now;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public Result<TData> setMessage(String message) {
        this.message = message;
        return this;
    }

    public String toString() {
        return "Result{code=" + this.code + ", message='" + this.message + '\'' + ", TData=" + this.TData + ", now=" + this.now + '}';
    }
}

