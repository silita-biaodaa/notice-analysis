package com.silita.biaodaa.disruptor.exception;

import com.lmax.disruptor.ExceptionHandler;
import org.slf4j.Logger;


public class AnalyzeException implements ExceptionHandler {

    Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @Override
    public void handleEventException(Throwable ex, long sequence, Object event) {
        //TODO disruptor处理过程中的异常处理
        logger.error(ex.getMessage(),ex);
    }

    @Override
    public void handleOnStartException(Throwable ex) {

    }

    @Override
    public void handleOnShutdownException(Throwable ex) {

    }
}
