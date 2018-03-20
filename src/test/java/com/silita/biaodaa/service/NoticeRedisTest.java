package com.silita.biaodaa.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zhangxiahui on 18/3/20.
 */
public class NoticeRedisTest extends ConfigTest  {


    @Autowired
    TestService testService;





    @Test
    public void pushRedisNotice(){
        testService.pushRedisNotice();
    }
}
