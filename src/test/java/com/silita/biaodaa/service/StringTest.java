package com.silita.biaodaa.service;

import com.silita.biaodaa.utils.MyStringUtils;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 91567 on 2018/3/20.
 */
public class StringTest {

    public static void main(String[] args) {
        String dateStr = "2017-03-12";
        System.out.println(dateStr.substring(0,4));
    }

    @Test
    public void test(){
        String ss = "网上</p>";
        System.out.println(ss.length());
    }

    @Test
    public void testDate()throws Exception{
        String rangeHtml="\n" +
                "开标时间倒计时\t\n" +
                "18\t\t19\t\t56\t\t04\t\n" +
                "投标保证金缴纳截止时间:2018-04-23 09:00:00开标时间:2018-04-23 09:00:00\n" +
                " \n" +
                "沅江市共华镇中心小学教师周转房项目\n" +
                "施工招标公告\n" +
                " \n" +
                "1.招标条件\n" +
                "本招标项目沅江市共华镇中心小学教师周转房项目。已由沅江市发展和改革局以沅发改核【2018】23号文件批准建设，建设资金来自财政资金，招标人为沅江市共华镇中心小学，招标代理机构为湖南广大天平工程项目管理有限公司，项目已具备招标条件，现对该项目的施工进行公开招标。\n" +
                "2.项目概况与招标范围\n" +
                "2.1  项目名称：沅江市共华镇中心小学教师周转房项目\n" +
                "2.2  建设地点：沅江市共华镇\n" +
                "2.3  规    模：本工程包括建筑工程、装饰装修工程，安装工程,总建筑面积1259.23平方米，建筑层数四层，结构形式为砖混结构，总投资约254.46万元。\n" +
                "2.4  工期要求：120日历天。\n" +
                "2.5  质量要求：达到合格工程标准。\n" +
                "2.6  保修要求：按照国务院279号令、建设部80号令。\n" +
                "   2.7  招标范围：沅江市共华镇中心小学教师周转房项目，包括建筑工程、装饰装修工程及安装工程等，具体内容详见工程量清单和施工图纸。\n" +
                "2.8  标段划分：1个标段\n" +
                "3.投标人资格要求";
        String timeRegex = "(\\d{1,2}:\\d{2})|(\\d{1,2}时\\d{0,2})|(\\d{1,2}：\\d{2})|(\\d{1,2}点\\d{0,2})";
        //匹配时间
        Pattern timePat = Pattern.compile(timeRegex);
        Matcher timeMat = timePat.matcher(rangeHtml);
        while (timeMat.find()) {
            if (MyStringUtils.isNull(timeMat.group().replaceAll("\\d{0,2}时", ""))) {

            } else {
                SimpleDateFormat dfTime = new SimpleDateFormat("HH:mm");
                dfTime.format(dfTime.parse(timeMat.group().replaceAll("时", ":").replaceAll("：", ":").replaceAll("点", ":")));
            }
        }
    }
}
