package com.silita.biaodaa.service;

import com.silita.biaodaa.disruptor.DisruptorOperator;
import com.silita.biaodaa.task.TestTask;
import com.snatch.model.EsNotice;
import com.snatch.model.Notice;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhangxiahui on 18/3/20.
 */
public class NoticeRedisTest extends ConfigTest  {


    @Autowired
    TestService testService;

    @Autowired
    DisruptorOperator disruptorOperator;

    @Autowired
    TestTask testTask;

    @Autowired
    RedisTemplate redisTemplate;



    @Test
    public void pushRedisNotice(){
        testService.pushRedisNotice();
    }


    @Test
    public void analyzeHandler(){
        disruptorOperator.init();
        disruptorOperator.start();
        Notice notice = null ;
        while (true){
            try {
                try{
                    notice = (Notice) redisTemplate.opsForList().leftPop("liuqi",0, TimeUnit.SECONDS);
                    EsNotice esNotice = testTask.noticeToEsNotice(notice);
                    disruptorOperator.publish(esNotice);
                }finally{
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
