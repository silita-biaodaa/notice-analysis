package com.silita.biaodaa.analysisRules.zhaobiao.other;

import com.silita.biaodaa.analysisRules.inter.SingleFieldAnalysis;
import com.silita.biaodaa.cache.GlobalCache;
import com.silita.biaodaa.dao.AnalyzeRangeMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 项目工期
 * Created by maofeng on 2018/3/21.
 */
@Component
public class OtherApplyProjectTimeLimit implements SingleFieldAnalysis {

    @Autowired
    AnalyzeRangeMapper analyzeRangeMapper;


    @Override
    public String analysis(String segment) {
        String rangeHtml = "";
        String timeLimit = "";
        Map<String,List<Map<String, Object>>> analyzeRangeByFieldMap = GlobalCache.getGlobalCache().getAnalyzeRangeByFieldMap();
        List<Map<String, Object>> arList = analyzeRangeByFieldMap.get("applyProjectTimeLimit");
        if(arList == null){
            arList = analyzeRangeMapper.queryAnalyzeRangeByField("applyProjectTimeLimit");
            analyzeRangeByFieldMap.put("applyProjectTimeLimit",arList);
            GlobalCache.getGlobalCache().setAnalyzeRangeByFieldMap(analyzeRangeByFieldMap);
        }

        for(int i = 0; i < arList.size(); i++) {
            String regex = String.valueOf(arList.get(i).get("regex")).replaceAll("\\\\","\\\\\\\\");
            Matcher matre = Pattern.compile(regex).matcher(segment);
            while (matre.find()) {
                rangeHtml = matre.group();
                if(!"".equals(rangeHtml)){
                    String regEx = "[1-9]\\d*";//工期数字
                    Pattern pat = Pattern.compile(regEx);
                    Matcher mat = pat.matcher(rangeHtml);
                    while (mat.find()) {
                        if(Double.parseDouble(mat.group())>10){
                            timeLimit = mat.group();
                            return timeLimit;
                        }
                    }
                }
            }
        }
        return timeLimit;
    }
}
