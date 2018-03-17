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
 * 项目金额
 */
@Component
public class ApplyProjSumHandler extends BaseHandler {

    Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @Autowired
    NoticeAnalyzeService noticeAnalyzeService;



    @Override
    public void onEvent(AnalyzeEvent event, long sequence, boolean endOfBatch) throws Exception {
        EsNotice esNotice = event.getEsNotice();
        AnalyzeDetail analyzeDetail = esNotice.getDetail();
        if(MyStringUtils.isNull(analyzeDetail.getProjSum())) {
            try {
                String s2 = noticeAnalyzeService.analyzeApplyProjSum(esNotice.getContent());
                logger.info("===解析[" + esNotice.getTitle() + "]的项目金额[" + s2 + "]===");
                if (!"".equals(s2) && null != s2 && !Constant.DEFAULT_STRING.equals(s2) && analyzeDetail.getProjSum() == null) {
                    analyzeDetail.setProjSum(s2);
                }
            } catch (Exception e) {
                logger.error("error--s2" + e, e);
            }
        }
    }
}
