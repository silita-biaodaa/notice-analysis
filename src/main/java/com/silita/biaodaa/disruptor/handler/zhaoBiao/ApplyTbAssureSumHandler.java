package com.silita.biaodaa.disruptor.handler.zhaoBiao;

import com.silita.biaodaa.analysisRules.inter.SingleFieldAnalysis;
import com.silita.biaodaa.analysisRules.notice.zhaobiao.other.OtherApplyTbAssureSum;
import com.silita.biaodaa.disruptor.handler.BaseHandler;
import com.snatch.model.EsNotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zhangxiahui on 18/3/13.
 * 保证金
 */
@Component
public class ApplyTbAssureSumHandler extends BaseHandler {

    @Autowired
    OtherApplyTbAssureSum otherApplyTbAssureSum;

    public ApplyTbAssureSumHandler () {
        this.fieldDesc = "保证金";
    }

    private SingleFieldAnalysis routeRules(String source){
        return otherApplyTbAssureSum;
    }

    @Override
    protected Object currentFieldValues(EsNotice esNotice) {
        return esNotice.getDetail().getTbAssureSum();
    }

    @Override
    protected Object executeAnalysis(String stringPart, EsNotice esNotice) {
        SingleFieldAnalysis analysis = routeRules(esNotice.getSource());
        return analysis.analysis(stringPart,null);
    }

    @Override
    protected void saveResult(EsNotice esNotice, Object analysisResult) {
        esNotice.getDetail().setTbAssureSum((String)analysisResult);
    }
}
