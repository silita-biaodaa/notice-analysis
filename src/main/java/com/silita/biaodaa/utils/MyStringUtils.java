package com.silita.biaodaa.utils;




import com.snatch.model.ZhaobiaoDetail;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dh on 2017/5/3.
 */
public class MyStringUtils {

	private static final String DEFAULT_STRING = "___";

	private static final String[] remit = {"网银转账","网上支付","银行保函","保险单","担保函","电汇","转账"};

	/**
	 * 对字符串中的转移字符增加'\'
	 * @param s
	 * @return
	 */
	public static String convertESC(String s){
		String[] e = {"(","（","）",")"};
		for (int i=0; i<e.length;i++){
			int si = s.indexOf(e[i]);
			do {
				if(si !=-1){
					s = s.substring(0,si)+"\\"+s.substring(si);
				}
				si = s.indexOf(e[i],si+2);
			}while (si!=-1);
		}
		return s;
	}

	public static boolean isNotNull(String str){
		if(str !=null && !str.trim().equals("")){
			return true;
		}else{
			return false;
		}
	}

	public static boolean isNotNull(List str){
		if(str !=null && str.size()>0){
			return true;
		}else{
			return false;
		}
	}

	public static boolean isNull(String str){
		return !isNotNull(str);
	}

	public static boolean isNull(List str){
		return !isNotNull(str);
	}


	public static boolean isNull(Object str){
		boolean flag=true;
		if(str==null){
			return flag;
		}
		if(str instanceof String){
			flag= !isNotNull((String)str);
		}else if(str instanceof List){
			flag= !isNotNull((List)str);
		}
		return flag;
	}

	public static boolean isNotNull(Object str){
		boolean flag=false;
		if(str instanceof String){
			flag= isNotNull((String)str);
		}else if(str instanceof List){
			flag= isNotNull((List)str);
		}
		return flag;
	}


	public static List<String> StringSplit(String str, int num) {
		int length = str.length();
		List<String> listStr = new ArrayList<String>();
		int lineNum = length % num == 0 ? length / num : length / num + 1;
		String subStr = "";
		for(int i = 1; i <= lineNum; i++){
			if(i < lineNum){
				subStr = str.substring((i-1) * num, i * num);
			}else{
				subStr = str.substring((i-1) * num, length);
			}
			listStr.add(subStr);
		}
		return listStr;
	}

	public static String assureStr(String assureStr, ZhaobiaoDetail zd){
		if(assureStr.equals("投标截止时间") || assureStr.equals("投标截止前")) {
			String tnEndDate = zd.getTbEndDate();
			if(zd.getTbEndDate().length() > 10) {	//投标截至时间去掉时间点
				tnEndDate = tnEndDate.substring(0, zd.getTbEndDate().indexOf("/"));
			}
			return tnEndDate;
		} else if(assureStr.equals("投标截止前一天") || assureStr.equals("开标前一天")) {
			String tnEndDate = zd.getTbEndDate();
			if(zd.getTbEndDate().length() > 10) {
				tnEndDate = tnEndDate.substring(0, zd.getTbEndDate().indexOf("/"));
			}
			return DateUtils.getSpecifiedDayBefore(tnEndDate);	//得到前一天
		} else if(assureStr.equals("报名截止时间")) {
			return zd.getBmEndDate();
		} else {
			return assureStr;
		}
	}

	public static List bmDateStr(List<String> list, ZhaobiaoDetail zd){
		String year = zd.getGsDate().substring(0, 5);
		for (int j = 0; j < list.size(); j++) {
			if (list.get(j).length() < 8) {	//没有年份(2016-01-01至01-05)拼接公告时间的年份
				list.set(j, year + list.get(j));
			}
			if (list.get(j).contains("公告时间")) {	//替换公告时间
				list.set(j, zd.getGsDate());
			}
		}
		List<String> dateList = new ArrayList<String>();
		if (DateUtils.compareDateStr2(list.get(0), list.get(1)) == 2) {	//第二个大于第一个(只取前俩个时间调整2个时间位置)
			dateList.add(0, list.get(0));
			dateList.add(1, list.get(1));
		} else if(DateUtils.compareDateStr2(list.get(0), list.get(1)) == 1) {
			dateList.add(0, list.get(1));
			dateList.add(1, list.get(0));
		}
		return dateList;
	}

	public static boolean isNotDefaultStringAndNull(String var){
		if (var != null && !DEFAULT_STRING.equals(var)) {
			return true;
		}
		return false;
	}

	public static String deleteHtmlTag (String content) {
		content = content.replaceAll("\\s*",""); // 去除空格
		String regEx_html="<.+?>"; // HTML标签的正则表达式
		Pattern pattern = Pattern.compile(regEx_html);
		Matcher matcher = pattern.matcher(content);
		content = matcher.replaceAll("");
		content = content.replaceAll("&nbsp;","");
		content = content.replaceAll(" ","");
		return content;
	}

	public static String findAssureSumRemit (String str) {
		String assureSumRemit = "";
		for (int i = 0; i < remit.length; i++) {
			if (str.contains(remit[i])) {
				if (StringUtils.isBlank(assureSumRemit)) {
					assureSumRemit = remit[i];
				} else {
					assureSumRemit += "、"+remit[i];
				}
			}
		}
		return assureSumRemit;
	}

}
