package com.holderzone.holdersaasstoreitem.aop;

import com.holderzone.framework.feign.starter.util.UserContextUtils;
import com.holderzone.holdersaasstoredto.dto.common.BaseDTO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author Mr.Q
 * @date 2019/12/29 21:28
 * desc：
 */
@Aspect
@Component
public class ControllerAspect {

    @Pointcut("execution(* com.holderzone.holdersaasstoreitem.controller.*.*(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            for (Object arg : args) {
                if (arg instanceof BaseDTO) {
                    if (!StringUtils.isEmpty(UserContextUtils.getEnterpriseGuid())) {
                        ((BaseDTO) arg).setEnterpriseGuid(UserContextUtils.getEnterpriseGuid());
                    }
                    if (!StringUtils.isEmpty(UserContextUtils.getUserGuid())) {
                        ((BaseDTO) arg).setUserGuid(UserContextUtils.getUserGuid());
                    }
                    if (!StringUtils.isEmpty(UserContextUtils.getUserGuid())) {
                        ((BaseDTO) arg).setUserName(UserContextUtils.getUserName());
                    }
                    if (!StringUtils.isEmpty(UserContextUtils.getUserAccount())) {
                        ((BaseDTO) arg).setAccount(UserContextUtils.getUserAccount());
                    }
                    if (!StringUtils.isEmpty(UserContextUtils.getStoreGuid())) {
                        ((BaseDTO) arg).setStoreGuid(UserContextUtils.getStoreGuid());
                    }
                }
            }
        }
    }
}

