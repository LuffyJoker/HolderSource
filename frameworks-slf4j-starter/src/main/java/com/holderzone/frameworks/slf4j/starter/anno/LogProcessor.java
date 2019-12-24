package com.holderzone.frameworks.slf4j.starter.anno;

import com.holderzone.frameworks.slf4j.starter.enums.LogClassName;
import com.holderzone.frameworks.slf4j.starter.enums.LogLevel;
import com.holderzone.frameworks.slf4j.starter.enums.LogPosition;
import com.holderzone.frameworks.slf4j.starter.serializer.LogSerializer;
import com.holderzone.frameworks.slf4j.starter.support.ArrayType;
import com.holderzone.frameworks.slf4j.starter.support.MethodInfo;
import com.holderzone.frameworks.slf4j.starter.support.MethodParser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.*;

@Aspect
public class LogProcessor {
    private static final Logger log = LoggerFactory.getLogger(LogProcessor.class);
    private static final int LINE_NUMBER = -2;
    private final LogSerializer logSerializer;

    public LogProcessor(LogSerializer logSerializer) {
        this.logSerializer = logSerializer;
    }

    @Before("@annotation(com.holderzone.frameworks.slf4j.starter.anno.LogBefore)")
    public void beforePrint(JoinPoint joinPoint) {
        if (this.isEnable()) {
            MethodSignature signature = (MethodSignature)joinPoint.getSignature();
            LogBefore annotation = (LogBefore)signature.getMethod().getAnnotation(LogBefore.class);
            this.beforePrint(signature, joinPoint.getArgs(), annotation.value(), annotation.logLevel(), annotation.logPosition(), annotation.logClassName());
        }

    }

    @AfterReturning(
            value = "@annotation(com.holderzone.frameworks.slf4j.starter.anno.LogAfter)",
            returning = "result"
    )
    public void afterPrint(JoinPoint joinPoint, Object result) {
        if (this.isEnable()) {
            MethodSignature signature = (MethodSignature)joinPoint.getSignature();
            LogAfter annotation = (LogAfter)signature.getMethod().getAnnotation(LogAfter.class);
            this.afterPrint(signature, result, annotation.value(), annotation.logLevel(), annotation.logPosition(), annotation.logClassName());
        }

    }

    @AfterThrowing(
            value = "@annotation(com.holderzone.frameworks.slf4j.starter.anno.LogThrowing)||@annotation(com.holderzone.frameworks.slf4j.starter.anno.LogAround)",
            throwing = "throwable"
    )
    public void throwingPrint(JoinPoint joinPoint, Throwable throwable) {
        if (this.isEnable()) {
            MethodSignature signature = (MethodSignature)joinPoint.getSignature();
            Method method = signature.getMethod();
            String methodName = method.getName();

            try {
                LogThrowing annotation = (LogThrowing)method.getAnnotation(LogThrowing.class);
                if (annotation != null) {
                    log.error(this.getThrowingInfo(annotation.value(), annotation.logClassName(), MethodParser.getMethodInfo(signature, -2)), throwable);
                } else {
                    LogAround logAround = (LogAround)method.getAnnotation(LogAround.class);
                    log.error(this.getThrowingInfo(logAround.value(), logAround.logClassName(), MethodParser.getMethodInfo(signature, -2)), throwable);
                }
            } catch (Exception var8) {
                log.error("{}.{}方法错误", signature.getDeclaringTypeName(), methodName);
            }
        }

    }

    @Around("@annotation(com.holderzone.frameworks.slf4j.starter.anno.LogAround)")
    public Object aroundPrint(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        Object result = joinPoint.proceed(args);
        if (this.isEnable()) {
            LogAround annotation = (LogAround)signature.getMethod().getAnnotation(LogAround.class);
            this.beforePrint(signature, args, annotation.value(), annotation.logLevel(), annotation.logPosition(), annotation.logClassName());
            this.afterPrint(signature, result, annotation.value(), annotation.logLevel(), annotation.logPosition(), annotation.logClassName());
        }

        return result;
    }

