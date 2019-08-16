package com.example.quartz.service.impl;

import com.example.quartz.domain.JobLog;
import com.example.quartz.repostitory.JobLogRepostitory;
import com.example.quartz.service.JobLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("jobLogService")
public class JobLogServiceImpl implements JobLogService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
    private JobLogRepostitory jobLogRepostitory;

    @Override
    public List<JobLog> findAllJobLogs() {
        log.debug("find all job log");
        return jobLogRepostitory.findAll();
    }

    @Override
    public JobLog saveJobLog(JobLog jobLog) {
        log.debug("save job log {}",jobLog);
        return jobLogRepostitory.save(jobLog);

    }

    @Override
    public void delete(Long id) {
        log.debug("delete job log by id {}",id);
        jobLogRepostitory.deleteById(id);

    }
}
