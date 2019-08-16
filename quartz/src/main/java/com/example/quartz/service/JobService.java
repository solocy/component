package com.example.quartz.service;

import com.example.quartz.domain.Job;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface JobService {

    Optional<Job> findJob(Long jobId);

    List<Job> findAllJobs();

    List<Job> findAllJobs(Job job);

    Job addJob(Job job);

    Job update(Job job);

    void delete(Long id);

    void run(Long id);

    void pause(Long id);

    void resume(Long id);

    List<Map<String, String>> getSysCronClazz();
}
