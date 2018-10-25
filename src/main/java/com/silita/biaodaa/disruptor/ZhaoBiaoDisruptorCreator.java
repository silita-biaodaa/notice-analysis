package com.silita.biaodaa.disruptor;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.silita.biaodaa.disruptor.event.AnalyzeEvent;
import com.silita.biaodaa.disruptor.exception.AnalyzeException;
import com.silita.biaodaa.disruptor.handler.SendMsgHandler;
import com.silita.biaodaa.disruptor.handler.zhaoBiao.InsertAnalyzeDetailHandler;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Remark: 创建disruptor
 */
@Component
public class ZhaoBiaoDisruptorCreator {

    private static Logger logger = org.slf4j.LoggerFactory.getLogger(ZhaoBiaoDisruptorCreator.class);

    private static Disruptor<AnalyzeEvent> processDisruptor;

    private static final EventFactory<AnalyzeEvent> EVENT_FACTORY = new EventFactory<AnalyzeEvent>() {
        @Override
        public AnalyzeEvent newInstance() {
            return new AnalyzeEvent();
        }
    };

    private static final int BUFFER_SIZE = 1024*1024;

    //TODO 需要根据其他因素调整线程数量
    private static final int THREAD_NUM = 20;
    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(THREAD_NUM);

    /**
     * 利用spring完成初始化，singleton
     */
    public static synchronized void initDisruptor(
            List<EventHandler> zhaobiaoHandlerList,InsertAnalyzeDetailHandler insertAnalyzeDetailHandler,SendMsgHandler sendMsgHandler) {
        if (processDisruptor == null) {
            EventHandler[] handlers = new EventHandler[zhaobiaoHandlerList.size()];
            logger.info("..........ZhaoBiaoDisruptorCreator init..........\nDISRUPTOR BUFFER_SIZE:" + BUFFER_SIZE + " THREAD_NUM:" + THREAD_NUM);
            processDisruptor = new Disruptor<AnalyzeEvent>(EVENT_FACTORY, BUFFER_SIZE, EXECUTOR, ProducerType.SINGLE, new SleepingWaitStrategy());
            processDisruptor.handleExceptionsWith(new AnalyzeException());
            zhaobiaoHandlerList.toArray(handlers);
            processDisruptor.handleEventsWith(handlers)
                    .then(insertAnalyzeDetailHandler).then(sendMsgHandler);
            logger.info("..........ZhaoBiaoDisruptorCreator init success..........");
        }
    }

    public static Disruptor<AnalyzeEvent> getProcessDisruptor() {
        return processDisruptor;
    }

    public static void shutdownDisruptor() {
        processDisruptor.shutdown();
        EXECUTOR.shutdown();
    }
}
