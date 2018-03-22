package com.silita.biaodaa.analysisRules.zhaobiao.other;

import com.silita.biaodaa.analysisRules.inter.SingleFieldAnalysis;
import com.silita.biaodaa.analysisRules.zhaobiao.hunan.HunanApplyPbMode;
import com.silita.biaodaa.cache.GlobalCache;
import com.silita.biaodaa.dao.AnalyzeRangeMapper;
import com.silita.biaodaa.service.NoticeAnalyzeService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
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

    Logger logger = Logger.getLogger(HunanApplyPbMode.class);

    @Autowired
    AnalyzeRangeMapper analyzeRangeMapper;

    @Autowired
    HunanApplyPbMode hunanApplyPbMode;

    @Override
    public String analysis(String segment) {
        String val = "";

        Map<String,List<Map<String, Object>>> analyzeRangeByFieldMap = GlobalCache.getGlobalCache().getAnalyzeRangeByFieldMap();
        List<Map<String, Object>> pbList = analyzeRangeByFieldMap.get("OtherPbMode");
        if(pbList == null){
            pbList = analyzeRangeMapper.queryAnalyzeRangePbMode("analyze_range_pbmode_other");
            analyzeRangeByFieldMap.put("OtherPbMode",pbList);
            GlobalCache.getGlobalCache().setAnalyzeRangeByFieldMap(analyzeRangeByFieldMap);
        }else{
            logger.info("=========OtherPbMode=======走的缓存=======");
        }

        for (int i = 0; i < pbList.size(); i++) {
            if (segment.indexOf(String.valueOf(pbList.get(i).get("anotherName"))) != -1) {
                val = String.valueOf(pbList.get(i).get("standardName"));
                break;
            }
        }
        if (StringUtils.isBlank(val)) {
            val = hunanApplyPbMode.analysis(segment);
        }
        return val;
    }
}
