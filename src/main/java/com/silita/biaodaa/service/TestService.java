package com.silita.biaodaa.service;

import com.silita.biaodaa.dao.TestMapper;
import com.silita.biaodaa.model.TUser;
import com.snatch.model.Notice;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by zhangxiahui on 18/3/13.
 */
@Component
public class TestService {

    @Autowired
    TestMapper testMapper;

    @Autowired
    RedisTemplate redisTemplate;

    Logger logger = Logger.getLogger(TestService.class);

    public TUser getTestName(String id){
        return testMapper.getTestName(id);
    }

    public void pushRedisNotice(){
        List<Notice> list = testMapper.getNoticeToRedis();
        for(Notice notice : list){
            redisTemplate.opsForList().leftPush("liuqi",notice);
        }
    }

    public void pushHunanRedisNotice(){
        int pageSize =100;
        int pageNum=0;
        int result=0;
        while(pageNum==0 || result>0) {
            int start =pageNum*pageSize;
            List<Notice> list = testMapper.getHunanNoticeToRedis(start, pageSize);
            if(list!=null && list.size()>0) {
                result = list.size();
                for (Notice notice : list) {
                    redisTemplate.opsForList().leftPush("liuqi", notice);
                }
            }else {
                result=0;
            }
            pageNum++;
        }
    }

    public int pushCustomRedisNotice(String tbName){
        int pageSize =10;
        int pageNum=0;
        int result=0;
        int totalCount=0;
        WeakReference<List<Notice>> list =null;
        while(pageNum==0 || result>0) {
            int start =pageNum*pageSize;
            list =new WeakReference(testMapper.pushCustomRedisNotice(start, pageSize,tbName));
            if(list.get()!=null && list.get().size()>0) {
                result = list.get().size();
                redisTemplate.opsForList().leftPushAll("liuqi",list.get());
                totalCount+=result;
                list.get().clear();
                list.clear();
            }else {
                result=0;
            }
            pageNum++;
        }
        if(list.get()!=null) {
            list.get().clear();
            list.clear();
        }

        System.gc();
        return totalCount;
    }

    public int pushCustomRedisSec(String tbName,int startNum,int tCount){
        int pageSize =100;
        int result=0;
        int totalCount=0;
        int runNum = startNum;
        WeakReference<List<Notice>> list =null;
        while((startNum==runNum || result>0) && totalCount<tCount) {
            list =new WeakReference(testMapper.pushCustomRedisNotice(runNum, pageSize,tbName));
            if(list.get()!=null && list.get().size()>0) {
                result = list.get().size();
//                for (Notice notice : list) {
//                    redisTemplate.opsForList().leftPush("liuqi", notice);
//                }
                redisTemplate.opsForList().leftPushAll("liuqi",list.get());
                totalCount+=result;
                list.get().clear();
                list.clear();
            }else {
                result=0;
            }
            runNum+=pageSize;
        }
        if(list.get()!=null) {
            list.get().clear();
            list.clear();
        }

        System.gc();
        return totalCount;
    }


}
