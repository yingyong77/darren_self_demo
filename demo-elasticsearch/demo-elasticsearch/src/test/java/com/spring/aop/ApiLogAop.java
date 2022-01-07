package com.spring.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;


/**
 * Api 出入口 请求日志
 *
 * @author Darren
 */
@Aspect
@Configuration
@Slf4j
public class ApiLogAop {


    @Pointcut(value = "execution(public * com.spring.aop.SpringAopBean.test(..))")
    public void controllerLog() {

    }

    /**
     * Before
     *
     * @param joinPoint
     */
    @Before("controllerLog()")
    public void doBefore(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        log.info("IP:" + request.getRemoteAddr() + "-->method begin-->" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

    }

    /**
     * After
     *
     * @param joinPoint
     */
    @AfterReturning(returning = "result", pointcut = "controllerLog()")
    public void exAfter(JoinPoint joinPoint, Object result) {

        log.info("method done-->" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
    }

}