    private void beforePrint(MethodSignature signature, Object[] args, String busName, LogLevel logLevel, LogPosition logPosition, LogClassName logClassName) {
        Method method = signature.getMethod();
        String methodName = method.getName();

        try {
            if (log.isDebugEnabled()) {
                if (logPosition != LogPosition.DEFAULT && logPosition != LogPosition.ENABLED) {
                    this.print(logLevel, this.getBeforeInfo(busName, logClassName, MethodParser.getMethodInfo(signature, -2), args));
                } else {
                    this.print(logLevel, this.getBeforeInfo(busName, logClassName, MethodParser.getMethodInfo(signature.getDeclaringTypeName(), methodName, signature.getParameterNames()), args));
                }
            } else if (logPosition == LogPosition.ENABLED) {
                this.print(logLevel, this.getBeforeInfo(busName, logClassName, MethodParser.getMethodInfo(signature.getDeclaringTypeName(), methodName, signature.getParameterNames()), args));
            } else {
                this.print(logLevel, this.getBeforeInfo(busName, logClassName, MethodParser.getMethodInfo(signature, -2), args));
            }
        } catch (Exception var10) {
            log.error("{}.{}方法错误", signature.getDeclaringTypeName(), methodName);
        }

    }

    private void afterPrint(MethodSignature signature, Object result, String busName, LogLevel logLevel, LogPosition logPosition, LogClassName logClassName) {
        Method method = signature.getMethod();
        String methodName = method.getName();

        try {
            if (log.isDebugEnabled()) {
                if (logPosition != LogPosition.DEFAULT && logPosition != LogPosition.ENABLED) {
                    this.print(logLevel, this.getAfterInfo(busName, logClassName, MethodParser.getMethodInfo(signature, -2), result));
                } else {
                    this.print(logLevel, this.getAfterInfo(busName, logClassName, MethodParser.getMethodInfo(signature.getDeclaringTypeName(), methodName, signature.getParameterNames()), result));
                }
            } else if (logPosition == LogPosition.ENABLED) {
                this.print(logLevel, this.getAfterInfo(busName, logClassName, MethodParser.getMethodInfo(signature.getDeclaringTypeName(), methodName, signature.getParameterNames()), result));
            } else {
                this.print(logLevel, this.getAfterInfo(busName, logClassName, MethodParser.getMethodInfo(signature, -2), result));
            }
        } catch (Exception var10) {
            log.error("{}.{}方法错误", signature.getDeclaringTypeName(), methodName);
        }

    }

    private String getBeforeInfo(String busName, LogClassName logClassName, MethodInfo methodInfo, Object[] params) {
        StringBuilder builder = this.createInfoBuilder(busName, logClassName, methodInfo);
        builder.append("接收参数：【");
        List<String> paramNames = methodInfo.getParamNames();
        int count = paramNames.size();
        if (count <= 0) {
            return builder.append("{}】").toString();
        } else {
            Map<String, Object> paramMap = new HashMap(count);

            for(int i = 0; i < count; ++i) {
                paramMap.put(paramNames.get(i), this.getParam(params[i]));
            }

            return builder.append(paramMap).append("】").toString();
        }
    }

    private String getAfterInfo(String busName, LogClassName logClassName, MethodInfo methodInfo, Object result) {
        return this.createInfoBuilder(busName, logClassName, methodInfo).append("返回结果：【").append(this.logSerializer.serializer(result)).append("】").toString();
    }

    private String getThrowingInfo(String busName, LogClassName logClassName, MethodInfo methodInfo) {
        return this.createInfoBuilder(busName, logClassName, methodInfo).append("异常信息：").toString();
    }

    private StringBuilder createInfoBuilder(String busName, LogClassName logClassName, MethodInfo methodInfo) {
        StringBuilder builder = new StringBuilder();
        builder.append("调用方法：【");
        if (methodInfo.getLineNumber() == -2) {
            String className = logClassName == LogClassName.NAME ? methodInfo.getClassAllName() : methodInfo.getClassSimpleName();
            builder.append(className).append(".").append(methodInfo.getMethodName());
        } else {
            builder.append(this.createMethodStack(logClassName, methodInfo));
        }

        return builder.append("】，").append("业务名称：【").append(busName).append("】，");
    }

    private Object getParam(Object param) {
        Class<?> type = param.getClass();
        return type.isArray() ? this.getList(type, param) : param;
    }

