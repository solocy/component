package com.example.log.repository;

import com.example.log.domain.Log;
import com.example.log.domain.enumeration.LogType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log,Long> {

    Page<Log> findAllByType(LogType logType, Pageable pageable);
}
