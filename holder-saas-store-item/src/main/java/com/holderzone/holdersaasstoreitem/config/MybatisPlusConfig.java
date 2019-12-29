package com.holderzone.holdersaasstoreitem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mr.Q
 * @date 2019/12/29 21:31
 * desc：
 */

@Configuration
public class MybatisPlusConfig {

    /**
     * 分页插件
     * 与sdk中的PageInterceptor冲突了，一旦配置这个，RoutingStatementHandler会创建代理类，PageInterceptor反射获取delegate就会获取不到
     //     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

    //乐观锁实现
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }
}
