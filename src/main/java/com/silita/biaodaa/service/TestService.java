package com.silita.biaodaa.service;

import com.silita.biaodaa.dao.TestMapper;
import com.silita.biaodaa.model.TUser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zhangxiahui on 18/3/13.
 */
@Component
public class TestService {

    @Autowired
    TestMapper testMapper;

    Logger logger = Logger.getLogger(TestService.class);

    public TUser getTestName(String id){
        return testMapper.getTestName(id);
    }




}
