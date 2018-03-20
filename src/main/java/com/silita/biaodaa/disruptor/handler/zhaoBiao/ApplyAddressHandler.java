package com.silita.biaodaa.disruptor.handler.zhaoBiao;

import com.silita.biaodaa.analysisRules.inter.SingleFieldAnalysis;
import com.silita.biaodaa.analysisRules.zhaobiao.hunan.HunanApplyAddress;
import com.silita.biaodaa.analysisRules.zhaobiao.other.OtherApplyAddress;
import com.silita.biaodaa.disruptor.handler.BaseHandler;
import com.snatch.model.EsNotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 报名地址
 */
@Component
public class ApplyAddressHandler extends BaseHandler {

    @Autowired
    HunanApplyAddress hunanApplyAddress;

    @Autowired
    OtherApplyAddress otherApplyAddress;

    public ApplyAddressHandler(){
        this.fieldDesc="报名地址";
    }

    private SingleFieldAnalysis routeRules(String source){
        switch (source){
            case "hunan": return hunanApplyAddress;
            case "tianj": return otherApplyAddress;
            case "guangd": return otherApplyAddress;
            case "shangh": return otherApplyAddress;
            default:return otherApplyAddress;
        }
    }

    @Override
    protected Object currentFieldValues(EsNotice esNotice) {
        return esNotice.getDetail().getBmSite();
    }

    @Override
    protected String executeAnalysis(String stringPart,String source) {
        SingleFieldAnalysis analysis = routeRules(source);
        return analysis.analysis(stringPart);
    }

    @Override
    protected void saveResult(EsNotice esNotice, Object analysisResult) {
        esNotice.getDetail().setBmSite((String)analysisResult);
    }
}
