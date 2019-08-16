package com.example.quartz.quartz;

import com.example.quartz.common.SpringContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 执行定时任务
 * 
 * @author MrBird
 *
 */
public class ScheduleRunnable implements Runnable {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	private Object target;
	private Method method;
	private String params;

	ScheduleRunnable(String beanName, String methodName, String params)
			throws NoSuchMethodException, SecurityException {
		this.target = SpringContextUtils.getBean(beanName);
		this.params = params;

		if (Objects.nonNull(params) && !params.isEmpty()) {
			this.method = target.getClass().getDeclaredMethod(methodName, String.class);
		} else {
			this.method = target.getClass().getDeclaredMethod(methodName);
		}
	}

	@Override
	public void run() {
		try {
		    // 利用反射执行方法
			ReflectionUtils.makeAccessible(method);
            if (Objects.nonNull(params) && !params.isEmpty()) {
				method.invoke(target, params);
			} else {
				method.invoke(target);
			}
		} catch (Exception e) {
			log.error("执行定时任务失败",e);
		}
	}

}
