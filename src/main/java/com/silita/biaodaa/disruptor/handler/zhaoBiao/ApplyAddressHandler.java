package com.silita.biaodaa.disruptor.handler.zhaoBiao;

import com.silita.biaodaa.disruptor.handler.BaseHandler;
import com.silita.biaodaa.service.NoticeAnalyzeService;
import com.snatch.model.EsNotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 报名地址
 */
@Component
public class ApplyAddressHandler extends BaseHandler {

    @Autowired
    NoticeAnalyzeService noticeAnalyzeService;

    public ApplyAddressHandler(){
        this.fieldDesc="报名地址";
    }

    @Override
    protected String executeAnalysis(String stringPart) {
        return noticeAnalyzeService.analyzeApplyAddress(stringPart);
    }

    @Override
    protected void saveResult(EsNotice esNotice, Object analysisResult) {
        esNotice.getDetail().setBmSite((String)analysisResult);
    }
}
