package com.example.log.service.impl;

import com.example.log.common.annotation.LogInfo;
import com.example.log.common.utils.UserAgentUtils;
import com.example.log.domain.Log;
import com.example.log.domain.enumeration.LogType;
import com.example.log.repository.LogRepository;
import com.example.log.service.LogService;
import com.example.log.utils.RequestHolder;
import eu.bitwalker.useragentutils.UserAgent;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LogRepository logRepository;

    /**
     * 切面记录日志
     *
     * @param log
     * @param pjp
     * @return
     */
    @Override
    public Log save(Log log, JoinPoint pjp) {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        pjp.getArgs();
        methodSignature.getParameterNames();
        HttpServletRequest hsr = RequestHolder.getHttpServletRequest();

        UserAgent userAgent = UserAgentUtils.getUserAgent(hsr);

        // 设置系统
        log.setDevice(userAgent.getOperatingSystem().getName());
        // 设置浏览器
        log.setBrowser(userAgent.getBrowser().getName());
        // 设置请求类型
        log.setRequestType(hsr.getMethod());
        // 设置方法名
        log.setMethodName(methodSignature.getDeclaringTypeName()+ "." + methodSignature.getName());
        // 设置参数
        log.setParams(Arrays.toString(pjp.getArgs()));
        // 设置描述
        if (method.isAnnotationPresent(LogInfo.class)) {
            log.setDesc(method.getDeclaredAnnotation(LogInfo.class).value());
        }
        // 设置访问url
        log.setUrl(hsr.getRequestURI());
        // 设置访问ip
        log.setAddress(hsr.getRemoteAddr());

        log.setCreateTime(Instant.now());
        logger.debug("save log {}",log);
        return logRepository.save(log);
    }

    /**
     * 拦截器记录日志
     *
     * @param log
     * @param hsr
     * @return
     */
    @Override
    public Log save(Log log, HttpServletRequest hsr) {

        UserAgent userAgent = UserAgentUtils.getUserAgent(hsr);

        // 设置系统
        log.setDevice(userAgent.getOperatingSystem().getName());
        // 设置浏览器
        log.setBrowser(userAgent.getBrowser().getName());
        // 设置请求类型
        log.setRequestType(hsr.getMethod());
        // 设置参数
//        log.setParams(hsr.getpa);
        // 设置访问url
        log.setUrl(hsr.getRequestURI());
        // 设置访问ip
        log.setAddress(hsr.getRemoteAddr());

        log.setCreateTime(Instant.now());
        logger.debug("save log {}",log);
        return logRepository.save(log);
    }

    @Override
    public Page<Log> getInfoLog(Pageable pageable) {

        return logRepository.findAllByType(LogType.INFO,pageable);
    }

    @Override
    public Page<Log> getErrorLog(Pageable pageable) {
        return logRepository.findAllByType(LogType.ERROR,pageable);
    }
}
