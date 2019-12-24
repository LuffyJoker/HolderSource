package com.holderzone.frameworks.slf4j.starter.serializer;

public class DefaultLogSerializer implements LogSerializer {
    public DefaultLogSerializer() {
    }

    public String serializer(Object object) {
        return object.toString();
    }
}
