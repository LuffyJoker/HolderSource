package com.holderzone.holder.saas.store.design.pattern.decorate;

/**
 * @author Mr.Q
 * @date 2020/1/1 23:04
 * desc：
 *  饮料配料
 */

public class Whip extends CondimentDecorator{
    Beverage beverage;
    double myCost = 20;

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ",Whip";
    }

    @Override
    public double cost() {
        return myCost + beverage.cost();
    }

}
