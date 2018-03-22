package com.silita.biaodaa.analysisRules.zhaobiao.other;

import com.silita.biaodaa.analysisRules.inter.SingleFieldAnalysis;
import com.silita.biaodaa.cache.GlobalCache;
import com.silita.biaodaa.dao.AnalyzeRangeMapper;
import com.silita.biaodaa.utils.CNNumberFormat;
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
    AnalyzeRangeMapper analyzeRangeMapper;

    @Override
    public String analysis(String segment,String keyWork) {
        String rangeHtml="";
        String deposit = "";

        Map<String,List<Map<String, Object>>> analyzeRangeByFieldMap = GlobalCache.getGlobalCache().getAnalyzeRangeByFieldMap();
        List<Map<String, Object>> arList = analyzeRangeByFieldMap.get("applyDeposit");
        if(arList == null){
            arList = analyzeRangeMapper.queryAnalyzeRangeByField("applyDeposit");
            analyzeRangeByFieldMap.put("applyDeposit",arList);
            GlobalCache.getGlobalCache().setAnalyzeRangeByFieldMap(analyzeRangeByFieldMap);
        }

        for (int i = 0; i < arList.size(); i++) {
            String start = arList.get(i).get("rangeStart").toString();
            String end = arList.get(i).get("rangeEnd").toString();
            int indexStart=0;
            int indexEnd=0;
            if(!"".equals(start)){
                indexStart = segment.indexOf(start);//范围开始位置
            }
            if(!"".equals(end)){
                indexEnd = segment.indexOf(end);//范围结束位置
            }
            if(indexStart > -1 && indexEnd> -1){
                if(indexEnd > indexStart){
                    rangeHtml = segment.substring(indexStart, indexEnd+1);//截取范围之间的文本
                }else if(indexStart > indexEnd) {
                    if(segment.length()-indexStart>=50){
                        rangeHtml = segment.substring(indexStart, indexStart+50);//截取范围开始至后30个字符
                    }else{
                        rangeHtml = segment.substring(indexStart, segment.length());//截取范围开始至后30个字符
                    }
                }
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
                if(deposit.length()>0){
                    break;
                }
            }
        }
        return deposit;
    }

}
