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
 * 项目金额
 */
@Component
public class ApplyProjSumHandler extends BaseHandler {

    Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @Autowired
    NoticeAnalyzeService noticeAnalyzeService;



    @Override
    public void onEvent(AnalyzeEvent event, long sequence, boolean endOfBatch) throws Exception {
        //必须新建一个对象，否则会导致一个对象被多出引用，值备覆盖
        String s2 = "";
        AnalyzeDetail analyzeDetail = event.getAnalyzeDetail();
        Notice notice = event.getNotice();
        try {
            s2 = noticeAnalyzeService.analyzeApplyProjSum(notice.getContent());
            logger.info("===解析["+notice.getTitle()+"]的项目金额["+s2+"]===");
            if (!"".equals(s2) && null != s2 && !Constant.DEFAULT_STRING.equals(s2) && analyzeDetail.getProjSum() == null) {
                analyzeDetail.setProjSum(s2);
                //System.out.println("f2-项目金额:" + s2);
            }
        } catch (Exception e) {
            System.out.println("error--s2");
            logger.error("error--s2" + e, e);
        }
    }
}
