package com.example.securitydemo.security.image.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class CodeService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void saveCode(String uuid,String code) {
        redisTemplate.opsForValue().set(uuid,code,1, TimeUnit.MINUTES);
    }

    public String getCode(String uuid) {
        return redisTemplate.opsForValue().get(uuid);
    }
}
