package com.silita.biaodaa.dao;

import com.silita.biaodaa.model.TUser;
import com.snatch.model.Notice;

import java.util.List;

/**
 * Created by zhangxiahui on 18/3/13.
 */
public interface TestMapper {
    TUser getTestName(String Id);

    List<Notice> getNoticeToRedis();

    List<Notice> getHunanNoticeToRedis(int start,int end);

    List<Notice> pushCustomRedisNotice(int start,int end,String tbName,String title);
}
