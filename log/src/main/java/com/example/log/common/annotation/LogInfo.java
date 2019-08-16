package com.example.log.common.annotation;


import java.lang.annotation.*;


/**
 * 日志记录标示
 */
@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface LogInfo {

    /**
     *  日志记录的消息
     *
     * @return
     */
    String value() default "";
}
