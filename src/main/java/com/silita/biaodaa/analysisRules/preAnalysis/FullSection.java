package com.silita.biaodaa.analysisRules.preAnalysis;

import com.silita.biaodaa.analysisRules.inter.PreAnalysisRule;
import com.silita.biaodaa.utils.HtmlTagUtils;
import com.silita.biaodaa.utils.MyStringUtils;
import com.snatch.model.EsNotice;
import org.springframework.stereotype.Component;

/**
 * Created by dh on 2018/4/2.
 */
@Component
public class FullSection implements PreAnalysisRule {
    @Override
    public String[] buildAnalysisList(EsNotice esNotice, String split1, String split2) throws Exception{
        if(MyStringUtils.isNull(esNotice.getContent())){
            throw new Exception("公告内容为空。[esNotice.getContent():"+esNotice.getContent()+"]");
        }
        String[] main = {HtmlTagUtils.clearInvalidTag(esNotice.getContent())};
        return main;
    }

}
