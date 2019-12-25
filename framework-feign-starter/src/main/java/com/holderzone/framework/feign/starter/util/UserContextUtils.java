package com.holderzone.framework.feign.starter.util;

import com.holderzone.framework.feign.starter.pojo.UserContext;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * @author Mr.Q
 * @date 2019/12/25 20:31
 * desc：
 */
public class UserContextUtils {

    private static final ThreadLocal<UserContext> THREAD_LOCAL = new ThreadLocal<>();

    public static void put(String json) {
        THREAD_LOCAL.set(FeignJsonUtils.toObject(UserContext.class, json));
    }

    public static void put(UserContext userContext) {
        THREAD_LOCAL.set(userContext);
    }

    public static void putErp(String enterpriseGuid) {
        UserContext userContext = new UserContext();
        userContext.setEnterpriseGuid(enterpriseGuid);
        THREAD_LOCAL.set(userContext);

    }

    public static void putErpAndStore(String enterpriseGuid, String storeGuid) {
        UserContext userContext = new UserContext();
        userContext.setEnterpriseGuid(enterpriseGuid);
        userContext.setStoreGuid(storeGuid);
        THREAD_LOCAL.set(userContext);
    }

    public static void putOrByErpAndStore(String json, String enterpriseGuid, String storeGuid) {
        if (StringUtils.hasText(json)) {
            put(json);
        } else {
            putErpAndStore(enterpriseGuid, storeGuid);
        }
    }

    public static UserContext get() {
        return THREAD_LOCAL.get();
    }

    public static String getJsonStr() {
        return Optional.ofNullable(THREAD_LOCAL.get()).map(FeignJsonUtils::writeValueAsString).orElse(null);
    }

    public static String getEnterpriseGuid() {
        return Optional.ofNullable(THREAD_LOCAL.get()).map(UserContext::getEnterpriseGuid).orElse(null);
    }

    public static String getEnterpriseName() {
        return Optional.ofNullable(THREAD_LOCAL.get()).map(UserContext::getEnterpriseName).orElse(null);
    }

    public static String getEnterpriseNo() {
        return Optional.ofNullable(THREAD_LOCAL.get()).map(UserContext::getEnterpriseNo).orElse(null);
    }

    public static String getStoreGuid() {
        return Optional.ofNullable(THREAD_LOCAL.get()).map(UserContext::getStoreGuid).orElse(null);
    }

    public static String getStoreName() {
        return Optional.ofNullable(THREAD_LOCAL.get()).map(UserContext::getStoreName).orElse(null);
    }

    public static String getUserGuid() {
        return Optional.ofNullable(THREAD_LOCAL.get()).map(UserContext::getUserGuid).orElse(null);
    }

    public static String getUserName() {
        return Optional.ofNullable(THREAD_LOCAL.get()).map(UserContext::getUserName).orElse(null);
    }

    public static String getUserAccount() {
        return Optional.ofNullable(THREAD_LOCAL.get()).map(UserContext::getAccount).orElse(null);
    }

    public static String getUserTel() {
        return Optional.ofNullable(THREAD_LOCAL.get()).map(UserContext::getTel).orElse(null);
    }

    public static String getSource() {
        return Optional.ofNullable(THREAD_LOCAL.get()).map(UserContext::getSource).orElse(null);
    }

    public static String getEnterpriseGuidOrDefault(String enterpriseGuid) {
        return Optional.ofNullable(THREAD_LOCAL.get()).map(UserContext::getEnterpriseGuid).orElse(enterpriseGuid);
    }

    public static String getEnterpriseNameOrDefault(String enterpriseName) {
        return Optional.ofNullable(THREAD_LOCAL.get()).map(UserContext::getEnterpriseName).orElse(enterpriseName);
    }

    public static String getEnterpriseNoOrDefault(String enterpriseNo) {
        return Optional.ofNullable(THREAD_LOCAL.get()).map(UserContext::getEnterpriseNo).orElse(enterpriseNo);
    }

    public static String getStoreGuidOrDefault(String storeGuid) {
        return Optional.ofNullable(THREAD_LOCAL.get()).map(UserContext::getStoreGuid).orElse(storeGuid);
    }

    public static String getStoreNameOrDefault(String storeName) {
        return Optional.ofNullable(THREAD_LOCAL.get()).map(UserContext::getStoreName).orElse(storeName);
    }

    public static String getUserGuidOrDefault(String userGuid) {
        return Optional.ofNullable(THREAD_LOCAL.get()).map(UserContext::getUserGuid).orElse(userGuid);
    }

    public static String getUserNameOrDefault(String userName) {
        return Optional.ofNullable(THREAD_LOCAL.get()).map(UserContext::getUserName).orElse(userName);
    }

    public static String getAccountOrDefault(String account) {
        return Optional.ofNullable(THREAD_LOCAL.get()).map(UserContext::getAccount).orElse(account);
    }

    public static String getTelOrDefault(String tel) {
        return Optional.ofNullable(THREAD_LOCAL.get()).map(UserContext::getTel).orElse(tel);
    }

    public static String getSourceOrDefault(String source) {
        return Optional.ofNullable(THREAD_LOCAL.get()).map(UserContext::getSource).orElse(source);
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }
}

