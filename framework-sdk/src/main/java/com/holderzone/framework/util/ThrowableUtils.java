package com.holderzone.framework.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * @author Mr.Q
 * @date 2019/12/24 23:29
 * desc：
 */
public final class ThrowableUtils {
    private ThrowableUtils() {
    }

    public static String asString(Throwable throwable) {
        Writer result = new StringWriter();
        PrintWriter printWriter = new PrintWriter(result);
        throwable.printStackTrace(printWriter);
        return result.toString();
    }
}
