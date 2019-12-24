package com.holderzone.framework.event.publish.impl;

import com.holderzone.framework.event.CustomerEvent;
import com.holderzone.framework.event.observer.CustomerObserver;
import com.holderzone.framework.event.publish.CustomerPublishRegister;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Mr.Q
 * @date 2019/12/24 21:51
 * desc：
 */
public class CustomerPublishImpl extends CustomerPublishRegister {
    private volatile List<CustomerObserver> lists = new LinkedList();
    private volatile List<Class<CustomerObserver>> listsForSpring = new LinkedList();
    private final AtomicInteger mCount = new AtomicInteger(0);
    private final ExecutorService executorService;
    private String packName;

    public CustomerPublishImpl() {
        this.executorService = new ThreadPoolExecutor(5, 15, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(99999), (r) -> {
            return new Thread(r, "customerPublish-" + this.mCount.incrementAndGet());
        }, new AbortPolicy());
    }

    public void publish(CustomerEvent customerEvent) {
        if (customerEvent != null) {
            this.lists.stream().filter((customerObserver) -> {
                Type[] genericInterfaces = customerObserver.getClass().getGenericInterfaces();
                return Arrays.stream(genericInterfaces).map((type) -> {
                    ParameterizedTypeImpl actuallyType = (ParameterizedTypeImpl)type;
                    return actuallyType.getRawType().getName().equals(CustomerObserver.class.getName()) ? type : null;
                }).anyMatch((observer) -> {
                    return null != observer && null != customerEvent.getSource() ? ((ParameterizedTypeImpl)observer).getActualTypeArguments()[0].getTypeName().equals(customerEvent.getSource().getClass().getName()) : false;
                });
            }).forEach((customerObserver) -> {
                this.executorService.submit(() -> {
                    customerObserver.notify(customerEvent);
                });
            });
        }

    }

    protected List<CustomerObserver> get() {
        return this.lists;
    }

    public void register(CustomerObserver customerObserver) {
        if (customerObserver != null) {
            this.lists.add(customerObserver);
        }

    }

    public void unRegister(CustomerObserver customerObserver) {
        if (customerObserver != null) {
            this.lists.remove(customerObserver);
        }

    }

    public void addObserver() {
        FactoryMethod.getInstance().setObserver(this.packName, this.listsForSpring);
    }

    public void addObserverObject() {
        FactoryMethod.getInstance().setObserver1(this.packName, this.lists);
    }

    public void setLists(List<CustomerObserver> lists) {
        this.lists = lists;
    }

    public String getPackName() {
        return this.packName;
    }

    public void setPackName(String packName) {
        this.packName = packName;
    }

    public List<CustomerObserver> getLists() {
        return this.lists;
    }

    public List<Class<CustomerObserver>> getListsForSpring() {
        return this.listsForSpring;
    }

    public void setListsForSpring(List<Class<CustomerObserver>> listsForSpring) {
        this.listsForSpring = listsForSpring;
    }
}

