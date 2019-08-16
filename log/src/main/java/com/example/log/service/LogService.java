package com.example.log.service;

import com.example.log.domain.Log;
import org.aspectj.lang.JoinPoint;

import javax.servlet.http.HttpServletRequest;

public interface LogService {

    Log save(Log log, JoinPoint pjp);

    Log save(Log log, HttpServletRequest hsr);
}

