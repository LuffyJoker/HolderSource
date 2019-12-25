package com.holderzone.framework.feign.starter.pojo;

import com.holderzone.framework.feign.starter.util.UserContextUtils;
import lombok.Data;

/**
 * @author Mr.Q
 * @date 2019/12/25 20:30
 * desc：
 */
@Data
public class AsyncTask<T> {

    private String userInfo;

    private String enterpriseGuid;

    private T data;

    public static <T> AsyncTask<T> wrapper(T data) {
        AsyncTask<T> asyncTask = new AsyncTask<>();
        asyncTask.data = data;
        asyncTask.userInfo = UserContextUtils.getJsonStr();
        asyncTask.enterpriseGuid = UserContextUtils.getEnterpriseGuid();
        return asyncTask;
    }
}
