package com.holderzone.frameworks.slf4j.starter.anno;

import com.holderzone.frameworks.slf4j.starter.enums.LogClassName;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogThrowing {
    String value();

    LogClassName logClassName() default LogClassName.NAME;
}
