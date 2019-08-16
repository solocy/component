package com.example.log.rest;

import com.example.log.common.annotation.LogInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/aspect")
public class TestAspectResource {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @LogInfo("测试")
    @GetMapping("/test")
    public String test() {
        log.debug("REST request to test");
        throw new RuntimeException();
//        return "success";
    }

    @LogInfo("测试2")
    @GetMapping("/test/{id}")
    public String test(@PathVariable String id) {
        log.debug("REST request to test");
        throw new RuntimeException();
//        return "success";
    }
}
