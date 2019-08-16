package com.example.log.common.interceptor;

import com.example.log.common.utils.CommonUtil;
import com.example.log.domain.Log;
import com.example.log.domain.enumeration.LogType;
import com.example.log.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;


/**
 * 利用拦截器记录访问日志
 *
 */
@Component
public class InterceptorConfig implements HandlerInterceptor {

    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Autowired
    private LogService logService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        startTime = ThreadLocal.withInitial(System::currentTimeMillis);
        System.out.println("start request -------------------------------------");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        Long time = System.currentTimeMillis() - startTime.get();
        Log log;
        if (Objects.nonNull(ex)) {
            log = new Log(LogType.ERROR,time);
            log.setExceptionDetail(CommonUtil.throwToString(ex));
        } else {
            log = new Log(LogType.INFO,time);
        }
        logService.save(log,request);
        startTime.remove();
    }
}
