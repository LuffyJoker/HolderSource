package com.holderzone.holder.saas.store.item.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mr.Q
 * @date 2019/12/29 21:30
 * desc：
 */
@Configuration
public class MpDataSourceConfig {


    @Bean
    public PlatformTransactionManager transactionManager(DynamicDataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


}
