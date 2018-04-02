package com.silita.biaodaa.disruptor.handler.zhaoBiao;

import com.silita.biaodaa.analysisRules.inter.SingleFieldAnalysis;
import com.silita.biaodaa.analysisRules.notice.zhaobiao.other.OtherApplyProjectTimeLimit;
import com.silita.biaodaa.disruptor.handler.BaseHandler;
import com.snatch.model.EsNotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/3/21.
 */
@Component
public class ApplyProjectTimeLimitHandler extends BaseHandler{

    @Autowired
    OtherApplyProjectTimeLimit otherApplyProjectTimeLimit;

    private SingleFieldAnalysis routeRules(String source){
        return otherApplyProjectTimeLimit;
    }

    public ApplyProjectTimeLimitHandler () {
        this.fieldDesc = "项目工期";
    }

    @Override
    protected Object currentFieldValues(EsNotice esNotice) {
        return esNotice.getDetail().getProjectTimeLimit();
    }

    @Override
    protected Object executeAnalysis(String stringPart, EsNotice esNotice) {
        SingleFieldAnalysis analysis = routeRules(esNotice.getSource());
        return analysis.analysis(stringPart,null);
    }

    @Override
    protected void saveResult(EsNotice esNotice, Object analysisResult) {
        esNotice.getDetail().setProjectTimeLimit((String)analysisResult);
    }
}
