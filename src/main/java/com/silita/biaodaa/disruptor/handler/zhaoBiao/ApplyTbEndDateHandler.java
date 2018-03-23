package com.silita.biaodaa.disruptor.handler.zhaoBiao;

import com.silita.biaodaa.analysisRules.inter.SingleFieldAnalysis;
import com.silita.biaodaa.analysisRules.zhaobiao.other.OtherApplyTbEndDate;
import com.silita.biaodaa.disruptor.handler.BaseHandler;
import com.snatch.model.EsNotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 解析开标时间(投标截止时间)
 * Created by 91567 on 2018/3/21.
 */
@Component
public class ApplyTbEndDateHandler extends BaseHandler {

    @Autowired
    OtherApplyTbEndDate otherApplyTbEndDate;

    public ApplyTbEndDateHandler() {
        this.fieldDesc="投标结束时间";
    }

    private SingleFieldAnalysis routeRules(String source){
        switch (source){
            case "hunan": return otherApplyTbEndDate;
            default:return otherApplyTbEndDate;
        }
    }

    @Override
    protected Object currentFieldValues(EsNotice esNotice) {
        return esNotice.getDetail().getTbEndDate();
    }

    @Override
    protected Object executeAnalysis(String stringPart, EsNotice esNotice) {
        SingleFieldAnalysis analysis = routeRules(esNotice.getSource());
        return analysis.analysis(stringPart,null);
    }

    @Override
    protected void saveResult(EsNotice esNotice, Object analysisResult) {
        String tbEndDate = (String)analysisResult;
        if(tbEndDate.contains(":")) {
            esNotice.getDetail().setTbEndDate(tbEndDate.substring(0,10));
            esNotice.getDetail().setTbEndTime(tbEndDate.substring(10));
        } else {
            esNotice.getDetail().setTbEndDate(tbEndDate);
        }
    }
}
