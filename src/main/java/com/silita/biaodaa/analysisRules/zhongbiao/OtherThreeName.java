package com.silita.biaodaa.analysisRules.zhongbiao;

import com.silita.biaodaa.analysisRules.inter.SingleFieldAnalysis;
import com.silita.biaodaa.dao.AnalyzeRangeMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 第三中标人解析
 * Created by hj on 2018/3/20.
 */
@Component
public class OtherThreeName implements SingleFieldAnalysis {

    Logger logger = Logger.getLogger(OtherThreeName.class);

    @Autowired
    AnalyzeRangeMapper analyzeRangeMapper;

    @Override
    public String analysis(String html,String keyWork) {
        String rangeHtml="";
        String threePeople = "";
        List<Map<String, Object>> arList = analyzeRangeMapper.queryAnalyzeRangeByField("threePeople");
        for (int i = 0; i < arList.size(); i++) {
            String start = arList.get(i).get("rangeStart").toString();
            String end = arList.get(i).get("rangeEnd").toString();
            int indexStart = 0;
            int indexEnd = 0;
            if (!"".equals(start)) {
                indexStart = html.indexOf(start);//范围开始位置
            }
            if (!"".equals(end)) {
                indexEnd = html.indexOf(end);//范围结束位置
            }
            if (indexStart != -1 && indexEnd != -1) {
                if (indexEnd > indexStart) {
                    rangeHtml = html.substring(indexStart, indexEnd + 1);//截取范围之间的文本
                } else if (indexStart > indexEnd) {
                    if (html.length() - indexStart < 30) {
                        rangeHtml = html.substring(indexStart, html.length());//截取范围开始到结尾
                    } else {
                        rangeHtml = html.substring(indexStart, indexStart + 30);
                    }
                }
                rangeHtml = rangeHtml.replaceAll("\\s*", "");	//去空格
                threePeople = rangeHtml.replace("第三名", "").replace("第三候选人", "").replace("3","").replace("、","")
                        .replace("第三中标候选人", "").replace("：","").replace("第3名", "").replace(":", "").replace("1","").replace("2","")
                        .replace("第3中标候选人", "").replace("为联合体牵头人","").replace("单位名称","").replace("第三中标排序单位名称","")
                        .replace("第三中标排序","");
                if (threePeople.indexOf("公司") == -1 && threePeople.indexOf("院") == -1) {
                    threePeople = "";
                }
                if(threePeople.length() > 0) {
                    break;
                }
            }
        }
        if(threePeople.contains("公司")) {
            threePeople = threePeople.substring(0,threePeople.indexOf("公司")+2);
        }else if (threePeople.contains("院")){
            threePeople = threePeople.substring(0,threePeople.indexOf("院")+1);
        }
        if(threePeople.length()>21){
            threePeople = "";
        }
        return threePeople;
    }
}
