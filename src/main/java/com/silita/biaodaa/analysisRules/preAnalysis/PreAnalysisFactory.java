package com.silita.biaodaa.analysisRules.preAnalysis;

import com.silita.biaodaa.analysisRules.inter.PreAnalysisRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by dh on 2018/4/2.
 */
@Component
public class PreAnalysisFactory {

    @Autowired
    private FullSection fullSection;

    @Autowired
    private SplitSection splitSection;

    public PreAnalysisRule getPreAnalysisRule(String clsName){
        switch (clsName){
            case "ApplyAddressHandler":return fullSection;
            default: return splitSection;
        }
    }



}
