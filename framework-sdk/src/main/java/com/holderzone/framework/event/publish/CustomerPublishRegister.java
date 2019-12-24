package com.holderzone.framework.event.publish;

import com.holderzone.framework.event.CustomerEvent;
import com.holderzone.framework.event.observer.CustomerObserver;

import java.util.List;

/**
 * @author Mr.Q
 * @date 2019/12/24 21:51
 * desc：
 */
public abstract class CustomerPublishRegister implements PublishRegister {
    public CustomerPublishRegister() {
    }

    protected abstract void publish(CustomerEvent var1);

    protected abstract List<CustomerObserver> get();
}
