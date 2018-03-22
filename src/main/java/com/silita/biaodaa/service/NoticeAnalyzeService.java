package com.silita.biaodaa.service;

import com.silita.biaodaa.cache.GlobalCache;
import com.silita.biaodaa.dao.AnalyzeRangeMapper;
import com.silita.biaodaa.utils.CNNumberFormat;
import com.silita.biaodaa.utils.ChineseCompressUtil;
import com.silita.biaodaa.utils.MyStringUtils;
import com.snatch.model.AnalyzeDetail;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhangxiahui on 18/3/13.
 */
@Component
public class NoticeAnalyzeService {

    Logger logger = Logger.getLogger(NoticeAnalyzeService.class);

    @Autowired
    AnalyzeRangeMapper analyzeRangeMapper;

    /**
     * 解析保证金
     * 正则匹配
     * return 保证金
     */
    public String analyzeApplyDeposit(String html) {
        String rangeHtml="";
        String deposit = "";

        Map<String,List<Map<String, Object>>> analyzeRangeByFieldMap = GlobalCache.getGlobalCache().getAnalyzeRangeByFieldMap();
        List<Map<String, Object>> arList = analyzeRangeByFieldMap.get("applyDeposit");
        if(arList == null){
            arList = analyzeRangeMapper.queryAnalyzeRangeByField("applyDeposit");
            analyzeRangeByFieldMap.put("applyDeposit",arList);
            GlobalCache.getGlobalCache().setAnalyzeRangeByFieldMap(analyzeRangeByFieldMap);
        }else{
            logger.info("=========applyDeposit=======走的缓存=======");
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
                        int cnn=CNNumberFormat.ChnStringToNumber(mat2.group().replaceAll("元", ""));
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

    /**
     * 解析项目金额
     * 正则匹配
     * return 项目金额
     */
    public String analyzeApplyProjSum(String html) {
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

    /**
     * 解析报名时间
     * 正则匹配
     * return 报名开始和结束
     */
    public List<String> analyzeApplyBmEndDate(String html){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
        SimpleDateFormat df2 = new SimpleDateFormat("MM-dd");// 设置日期格式2
        String dateRegex = "(\\d{4}-\\d{1,2}-\\d{1,2})|(\\d{4}年\\d{1,2}月\\d{1,2})";//匹配日期格式1
        String dateRegex2 = "(\\d{1,2}月\\d{1,2})";//匹配日期格式2
        List<String> list = null;
        List<String> list2 = null;
        String rangeHtml="";

        Map<String,List<Map<String, Object>>> analyzeRangeByFieldMap = GlobalCache.getGlobalCache().getAnalyzeRangeByFieldMap();
        List<Map<String, Object>> arList = analyzeRangeByFieldMap.get("applyDate");
        if(arList == null){
            arList = analyzeRangeMapper.queryAnalyzeRangeByField("applyDate");
            analyzeRangeByFieldMap.put("applyDate",arList);
            GlobalCache.getGlobalCache().setAnalyzeRangeByFieldMap(analyzeRangeByFieldMap);
        }else{
            logger.info("=========applyDate=======走的缓存=======");
        }

        for (int i = 0; i < arList.size(); i++) {
            String rangeRegex = arList.get(i).get("regex").toString();
            Pattern rangePat = Pattern.compile(rangeRegex);
            Matcher rangeMat = rangePat.matcher(html);
            if (rangeMat.find()) {
                list = new ArrayList<String>();
                rangeHtml = rangeMat.group();
                //处理
                if(rangeHtml.contains("即日起") || rangeHtml.contains("发布之日起") || rangeHtml.contains("发布之时起")) {
                    list.add("openDate");
                    if(rangeHtml.contains("投标截止时间")) {
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
                if(list.size() == 1) {
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
                    if(list2.size()>0) {
                        String year = list.get(0).substring(0,5);
                        String bmEndDate = year + list2.get(list2.size()-1);
                        list.add(bmEndDate);
                    }
                    //排除bmStratDate = 排除bmEndDate
                    if(list.get(0).equals(list.get(1))) {
                        list.clear();
                    }
                }
                if(list.size()>1){
                    break;
                }
            }
        }
        return list;
    }

    /**
     * 解析开标时间(投标截止时间)
     * @param html
     * @return
     */
    public String analyzeApplyTbEndDate(String html){
        SimpleDateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
        SimpleDateFormat dfTime = new SimpleDateFormat("HH:mm");
        String dateRegex = "(\\d{4}-\\d{1,2}-\\d{1,2})|(\\d{4}年\\d{1,2}月\\d{1,2})";//匹配日期格式1
        String timeRegex = "(\\d{1,2}:\\d{2})|(\\d{1,2}时\\d{2})|(\\d{1,2}：\\d{2})";
        List<String> list = null;
        List<String> list2 = null;
        String bmEndDateAndTime = "";
        String rangeHtml="";

        Map<String,List<Map<String, Object>>> analyzeRangeByFieldMap = GlobalCache.getGlobalCache().getAnalyzeRangeByFieldMap();
        List<Map<String, Object>> arList = analyzeRangeByFieldMap.get("applyBidDate");
        if(arList == null){
            arList = analyzeRangeMapper.queryAnalyzeRangeByField("applyBidDate");
            analyzeRangeByFieldMap.put("applyDate",arList);
            GlobalCache.getGlobalCache().setAnalyzeRangeByFieldMap(analyzeRangeByFieldMap);
        }else{
            logger.info("=========applyDate=======走的缓存=======");
        }

        for (int i = 0; i < arList.size(); i++) {
            String rangeRegex = arList.get(i).get("regex").toString();
            Pattern rangePat = Pattern.compile(rangeRegex);
            Matcher rangeMat = rangePat.matcher(html);
            if (rangeMat.find()) {
                list = new ArrayList<String>();
                list2 = new ArrayList<String>();
                rangeHtml = rangeMat.group();
                //匹配日期
                Pattern datePat = Pattern.compile(dateRegex);
                Matcher dateMat = datePat.matcher(rangeHtml);
                while (dateMat.find()) {
                    try {
                        list.add(dfDate.format(dfDate.parse(dateMat.group().replaceAll("年", "-").replaceAll("月", "-"))));
                    } catch (ParseException e) {
                        e.printStackTrace();
                        continue;
                    }
                }
                //匹配时间
                Pattern timePat = Pattern.compile(timeRegex);
                Matcher timeMat = timePat.matcher(rangeHtml);
                while (timeMat.find()) {
                    try {
                        list2.add(dfTime.format(dfTime.parse(timeMat.group().replaceAll("时", ":").replaceAll("：",":"))));
                    } catch (ParseException e) {
                        e.printStackTrace();
                        continue;
                    }
                }
                //拼凑日期+时间
                if(list2.size() > 0 && list.size() > 0) {
                    bmEndDateAndTime = list.get(list.size()-1) + list2.get(list2.size()-1);
                } else if(list.size() > 0){
                    bmEndDateAndTime = list.get(list.size()-1);
                }
                if(MyStringUtils.isNotNull(bmEndDateAndTime)){
                    break;
                }
            }
        }
        return bmEndDateAndTime;
    }

    public String analyzeApplyTime(String html) {
        SimpleDateFormat dftime = new SimpleDateFormat("HH:mm");
        String bmEndTime = "";
        String rangeHtml = "";
        Map<String,List<Map<String, Object>>> analyzeRangeByFieldMap = GlobalCache.getGlobalCache().getAnalyzeRangeByFieldMap();
        List<Map<String, Object>> arList = analyzeRangeByFieldMap.get("applyDate");
        if(arList == null){
            arList = analyzeRangeMapper.queryAnalyzeRangeByField("applyDate");
            analyzeRangeByFieldMap.put("applyDate",arList);
            GlobalCache.getGlobalCache().setAnalyzeRangeByFieldMap(analyzeRangeByFieldMap);
        }else{
            logger.info("=========applyDate=======走的缓存=======");
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
                if (indexEnd > indexStart) {
                    rangeHtml = html.substring(indexStart, indexEnd + 1);//截取范围之间的文本
                } else if (indexStart > indexEnd) {
                    if (html.length() - indexStart >= 80) {
                        rangeHtml = html.substring(indexStart, indexStart + 80);//截取范围开始至后100个字符
                    } else {
                        rangeHtml = html.substring(indexStart, html.length());//截取范围开始至后100个字符
                    }
                }
                //匹配时间
                String regTime = "(\\d{1,2}:\\d{2}|(\\d{1,2}时)|\\d{1,2}：\\d{2})";
//                String regTime = "(\\d{1,2}:\\d{1,2})|(\\d{1,2}时)";
                Pattern patTime = Pattern.compile(regTime);
                rangeHtml = rangeHtml.replaceAll("\\s*", "");    //去空格
                Matcher matTime = patTime.matcher(rangeHtml);
                while (matTime.find()) {
                    if ("".equals(bmEndTime)) {
                        try {
                            bmEndTime = dftime.format(dftime.parse(matTime.group().replaceAll("时", ":00").replaceAll("：",":")));    //得到时间
                        } catch (ParseException e) {
                            e.printStackTrace();
                            continue;
                        }
//                        if(bmEndTime != ""){
//                            break;
//                        }
                    }
                }
            }
        }
        return bmEndTime;
    }

    public void insertAnalyzeDetail(AnalyzeDetail analyzeDetail){
        analyzeRangeMapper.insertAnalyzeDetail(analyzeDetail);
    }

    public void batchInsertAnalyzeDetail(List<AnalyzeDetail> list){
        Map<String,Object> param = new HashMap<>();
        param.put("list",list);
        analyzeRangeMapper.batchInsertAnalyzeDetail(param);
    }

    /**
     * 解析报名地址
     * 数据匹配
     * return 报名地址
     */
    public String analyzeApplyAddress(String html) {
        String rangeHtml="";
        String address = null;
        List<Map<String, Object>> arList = analyzeRangeMapper.queryAnalyzeRangeByField("applyAddress");
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
            if(indexStart != -1 && indexEnd != -1){
                if(indexEnd > indexStart){
                    rangeHtml = html.substring(indexStart, indexEnd+1);//截取范围之间的文本
                }else if(indexStart > indexEnd) {
                    if(html.length() - indexStart >= 80){
                        rangeHtml = html.substring(indexStart, indexStart + 80);
                    }else{
                        rangeHtml = html.substring(indexStart, html.length());//截取范围开始到结尾
                    }
                }
                List<String> addrList = analyzeRangeMapper.queryAnalyzeRangeBmAddr();
                for (int j = 0; j < addrList.size(); j++) {
                    int indexNum =  rangeHtml.indexOf(addrList.get(j));
                    if(indexNum != -1){
                        address = addrList.get(j);
                        break;
                    }
                }
                if(!"".equals(address)){
                    //查询标准化地址
                    List<String> baseList = analyzeRangeMapper.queryBaseBmAddress(address);
                    if(!baseList.isEmpty()){
                        address = baseList.get(0);
                    }
                    break;
                }
            }
        }
//		if("".equals(address)) {
        if(html.indexOf("下载招标文件") != -1 ||html.indexOf("下载获取招标文件") != -1) {
            return "网上下载";
        }
        int start = html.indexOf("下载");
        int end = html.indexOf("招标文件");
        if((start != -1 && end != -1) && (end - start > 0 && end - start <20)) {
            return "网上下载";
        }
        if(html.indexOf("在") != -1 && html.indexOf("进行网上下载") != -1) {
            return "网上下载";
        }
//		}
        return address;
    }

}
