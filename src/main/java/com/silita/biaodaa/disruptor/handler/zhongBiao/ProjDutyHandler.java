package com.silita.biaodaa.disruptor.handler.zhongBiao;

import com.silita.biaodaa.analysisRules.notice.zhongbiao.OtherProjDuty;
import com.silita.biaodaa.disruptor.handler.BaseHandler;
import com.snatch.model.EsNotice;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by hujia on 18/3/20.
 * 项目经理
 */
@Component
public class ProjDutyHandler extends BaseHandler {

    Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @Autowired
    OtherProjDuty otherProjDuty;

    public ProjDutyHandler(){
        this.fieldDesc="项目经理";
    }

    @Override
    protected Object currentFieldValues(EsNotice esNotice) {
        return esNotice.getDetailZhongBiao().getOneProjDuty();
    }


    @Override
    protected String executeAnalysis(String stringPart,EsNotice esNotice) {
        return otherProjDuty.analysis(stringPart,esNotice.getSource());
    }

    @Override
    protected void saveResult(EsNotice esNotice, Object analysisResult) {
        esNotice.getDetailZhongBiao().setOneProjDuty((String) analysisResult);
    }

}
