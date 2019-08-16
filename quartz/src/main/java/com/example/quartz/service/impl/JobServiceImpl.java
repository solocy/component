package com.example.quartz.service.impl;

import com.example.quartz.common.CronTag;
import com.example.quartz.domain.Job;
import com.example.quartz.domain.enumeration.ScheduleStatus;
import com.example.quartz.quartz.ScheduleUtils;
import com.example.quartz.repostitory.JobRepostitory;
import com.example.quartz.service.JobService;
import com.example.quartz.task.TestTask;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Method;
import java.util.*;

@Service
@Transactional
public class JobServiceImpl implements JobService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private JobRepostitory jobRepostitory;

    @Override
    public Optional<Job> findJob(Long jobId) {
        log.debug("find job by id :{}",jobId);
        return jobRepostitory.findById(jobId);
    }

    @Override
    public List<Job> findAllJobs() {
        log.debug("find all job");
        return jobRepostitory.findAll();
    }

    @Override
    public List<Job> findAllJobs(Job job) {
        return null;
    }

    @Override
    public Job addJob(Job job) {
        log.debug("save job {}",job);
        job.setCreateTime(new Date());
        job.setStatus(ScheduleStatus.PAUSE);
        job = jobRepostitory.save(job);
        ScheduleUtils.createScheduleJob(scheduler,job);
        return job;
    }

    @Override
    public Job update(Job job) {
        log.debug("save job {}",job);
        ScheduleUtils.updateScheduleJob(scheduler,job);
        return jobRepostitory.save(job);
    }

    @Override
    public void delete(Long id) {
        log.debug("delete job by id {}",id);
        ScheduleUtils.deleteScheduleJob(scheduler,id);
        jobRepostitory.deleteById(id);
    }

    @Override
    public void run(Long  id) {
        log.debug("run job by id {}",id);
        Optional<Job> optionalJob = findJob(id);
        optionalJob.ifPresent(job -> ScheduleUtils.run(scheduler, job));
    }

    @Override
    public void pause(Long id) {
        log.debug("pause job by id {}",id);
        ScheduleUtils.pauseJob(scheduler,id);
        jobRepostitory.updateJob(id, ScheduleStatus.PAUSE);
    }

    @Override
    public void resume(Long id) {
        log.debug("resume job by id {}",id);
        ScheduleUtils.resumeJob(scheduler,id);
        jobRepostitory.updateJob(id,ScheduleStatus.NORMAL);
    }

    /**
     * 获取TestTasK 类中有注解CronTag 的方法名和注解值
     *
     * @return
     */
    @Override
    public List<Map<String, String>> getSysCronClazz() {
        Class cls = TestTask.class;
        List<Map<String,String>> result = new LinkedList<>();
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            boolean b = method.isAnnotationPresent(CronTag.class);
            if (!b) {
                continue;
            }
            Map<String,String> map = new HashMap<>();
            CronTag cronTag = method.getAnnotation(CronTag.class);
            map.put("method",method.getName());
            map.put("desc",cronTag.value());
            result.add(map);
        }
        return result;
    }
}
