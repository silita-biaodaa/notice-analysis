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

    @Override
    protected String executeAnalysis(String stringPart) {
        this.fieldDesc="报名地址";
        return noticeAnalyzeService.analyzeApplyAddress(stringPart);
    }

    @Override
    protected void saveResult(EsNotice esNotice, String analysisResult) {
        esNotice.getDetail().setBmSite(analysisResult);
    }
}
