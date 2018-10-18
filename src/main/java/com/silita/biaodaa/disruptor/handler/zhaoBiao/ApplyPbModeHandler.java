package com.silita.biaodaa.disruptor.handler.zhaoBiao;


import com.silita.biaodaa.analysisRules.inter.SingleFieldAnalysis;
import com.silita.biaodaa.analysisRules.notice.zhaobiao.hunan.HunanApplyPbMode;
import com.silita.biaodaa.analysisRules.notice.zhaobiao.other.OtherApplyPbMode;
import com.silita.biaodaa.disruptor.handler.BaseAnalysisHandler;
import com.snatch.model.EsNotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/3/21.
 */
@Component
public class ApplyPbModeHandler extends BaseAnalysisHandler {
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
    protected Object executeAnalysis(String stringPart, EsNotice esNotice) throws Exception{
        SingleFieldAnalysis analysis = routeRules(esNotice.getSource());
        return analysis.analysis(stringPart,esNotice,null);
    }

    @Override
    protected void saveResult(EsNotice esNotice, Object analysisResult) {
        esNotice.getDetail().setPbMode((String)analysisResult);
    }
}
