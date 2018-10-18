package com.silita.biaodaa.disruptor.handler.zhaoBiao;

import com.silita.biaodaa.analysisRules.inter.SingleFieldAnalysis;
import com.silita.biaodaa.analysisRules.notice.zhaobiao.other.OtherApplyAssureSumRemit;
import com.silita.biaodaa.disruptor.handler.BaseAnalysisHandler;
import com.snatch.model.EsNotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 保证金汇款方式
 * Created by maofeng on 2018/3/19.
 */
@Component
public class ApplyAssureSumRemitHandler extends BaseAnalysisHandler {

    @Autowired
    OtherApplyAssureSumRemit otherApplySumRemit;

    public ApplyAssureSumRemitHandler() {
        this.fieldDesc = "保证金汇款方式";
    }

    private SingleFieldAnalysis routeRules(String source){
        return otherApplySumRemit;
    }


    @Override
    protected Object currentFieldValues(EsNotice esNotice) {
        return esNotice.getDetail().getAssureSumRemit();
    }

    @Override
    protected String executeAnalysis(String stringPart,EsNotice esNotice)  throws Exception{
        SingleFieldAnalysis analysis = routeRules(esNotice.getSource());
        return analysis.analysis(stringPart,esNotice,null);
    }

    @Override
    protected void saveResult(EsNotice esNotice, Object assureSumRemit) {
        esNotice.getDetail().setAssureSumRemit((String)assureSumRemit);
    }

}
