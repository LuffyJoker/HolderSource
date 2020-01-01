package com.holderzone.holder.saas.store.design.pattern.decorate;

/**
 * @author Mr.Q
 * @date 2020/1/1 23:02
 * desc：
 * 具体的饮料类型
 */
public class HouseBlend extends Beverage {
    public HouseBlend() {
        descriptionString = "House Blend Coffee";
    }

    @Override
    public double cost() {
        return 5;
    }
}