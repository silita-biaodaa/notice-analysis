package com.silita.biaodaa.disruptor.handler.zhongBiao;

import com.lmax.disruptor.EventHandler;
import com.silita.biaodaa.common.kafka.KafkaProducerUtil;
import com.silita.biaodaa.disruptor.event.AnalyzeEvent;
import com.silita.biaodaa.service.NoticeAnalyzeService;
import com.snatch.model.AnalyzeDetailZhongBiao;
import com.snatch.model.EsNotice;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by hujia on 18/3/20.
 */
@Component
public class InsertAnalyzeDetailZhongBiaoHandler implements EventHandler<AnalyzeEvent> {

    Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @Autowired
    NoticeAnalyzeService noticeAnalyzeService;

    @Autowired
    KafkaProducerUtil kafkaProducerUtil;

    @Override
    public void onEvent(AnalyzeEvent event, long sequence, boolean endOfBatch) throws Exception {
        AnalyzeDetailZhongBiao analyzeDetailZhongBiao = event.getEsNotice().getDetailZhongBiao();
        EsNotice esNotice = event.getEsNotice();
        try {
            analyzeDetailZhongBiao.setRedisId(esNotice.getRedisId());
            analyzeDetailZhongBiao.setNoticeUrl(esNotice.getUrl());
            analyzeDetailZhongBiao.setTitle(esNotice.getTitle());
            noticeAnalyzeService.insertAnalyzeDetailZhongbiao(analyzeDetailZhongBiao);
        } catch (Exception e) {
            logger.error("入库" + e, e);
        }finally {
            esNotice.setDetailZhongBiao(analyzeDetailZhongBiao);
            kafkaProducerUtil.sendkafkaMsg(esNotice);//send kafka msg
        }
    }



}
