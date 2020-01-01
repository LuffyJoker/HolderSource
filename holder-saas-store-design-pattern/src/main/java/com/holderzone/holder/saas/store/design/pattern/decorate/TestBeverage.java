package com.holderzone.holder.saas.store.design.pattern.decorate;

/**
 * @author Mr.Q
 * @date 2020/1/1 23:06
 * desc：测试使用装饰者模式
 */
public class TestBeverage {
    public static void main(String args[]) {
        //不加调料的HouseBlend
        Beverage beverage = new HouseBlend();
        System.out.println(beverage.getDescription() + "     $" + beverage.cost());
        //加入调料的HouseBlend
        Beverage beverage2 = new HouseBlend();
        beverage2 = new Mocha(beverage2);
        System.out.println(beverage2.getDescription() + "    $" + beverage2.cost());
    }
}
