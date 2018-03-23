package com.silita.biaodaa.analysisRules.zhaobiao.other;

import com.silita.biaodaa.analysisRules.inter.SingleFieldAnalysis;
import com.silita.biaodaa.cache.GlobalCache;
import com.silita.biaodaa.dao.AnalyzeRangeMapper;
import com.silita.biaodaa.dao.CommonMapper;
import com.silita.biaodaa.utils.MyStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;

/**
 * 保证金汇款方式
 * Created by Administrator on 2018/3/21.
 */
@Component
public class OtherApplyAssureSumRemit implements SingleFieldAnalysis {

    @Autowired
    CommonMapper commonMapper;

    @Override
    public String analysis(String segment,String keyWork) {
        String assureSumRemit = "";
        Map<String,List<Map<String, Object>>> analyzeRangeByFieldMap = GlobalCache.getGlobalCache().getAnalyzeRangeByFieldMap();
        List<Map<String, Object>> arList = analyzeRangeByFieldMap.get("applyAssureSumRemit");
        if (arList == null) {
            arList = commonMapper.queryRegexByField("applyAssureSumRemit");
            analyzeRangeByFieldMap.put("applyAssureSumRemit",arList);
            GlobalCache.getGlobalCache().setAnalyzeRangeByFieldMap(analyzeRangeByFieldMap);
        }

        for (int i = 0; i < arList.size(); i++) {
            String regex = String.valueOf(arList.get(i).get("regex")).replaceAll("\\\\","\\\\\\\\");
            Pattern pa = Pattern.compile(regex);
            Matcher ma = pa.matcher(segment);
            if (ma.find()) {
                String txt = ma.group();
                assureSumRemit = MyStringUtils.findAssureSumRemit(txt);
                break;
            }
        }
        return assureSumRemit;
    }
}
