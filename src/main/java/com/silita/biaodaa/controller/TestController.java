package com.silita.biaodaa.controller;

import com.google.common.collect.ImmutableMap;
import com.silita.biaodaa.disruptor.DisruptorOperator;
import com.silita.biaodaa.model.TUser;
import com.silita.biaodaa.service.TestService;
import com.silita.biaodaa.task.TestTask;
import com.snatch.model.EsNotice;
import com.snatch.model.Notice;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Created by zhangxiahui on 17/5/26.
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    TestService testService;

    @Autowired
    DisruptorOperator disruptorOperator;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    TestTask testTask;


    private Lock lock = new ReentrantLock();//基于底层IO阻塞考虑

    private static final Logger logger = Logger.getLogger(TestController.class);




    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Map<String, Object> getTestID(@PathVariable String id) {
        TUser user = testService.getTestName(id);
        if(user==null){
            user = new TUser();
        }
        return new ImmutableMap.Builder<String, Object>().put("status", 1)
                .put("msg", "成功").put("data",user).build();
    }

    @ResponseBody
    @RequestMapping(value = "/testTask", method = RequestMethod.GET)
    public Map<String, Object> testTask() {
        Notice notice = null ;
        try {
            lock.lockInterruptibly();
            try{
                notice = (Notice) redisTemplate.opsForList().leftPop("liuqi",0, TimeUnit.SECONDS);
                EsNotice esNotice = testTask.noticeToEsNotice(notice);
                disruptorOperator.publish(esNotice);
            }finally{
                lock.unlock();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ImmutableMap.Builder<String, Object>().put("status", 1)
                .put("msg", "成功").put("data",notice).build();
    }


    @ResponseBody
    @RequestMapping(value = "/pushRedis", method = RequestMethod.GET)
    public Map<String, Object> pushRedis() {
        try {
            testService.pushRedisNotice();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ImmutableMap.Builder<String, Object>().put("status", 1)
                .put("msg", "push到Redis成功!").build();
    }

    @ResponseBody
    @RequestMapping(value = "/pushCustomRedis", method = RequestMethod.GET)
    public Map<String, Object> pushCustomRedis(String tbName) {
        try {
            int total = testService.pushCustomRedisNotice(tbName);
            return new ImmutableMap.Builder<String, Object>().put("status", 1)
                    .put("msg", "custom push到Redis成功!").put("successCount",total).build();
        } catch (Exception e) {
            e.printStackTrace();
            return new ImmutableMap.Builder<String, Object>().put("status", 0)
                    .put("msg", e.getMessage()).build();
        }

    }


}
