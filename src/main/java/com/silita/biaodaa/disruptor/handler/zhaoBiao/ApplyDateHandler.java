package com.silita.biaodaa.disruptor.handler.zhaoBiao;

import com.silita.biaodaa.common.Constant;
import com.silita.biaodaa.disruptor.event.AnalyzeEvent;
import com.silita.biaodaa.disruptor.handler.BaseHandler;
import com.silita.biaodaa.service.NoticeAnalyzeService;
import com.silita.biaodaa.utils.MyStringUtils;
import com.snatch.model.AnalyzeDetail;
import com.snatch.model.EsNotice;
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
        EsNotice esNotice = event.getEsNotice();
        AnalyzeDetail ad = esNotice.getDetail();
        if(MyStringUtils.isNull(ad.getBmStartDate())) {
            try {
                List<String> li = noticeAnalyzeService.analyzeApplyDate(esNotice.getContent());
                if (li != null && li.size() > 1) {
                    logger.info("===解析[" + esNotice.getTitle() + "]的报名时间[" + li.get(0) + "][" + li.get(1) + "]===");
                    if (!"".equals(li.get(0)) && null != li.get(0)
                            && !Constant.DEFAULT_STRING.equals(li.get(0)) && ad.getBmStartDate() == null) {
                        ad.setBmStartDate(li.get(0));//报名时间
                    }
                    if (!"".equals(li.get(1)) && null != li.get(1)
                            && !Constant.DEFAULT_STRING.equals(li.get(1)) && ad.getBmEndDate() == null) {
                        ad.setBmEndDate(li.get(1));//报名截止时间
                    }
                    //########报名结束时间点
                    String bmEndTime = noticeAnalyzeService.analyzeApplyTime(esNotice.getContent());
                    if (!"".equals(bmEndTime) && null != bmEndTime && !Constant.DEFAULT_STRING.equals(bmEndTime) && ad.getBmEndTime() == null) {
                        ad.setBmEndTime(bmEndTime);
                    }
                } else {
                    logger.info("===解析[" + esNotice.getTitle() + "]的报名时间[]===");
                }
            } catch (Exception e) {
                logger.error("error--li" + e, e);
            }
        }

    }

    @Override
    protected Object currentFieldValues(EsNotice esNotice) {
        return null;
    }

    @Override
    protected String executeAnalysis(String stringPart) {
        //TODO：具体逻辑代码负责人实现
        return null;
    }

    @Override
    protected void saveResult(EsNotice esNotice, Object analysisResult) {
        //TODO：具体逻辑代码负责人实现
    }
}
