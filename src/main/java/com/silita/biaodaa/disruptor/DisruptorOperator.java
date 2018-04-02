package com.silita.biaodaa.disruptor;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.silita.biaodaa.common.SnatchContent;
import com.silita.biaodaa.disruptor.event.AnalyzeEvent;
import com.silita.biaodaa.disruptor.handler.zhaoBiao.*;
import com.silita.biaodaa.disruptor.handler.zhongBiao.*;
import com.snatch.model.EsNotice;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DisruptorOperator {

    Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @Autowired
    ApplyTbAssureSumHandler tbAssureSumHandler;

    @Autowired
    ApplyProjSumHandler applyProjSumHandler;

    @Autowired
    ApplyDateHandler applyDateHandler;

    @Autowired
    InsertAnalyzeDetailHandler insertAnalyzeDetailHandler;

    @Autowired
    ApplyAssureSumRemitHandler assureSumRemitHandler;

    @Autowired
    ApplyAddressHandler applyAddressHandler;

    @Autowired
    ApplyTbEndDateHandler applyTbEndDateHandler;

    @Autowired
    ApplyAssureEndDateHandler applyAssureEndDateHandler;

    @Autowired
    ApplyProjectTimeLimitHandler applyProjectTimeLimitHandler;

    @Autowired
    ApplyPbModeHandler applyPbModeHandler;


    @Autowired
    OneNameHandler oneNameHandler;

    @Autowired
    TwoNameHandler twoNameHandler;

    @Autowired
    ThreeNameHandler threeNameHandler;

    @Autowired
    ProjDutyHandler projDutyHandler;

    @Autowired
    InsertAnalyzeDetailZhongBiaoHandler insertAnalyzeDetailZhongBiaoHandler;



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
        ZhaoBiaoDisruptorCreator.initDisruptor(applyAddressHandler,applyProjSumHandler,applyDateHandler,insertAnalyzeDetailHandler);
        ZhongBiaoDisruptorCreator.initDisruptor(oneNameHandler,twoNameHandler,threeNameHandler,projDutyHandler,insertAnalyzeDetailZhongBiaoHandler);
    }

    /**
     * 启动disruptor
     */
    public void start() {
        ZhaoBiaoDisruptorCreator.getProcessDisruptor().start();
        logger.info("ZhaoBiao DisruptorCreator start success...");
        ZhongBiaoDisruptorCreator.getProcessDisruptor().start();
        logger.info("ZhongBiao DisruptorCreator start success###");
    }

    /**
     * 关闭disruptor
     */
    public void shutdown() {
        ZhaoBiaoDisruptorCreator.shutdownDisruptor();
        logger.info("ZhaoBiao DisruptorCreator shutdown");
        ZhongBiaoDisruptorCreator.shutdownDisruptor();
        logger.info("ZhongBiao DisruptorCreator shutdown");
    }

    /**
     * 提交数据让disruptor处理
     * @param esNotice
     */
    public void publish(EsNotice esNotice) {
        String type = esNotice.getType().toString();
        if(SnatchContent.ZHAO_BIAO_TYPE.equals(type)) {
            ZhaoBiaoDisruptorCreator.getProcessDisruptor().publishEvent(eventTranslator, esNotice);
        }else if(SnatchContent.ZHONG_BIAO_TYPE.equals(type)){
            ZhongBiaoDisruptorCreator.getProcessDisruptor().publishEvent(eventTranslator, esNotice);
        }else {
            //TODO:其他类型后续需要整理
            ZhaoBiaoDisruptorCreator.getProcessDisruptor().publishEvent(eventTranslator, esNotice);
            logger.warn("其他类型，按招标逻辑处理。[type:"+esNotice.getType()+"][title:"+esNotice.getTitle()+"][url:"+esNotice.getUrl()+"]");
        }
    }

}
