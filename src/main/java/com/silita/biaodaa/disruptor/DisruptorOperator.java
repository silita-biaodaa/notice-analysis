package com.silita.biaodaa.disruptor;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.silita.biaodaa.common.SnatchContent;
import com.silita.biaodaa.disruptor.event.AnalyzeEvent;
import com.silita.biaodaa.disruptor.handler.SendMsgHandler;
import com.silita.biaodaa.disruptor.handler.zhaoBiao.*;
import com.silita.biaodaa.disruptor.handler.zhongBiao.*;
import com.snatch.model.EsNotice;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DisruptorOperator {

    Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    //招標-------start------
    @Autowired
    ApplyAddressHandler applyAddressHandler;

    @Autowired
    ApplyAssureEndDateHandler applyAssureEndDateHandler;

    @Autowired
    ApplyAssureSumRemitHandler assureSumRemitHandler;

    @Autowired
    ApplyDateHandler applyDateHandler;

    @Autowired
    ApplyPbModeHandler applyPbModeHandler;

    @Autowired
    ApplyProjectTimeLimitHandler applyProjectTimeLimitHandler;

    @Autowired
    ApplyProjSumHandler applyProjSumHandler;

    @Autowired
    ApplyTbAssureSumHandler applyTbAssureSumHandler;

    @Autowired
    ApplyTbEndDateHandler applyTbEndDateHandler;

    @Autowired
    InsertAnalyzeDetailHandler insertAnalyzeDetailHandler;
    //招標-------end------

    //----中標start------
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
    //----中標end------

    @Autowired
    SendMsgHandler sendMsgHandler;


    private static EventTranslatorOneArg<AnalyzeEvent,EsNotice> eventTranslator = new EventTranslatorOneArg<AnalyzeEvent,EsNotice>() {
        @Override
        public void translateTo(AnalyzeEvent event, long sequence, EsNotice esNotice) {
            event.setEsNotice(esNotice);
        }
    };

    /**
     * 初始化disruptor
     */
    @PostConstruct
    public void init() {
        List<EventHandler> zhaobiaoHandlerList = new ArrayList<EventHandler>();
        zhaobiaoHandlerList.add(applyAddressHandler);
        zhaobiaoHandlerList.add(applyAssureEndDateHandler);
        zhaobiaoHandlerList.add(assureSumRemitHandler);
        zhaobiaoHandlerList.add(applyDateHandler);
        zhaobiaoHandlerList.add(applyPbModeHandler);
        zhaobiaoHandlerList.add(applyProjectTimeLimitHandler);
        zhaobiaoHandlerList.add(applyProjSumHandler);
        zhaobiaoHandlerList.add(applyTbAssureSumHandler);
        zhaobiaoHandlerList.add(applyTbEndDateHandler);
        ZhaoBiaoDisruptorCreator.initDisruptor(zhaobiaoHandlerList,insertAnalyzeDetailHandler,sendMsgHandler);

        List<EventHandler> zhongbiaoHandlerList = new ArrayList<EventHandler>();
        zhongbiaoHandlerList.add(oneNameHandler);
        zhongbiaoHandlerList.add(twoNameHandler);
        zhongbiaoHandlerList.add(threeNameHandler);
        zhongbiaoHandlerList.add(projDutyHandler);
        ZhongBiaoDisruptorCreator.initDisruptor(zhongbiaoHandlerList,insertAnalyzeDetailZhongBiaoHandler,sendMsgHandler);
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
        if(SnatchContent.ZHAO_BIAO_TYPE.equals(type)
                || SnatchContent.OTHER_TYPE.equals(type)
                || SnatchContent.DL_ZHAO_BIAO_TYPE.equals(type)
                || SnatchContent.PRE_QUALIFICATION.equals(type)
                || SnatchContent.ZI_GE_YU_SHEN_TYPE.equals(type)) {
            //招标
            ZhaoBiaoDisruptorCreator.getProcessDisruptor().publishEvent(eventTranslator, esNotice);
        }else if(SnatchContent.ZHONG_BIAO_TYPE.equals(type)
                || SnatchContent.DL_ZHONG_BIAO_TYPE.equals(type)
                || SnatchContent.ZHONG_BIAO_BU_CHONG_TYPE.equals(type)
                || SnatchContent.HE_TONG_TYPE.equals(type)){
            //中标
            ZhongBiaoDisruptorCreator.getProcessDisruptor().publishEvent(eventTranslator, esNotice);
        }else {
            //TODO:其他类型后续需要整理
            ZhaoBiaoDisruptorCreator.getProcessDisruptor().publishEvent(eventTranslator, esNotice);
            logger.warn("其他类型，暂时按招标解析逻辑处理。[type:"+esNotice.getType()+"][title:"+esNotice.getTitle()+"][url:"+esNotice.getUrl()+"]");
        }
    }

}
