package com.silita.biaodaa.analysisRules.notice.zhaobiao.hunan;

import com.silita.biaodaa.analysisRules.inter.SingleFieldAnalysis;
import com.silita.biaodaa.cache.GlobalCache;
import com.silita.biaodaa.service.AnalyzeRangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 评标办法
 * Created by maofeng on 2018/3/21.
 */
@Component
public class HunanApplyPbMode implements SingleFieldAnalysis {

    @Autowired
    AnalyzeRangeService analyzeRangeService;

    @Override
    public String analysis(String segment,String keyWork) {
        String val = "";

        Map<String,List<Map<String, Object>>> analyzeRangeByFieldMap = GlobalCache.getGlobalCache().getAnalyzeRangeByFieldMap();
        List<Map<String, Object>> pbList = analyzeRangeByFieldMap.get("HuNanPbMode");
        if(pbList == null){
            pbList = analyzeRangeService.queryAnalyzeRangePbMode("mishu_analyze.analyze_range_pbmode");
            analyzeRangeByFieldMap.put("HuNanPbMode",pbList);
            GlobalCache.getGlobalCache().setAnalyzeRangeByFieldMap(analyzeRangeByFieldMap);
        }

        for (int i = 0; i < pbList.size(); i++) {
            if (segment.indexOf(String.valueOf(pbList.get(i).get("anotherName"))) != -1) {
                val = String.valueOf(pbList.get(i).get("standardName"));
                break;
            }
        }
        return val;
    }
}
