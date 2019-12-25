package com.example.quartz.config;

import com.example.quartz.quartz.ScheduleUtils;
import com.example.quartz.service.JobService;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author videon
 * @date 2019/7/5 1:42 PM
 */
@Component
public class InitProject implements ApplicationRunner {
    private final Logger log = LoggerFactory.getLogger(InitProject.class);

    @Autowired
    private JobService jobService;

    @Autowired
    private Scheduler scheduler;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.debug("初始化定时任务");
        jobService.findAllJobs().forEach(jobDTO -> {
            log.debug("job:{}",jobDTO);
            ScheduleUtils.createScheduleJob(scheduler, jobDTO);
        });

    }
}
