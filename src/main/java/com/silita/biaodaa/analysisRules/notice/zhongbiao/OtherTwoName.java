package com.silita.biaodaa.analysisRules.notice.zhongbiao;

import com.silita.biaodaa.analysisRules.inter.SingleFieldAnalysis;
import com.silita.biaodaa.dao.CommonMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 第二中标人解析
 * Created by hj on 2018/3/20.
 */
@Component
public class OtherTwoName implements SingleFieldAnalysis {

    Logger logger = Logger.getLogger(OtherTwoName.class);

    @Autowired
    CommonMapper commonMapper;

    @Override
    public String analysis(String html,String keyWork) {
            String rangeHtml="";
            String twoPeople = "";
            List<Map<String, Object>> arList = commonMapper.queryRegexByField("twoPeople");
            for (int i = 0; i < arList.size(); i++) {
                String start = arList.get(i).get("startKey").toString();
                String end = arList.get(i).get("endKey").toString();
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
                    twoPeople = rangeHtml.replace("第二名", "").replace("第二候选人", "")
                            .replace("第二中标候选人", "").replace("：","").replace("第2名", "").replace(":", "")
                            .replace("第2中标候选人", "").replace("第三候选人", "").replace("为联合体牵头人","").replace("单位名称","")
                            .replace("第二中标排序单位名称","").replace("投标人名称","").replace("第二中标排序","");
                    if (twoPeople.indexOf("公司") == -1 && twoPeople.indexOf("院") == -1) {
                        twoPeople = "";
                    }
                    if(twoPeople.length() > 0) {
                        break;
                    }
                }
            }
        if(twoPeople.contains("公司")) {
            twoPeople = twoPeople.substring(0,twoPeople.indexOf("公司")+2);
        }else if (twoPeople.contains("院")){
            twoPeople = twoPeople.substring(0,twoPeople.indexOf("院")+1);
        }
        if(twoPeople.length()>21){
            twoPeople = "";
        }
            return twoPeople;
        }
}
