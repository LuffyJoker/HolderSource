package com.holderzone.holder.saas.store.design.pattern.decorate;

/**
 * @author Mr.Q
 * @date 2020/1/1 22:59
 * desc：
 *  饮料，被装饰者
 */
public abstract class Beverage {

    String descriptionString = "未知饮料";

    //类型描述父类实现
    public String getDescription() {
        return descriptionString;
    }

    //子类需要实现的抽象方法
    public abstract double cost();
}
