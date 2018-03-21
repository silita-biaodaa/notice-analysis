package com.silita.biaodaa.disruptor.handler.zhaoBiao;

import com.silita.biaodaa.analysisRules.inter.SingleFieldAnalysis;
import com.silita.biaodaa.analysisRules.zhaobiao.hunan.HunanProjSum;
import com.silita.biaodaa.disruptor.handler.BaseHandler;
import com.silita.biaodaa.service.NoticeAnalyzeService;
import com.snatch.model.EsNotice;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zhangxiahui on 18/3/13.
 * 项目金额
 */
@Component
public class ApplyProjSumHandler extends BaseHandler {

    Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @Autowired
    HunanProjSum hunanProjSum;

    public ApplyProjSumHandler(){
        this.fieldDesc="项目金额";
    }

    private SingleFieldAnalysis routeRules(String source){
        return hunanProjSum;
    }

    @Override
    protected Object currentFieldValues(EsNotice esNotice) {
        return null;
    }

    @Override
    protected Object executeAnalysis(String stringPart,String source) {
        SingleFieldAnalysis analysis = routeRules(source);
        return analysis.analysis(stringPart);
    }

    @Override
    protected void saveResult(EsNotice esNotice, Object analysisResult) {
        if(analysisResult!=null){
            String proJSum = analysisResult.toString();
            if(!"".equals(proJSum)){
                esNotice.getDetail().setProjSum(analysisResult.toString());
            }
        }
    }


}
