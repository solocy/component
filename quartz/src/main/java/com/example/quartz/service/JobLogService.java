package com.example.quartz.service;


import com.example.quartz.domain.JobLog;

import java.util.List;

public interface JobLogService{

	List<JobLog> findAllJobLogs();

	JobLog saveJobLog(JobLog log);

	void delete(Long id);
}
