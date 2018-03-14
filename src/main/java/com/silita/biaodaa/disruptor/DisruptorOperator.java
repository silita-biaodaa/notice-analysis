package com.silita.biaodaa.disruptor;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.silita.biaodaa.disruptor.handler.zhaoBiao.ApplyDateHandler;
import com.silita.biaodaa.disruptor.handler.zhaoBiao.ApplyProjSumHandler;
import com.silita.biaodaa.disruptor.handler.zhaoBiao.InsertAnalyzeDetailHandler;
import com.silita.biaodaa.disruptor.handler.zhaoBiao.TbAssureSumHandler;
import com.snatch.model.AnalyzeDetail;
import com.snatch.model.Notice;
import com.silita.biaodaa.disruptor.event.AnalyzeEvent;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Author: caigang
 * Time: 2015/8/12 16:39
 * Remark: disruptor相关操作
 */
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



    private static EventTranslatorOneArg<AnalyzeEvent,Notice> eventTranslator = new EventTranslatorOneArg<AnalyzeEvent,Notice>() {
        @Override
        public void translateTo(AnalyzeEvent event, long sequence, Notice notice) {
            event.setNotice(notice);
            event.setAnalyzeDetail(new AnalyzeDetail());

        }
    };

    /**
     * 初始化disruptor
     */
    public void init() {
        ZhaoBiaoDisruptorCreator.initDisruptor(tbAssureSumHandler,applyProjSumHandler,applyDateHandler,insertAnalyzeDetailHandler);
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
     * @param notice
     */
    public void publish(Notice notice) {
        ZhaoBiaoDisruptorCreator.getProcessDisruptor().publishEvent(eventTranslator,notice);
    }

}
