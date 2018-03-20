package com.silita.biaodaa.disruptor.handler;

import com.lmax.disruptor.EventHandler;
import com.silita.biaodaa.disruptor.event.AnalyzeEvent;

public abstract class BaseHandler implements EventHandler<AnalyzeEvent> {


    public BaseHandler() {


    }
}
