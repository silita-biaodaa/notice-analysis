package com.silita.biaodaa.analysisRules.zhaobiao.hunan;

import com.silita.biaodaa.analysisRules.inter.SingleFieldAnalysis;
import com.silita.biaodaa.cache.GlobalCache;
import com.silita.biaodaa.dao.AnalyzeRangeMapper;
import com.silita.biaodaa.utils.CNNumberFormat;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhangxiahui on 18/3/21.
 */
@Component
public class HunanProjSum implements SingleFieldAnalysis {

    Logger logger = Logger.getLogger(HunanProjSum.class);

    @Autowired
    AnalyzeRangeMapper analyzeRangeMapper;

    /**
     * 解析项目金额
     * 正则匹配
     * return 项目金额
     */
    @Override
    public String analysis(String html) {
        String rangeHtml="";
        String deposit = "";
        Map<String,List<Map<String, Object>>> analyzeRangeByFieldMap = GlobalCache.getGlobalCache().getAnalyzeRangeByFieldMap();
        List<Map<String, Object>> arList = analyzeRangeByFieldMap.get("applyProjSum");
        if(arList == null){
            arList = analyzeRangeMapper.queryAnalyzeRangeByField("applyProjSum");
            analyzeRangeByFieldMap.put("applyProjSum",arList);
            GlobalCache.getGlobalCache().setAnalyzeRangeByFieldMap(analyzeRangeByFieldMap);
        }else{
            logger.info("=========applyProjSum=======走的缓存=======");
        }
        for (int i = 0; i < arList.size(); i++) {
            String start = arList.get(i).get("rangeStart").toString();
            String end = arList.get(i).get("rangeEnd").toString();
            int indexStart=0;
            int indexEnd=0;
            if(!"".equals(start)){
                indexStart = html.indexOf(start);//范围开始位置
            }
            if(!"".equals(end)){
                indexEnd = html.indexOf(end);//范围结束位置
            }
            if(indexStart > -1 && indexEnd> -1){
                if(indexEnd > indexStart){
                    rangeHtml = html.substring(indexStart, indexEnd+1);//截取范围之间的文本
                }else if(indexStart > indexEnd) {
                    if(html.length()-indexStart>=50){
                        rangeHtml = html.substring(indexStart, indexStart+50);//截取范围开始至后30个字符
                    }else{
                        rangeHtml = html.substring(indexStart, html.length());//截取范围开始至后30个字符
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
                    if(cnn>=200000){//项目金额需大于等于200000
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
                        int cnn=CNNumberFormat.ChnStringToNumber(mat2.group().replaceAll("元", ""));
                        if(cnn>0){
                            deposit = String.valueOf(cnn);
                            if(Double.parseDouble(deposit)<200000){//项目金额需大于等于200000
                                deposit="";
                            }
                        }else{
                            deposit = mat2.group().replaceAll("万", "").replaceAll("元", "");
                            if(Double.parseDouble(deposit)<200000){//项目金额需大于等于200000
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
//		if(MyStringUtils.isNotNull(deposit)) {
//			BigDecimal d = new BigDecimal(Double.parseDouble(deposit) / 10000);
//			deposit = "约" + String.valueOf(d) + "万";
//		}
        return deposit;
    }
}
