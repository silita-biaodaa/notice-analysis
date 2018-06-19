package com.silita.biaodaa;

import com.silita.biaodaa.disruptor.DisruptorOperator;
import com.silita.biaodaa.task.AnalysisTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhangxiahui on 18/3/13.
 */
@Component
@Scope("singleton")
public class AnalysisBootstrap implements ApplicationListener<ApplicationEvent> {

    protected Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    AnalysisTask analysisTask;

    @Autowired
    DisruptorOperator disruptorOperator;



    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent) {
            boolean isRoot = ((ContextRefreshedEvent) event).getApplicationContext().getParent() == null;
            if (isRoot) {
//                disruptorOperator.init();
                disruptorOperator.start();
                try {
                    final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(9);
                    scheduler.scheduleAtFixedRate(analysisTask, 0, 500, TimeUnit.MILLISECONDS);
                    logger.info("===========任务启动完成=========");
                } catch (Exception e) {
                    logger.info("任务启动异常", e);
                }
            }
        }
    }
}
