package com.example.quartz.web.rest;


import com.example.quartz.domain.Job;
import com.example.quartz.service.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class JobResource {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JobService jobService;


    @PostMapping("/jobs")
    public Job createJob(@RequestBody Job job) {
        log.debug("REST request to create job {}",job);
        return jobService.addJob(job);
    }

    @GetMapping("/jobs/{jobId}")
    public Optional<Job> findJob(@PathVariable Long jobId) {
        log.debug("REST request to get job by id {}",jobId);
        return jobService.findJob(jobId);
    }

    @GetMapping("/jobs")
    List<Job> findAllJobs() {
        log.debug("REST request to get all job");
        return jobService.findAllJobs();
    }

    @PutMapping("/jobs")
    public Job update(Job job) {
        log.debug("REST request to update job by job {}",job);
        return jobService.update(job);
    }

    @DeleteMapping("/jobs/{id}")
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete job by id {}",id);
        jobService.delete(id);
    }

    @GetMapping("jobs/run/{id}")
    public void run(@PathVariable Long id) {
        log.debug("REST request to run job by id {}",id);
        jobService.run(id);
    }

    @GetMapping("jobs/pause/{id}")
    public void pause(@PathVariable Long id) {
        log.debug("REST request to pause job by id {}",id);
        jobService.pause(id);
    }

    @GetMapping("jobs/resume/{id}")
    public void resume(@PathVariable Long id) {
        log.debug("REST request to resume job by id {}",id);
        jobService.resume(id);
    }


    @GetMapping("jobs/clazz")
    public List<Map<String, String>> getSysCronClazz() {
        log.debug("REST request to get sysCromclass");
        return jobService.getSysCronClazz();
    }
}
