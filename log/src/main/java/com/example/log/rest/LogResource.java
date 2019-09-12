package com.example.log.rest;

import com.example.log.domain.Log;
import com.example.log.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LogResource {

    @Autowired
    private LogService logService;


    @GetMapping("/log/{type}")
    public Page<Log> getLog(@PathVariable String type, Pageable pageable) {

        if ("info".equals(type) ) {
            return logService.getInfoLog(pageable);
        } else {
            return logService.getErrorLog(pageable);
        }
    }

}
