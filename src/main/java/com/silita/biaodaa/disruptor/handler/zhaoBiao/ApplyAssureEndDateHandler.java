package com.silita.biaodaa.disruptor.handler.zhaoBiao;

import com.silita.biaodaa.analysisRules.inter.SingleFieldAnalysis;
import com.silita.biaodaa.analysisRules.zhaobiao.other.OtherAssureEndDate;
import com.silita.biaodaa.disruptor.handler.BaseHandler;
import com.snatch.model.EsNotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by 91567 on 2018/3/22.
 */
@Component
public class ApplyAssureEndDateHandler extends BaseHandler {

    @Autowired
    OtherAssureEndDate otherAssureEndDate;

    public ApplyAssureEndDateHandler() {
        this.fieldDesc="保证金结束时间";
    }

    private SingleFieldAnalysis routeRules(String source){
        switch (source){
            case "hunan": return otherAssureEndDate;
            default:return otherAssureEndDate;
        }
    }

    @Override
    protected Object currentFieldValues(EsNotice esNotice) {
        return esNotice.getDetail().getAssureEndDate();
    }

    @Override
    protected Object executeAnalysis(String stringPart, String source) {
        SingleFieldAnalysis analysis = routeRules(source);
        return analysis.analysis(stringPart);
    }

    @Override
    protected void saveResult(EsNotice esNotice, Object analysisResult) {
        String assureEndDate = (String)analysisResult;
        if(assureEndDate.contains(":")) {
            esNotice.getDetail().setAssureEndDate(assureEndDate.substring(0,10));
            esNotice.getDetail().setAssureEndTime(assureEndDate.substring(10));
        } else {
            esNotice.getDetail().setAssureEndDate(assureEndDate);
        }
    }
}
