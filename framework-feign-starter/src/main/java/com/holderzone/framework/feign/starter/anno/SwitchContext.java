package com.holderzone.framework.feign.starter.anno;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SwitchContext {

    /**
     * 适用于当前注解的SPEL
     * #asyncTask.userInfo
     * #messageExt.getProperty('message-context')
     * eg: @SwitchContext(userInfo = "#asyncTask.userInfo")
     * eg: @SwitchContext(userInfo = "#messageExt.getProperty('message-context')")
     * <p>
     * 适用于动态数据源SwitchDatasourceAnno的SPEL，
     * 用于从当前SwitchContext中取到enterpriseGuid；
     * SwitchContext比SwitchDatasourceAnno优先级高，所以才能获取到。
     * T(com.holderzone.feign.spring.boot.util.UserContextUtils).getEnterpriseGuid()
     * eg: @SwitchDatasourceAnno(enterpriseGuid = "T(com.holderzone.feign.spring.boot.util.UserContextUtils).getEnterpriseGuid()")
     *
     * @return
     */
    String userInfo() default "#asyncTask.userInfo";
}
