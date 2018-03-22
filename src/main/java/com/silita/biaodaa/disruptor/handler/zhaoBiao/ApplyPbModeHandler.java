package com.silita.biaodaa.disruptor.handler.zhaoBiao;


import com.silita.biaodaa.analysisRules.inter.SingleFieldAnalysis;
import com.silita.biaodaa.analysisRules.zhaobiao.hunan.HunanApplyAddress;
import com.silita.biaodaa.analysisRules.zhaobiao.hunan.HunanApplyPbMode;
import com.silita.biaodaa.analysisRules.zhaobiao.other.OtherApplyAddress;
import com.silita.biaodaa.analysisRules.zhaobiao.other.OtherApplyPbMode;
import com.silita.biaodaa.disruptor.handler.BaseHandler;
import com.snatch.model.EsNotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/3/21.
 */
@Component
public class ApplyPbModeHandler extends BaseHandler{
    @Autowired
    HunanApplyPbMode hunanApplyPbMode;

    @Autowired
    OtherApplyPbMode otherApplyPbMode;

    private SingleFieldAnalysis routeRules(String source){
        switch (source) {
            case "hunan": return hunanApplyPbMode;
            default:return otherApplyPbMode;
        }
    }

    @Override
    protected Object currentFieldValues(EsNotice esNotice) {
        return esNotice.getDetail().getPbMode();
    }

    @Override
    protected Object executeAnalysis(String stringPart, String source) {
        SingleFieldAnalysis analysis = routeRules(source);
        return analysis.analysis(stringPart,null);
    }

    @Override
    protected void saveResult(EsNotice esNotice, Object analysisResult) {
        esNotice.getDetail().setPbMode((String)analysisResult);
    }
}
