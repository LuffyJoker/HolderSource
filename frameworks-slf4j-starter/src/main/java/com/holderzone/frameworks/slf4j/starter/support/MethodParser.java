package com.holderzone.frameworks.slf4j.starter.support;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.LocalVariableAttribute;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MethodParser {
    private static final ClassPool POOL = ClassPool.getDefault();

    public MethodParser() {
    }

    public static CtMethod getMethod(String className, String methodName) throws NotFoundException {
        return POOL.get(className).getDeclaredMethod(methodName);
    }

    public static MethodInfo getMethodInfo(String className, String methodName, String[] parameterNames) {
        try {
            return getMethodInfo(getMethod(className, methodName), parameterNames);
        } catch (Exception var4) {
            return new MethodInfo(className, className.substring(className.lastIndexOf(46)), methodName, new ArrayList(0), -2);
        }
    }

    public static MethodInfo getMethodInfo(CtMethod method, String[] parameterNames) {
        CtClass declaringClass = method.getDeclaringClass();

        try {
            javassist.bytecode.MethodInfo methodInfo = method.getMethodInfo();
            int lineNumber = methodInfo.getLineNumber(0);
            ArrayList paramNames;
            if (parameterNames != null) {
                paramNames = new ArrayList(parameterNames.length);
                Collections.addAll(paramNames, parameterNames);
            } else {
                LocalVariableAttribute attribute = (LocalVariableAttribute)methodInfo.getCodeAttribute().getAttribute("LocalVariableTable");
                if (attribute != null) {
                    int count = method.getParameterTypes().length;
                    paramNames = new ArrayList(count);

                    for(int i = 1; i <= count; ++i) {
                        paramNames.add(attribute.variableName(i));
                    }
                } else {
                    paramNames = new ArrayList(0);
                }
            }

            return new MethodInfo(declaringClass.getName(), declaringClass.getSimpleName(), method.getName(), paramNames, lineNumber);
        } catch (Exception var9) {
            return new MethodInfo(declaringClass.getName(), declaringClass.getSimpleName(), method.getName(), new ArrayList(0), -2);
        }
    }

    public static MethodInfo getMethodInfo(MethodSignature signature, int lineNumber) {
        Class declaringClass = signature.getDeclaringType();
        String[] parameterNames = signature.getParameterNames();
        List<String> paramNames = new ArrayList(parameterNames.length);
        Collections.addAll(paramNames, parameterNames);
        return new MethodInfo(declaringClass.getName(), declaringClass.getSimpleName(), signature.getName(), paramNames, lineNumber);
    }
}

