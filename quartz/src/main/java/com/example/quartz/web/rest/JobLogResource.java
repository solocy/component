package com.example.quartz.web.rest;

import com.example.quartz.domain.JobLog;
import com.example.quartz.service.JobLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class JobLogResource {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JobLogService jobLogService;

    @GetMapping("/joblogs")
    public List<JobLog> getAllJobLogs() {
        log.debug("REST request to get all job log");
        return jobLogService.findAllJobLogs();
    }

    @DeleteMapping("/joblogs/{id}")
    public void deleteJobLog(@PathVariable Long id) {
        log.debug("REST request to  delete job log by id {}",id);
        jobLogService.delete(id);
    }
}
