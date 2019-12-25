package com.holderzone.framework.feign.starter.util;

import com.holderzone.framework.feign.starter.core.GenericException;
import com.holderzone.framework.feign.starter.core.SpecificException;
import com.netflix.hystrix.exception.HystrixBadRequestException;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.function.Supplier;

/**
 * @author Mr.Q
 * @date 2019/12/25 20:31
 * desc：
 */
public class FeignErrorUtils {

    public static String asString(Throwable throwable) {
        Writer result = new StringWriter();
        PrintWriter printWriter = new PrintWriter(result);
        throwable.printStackTrace(printWriter);
        return result.toString();
    }

    public static String asStringIfAbsent(Throwable throwable) {
        String message = throwable.getMessage();
        return message != null ? message : asString(throwable);
    }

    public static void run(Runnable runnable) throws SpecificException.Checked, GenericException.Checked {
        try {
            runnable.run();
        } catch (RuntimeException e) {
            handleHystrixSpecific(e);
            handleHystrixGeneric(e);
            handleSpecific(e);
            handleGeneric(e);
            throw e;
        }
    }

    public static void runSpecific(Runnable runnable) throws SpecificException.Checked {
        try {
            runnable.run();
        } catch (RuntimeException e) {
            handleHystrixSpecific(e);
            handleSpecific(e);
            throw e;
        }
    }

    public static void runGeneric(Runnable runnable) throws GenericException.Checked {
        try {
            runnable.run();
        } catch (RuntimeException e) {
            handleHystrixGeneric(e);
            handleGeneric(e);
            throw e;
        }
    }

    public static <T> T get(Supplier<T> supplier) throws SpecificException.Checked, GenericException.Checked {
        try {
            return supplier.get();
        } catch (RuntimeException e) {
            handleHystrixSpecific(e);
            handleHystrixGeneric(e);
            handleSpecific(e);
            handleGeneric(e);
            throw e;
        }
    }

    public static <T> T getSpecific(Supplier<T> supplier) throws SpecificException.Checked {
        try {
            return supplier.get();
        } catch (RuntimeException e) {
            handleHystrixSpecific(e);
            handleSpecific(e);
            throw e;
        }
    }

    public static <T> T getGeneric(Supplier<T> supplier) throws GenericException.Checked {
        try {
            return supplier.get();
        } catch (RuntimeException e) {
            handleHystrixGeneric(e);
            handleGeneric(e);
            throw e;
        }
    }

    private static void handleHystrixSpecific(RuntimeException e) throws SpecificException.Checked {
        if (e instanceof HystrixBadRequestException) {
            handleSpecific(e.getCause());
        }
    }

    private static void handleHystrixGeneric(RuntimeException e) throws GenericException.Checked {
        if (e instanceof HystrixBadRequestException) {
            handleGeneric(e.getCause());
        }
    }

    private static void handleSpecific(Throwable cause) throws SpecificException.Checked {
        if (cause instanceof SpecificException) {
            throw ((SpecificException) cause).checked();
        }
    }

    private static void handleGeneric(Throwable cause) throws GenericException.Checked {
        if (cause instanceof GenericException) {
            throw ((GenericException) cause).checked();
        }
    }
}

