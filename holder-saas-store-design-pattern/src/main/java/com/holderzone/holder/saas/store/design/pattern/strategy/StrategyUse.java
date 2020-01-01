package com.holderzone.holder.saas.store.design.pattern.strategy;

/**
 * @author Mr.Q
 * @date 2020/1/2 0:44
 * desc：
 */
public class StrategyUse {
    private Strategy strategy;
    public StrategyUse(Strategy strategy) {
        this.strategy = strategy;
    }
    public void useStrategy() {
        strategy.algorithmInterface();
    }
}
