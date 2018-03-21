package com.silita.biaodaa.analysisRules.zhaobiao.other;

import com.silita.biaodaa.analysisRules.inter.DoubleFieldAnalysis;
import com.silita.biaodaa.cache.GlobalCache;
import com.silita.biaodaa.dao.AnalyzeRangeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 其他站点报名开始结束时间解析
 * Created by 91567 on 2018/3/21.
 */
@Component
public class OtherApplyBmEndDate implements DoubleFieldAnalysis {

    @Autowired
    AnalyzeRangeMapper analyzeRangeMapper;

    @Override
    public List analysis(String segment) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
        SimpleDateFormat df2 = new SimpleDateFormat("MM-dd");// 设置日期格式2
        String dateRegex = "(\\d{4}-\\d{1,2}-\\d{1,2})|(\\d{4}年\\d{1,2}月\\d{1,2})";//匹配日期格式1
        String dateRegex2 = "(\\d{1,2}月\\d{1,2})";//匹配日期格式2
        List<String> list = null;
        List<String> list2 = null;
        String rangeHtml = "";

        Map<String, List<Map<String, Object>>> analyzeRangeByFieldMap = GlobalCache.getGlobalCache().getAnalyzeRangeByFieldMap();
        List<Map<String, Object>> arList = analyzeRangeByFieldMap.get("applyDate");
        if (arList == null) {
            arList = analyzeRangeMapper.queryAnalyzeRangeByField("applyDate");
            analyzeRangeByFieldMap.put("applyDate", arList);
            GlobalCache.getGlobalCache().setAnalyzeRangeByFieldMap(analyzeRangeByFieldMap);
        } else {
            System.out.println("=========applyDate=======走的缓存=======");
        }

        for (int i = 0; i < arList.size(); i++) {
            String rangeRegex = arList.get(i).get("regex").toString();
            Pattern rangePat = Pattern.compile(rangeRegex);
            Matcher rangeMat = rangePat.matcher(segment);
            if (rangeMat.find()) {
                list = new ArrayList<String>();
                rangeHtml = rangeMat.group();
                //处理
                if (rangeHtml.contains("即日起") || rangeHtml.contains("发布之日起") || rangeHtml.contains("发布之时起")) {
                    list.add("openDate");
                    if (rangeHtml.contains("投标截止时间")) {
                        list.add("tbEndDate");
                        break;
                    }
                }
                Pattern datePat = Pattern.compile(dateRegex);
                Matcher dateMat = datePat.matcher(rangeHtml);
                while (dateMat.find()) {
                    try {
                        list.add(df.format(df.parse(dateMat.group().replaceAll("年", "-").replaceAll("月", "-"))));
                    } catch (ParseException e) {
                        e.printStackTrace();
                        continue;
                    }
                }
                //处理这种情况 2017年 02 月 03 日至 02 月 08 日
                if (list.size() == 1) {
                    list2 = new ArrayList<String>();
                    Pattern datePat2 = Pattern.compile(dateRegex2);
                    Matcher dateMat2 = datePat2.matcher(rangeHtml);
                    while (dateMat2.find()) {
                        try {
                            list2.add(df2.format(df2.parse(dateMat2.group().replaceAll("年", "-").replaceAll("月", "-"))));
                        } catch (ParseException e) {
                            e.printStackTrace();
                            continue;
                        }
                    }
                    if (list2.size() > 0) {
                        String year = list.get(0).substring(0, 5);
                        String bmEndDate = year + list2.get(list2.size() - 1);
                        list.add(bmEndDate);
                    }
                    //排除bmStratDate = 排除bmEndDate
                    if (list.get(0).equals(list.get(1))) {
                        list.clear();
                    }
                }
                if (list.size() > 1) {
                    break;
                }
            }
        }
        return list;
    }
}
