package com.self.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class RedisController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/redis/get")
    public String get(@RequestParam String key){
        return redisTemplate.opsForValue().get(key, 100, 100);
    }

    @PostMapping("/redis/set")
    public void set(@RequestParam String key, @RequestParam String value){
        redisTemplate.opsForValue().set(key, value);
    }
}
