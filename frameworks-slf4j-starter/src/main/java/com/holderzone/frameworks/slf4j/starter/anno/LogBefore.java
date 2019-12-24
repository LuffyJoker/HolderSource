package com.holderzone.frameworks.slf4j.starter.anno;

import com.holderzone.frameworks.slf4j.starter.enums.LogClassName;
import com.holderzone.frameworks.slf4j.starter.enums.LogLevel;
import com.holderzone.frameworks.slf4j.starter.enums.LogPosition;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogBefore {
    String value();

    LogLevel logLevel() default LogLevel.DEBUG;

    LogPosition logPosition() default LogPosition.DEFAULT;

    LogClassName logClassName() default LogClassName.NAME;
}
