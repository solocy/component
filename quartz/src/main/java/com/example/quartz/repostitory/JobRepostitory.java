package com.example.quartz.repostitory;

import com.example.quartz.domain.Job;
import com.example.quartz.domain.enumeration.ScheduleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepostitory extends JpaRepository<Job,Long> {

    @Modifying
    @Query("update Job j set j.status = ?2 where j.jobId = ?1")
    void updateJob(Long id, ScheduleStatus status);
}
