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

import java.util.List;

/**
 * Created by zhangxiahui on 18/3/13.
 */
@Component
public class ApplyDateHandler extends BaseHandler {

    Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @Autowired
    NoticeAnalyzeService noticeAnalyzeService;



    @Override
    public void onEvent(AnalyzeEvent event, long sequence, boolean endOfBatch) throws Exception {
        List<String> li = null;
        AnalyzeDetail ad = event.getAnalyzeDetail();
        Notice notice = event.getNotice();
        try {
            li = noticeAnalyzeService.analyzeApplyDate(notice.getContent());
            if (li != null && li.size() > 1) {
                logger.info("===解析["+notice.getTitle()+"]的报名时间["+li.get(0)+"]["+li.get(1)+"]===");
                if (!"".equals(li.get(0)) && null != li.get(0)
                        && !Constant.DEFAULT_STRING.equals(li.get(0)) && ad.getBmStartDate() == null) {
                    ad.setBmStartDate(li.get(0));//报名时间
                }
                if (!"".equals(li.get(1)) && null != li.get(1)
                        && !Constant.DEFAULT_STRING.equals(li.get(1)) && ad.getBmEndDate() == null) {
                    ad.setBmEndDate(li.get(1));//报名截止时间
                }
                //########报名结束时间点
                String bmEndTime = noticeAnalyzeService.analyzeApplyTime(notice.getContent());
                if (!"".equals(bmEndTime) && null != bmEndTime && !Constant.DEFAULT_STRING.equals(bmEndTime) && ad.getBmEndTime() == null) {
                    ad.setBmEndTime(bmEndTime);
                }
            }else{
                logger.info("===解析["+notice.getTitle()+"]的报名时间[]===");
            }
        } catch (Exception e) {
            System.out.println("error--li" + e.getMessage());
            logger.error("error--li" + e, e);
        }
    }
}
