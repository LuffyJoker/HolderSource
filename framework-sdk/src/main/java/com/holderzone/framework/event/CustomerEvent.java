package com.holderzone.framework.event;

/**
 * @author Mr.Q
 * @date 2019/12/24 21:49
 * desc：
 */
public class CustomerEvent<T> {
    private T source;

    public CustomerEvent(T source) {
        this.source = source;
    }

    public CustomerEvent() {
    }

    public Object getSource() {
        return this.source;
    }

    public void setSource(T source) {
        this.source = source;
    }
}
