package com.holderzone.frameworks.slf4j.starter.support;

public enum ArrayType {
    CHAR_ARRAY(char[].class),
    BYTE_ARRAY(byte[].class),
    SHORT_ARRAY(short[].class),
    INT_ARRAY(int[].class),
    LONG_ARRAY(long[].class),
    FLOAT_ARRAY(float[].class),
    DOUBLE_ARRAY(double[].class),
    BOOLEAN_ARRAY(boolean[].class),
    OBJECT_ARRAY(Object[].class);

    private Class type;

    private ArrayType(Class type) {
        this.type = type;
    }

    public Class getType() {
        return this.type;
    }
}
