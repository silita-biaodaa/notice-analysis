package com.silita.biaodaa.utils;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dh on 2018/11/2.
 */
public class RegexUtils {
    private static Logger logger = Logger.getLogger(RegexUtils.class);

    /**
     * 判断字符是否有匹配的值。
     * @param s
     * @param regex
     * @return
     */
    public static boolean matchExists(String s,String regex){
        boolean isExists =false;
        try {
            if(s != null) {
                Pattern ptn = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
                Matcher mt = ptn.matcher(s);
                isExists = mt.find();
//            logger.debug("find:" + isExists);
                if (isExists) {
                    logger.debug("group:" + mt.group());
                }
            }
        }catch(Exception e){
            logger.error(e,e);
        }
        return isExists;
    }

    /**
     * 遍历字符数组，返回匹配表达式的集合
     * @param arr
     * @param regex
     * @return
     */
    public static List<String> matchExists(String[] arr, String regex){
        List<String> matchList = new ArrayList<String>();
        if(arr!=null) {
            for (String s : arr) {
                if (matchExists(s, regex)) {
                    matchList.add(s);
                }
            }
        }
        return matchList;
    }

    /**
     * 提取匹配表达式的值
     * @param s
     * @param regex
     * @return
     */
    public static String matchValue(String s,String regex){
        String mStr=null;
        try {
            if(s != null) {
                Pattern ptn = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
                Matcher mt = ptn.matcher(s);
                if (mt.find()) {
                    mStr = mt.group();
                    logger.debug("group:" + mt.group());
                }
            }
        }catch(Exception e){
            logger.error(e,e);
        }
        return mStr;
    }

    public static String insertMatchValuePos(String s,String regex,String posfix){
        String mStr=null;
        try {
            if(s != null) {
                StringBuilder sb = new StringBuilder(s);
                Pattern ptn = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
                Matcher mt = ptn.matcher(s);
                while(mt.find()) {
                    mStr = mt.group();
                    int sIdx = sb.indexOf(mStr);
                    int eIdx = sIdx+mStr.length();
                    //匹配字符后面没有posfix时，才进行插入补充
                    if((eIdx+1)<s.length()
                            && s.substring(eIdx,eIdx+1).indexOf(posfix)==-1){
                        sb.insert(eIdx,posfix);
                    }else if(eIdx+1>s.length()){
                        sb.insert(eIdx,posfix);
                    }
                }
                if(s.length() < sb.length()){
                    s = sb.toString();
                }
            }
        }catch(Exception e){
            logger.error(e,e);
        }
        return s;
    }

}
