package com.silita.biaodaa.analysisRules.inter;

import com.snatch.model.EsNotice;

/**
 * Created by dh on 2018/4/2.
 */
public interface PreAnalysisRule {
    String[] buildAnalysisList(EsNotice esNotice, String split1, String split2) throws Exception;
}
