package com.holderzone.framework.event.publish.impl;

import com.holderzone.framework.anno.CustomerRegister;
import com.holderzone.framework.event.observer.CustomerObserver;
import com.holderzone.framework.util.ScanPackageUtils;

import java.util.*;

/**
 * @author Mr.Q
 * @date 2019/12/24 21:52
 * desc：
 */
public class FactoryMethod {
    private static Set<Class<?>> classesCach = new HashSet();
    private static FactoryMethod factoryMethod;

    private FactoryMethod() {
    }

    public void flushCach(String packName) {
        classesCach = ScanPackageUtils.getClasses(packName);
    }

    public static FactoryMethod getInstance() {
        return factoryMethod;
    }

    public void setObserver(String packName, List<Class<CustomerObserver>> list) {
        if (classesCach.size() == 0) {
            classesCach = ScanPackageUtils.getClasses(packName);
        }

        classesCach.stream().filter((aClass) -> {
            Optional<CustomerRegister> annotation = Optional.ofNullable(aClass.getAnnotation(CustomerRegister.class));
            return annotation.isPresent() && ((CustomerRegister)annotation.get()).isRegister();
        }).forEach((aClass) -> {
            Class<?>[] interfaces = aClass.getInterfaces();
            if (Arrays.asList(interfaces).contains(CustomerObserver.class)) {
                list.add((Class<CustomerObserver>) aClass);
            }

        });
    }

    public void setObserver1(String packName, List<CustomerObserver> lists) {
        if (classesCach.size() == 0) {
            classesCach = ScanPackageUtils.getClasses(packName);
        }

        classesCach.stream().filter((aClass) -> {
            Optional<CustomerRegister> annotation = Optional.ofNullable(aClass.getAnnotation(CustomerRegister.class));
            return annotation.isPresent() && ((CustomerRegister)annotation.get()).isRegister();
        }).forEach((aClass) -> {
            Class[] interfaces = aClass.getInterfaces();

            try {
                Object o = aClass.newInstance();
                lists.add((CustomerObserver)o);
            } catch (InstantiationException var4) {
                var4.printStackTrace();
            } catch (IllegalAccessException var5) {
                var5.printStackTrace();
            }

            if (Arrays.asList(interfaces).contains(CustomerObserver.class)) {
            }

        });
    }

    static {
        if (factoryMethod == null) {
            factoryMethod = new FactoryMethod();
        }

    }
}

