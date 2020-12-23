package com.worhard.study.controller;

import com.worhard.study.config.rabbitmq.MsgProducer;
import com.worhard.study.config.redis.RedisUtil;
import com.worhard.study.dao.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author luowen
 * @description
 * @since 2020/12/23
 */
@RestController
public class StudyTestController {


    @Autowired
    private TestMapper testMapper;

    @GetMapping("/api/test")
    public Map<String,String>  test(){
        Map<String, String> map = new HashMap<>();
        map.put("key","value");
        map = testMapper.test1();
        return map;
    }


    @Autowired
    private RedisUtil redisUtil;
    @GetMapping("/api/reditest")
    public Map<String,String>  redistest(){
        Map<String, String> map = new HashMap<>();
        redisUtil.set("key","value");
        Object key = redisUtil.get("key");
        map.put("key",key.toString());
        return map;
    }

    @Autowired
    private MsgProducer msgProducer;
    @GetMapping("/api/rabbitmqtest")
    public void  rabbitmqtest(){
        msgProducer.send2FanoutTestQueue("123456465");
    }

}
