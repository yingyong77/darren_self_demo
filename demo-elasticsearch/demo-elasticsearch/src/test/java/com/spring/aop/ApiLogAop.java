package com.spring.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private SpringAopBean annotationConfigScanBean;

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
//         logger.info("====doBefore方法进入了====");
//
//        // 获取签名
//        Signature signature = joinPoint.getSignature();
//        // 获取切入的包名
//        String declaringTypeName = signature.getDeclaringTypeName();
//        // 获取即将执行的方法名
//        String funcName = signature.getName();
//        logger.info("即将执行方法为: {}，属于{}包", funcName, declaringTypeName);
//
//        // 也可以用来记录一些信息，比如获取请求的url和ip
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        // 获取请求url
//        String url = request.getRequestURL().toString();
//        // 获取请求ip
//        String ip = request.getRemoteAddr();
//        logger.info("用户请求的url为：{}，ip地址为：{}", url, ip);

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
