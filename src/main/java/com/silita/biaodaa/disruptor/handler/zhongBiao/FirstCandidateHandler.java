package com.silita.biaodaa.disruptor.handler.zhongBiao;

import com.silita.biaodaa.analysisRules.notice.zhongbiao.FirstCandidateRule;
import com.silita.biaodaa.disruptor.handler.BaseAnalysisHandler;
import com.snatch.model.EsNotice;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by hujia on 18/3/20.
 * 第一中标人
 */
@Component
public class FirstCandidateHandler extends BaseAnalysisHandler {

    Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @Autowired
    FirstCandidateRule firstCandidateRule;

    public FirstCandidateHandler(){
        this.fieldDesc="第一中标人";
    }

    @Override
    protected Object currentFieldValues(EsNotice esNotice) {
        return esNotice.getDetailZhongBiao().getOneName();
    }


    @Override
    protected String executeAnalysis(String stringPart,EsNotice esNotice) throws Exception {
        return firstCandidateRule.analysis(stringPart,esNotice,null);
    }

    @Override
    protected void saveResult(EsNotice esNotice, Object analysisResult) {
        esNotice.getDetailZhongBiao().setOneName((String) analysisResult);
    }

}
