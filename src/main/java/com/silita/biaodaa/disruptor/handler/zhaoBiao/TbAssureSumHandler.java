package com.silita.biaodaa.disruptor.handler.zhaoBiao;

import com.snatch.model.AnalyzeDetail;
import com.snatch.model.Notice;
import com.silita.biaodaa.common.Constant;
import com.silita.biaodaa.disruptor.event.AnalyzeEvent;
import com.silita.biaodaa.disruptor.handler.BaseHandler;
import com.silita.biaodaa.service.NoticeAnalyzeService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zhangxiahui on 18/3/13.
 * 保证金
 */
@Component
public class TbAssureSumHandler extends BaseHandler {

    Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @Autowired
    NoticeAnalyzeService noticeAnalyzeService;


    @Override
    public void onEvent(AnalyzeEvent event, long sequence, boolean endOfBatch) throws Exception {
        //必须新建一个对象，否则会导致一个对象被多出引用，值备覆盖
        String s1 = "";
        AnalyzeDetail analyzeDetail = event.getAnalyzeDetail();
        Notice notice = event.getNotice();
        try {
            s1 = noticeAnalyzeService.analyzeApplyDeposit(notice.getContent());
            logger.info("===解析["+notice.getTitle()+"]的保证金["+s1+"]===");
            if (!"".equals(s1) && null != s1 && !Constant.DEFAULT_STRING.equals(s1) && analyzeDetail.getTbAssureSum() == null) {
                analyzeDetail.setTbAssureSum(s1);
            }
        } catch (Exception e) {
            logger.error("error--s1" + e, e);
        }
    }
}
