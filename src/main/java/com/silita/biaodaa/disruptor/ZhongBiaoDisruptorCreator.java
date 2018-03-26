package com.silita.biaodaa.disruptor;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.silita.biaodaa.disruptor.event.AnalyzeEvent;
import com.silita.biaodaa.disruptor.exception.AnalyzeException;
import com.silita.biaodaa.disruptor.handler.zhongBiao.*;
import com.silita.biaodaa.service.NoticeAnalyzeService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Remark: 创建ZhongBiaoDisruptorCreator
 */
@Component
public class ZhongBiaoDisruptorCreator {

    @Autowired
    NoticeAnalyzeService noticeAnalyzeService;

    private static Logger logger = org.slf4j.LoggerFactory.getLogger(ZhongBiaoDisruptorCreator.class);

    private static Disruptor<AnalyzeEvent> processZhongbiaoDisruptor;

    private static final EventFactory<AnalyzeEvent> EVENT_FACTORY = new EventFactory<AnalyzeEvent>() {
        @Override
        public AnalyzeEvent newInstance() {
            return new AnalyzeEvent();
        }
    };

    private static final int BUFFER_SIZE = 1024;

    //TODO 需要根据其他因素调整线程数量
    private static final int THREAD_NUM = 6;
    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(THREAD_NUM);

    /**
     * 利用spring完成初始化，singleton
     */
    public static synchronized void initDisruptor(OneNameHandler oneNameHandler, TwoNameHandler twoNameHandler,
                                                  ThreeNameHandler threeNameHandler, ProjDutyHandler projDutyHandler, InsertAnalyzeDetailZhongBiaoHandler insertAnalyzeDetailZhongBiaoHandler) {
        if(processZhongbiaoDisruptor == null) {
            logger.info(".......... processZhongbiaoDisruptor init..........\nDISRUPTOR BUFFER_SIZE:"+BUFFER_SIZE+" THREAD_NUM:"+THREAD_NUM);
            processZhongbiaoDisruptor = new Disruptor<AnalyzeEvent>(EVENT_FACTORY,BUFFER_SIZE,EXECUTOR,ProducerType.SINGLE,new SleepingWaitStrategy());
            processZhongbiaoDisruptor.handleExceptionsWith(new AnalyzeException());
            processZhongbiaoDisruptor.handleEventsWith(oneNameHandler)
                    .then(twoNameHandler)
                    .then(threeNameHandler)
                    .then(projDutyHandler)
                    .then(insertAnalyzeDetailZhongBiaoHandler);
            logger.info("..........zhongbiao disruptor init success..........");
        }
    }

    public static Disruptor<AnalyzeEvent> getProcessDisruptor() {
        return processZhongbiaoDisruptor;
    }

    public static void shutdownDisruptor() {
        processZhongbiaoDisruptor.shutdown();
        EXECUTOR.shutdown();
    }
}