    private List<Object> getList(Class valueType, Object value) {
        ArrayList list;
        if (valueType.isAssignableFrom(ArrayType.OBJECT_ARRAY.getType())) {
            Object[] array = (Object[])((Object[])value);
            list = new ArrayList(array.length);
            Collections.addAll(list, array);
            return list;
        } else {
            int var6;
            int var7;
            if (valueType.isAssignableFrom(ArrayType.INT_ARRAY.getType())) {
                int[] array = (int[])((int[])value);
                list = new ArrayList(array.length);
                int[] var24 = array;
                var6 = array.length;

                for(var7 = 0; var7 < var6; ++var7) {
                    int v = var24[var7];
                    list.add(v);
                }

                return list;
            } else if (valueType.isAssignableFrom(ArrayType.LONG_ARRAY.getType())) {
                long[] array = (long[])((long[])value);
                list = new ArrayList(array.length);
                long[] var23 = array;
                var6 = array.length;

                for(var7 = 0; var7 < var6; ++var7) {
                    long v = var23[var7];
                    list.add(v);
                }

                return list;
            } else if (valueType.isAssignableFrom(ArrayType.DOUBLE_ARRAY.getType())) {
                double[] array = (double[])((double[])value);
                list = new ArrayList(array.length);
                double[] var22 = array;
                var6 = array.length;

                for(var7 = 0; var7 < var6; ++var7) {
                    double v = var22[var7];
                    list.add(v);
                }

                return list;
            } else if (valueType.isAssignableFrom(ArrayType.FLOAT_ARRAY.getType())) {
                float[] array = (float[])((float[])value);
                list = new ArrayList(array.length);
                float[] var21 = array;
                var6 = array.length;

                for(var7 = 0; var7 < var6; ++var7) {
                    float v = var21[var7];
                    list.add(v);
                }

                return list;
            } else if (valueType.isAssignableFrom(ArrayType.CHAR_ARRAY.getType())) {
                char[] array = (char[])((char[])value);
                list = new ArrayList(array.length);
                char[] var20 = array;
                var6 = array.length;

                for(var7 = 0; var7 < var6; ++var7) {
                    char v = var20[var7];
                    list.add(v);
                }

                return list;
            } else if (valueType.isAssignableFrom(ArrayType.BOOLEAN_ARRAY.getType())) {
                boolean[] array = (boolean[])((boolean[])value);
                list = new ArrayList(array.length);
                boolean[] var19 = array;
                var6 = array.length;

                for(var7 = 0; var7 < var6; ++var7) {
                    boolean v = var19[var7];
                    list.add(v);
                }

                return list;
            } else if (valueType.isAssignableFrom(ArrayType.BYTE_ARRAY.getType())) {
                byte[] array = (byte[])((byte[])value);
                list = new ArrayList(array.length);
                byte[] var17 = array;
                var6 = array.length;

                for(var7 = 0; var7 < var6; ++var7) {
                    byte v = var17[var7];
                    list.add(v);
                }

                return list;
            } else if (!valueType.isAssignableFrom(ArrayType.SHORT_ARRAY.getType())) {
                return new ArrayList(0);
            } else {
                short[] array = (short[])((short[])value);
                list = new ArrayList(array.length);
                short[] var5 = array;
                var6 = array.length;

                for(var7 = 0; var7 < var6; ++var7) {
                    short v = var5[var7];
                    list.add(v);
                }

                return list;
            }
        }
    }

    private StackTraceElement createMethodStack(LogClassName logClassName, MethodInfo methodInfo) {
        String className = logClassName == LogClassName.NAME ? methodInfo.getClassAllName() : methodInfo.getClassSimpleName();
        return new StackTraceElement(className, methodInfo.getMethodName(), String.format("%s.java", methodInfo.getClassSimpleName()), methodInfo.getLineNumber());
    }

    private void print(LogLevel logLevel, String msg) {
        switch(logLevel) {
            case DEBUG:
                log.debug(msg);
                break;
            case INFO:
                log.info(msg);
                break;
            case WARN:
                log.warn(msg);
                break;
            case ERROR:
                log.error(msg);
        }

    }

    private boolean isEnable() {
        return log.isDebugEnabled() || log.isInfoEnabled() || log.isWarnEnabled() || log.isErrorEnabled() || log.isTraceEnabled();
    }
}

