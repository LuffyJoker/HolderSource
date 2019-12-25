package com.holderzone.framework.feign.starter.aspect;

import com.holderzone.framework.feign.starter.anno.SwitchContext;
import com.holderzone.framework.feign.starter.util.UserContextUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.Ordered;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class SwitchContextAspect implements Ordered {

    @Around(value = "@annotation(switchContext)")
    public Object switchContext(ProceedingJoinPoint joinPoint, SwitchContext switchContext) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = discoverer.getParameterNames(method);
        String userInfo = parseUserInfo(switchContext.userInfo(), args, parameterNames);

        try {
            UserContextUtils.put(userInfo);
            return joinPoint.proceed(args);
        } finally {
            UserContextUtils.remove();
        }
    }

    private String parseUserInfo(String userInfoExpression, Object[] args, String[] parameterNames) {
        StandardEvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < args.length; i++) {
            context.setVariable(parameterNames[i], args[i]);
        }
        SpelExpressionParser parser = new SpelExpressionParser();
        return parser.parseExpression(userInfoExpression).getValue(context, String.class);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1;
    }
}
