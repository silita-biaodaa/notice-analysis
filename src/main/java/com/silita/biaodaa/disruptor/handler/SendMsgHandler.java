package com.silita.biaodaa.disruptor.handler;

import com.lmax.disruptor.EventHandler;
import com.silita.biaodaa.common.kafka.KafkaProducerUtil;
import com.silita.biaodaa.disruptor.event.AnalyzeEvent;
import com.snatch.model.EsNotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消息发送到清洗模块
 */
@Component
public class SendMsgHandler implements EventHandler<AnalyzeEvent> {

    @Autowired
    KafkaProducerUtil kafkaProducerUtil;

    @Override
    public void onEvent(AnalyzeEvent event, long sequence, boolean endOfBatch) throws Exception {
        EsNotice esNotice = event.getEsNotice();
        try {
            kafkaProducerUtil.sendkafkaMsg(esNotice, null);//send kafka msg
        }finally {
            esNotice=null;
            event=null;
        }

    }


}
