package com.silita.biaodaa.disruptor.handler.zhaoBiao;

import com.lmax.disruptor.EventHandler;
import com.silita.biaodaa.common.Constant;
import com.silita.biaodaa.common.kafka.KafkaProducerUtil;
import com.silita.biaodaa.disruptor.event.AnalyzeEvent;
import com.silita.biaodaa.service.NoticeAnalyzeService;
import com.snatch.model.AnalyzeDetail;
import com.snatch.model.EsNotice;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangxiahui on 18/3/14.
 */
@Component
public class InsertAnalyzeDetailHandler implements EventHandler<AnalyzeEvent> {

    Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @Autowired
    NoticeAnalyzeService noticeAnalyzeService;

    @Autowired
    KafkaProducerUtil kafkaProducerUtil;

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
            logger.error("招標公告入库" + e, e);
        }finally {
            esNotice.setDetail(analyzeDetail);
            sendkafkaMsg(esNotice);//send kafka msg
        }
    }

    public void sendkafkaMsg(EsNotice es){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("deliveryId", es.getRedisId());
        map.put("model", es);
        map.put("start", Constant.NOTICE_START);
        kafkaProducerUtil.sendMsg(map);//kafka消息发送
        logger.info("kafka发送消息finished。title:" + es.getTitle() + "##Opendate:" + es.getOpenDate() + "##catchtype-->type:" + es.getType() + "##BusinessType:" + es.getBusinessType());
    }
}
