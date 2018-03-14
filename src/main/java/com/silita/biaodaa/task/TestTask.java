package com.silita.biaodaa.task;

import com.snatch.model.Notice;
import com.silita.biaodaa.disruptor.DisruptorOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhangxiahui on 18/3/13.
 */
@Component
public class TestTask implements Runnable {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    DisruptorOperator disruptorOperator;

    @Autowired
    RedisTemplate redisTemplate;


    private Lock lock = new ReentrantLock();//基于底层IO阻塞考虑


    @Override
    public void run() {
        logger.info("=================定时任务测试执行=============");

        try {
            Notice notice= takeFromHead(0);
            disruptorOperator.publish(notice);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public Notice takeFromHead(int timeout) throws InterruptedException{
        lock.lockInterruptibly();
        try{
            return (Notice) redisTemplate.opsForList().leftPop("liuqi",timeout, TimeUnit.SECONDS);
        }finally{
            lock.unlock();
        }
    }




}
