package com.example.quartz.common;

import java.lang.annotation.*;

/**
 * 定时方法标示
 *
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CronTag {

    /**
     * 方法使用
     * @return
     */
    String value() default "";
}