package com.silita.biaodaa.analysisRules.notice.zhaobiao.other;

import com.silita.biaodaa.analysisRules.inter.SingleFieldAnalysis;
import com.silita.biaodaa.cache.GlobalCache;
import com.silita.biaodaa.service.CommonService;
import com.silita.biaodaa.utils.CNNumberFormat;
import com.snatch.model.EsNotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 保证金
 * Created by maofeng on 2018/3/22.
 */
@Component
public class OtherApplyTbAssureSum implements SingleFieldAnalysis{

    @Autowired
    CommonService commonService;

    @Override
    public String analysis(String segment, EsNotice esNotice, String keyWork) {
        String rangeHtml="";
        String deposit = "";

        Map<String,List<Map<String, Object>>> analyzeRangeByFieldMap = GlobalCache.getGlobalCache().getAnalyzeRangeByFieldMap();
        List<Map<String, Object>> arList = analyzeRangeByFieldMap.get("applyDeposit");
        if(arList == null){
            arList = commonService.queryRegexByField("applyDeposit");
            analyzeRangeByFieldMap.put("applyDeposit",arList);
            GlobalCache.getGlobalCache().setAnalyzeRangeByFieldMap(analyzeRangeByFieldMap);
        }

        for (int i = 0; i < arList.size(); i++) {
            String regex = String.valueOf(arList.get(i).get("regex")).replaceAll("\\\\","\\\\\\\\");
            Pattern pa = Pattern.compile(regex);
            Matcher ma = pa.matcher(segment);
            while (ma.find()) {
                rangeHtml = ma.group();
                //匹配中文人民币
                String regExCn = "([零壹贰叁肆伍陆柒捌玖拾佰仟万亿])";//大写人民币
                Pattern pat1 = Pattern.compile(regExCn);
                rangeHtml = rangeHtml.replaceAll("\\s*", "");	//去空格
                Matcher mat1 = pat1.matcher(rangeHtml);
                String bigDeposit="";
                while (mat1.find()) {
                    bigDeposit+=mat1.group();
                }
                if(bigDeposit.length()>0){
                    int cnn = CNNumberFormat.ChnStringToNumber(bigDeposit);
                    if(cnn>=1000){//保证金需大于等于1000
                        deposit = String.valueOf(cnn);
                        break;
                    }
                }

                //匹配阿拉伯人民币
                String regExAr = "\\d+(\\.\\d+)?亿元|\\d+(\\.\\d+)?万元|\\d+(\\.\\d+)?元";//阿拉伯人民币
                Pattern pat2 = Pattern.compile(regExAr);
                Matcher mat2 = pat2.matcher(rangeHtml);
                while (mat2.find()) {
                    if("".equals(deposit)){
                        int cnn= CNNumberFormat.ChnStringToNumber(mat2.group().replaceAll("元", ""));
                        if(cnn>0){
                            deposit = String.valueOf(cnn);
                            if(Double.parseDouble(deposit)<1000){//项目金额需大于等于1000
                                deposit="";
                            }
                        }else{
                            deposit = mat2.group().replaceAll("万", "").replaceAll("元", "");
                            if(Double.parseDouble(deposit)<1000){//项目金额需大于等于1000
                                deposit="";
                            }
                        }
                    }
                }
            }
            if(deposit.length()>0){
                break;
            }
        }
        return deposit;
    }

}
