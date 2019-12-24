package com.holderzone.framework.event.observer;

import com.holderzone.framework.event.CustomerEvent;

/**
 * @author Mr.Q
 * @date 2019/12/24 21:50
 * desc：
 */
public interface CustomerObserver<T> {
    void notify(CustomerEvent<T> var1);
}
