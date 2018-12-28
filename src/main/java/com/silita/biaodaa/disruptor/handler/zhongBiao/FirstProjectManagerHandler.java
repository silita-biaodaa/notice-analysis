package com.silita.biaodaa.disruptor.handler.zhongBiao;

import com.silita.biaodaa.analysisRules.notice.zhongbiao.FirstProjectManagerRule;
import com.silita.biaodaa.disruptor.handler.BaseAnalysisHandler;
import com.snatch.model.EsNotice;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 第一中标金额
 */
@Component
public class FirstProjectManagerHandler extends BaseAnalysisHandler {

    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    FirstProjectManagerRule firstProjectManagerRule;

    public FirstProjectManagerHandler(){
        this.fieldDesc="第一中标项目经理";
    }

    @Override
    protected Object currentFieldValues(EsNotice esNotice) {
        return esNotice.getDetailZhongBiao().getOneProjDuty();
    }


    @Override
    protected String executeAnalysis(String stringPart,EsNotice esNotice) throws Exception {
        return firstProjectManagerRule.analysis(stringPart,esNotice,null);
    }

    @Override
    protected void saveResult(EsNotice esNotice, Object analysisResult) {
        esNotice.getDetailZhongBiao().setOneProjDuty((String) analysisResult);
    }

}
