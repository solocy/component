package com.example.quartz.repostitory;

import com.example.quartz.domain.JobLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobLogRepostitory extends JpaRepository<JobLog,Long> {
}
