package com.silita.biaodaa.disruptor;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.silita.biaodaa.disruptor.handler.zhaoBiao.*;
import com.snatch.model.AnalyzeDetail;
import com.snatch.model.Notice;
import com.silita.biaodaa.disruptor.event.AnalyzeEvent;
import com.silita.biaodaa.disruptor.handler.zhaoBiao.*;
import com.snatch.model.EsNotice;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DisruptorOperator {

    Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @Autowired
    TbAssureSumHandler tbAssureSumHandler;

    @Autowired
    ApplyProjSumHandler applyProjSumHandler;

    @Autowired
    ApplyDateHandler applyDateHandler;

    @Autowired
    InsertAnalyzeDetailHandler insertAnalyzeDetailHandler;

    @Autowired
    AssureSumRemitHandler assureSumRemitHandler;

    @Autowired
    ApplyAddressHandler applyAddressHandler;



    private static EventTranslatorOneArg<AnalyzeEvent,EsNotice> eventTranslator = new EventTranslatorOneArg<AnalyzeEvent,EsNotice>() {
        @Override
        public void translateTo(AnalyzeEvent event, long sequence, EsNotice esNotice) {
            event.setEsNotice(esNotice);
        }
    };

    /**
     * 初始化disruptor
     */
    public void init() {
        ZhaoBiaoDisruptorCreator.initDisruptor(tbAssureSumHandler,applyProjSumHandler,applyDateHandler,assureSumRemitHandler,insertAnalyzeDetailHandler);
    }

    /**
     * 启动disruptor
     */
    public void start() {
        ZhaoBiaoDisruptorCreator.getProcessDisruptor().start();
        logger.info("disruptor start success");
    }

    /**
     * 关闭disruptor
     */
    public void shutdown() {
        ZhaoBiaoDisruptorCreator.shutdownDisruptor();
        logger.info("disruptor shutdown");
    }

    /**
     * 提交数据让disruptor处理
     * @param esNotice
     */
    public void publish(EsNotice esNotice) {
        ZhaoBiaoDisruptorCreator.getProcessDisruptor().publishEvent(eventTranslator,esNotice);
    }

}
