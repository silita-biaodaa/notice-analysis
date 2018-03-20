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
        EsNotice esNotice = event.getEsNotice();
        AnalyzeDetail analyzeDetail = esNotice.getDetail();
        if(MyStringUtils.isNull(analyzeDetail.getTbAssureSum())) {
            try {
                String s1 = noticeAnalyzeService.analyzeApplyDeposit(esNotice.getContent());
                logger.info("===解析[" + esNotice.getTitle() + "]的保证金[" + s1 + "]===");
                if (!"".equals(s1) && null != s1 && !Constant.DEFAULT_STRING.equals(s1) && analyzeDetail.getTbAssureSum() == null) {
                    analyzeDetail.setTbAssureSum(s1);
                }
            } catch (Exception e) {
                logger.error("error--s1" + e, e);
            }
        }
    }

    @Override
    protected Object currentFieldValues(EsNotice esNotice) {
        return null;
    }

    @Override
    protected Object executeAnalysis(String stringPart) {
        return null;
    }

    @Override
    protected void saveResult(EsNotice esNotice, Object analysisResult) {

    }
}
