package com.silita.biaodaa.disruptor.handler.zhaoBiao;

import com.silita.biaodaa.disruptor.handler.BaseHandler;
import com.silita.biaodaa.service.NoticeAnalyzeService;
import com.snatch.model.EsNotice;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zhangxiahui on 18/3/13.
 * 项目金额
 */
@Component
public class ApplyProjSumHandler extends BaseHandler {

    Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @Autowired
    NoticeAnalyzeService noticeAnalyzeService;

    public ApplyProjSumHandler(){
        this.fieldDesc="项目金额";
    }

    @Override
    protected Object currentFieldValues(EsNotice esNotice) {
        return null;
    }

    @Override
    protected Object executeAnalysis(String stringPart) {
        return noticeAnalyzeService.analyzeApplyProjSum(stringPart);
    }

    @Override
    protected void saveResult(EsNotice esNotice, Object analysisResult) {
        if(analysisResult!=null){
            esNotice.getDetail().setProjSum(analysisResult.toString());
        }
    }


}
