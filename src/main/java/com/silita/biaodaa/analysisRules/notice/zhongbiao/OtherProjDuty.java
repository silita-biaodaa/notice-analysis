package com.silita.biaodaa.analysisRules.notice.zhongbiao;

import com.silita.biaodaa.analysisRules.inter.SingleFieldAnalysis;
import com.silita.biaodaa.service.CommonService;
import com.snatch.model.EsNotice;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 项目经理解析
 * Created by hj on 2018/3/20.
 */
@Component
public class OtherProjDuty implements SingleFieldAnalysis {

    public static Logger logger = Logger.getLogger(OtherProjDuty.class);

    @Autowired
    CommonService commonService;

    @Override
    public String analysis(String html, EsNotice esNotice, String keyWork) {
        String rangeHtml="";
        String projDuty = "";
        List<Map<String, Object>> arList = commonService.queryRegexByField("projDuty");
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
                    if (html.length() - indexStart < 20) {
                        rangeHtml = html.substring(indexStart, html.length());//截取范围开始到结尾
                    } else {
                        rangeHtml = html.substring(indexStart, indexStart + 20);
                    }
                }
                rangeHtml = rangeHtml.replaceAll("\\s*", "");	//去空格
                projDuty = rangeHtml.replace("项目经理", "").replace("项目负责人", "")
                        .replace("建造师姓名", "").replace("：","").replace("项目经理（总监）", "")
                        .replace("项目负责人姓名", "").replace("项目经理姓名", "").replace("姓名","").replace("（总监）","")
                        .replace("（经理）","").replace("，","").replace("；","");
                if(projDuty.contains("）")||projDuty.contains("（")||projDuty.contains("须具备")||projDuty.contains("资质")
                        ||projDuty.contains("证书")||projDuty.contains("注册")||projDuty.contains("及其")||projDuty.contains("中标")
                        ||projDuty.contains("/")||projDuty.contains("执业")||projDuty.contains("、")||projDuty.contains("须具备")
                        ||projDuty.contains("建造师")||projDuty.contains("建筑面"))continue;
                if(projDuty.length() > 0) {
                    projDuty = projDuty.substring(0,3);
                    projDuty = projDuty.replace("，","").replace("；","").replace("、","");
                    break;
                }
            }
        }
        return projDuty;
    }
}
