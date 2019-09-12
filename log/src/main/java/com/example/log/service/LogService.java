package com.example.log.service;

import com.example.log.domain.Log;
import org.aspectj.lang.JoinPoint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface LogService {

    Log save(Log log, JoinPoint pjp);

    Log save(Log log, HttpServletRequest hsr);

    Page<Log> getInfoLog(Pageable pageable);
    Page<Log> getErrorLog(Pageable pageable);
}

