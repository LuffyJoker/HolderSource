package com.holderzone.holder.saas.store.design.pattern.observable;

/**
 * @author Mr.Q
 * @date 2020/1/3 0:41
 * desc：
 */
public class ObserverA implements Observer {

    //观察者状态
    private String observerState;

    @Override
    public void update(String newState) {
        //更新观察者状态，让它与目标状态一致
        observerState = newState;
        System.out.println("接收到消息：" + newState + "；我是A模块，快来抢吧！！");
    }
}
