package com.silita.biaodaa.disruptor.handler.zhongBiao;

import com.lmax.disruptor.EventHandler;
import com.silita.biaodaa.analysisRules.notice.NoticeTableAnalysis;
import com.silita.biaodaa.disruptor.event.AnalyzeEvent;
import com.snatch.model.EsNotice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 中标公告前置解析handle
 * Created by dh on 2018/11/19.
 */
@Component
public class BeforeBidsHandle implements EventHandler<AnalyzeEvent> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private NoticeTableAnalysis noticeTableAnalysis;

    @Override
    public void onEvent(AnalyzeEvent event, long sequence, boolean endOfBatch) throws Exception {
        long start = System.nanoTime();
        logger.debug("中标公告：表格解析开始...");
        EsNotice esNotice = event.getEsNotice();
        Map<String,String> resMap = noticeTableAnalysis.analysis(esNotice, esNotice.getContent(), NoticeTableAnalysis.ZHONGBIAO_TB);
        esNotice.setTbAnalysisMap(resMap);
        long end = System.nanoTime()-start;
        logger.info("中标公告，表格解析结束,耗时："+end/(1000*1000)+"毫秒||"+end/(1000)+"微秒");
    }
}
