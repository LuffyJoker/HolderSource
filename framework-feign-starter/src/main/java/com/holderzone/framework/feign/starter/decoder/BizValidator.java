package com.holderzone.framework.feign.starter.decoder;

/**
 * @author Mr.Q
 * @date 2019/12/25 20:28
 * desc：
 */
public final class BizValidator {

    public static final String UNKNOWN_REASON = "未知原因";

    private static final int BIZ_CODE_LOWER_BOUNDARY_INCLUDE = 400;

    private static final int BIZ_CODE_UPPER_BOUNDARY_EXCLUDE = 500;

    public static boolean isBizError(int status) {
        return BIZ_CODE_LOWER_BOUNDARY_INCLUDE <= status
                && status < BIZ_CODE_UPPER_BOUNDARY_EXCLUDE;
    }
}
