package com.example.quartz.task;

import com.example.quartz.common.CronTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("testTask")
public class TestTask {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @CronTag(value = "测试")
    public void test() {
        log.info("test is run ");
    }
}
