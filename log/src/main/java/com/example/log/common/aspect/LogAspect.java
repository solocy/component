package com.example.log.common.aspect;


import com.example.log.common.utils.CommonUtil;
import com.example.log.domain.Log;
import com.example.log.domain.enumeration.LogType;
import com.example.log.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 利用切面和注解记录访问日志
 *
 */
@Aspect
@Component
public class LogAspect {

    @Autowired
    private LogService logService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private long currentTime;

    @Pointcut("@annotation(com.example.log.common.annotation.LogInfo)")
    public void pointCut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    @Around("pointCut()")
    public Object logAround(ProceedingJoinPoint pjp) {

        Object result;
        currentTime = System.currentTimeMillis();
        try {
            result = pjp.proceed();
        } catch (Throwable throwable) {
            throw  new RuntimeException(throwable);
        }
        Long time = System.currentTimeMillis() - currentTime;
        logger.debug("request end, start add log information");
        Log log = new Log(LogType.INFO,time);
        logService.save(log,pjp);
        return result;
    }

    @AfterThrowing(pointcut = "pointCut()",throwing = "e")
    public void logAfterThrowing(JoinPoint pjp, Throwable e) {
        Long time = System.currentTimeMillis() -currentTime;
        Log log = new Log(LogType.ERROR,time);
        log.setExceptionDetail(CommonUtil.throwToString(e));
        logService.save(log,pjp);
        logger.error(e.getLocalizedMessage());
    }

}
