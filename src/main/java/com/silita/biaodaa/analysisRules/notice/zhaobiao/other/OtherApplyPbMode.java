package com.silita.biaodaa.analysisRules.notice.zhaobiao.other;

import com.silita.biaodaa.analysisRules.inter.SingleFieldAnalysis;
import com.silita.biaodaa.analysisRules.notice.zhaobiao.hunan.HunanApplyPbMode;
import com.silita.biaodaa.cache.GlobalCache;
import com.silita.biaodaa.service.CommonService;
import com.silita.biaodaa.service.AnalyzeRangeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 评标办法
 * Created by maofeng on 2018/3/21.
 */
@Component
public class OtherApplyPbMode implements SingleFieldAnalysis {

    @Autowired
    CommonService commonService;

    @Autowired
    HunanApplyPbMode hunanApplyPbMode;

    @Autowired
    AnalyzeRangeService analyzeRangeService;

    @Override
    public String analysis(String segment,String keyWork)  throws Exception{
        String val = "";

        Map<String,List<Map<String, Object>>> analyzeRangeByFieldMap = GlobalCache.getGlobalCache().getAnalyzeRangeByFieldMap();
        List<Map<String, Object>> pbList = analyzeRangeByFieldMap.get("OtherPbMode");
        if(pbList == null){
            pbList = analyzeRangeService.queryAnalyzeRangePbMode("mishu_analyze.analyze_range_pbmode_other");
            analyzeRangeByFieldMap.put("OtherPbMode",pbList);
            GlobalCache.getGlobalCache().setAnalyzeRangeByFieldMap(analyzeRangeByFieldMap);
        }

        for (int i = 0; i < pbList.size(); i++) {
            if (segment.indexOf(String.valueOf(pbList.get(i).get("anotherName"))) != -1) {
                val = String.valueOf(pbList.get(i).get("standardName"));
                break;
            }
        }
        if (StringUtils.isBlank(val)) {
            val = hunanApplyPbMode.analysis(segment,null);
        }
        return val;
    }
}
