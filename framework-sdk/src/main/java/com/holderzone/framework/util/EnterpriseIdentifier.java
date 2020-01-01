package com.holderzone.framework.util;

public class EnterpriseIdentifier {
    private static final ThreadLocal<String> enterpriseThreadLocal = new ThreadLocal();

    public EnterpriseIdentifier() {
    }

    public static String getEnterpriseGuid() {
        return (String)enterpriseThreadLocal.get();
    }

    public static void setEnterpriseGuid(String enterpriseGuid) {
        enterpriseThreadLocal.set(enterpriseGuid);
    }

    public static void remove() {
        enterpriseThreadLocal.remove();
    }
}
