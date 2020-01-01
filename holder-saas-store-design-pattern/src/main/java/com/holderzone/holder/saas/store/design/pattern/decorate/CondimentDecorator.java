package com.holderzone.holder.saas.store.design.pattern.decorate;

/**
 * @author Mr.Q
 * @date 2020/1/1 23:01
 * desc：
 * 调料，装饰者；继承被装饰者，是为了被装饰者被装饰之后，类型不变
 */
public abstract class CondimentDecorator extends Beverage {
    // 调料材料子类必须实现的抽象方法
    public abstract String getDescription();
}