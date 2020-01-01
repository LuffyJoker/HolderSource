package com.holderzone.holder.saas.store.design.pattern.decorate;

/**
 * @author Mr.Q
 * @date 2020/1/1 23:03
 * desc：
 * 具体的装饰元素，在构造方法中传入被装饰者，使用 Mocha 装饰之后，类型还是被装饰者的类型
 */
public class Mocha extends CondimentDecorator {
    Beverage beverage;
    double myCost = 20;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        return myCost + beverage.cost();
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ",Mocha";
    }
}
