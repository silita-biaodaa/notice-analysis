package com.silita.biaodaa.utils;

/**
 * Created by dh on 2018/7/10.
 */
public class HtmlTagUtils {

    public static String clearInvalidTag(String content){
//        content = content.replaceAll("<(?!img|br|p|/p).*?>",""); //去除所有标签，只剩img,br,p
//        content = content.replaceAll("<script[^>]*?>.*?</script >","");//去除script块
//        content = content.replaceAll("(style|class|align)+?=*?\".*\"","");//去除样式属性
//        content = content.replaceAll("<!--[\\s\\S\\W\\w.]*-->","");//去除样式属性
//        content = content.replaceAll("&.*?;","");//去除转义字符'
//        content = content.replaceAll("[  ]*","");//剔除空格
//        content = content.replaceAll("\\n\\n","");//剔除连续换行符

        content = content.replaceAll("(<(?!img|br|p|/p).*?>)" +//剔除连续换行符
                "|(<script[^>]*?>.*?</script >)" +
                "|((style|class|align)+?=*?\".*\")" +
                "|(<!--[\\s\\S\\W\\w.]*-->)" +
                "|(&.*?;)" +
                "|([  ]*)" +
                "|(\\n\\n)","");
        return content;
    }

    public static String clearTagByTable(String content){
//        content = content.replaceAll("<(?!img|br|p|/p|table|/table|tr|/tr|/td|td|span|/span).*?>",""); //去除所有标签，只剩img,br,p
//        content = content.replaceAll("<script[^>]*?>.*?</script >","");//去除script块
//        content = content.replaceAll("<!--[\\s\\S\\W\\w.]*-->","");//去除样式属性
//        content = content.replaceAll("&.*?;","");//去除转义字符'
//        content = content.replaceAll("\\n\\n","");//剔除连续换行符

        content = content.replaceAll("(<(?!img|br|p|/p|table|/table|tr|/tr|/td|td|span|/span).*?>)" + //去除所有标签，只剩img,br,p
                "|(<script[^>]*?>.*?</script >)" +
                "|(<!--[\\s\\S\\W\\w.]*-->)" +
                "|(&.*?;)" +
                "|(\\n\\n)","");
        return content;
    }
}
