package com.silita.biaodaa.service;

import com.mishu.model.Notice;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>Created by mayongbin01 on 2017/3/9.</p>
 */
public class RedisTest extends ConfigTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void getAndPutTest() {
        //redisTemplate.opsForHash().put("user", "age", "20");
        Object object = redisTemplate.opsForHash().get("user", "age");
        System.out.println(object);
    }



    @Test
    public void getNotice(){
        byte[] rawKey = redisTemplate.getKeySerializer().serialize("maofeng");
        RedisConnection connection = RedisConnectionUtils.getConnection(redisTemplate.getConnectionFactory());
        List<byte[]> results = connection.bLPop(0, rawKey);
        if(CollectionUtils.isEmpty(results)){
        }
        Notice notice =  (Notice)redisTemplate.getValueSerializer().deserialize(results.get(1));
        System.out.println(notice);
    }

    @Test
    public void test(){
        Notice notice = (Notice) redisTemplate.opsForList().leftPop("maofeng",5, TimeUnit.SECONDS);
        System.out.println(notice);
    }


}