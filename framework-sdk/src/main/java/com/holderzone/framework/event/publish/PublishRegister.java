package com.holderzone.framework.event.publish;

import com.holderzone.framework.event.observer.CustomerObserver;

/**
 * @author Mr.Q
 * @date 2019/12/24 21:51
 * desc：
 */
public interface PublishRegister {
    void register(CustomerObserver var1);

    void unRegister(CustomerObserver var1);
}