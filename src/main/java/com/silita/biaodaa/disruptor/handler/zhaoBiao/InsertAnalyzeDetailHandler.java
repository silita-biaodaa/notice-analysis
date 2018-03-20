package com.silita.biaodaa.disruptor.handler.zhaoBiao;

import com.lmax.disruptor.EventHandler;
import com.silita.biaodaa.disruptor.event.AnalyzeEvent;
import com.silita.biaodaa.service.NoticeAnalyzeService;
import com.snatch.model.AnalyzeDetail;
import com.snatch.model.EsNotice;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zhangxiahui on 18/3/14.
 */
@Component
public class InsertAnalyzeDetailHandler implements EventHandler<AnalyzeEvent> {

    Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @Autowired
    NoticeAnalyzeService noticeAnalyzeService;

    @Override
    public void onEvent(AnalyzeEvent event, long sequence, boolean endOfBatch) throws Exception {
        EsNotice esNotice = event.getEsNotice();
        AnalyzeDetail analyzeDetail = esNotice.getDetail();
        try {
            analyzeDetail.setRedisId(esNotice.getRedisId());
            analyzeDetail.setNoticeUrl(esNotice.getUrl());
            analyzeDetail.setTitle(esNotice.getTitle());
            noticeAnalyzeService.insertAnalyzeDetail(analyzeDetail);
        } catch (Exception e) {
            logger.error("入库" + e, e);
        }
    }
}
