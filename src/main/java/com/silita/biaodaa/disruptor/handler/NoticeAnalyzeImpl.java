//package com.silita.biaodaa.disruptor.handler;
//import cloud.simple.service.commons.utils.AnalysisUtils;
//import cloud.simple.service.commons.utils.CNNumberFormat;
//import cloud.simple.service.commons.utils.DateUtils;
//import cloud.simple.service.commons.utils.ProjTypeUtil;
//import cloud.simple.service.dao.AnalyzeDao;
//import com.snatch.model.AreaType;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//
//@Service("NoticeAnalyzeServicePinqi")
//public class NoticeAnalyzeImpl implements NoticeAnalyze{
//
//	@Autowired
//	private AnalyzeDao dao;
//
//	//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
//	//SimpleDateFormat df1 = new SimpleDateFormat("MM-dd");// 设置日期格式
//	//SimpleDateFormat dftime = new SimpleDateFormat("HH:mm");
//
//	//资金来源
//	public final String ZJArr[] = new String[]{"财政", "投资", "国", "政府", "中央", "专项建设基金", "专项资金", "上级", "省", "补助",
//			"补贴", "市", "县", "区", "筹集", "地方配套", "建设单位筹措","建设资金","后扶资金","项目资金"};
//	public final String ZJArr1[] = new String[]{"银行贷款", "融资", "国拨(债)", "贷款", "统贷", "债券基金", "借资", "政策性贷款"};
//	public final String ZJArr2[] = new String[]{"国际组织", "外国政府", "国际组织贷款资金", "外国政府及其机构贷款资金"};
//	//保证金截止时间
//	private final String[] EndDate = new String[]{"投标截止时前一天","投标截止时间前1个工作日", "投标截止时间前一个工作日", "投标文件截止时间前",
//			"投标截止时间前一天", "递交投标文件截止前一天", "投标截止日期前一天", "投标文件递交截止时间前一天"};
//
//	/**
//	 * 解析报名时间
//	 * 正则匹配
//	 * return 报名开始和结束
//	 */
//	@Override
//	public List<String> analyzeApplyDate(String html){
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
//		SimpleDateFormat df1 = new SimpleDateFormat("MM-dd");// 设置日期格式
//		List<String> list = new ArrayList<String>();
//		String rangeHtml="";
//		List<Map<String, Object>> arList = dao.queryAnalyzeRangeByField("applyDate");
//		for (int i = 0; i < arList.size(); i++) {
//			String start = arList.get(i).get("rangeStart").toString();
//			String end = arList.get(i).get("rangeEnd").toString();
//			int indexStart=0;
//			int indexEnd=0;
//			if(!"".equals(start)){
//				indexStart = html.indexOf(start);//范围开始位置
//			}
//			if(!"".equals(end)){
//				indexEnd = html.indexOf(end);//范围结束位置
//			}
//			if(indexStart > -1 && indexEnd> -1){
//				if(indexEnd > indexStart){
//					rangeHtml = html.substring(indexStart, indexEnd+1);//截取范围之间的文本
//				}else{
//					if(html.length()-indexStart>=80){
//						rangeHtml = html.substring(indexStart, indexStart+80);//截取范围开始至后100个字符
//					}else{
//						rangeHtml = html.substring(indexStart, html.length());//截取范围开始至后100个字符
//					}
//				}
//				String regEx = "(\\d{4}-\\d{1,2}-\\d{1,2})|(\\d{4}年\\d{1,2}月\\d{1,2})";//匹配日期
//				Pattern pat = Pattern.compile(regEx);
//				Matcher mat = pat.matcher(rangeHtml);
//				list = new ArrayList<String>();
//				while (mat.find()) {
//					try {
//						list.add(df.format(df.parse(mat.group().replaceAll("年", "-").replaceAll("月", "-"))));
//					} catch (ParseException e) {
//						e.printStackTrace();
//						continue;
//					}
//				}
//				if(list.size()>1){
//					break;
//				}
//			}
//		}
//		if(list.size() < 2) {
//			for (int i = 0; i < arList.size(); i++) {
//				String start = arList.get(i).get("rangeStart").toString();
//				String end = arList.get(i).get("rangeEnd").toString();
//				int indexStart = 0;
//				int indexEnd = 0;
//				if (!"".equals(start)) {
//					indexStart = html.indexOf(start);//范围开始位置
//				}
//				if (!"".equals(end)) {
//					indexEnd = html.indexOf(end);//范围结束位置
//				}
//				if (indexStart > -1 && indexEnd > -1) {
//					if (indexEnd > indexStart) {
//						rangeHtml = html.substring(indexStart, indexEnd + 1);//截取范围之间的文本
//					} else if (indexStart > indexEnd) {
//						if (html.length() - indexStart >= 60) {
//							rangeHtml = html.substring(indexStart, indexStart + 60);//截取范围开始至后100个字符
//						} else {
//							rangeHtml = html.substring(indexStart, html.length());//截取范围开始至后100个字符
//						}
//					}
//					String regEx = "(\\d{1,2}-\\d{1,2})|(\\d{1,2}月\\d{1,2})";//匹配日期
//					Pattern pat = Pattern.compile(regEx);
//					rangeHtml = rangeHtml.replaceAll("\\s*", "");    //去空格
//					Matcher mat = pat.matcher(rangeHtml);
//					list = new ArrayList<String>();
//					if (rangeHtml.contains("即日起")) {
//						list.add("公告时间");
//						while (mat.find()) {
//							try {
//								list.add(df1.format(df1.parse(mat.group().replaceAll("年", "-").replaceAll("月", "-"))));
//							} catch (ParseException e) {
//								e.printStackTrace();
//							}
//						}
//						if (list.size() == 2) {
//							break;
//						}
//					} else {
//						while (mat.find()) {
//							try {
//								list.add(df1.format(df1.parse(mat.group().replaceAll("年", "-").replaceAll("月", "-"))));
//							} catch (ParseException e) {
//								e.printStackTrace();
//								continue;
//							}
//						}
//						if (list.size() > 1) {
//							break;
//						}
//					}
//					if (list.size() > 1) {
//						if (DateUtils.compareDateStr(list.get(0), list.get(1)) > 30 ) {
//							list.clear();
//						}
//					}
//					if (list.size() > 1) {
//						break;
//					}
//				}
//			}
//		}
//		return list;
//	}
//
//	@Override
//	public String analyzeApplyTime(String html) {
//		SimpleDateFormat dftime = new SimpleDateFormat("HH:mm");
//		String bmEndTime = "";
//		String rangeHtml = "";
//		List<Map<String, Object>> arList = dao.queryAnalyzeRangeByField("applyDate");
//		for (int i = 0; i < arList.size(); i++) {
//			String start = arList.get(i).get("rangeStart").toString();
//			String end = arList.get(i).get("rangeEnd").toString();
//			int indexStart=0;
//			int indexEnd=0;
//			if(!"".equals(start)){
//				indexStart = html.indexOf(start);//范围开始位置
//			}
//			if(!"".equals(end)){
//				indexEnd = html.indexOf(end);//范围结束位置
//			}
//			if(indexStart > -1 && indexEnd> -1){
//				if (indexEnd > indexStart) {
//					rangeHtml = html.substring(indexStart, indexEnd + 1);//截取范围之间的文本
//				} else if (indexStart > indexEnd) {
//					if (html.length() - indexStart >= 80) {
//						rangeHtml = html.substring(indexStart, indexStart + 80);//截取范围开始至后100个字符
//					} else {
//						rangeHtml = html.substring(indexStart, html.length());//截取范围开始至后100个字符
//					}
//				}
//				//匹配时间
//				String regTime = "(\\d{1,2}:\\d{2}|(\\d{1,2}时)|\\d{1,2}：\\d{2})";
////                String regTime = "(\\d{1,2}:\\d{1,2})|(\\d{1,2}时)";
//				Pattern patTime = Pattern.compile(regTime);
//				rangeHtml = rangeHtml.replaceAll("\\s*", "");    //去空格
//				Matcher matTime = patTime.matcher(rangeHtml);
//				while (matTime.find()) {
//					if ("".equals(bmEndTime)) {
//						try {
//							bmEndTime = dftime.format(dftime.parse(matTime.group().replaceAll("时", ":00").replaceAll("：",":")));    //得到时间
//						} catch (ParseException e) {
//							e.printStackTrace();
//							continue;
//						}
////                        if(bmEndTime != ""){
////                            break;
////                        }
//					}
//				}
//			}
//		}
//		return bmEndTime;
//	}
//
//	/**
//	 * 解析报名地址
//	 * 数据匹配
//	 * return 报名地址
//	 */
//	@Override
//	public String analyzeApplyAddress(String html) {
//		String rangeHtml="";
//		String address = "";
//		List<Map<String, Object>> arList = dao.queryAnalyzeRangeByField("applyAddress");
//		for (int i = 0; i < arList.size(); i++) {
//			String start = arList.get(i).get("rangeStart").toString();
//			String end = arList.get(i).get("rangeEnd").toString();
//			int indexStart=0;
//			int indexEnd=0;
//			if(!"".equals(start)){
//				indexStart = html.indexOf(start);//范围开始位置
//			}
//			if(!"".equals(end)){
//				indexEnd = html.indexOf(end);//范围结束位置
//			}
//			if(indexStart != -1 && indexEnd != -1){
//				if(indexEnd > indexStart){
//					rangeHtml = html.substring(indexStart, indexEnd+1);//截取范围之间的文本
//				}else if(indexStart > indexEnd) {
//					if(html.length() - indexStart >= 80){
//						rangeHtml = html.substring(indexStart, indexStart + 80);
//					}else{
//						rangeHtml = html.substring(indexStart, html.length());//截取范围开始到结尾
//					}
//				}
//				List<String> addrList = dao.queryAnalyzeRangeBmAddr();
//				for (int j = 0; j < addrList.size(); j++) {
//					int indexNum =  rangeHtml.indexOf(addrList.get(j));
//					if(indexNum != -1){
//						address = addrList.get(j);
//						break;
//					}
//				}
//				if(!"".equals(address)){
//					//查询标准化地址
//					List<String> baseList = dao.queryBaseBmAddress(address);
//					if(!baseList.isEmpty()){
//						address = baseList.get(0);
//					}
//					break;
//				}
//			}
//		}
////		if("".equals(address)) {
//		if(html.indexOf("下载招标文件") != -1 ||html.indexOf("下载获取招标文件") != -1) {
//			return "网上下载";
//		}
//		int start = html.indexOf("下载");
//		int end = html.indexOf("招标文件");
//		if((start != -1 && end != -1) && (end - start > 0 && end - start <20)) {
//			return "网上下载";
//		}
//		if(html.indexOf("在") != -1 && html.indexOf("进行网上下载") != -1) {
//			return "网上下载";
//		}
////		}
//		return address;
//	}
//
//	/**
//	 * 解析保证金
//	 * 正则匹配
//	 * return 保证金
//	 */
//	@Override
//	public String analyzeApplyDeposit(String html) {
//		String rangeHtml="";
//		String deposit = "";
//		List<Map<String, Object>> arList = dao.queryAnalyzeRangeByField("applyDeposit");
//		for (int i = 0; i < arList.size(); i++) {
//			String start = arList.get(i).get("rangeStart").toString();
//			String end = arList.get(i).get("rangeEnd").toString();
//			int indexStart=0;
//			int indexEnd=0;
//			if(!"".equals(start)){
//				indexStart = html.indexOf(start);//范围开始位置
//			}
//			if(!"".equals(end)){
//				indexEnd = html.indexOf(end);//范围结束位置
//			}
//			if(indexStart > -1 && indexEnd> -1){
//				if(indexEnd > indexStart){
//					rangeHtml = html.substring(indexStart, indexEnd+1);//截取范围之间的文本
//				}else if(indexStart > indexEnd) {
//					if(html.length()-indexStart>=50){
//						rangeHtml = html.substring(indexStart, indexStart+50);//截取范围开始至后30个字符
//					}else{
//						rangeHtml = html.substring(indexStart, html.length());//截取范围开始至后30个字符
//					}
//				}
//				//匹配中文人民币
//				String regExCn = "([零壹贰叁肆伍陆柒捌玖拾佰仟万亿])";//大写人民币
//				Pattern pat1 = Pattern.compile(regExCn);
//				rangeHtml = rangeHtml.replaceAll("\\s*", "");	//去空格
//				Matcher mat1 = pat1.matcher(rangeHtml);
//				String bigDeposit="";
//				while (mat1.find()) {
//					bigDeposit+=mat1.group();
//				}
//				if(bigDeposit.length()>0){
//					int cnn = CNNumberFormat.ChnStringToNumber(bigDeposit);
//					if(cnn>=1000){//保证金需大于等于1000
//						deposit = String.valueOf(cnn);
//						break;
//					}
//				}
//
//				//匹配阿拉伯人民币
//				String regExAr = "\\d+(\\.\\d+)?亿元|\\d+(\\.\\d+)?万元|\\d+(\\.\\d+)?元";//阿拉伯人民币
//				Pattern pat2 = Pattern.compile(regExAr);
//				Matcher mat2 = pat2.matcher(rangeHtml);
//				while (mat2.find()) {
//					if("".equals(deposit)){
//						int cnn=CNNumberFormat.ChnStringToNumber(mat2.group().replaceAll("元", ""));
//						if(cnn>0){
//							deposit = String.valueOf(cnn);
//							if(Double.parseDouble(deposit)<1000){//项目金额需大于等于1000
//								deposit="";
//							}
//						}else{
//							deposit = mat2.group().replaceAll("万", "").replaceAll("元", "");
//							if(Double.parseDouble(deposit)<1000){//项目金额需大于等于1000
//								deposit="";
//							}
//						}
//					}
//				}
//				if(deposit.length()>0){
//					break;
//				}
//			}
//		}
//		return deposit;
//	}
//
//	/**
//	 * 解析项目金额
//	 * 正则匹配
//	 * return 项目金额
//	 */
//	@Override
//	public String analyzeApplyProjSum(String html) {
//		String rangeHtml="";
//		String deposit = "";
//		List<Map<String, Object>> arList = dao.queryAnalyzeRangeByField("applyProjSum");
//		for (int i = 0; i < arList.size(); i++) {
//			String start = arList.get(i).get("rangeStart").toString();
//			String end = arList.get(i).get("rangeEnd").toString();
//			int indexStart=0;
//			int indexEnd=0;
//			if(!"".equals(start)){
//				indexStart = html.indexOf(start);//范围开始位置
//			}
//			if(!"".equals(end)){
//				indexEnd = html.indexOf(end);//范围结束位置
//			}
//			if(indexStart > -1 && indexEnd> -1){
//				if(indexEnd > indexStart){
//					rangeHtml = html.substring(indexStart, indexEnd+1);//截取范围之间的文本
//				}else if(indexStart > indexEnd) {
//					if(html.length()-indexStart>=50){
//						rangeHtml = html.substring(indexStart, indexStart+50);//截取范围开始至后30个字符
//					}else{
//						rangeHtml = html.substring(indexStart, html.length());//截取范围开始至后30个字符
//					}
//				}
//				//匹配中文人民币
//				String regExCn = "([零壹贰叁肆伍陆柒捌玖拾佰仟万亿])";//大写人民币
//				Pattern pat1 = Pattern.compile(regExCn);
//				rangeHtml = rangeHtml.replaceAll("\\s*", "");	//去空格
//				Matcher mat1 = pat1.matcher(rangeHtml);
//				String bigDeposit="";
//				while (mat1.find()) {
//					bigDeposit+=mat1.group();
//				}
//				if(bigDeposit.length()>0){
//					int cnn = CNNumberFormat.ChnStringToNumber(bigDeposit);
//					if(cnn>=200000){//项目金额需大于等于200000
//						deposit = String.valueOf(cnn);
//						break;
//					}
//				}
//
//				//匹配阿拉伯人民币
//				String regExAr = "\\d+(\\.\\d+)?亿元|\\d+(\\.\\d+)?万元|\\d+(\\.\\d+)?元";//阿拉伯人民币
//				Pattern pat2 = Pattern.compile(regExAr);
//				Matcher mat2 = pat2.matcher(rangeHtml);
//				while (mat2.find()) {
//					if("".equals(deposit)){
//						int cnn=CNNumberFormat.ChnStringToNumber(mat2.group().replaceAll("元", ""));
//						if(cnn>0){
//							deposit = String.valueOf(cnn);
//							if(Double.parseDouble(deposit)<200000){//项目金额需大于等于200000
//								deposit="";
//							}
//						}else{
//							deposit = mat2.group().replaceAll("万", "").replaceAll("元", "");
//							if(Double.parseDouble(deposit)<200000){//项目金额需大于等于200000
//								deposit="";
//							}
//						}
//					}
//				}
//				if(deposit.length()>0){
//					break;
//				}
//			}
//		}
////		if(MyStringUtils.isNotNull(deposit)) {
////			BigDecimal d = new BigDecimal(Double.parseDouble(deposit) / 10000);
////			deposit = "约" + String.valueOf(d) + "万";
////		}
//		return deposit;
//	}
//
//	/**
//	 * 解析投标截止日期
//	 * 正则匹配
//	 * return 投标截止日期
//	 */
//	@Override
//	public String analyzeApplyBidDate(String html){
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
//		SimpleDateFormat dftime = new SimpleDateFormat("HH:mm");
//		String rangeHtml="";
//		String bidDate="";
//		String bidTime = "";
//		List<Map<String, Object>> arList = dao.queryAnalyzeRangeByField("applyBidDate");
//		for (int i = 0; i < arList.size(); i++) {
//			String start = arList.get(i).get("rangeStart").toString();
//			String end = arList.get(i).get("rangeEnd").toString();
//			int indexStart=0;
//			int indexEnd=0;
//			if(!"".equals(start)){
//				indexStart = html.indexOf(start);//范围开始位置
//			}
//			if(!"".equals(end)){
//				indexEnd = html.indexOf(end);//范围结束位置
//			}
//			if(indexStart > -1 && indexEnd> -1){
//				if (indexEnd > indexStart) {
//					rangeHtml = html.substring(indexStart, indexEnd + 1);//截取范围之间的文本
//				} else if (indexStart > indexEnd) {
//					if (html.length() - indexStart >= 50) {
//						rangeHtml = html.substring(indexStart, indexStart + 50);//截取范围开始至后100个字符
//					} else {
//						rangeHtml = html.substring(indexStart, html.length());//截取范围开始至后100个字符
//					}
//				}
//				//匹配时间
//				String regTime = "(\\d{1,2}:\\d{2}|(\\d{1,2}时)|\\d{1,2}：\\d{2})";
//				Pattern patTime = Pattern.compile(regTime);
//				rangeHtml = rangeHtml.replaceAll("\\s*", "");    //去空格
//				Matcher matTime = patTime.matcher(rangeHtml);
//				while (matTime.find()) {
//					if ("".equals(bidTime)) {
//						try {
//							bidTime = dftime.format(dftime.parse(matTime.group().replaceAll("时", ":00").replaceAll("：",":")));    //得到时间
//						} catch (ParseException e) {
//							e.printStackTrace();
//							continue;
//						}
//						if(bidTime != ""){
//							break;
//						}
//					}
//				}
//				String regEx = "(\\d{4}-\\d{1,2}-\\d{1,2})|(\\d{4}年\\d{1,2}月\\d{1,2})";//匹配日期
//				Pattern pat = Pattern.compile(regEx);
//				rangeHtml = rangeHtml.replaceAll("\\s*", "");	//去空格
//				Matcher mat = pat.matcher(rangeHtml);
//				while (mat.find()) {
//					if("".equals(bidDate)){
//						try {
//							bidDate = df.format(df.parse(mat.group().replaceAll("年", "-").replaceAll("月", "-")));
//						} catch (ParseException e) {
//							e.printStackTrace();
//							continue;
//						}
//						break;
//					}
//				}
//			}
//		}
//		bidDate = bidDate + "/" + bidTime ;
//		return bidDate;
//	}
//
//	/**
//	 * 解析开标地点
//	 * 数据匹配
//	 * return 开标地点
//	 */
//	@Override
//	public String analyzeApplyBidAddress(String html) {
//		String rangeHtml="";
//		String address = "";
//		List<Map<String, Object>> arList = dao.queryAnalyzeRangeByField("applyBidAddress");
//		for (int i = 0; i < arList.size(); i++) {
//			String start = arList.get(i).get("rangeStart").toString();
//			String end = arList.get(i).get("rangeEnd").toString();
//			int indexStart=0;
//			int indexEnd=0;
//			if(!"".equals(start)){
//				indexStart = html.indexOf(start);//范围开始位置
//			}
//			if(!"".equals(end)){
//				indexEnd = html.indexOf(end);//范围结束位置
//			}
//			if(indexStart != -1 && indexEnd != -1){
//				if(indexEnd > indexStart){
//					rangeHtml = html.substring(indexStart, indexEnd+1);//截取范围之间的文本
//				}else if(indexStart > indexEnd) {
//					if(html.length()-indexStart>=100){
//						rangeHtml = html.substring(indexStart, indexStart+100);//截取范围开始至后100个字符
//					}else{
//						rangeHtml = html.substring(indexStart, html.length());//截取范围开始至后100个字符
//					}
//				}
//				List<String> addrList = dao.queryAnalyzeRangeKbAddr();
//				for (int j = 0; j < addrList.size(); j++) {
//					int indexNum =  rangeHtml.indexOf(addrList.get(j));
//					if(indexNum != -1){
//						address = addrList.get(j);
//						break;
//					}
//				}
//				if(!"".equals(address)){
//					List<String> baseList = dao.queryBaseKbAddress(address);
//					if(!baseList.isEmpty()){
//						address = baseList.get(0);
//					}
//					break;
//				}
//			}
//		}
//		return address;
//	}
//
//
//	/**
//	 * 解析项目类型
//	 * 数据匹配
//	 * return 项目类型
//	 */
//	@Override
//	public String analyzeApplyProjType(int id,String html) {
//		String projType="";
//		//根据资质要求判断类型
//		String zzRank = "";
////		zzRank = zzRankParse.parseHtml(html);
//		zzRank = analyzeZZRank(html);
//		if(zzRank != "" || !(zzRank.equals(""))) {
//			LinkedHashMap<String, String> map = ProjTypeUtil.type();
//			for(String key : map.keySet()) {
//				if(zzRank.contains(key)) {
//					return map.get(key);
//				}
//			}
//			List<String> projList = dao.queryProjType();
//			for (int i = 0; i < projList.size(); i++) {
//				if(zzRank.indexOf(projList.get(i)) != -1) {
//					projType = projList.get(i);
//					return projType;
//				}
//			}
//		}
//		//根据项目标题判断类型
//		if(projType == "" || projType == null) {
//			String title = "";
//			title = dao.queryProjType(id);
//			if(title != "" || title != null) {
//				LinkedHashMap<String, String> map = ProjTypeUtil.type();
//				for(String key : map.keySet()) {
//					if(title.contains(key)) {
//						return map.get(key);
//					}
//				}
//			}
//		}
//		if(projType == "" || projType == null) {
//			List<Map<String,Object>> zh =new ArrayList<Map<String,Object>>();
//			List<Map<String,Object>>list = dao.queryzh();
//			for (int i = 0; i < list.size(); i++) {
//				int num = html.indexOf(list.get(i).get("name").toString());
//				if(num != -1){
//					Map<String, Object> map = new HashMap<String, Object>();
//					map.put("name", list.get(i).get("name"));//匹配公告资质最全名称
//					map.put("uuid", list.get(i).get("mainUUid"));//匹配资质类型id
//					map.put("rank", list.get(i).get("rank").toString());//等级
//					String str="";
//					if(html.indexOf("安全生产许可证") != -1){//查找安全生产许可证
//						map.put("licence","yes");//有安全生产许可证条件
//					}
//					if(num>5){
//						str= html.substring(num-5,num);//查找和
//					}
//					if(str.indexOf("和") !=-1){
//						map.put("type", "AND");//和
//					}else{
//						map.put("type", "OR");//或
//					}
//
//					for(int j=0;j<zh.size();j++ ){
//						if(map.get("uuid").equals(zh.get(j).get("uuid"))&&!"".equals(map.get("rank"))&&!"".equals(zh.get(j).get("rank"))){
//							if(Integer.parseInt(map.get("rank").toString())>Integer.parseInt(zh.get(j).get("rank").toString())){
//								zh.remove(j);
//								zh.add(j,map);
//							}
//							map=null;
//							break;
//						}
//					}
//					if(map!=null){
//						zh.add(map);
//					}
//				}
//			}
//			dao.insertSnatchUrlCertAnalyze(id,zh);//插入资质要求表
//			if(zh.size()>0){
//				String mainUuid = String.valueOf(zh.get(0).get("uuid"));
//				projType = dao.queryAptitudeProjtype(mainUuid);//查询项目类型
//			}
//		}
//		return projType;
//	}
//
//	/**
//	 * 解析项目地区
//	 * 前100字符匹配
//	 * return 项目地区
//	 */
//	@Override
//	public String analyzeApplyProjDq(String text) {
//		String val = "";
//		List<Map<String,Object>> list = dao.queryAreaByGrade(1);//查找全国市级
//		String rangeHtml="";
//		if(text.length()>=100){
//			rangeHtml = text.substring(0, 100);//截取开始至后100个字符
//		}else{
//			rangeHtml = text.substring(0, text.length());//截取到最后
//		}
//		for (int i = 0; i < list.size(); i++) {
//			if(rangeHtml.indexOf(String.valueOf(list.get(i).get("name"))) !=-1){
//				val = String.valueOf(list.get(i).get("name"));
//				break;
//			}
//		}
//		return val;
//	}
//
//	/**
//	 * 解析评标办法
//	 * 全文匹配
//	 * return 评标办法
//	 */
//	@Override
//	public String analyzeApplyPbMode(String text) {
//		String val = "";
//		List<Map<String, Object>> pbList = dao.queryAnalyzeRangePbMode();
//		for (int i = 0; i < pbList.size(); i++) {
//			if (text.indexOf(String.valueOf(pbList.get(i).get("anotherName"))) != -1) {
//				val = String.valueOf(pbList.get(i).get("standardName"));
//				break;
//			}
//		}
//		return val;
//	}
//
//	@Override
//	public String analyzeApplyProjectTimeLimit(String text) {
//		String rangeHtml = "";
//		String timeLimit = "";
//		List<Map<String, Object>> arList = dao.queryAnalyzeRangeByField("applyProjectTimeLimit");
//		for(int i = 0; i < arList.size(); i++) {
//			String regex = String.valueOf(arList.get(i).get("regex")).replaceAll("\\\\","\\\\\\\\");
//			Matcher matre = Pattern.compile(regex).matcher(text);
//			while (matre.find()) {
//				rangeHtml = matre.group();
//				if(!"".equals(rangeHtml)){
//					String regEx = "[1-9]\\d*";//工期数字
//					Pattern pat = Pattern.compile(regEx);
//					Matcher mat = pat.matcher(rangeHtml);
//					while (mat.find()) {
//						if(Double.parseDouble(mat.group())>10){
//							timeLimit = mat.group();
//							return timeLimit;
//						}
//					}
//				}
//			}
//		}
//		return timeLimit;
//	}
//
//	/**
//	 * 解析湖南地区
//	 * @param text
//	 * @return
//	 */
//	@Override
//	public AreaType analyzeHuNanArea(String text) {
//		String rangeHtml = "";
//		AreaType val = null;
//		List<Map<String, Object>> arList = dao.queryAnalyzeRangeByField("projectArea");
//		for (int i = 0; i < arList.size(); i++) {
//			String start = arList.get(i).get("rangeStart").toString();
//			String end = arList.get(i).get("rangeEnd").toString();
//			int indexStart = 0;
//			int indexEnd = 0;
//			if (!"".equals(start)) {
//				indexStart = text.indexOf(start);//范围开始位置
//			}
//			if (!"".equals(end)) {
//				indexEnd = text.indexOf(end);//范围结束位置
//			}
//			if (indexStart > -1 && indexEnd > -1) { //截取范围之间的文本
//				if (indexEnd > indexStart) {
//					rangeHtml = text.substring(indexStart, indexEnd + 1);
//				} else if (indexStart > indexEnd) {
//					if (text.length() - indexStart >= 30) {
//						rangeHtml = text.substring(indexStart, indexStart + 30);
//					} else {
//						rangeHtml = text.substring(indexStart, text.length());
//					}
//				}
//				List<AreaType> list = dao.querysCity();//查找湖南市县
//				for (int j = 0; j < list.size(); j++) {
//					if(rangeHtml.indexOf(String.valueOf(list.get(j).getDisplay_name())) != -1) {	//匹配别名
//						val = list.get(j);
//						break;
//					}
//					if (rangeHtml.indexOf(String.valueOf(list.get(j).getName())) != -1) {	//匹配正式名称
//						val = list.get(j);
//						break;
//					}
//				}
//				if (val!=null &&!"".equals(val.getName())) {
//					break;
//				}
//			}
//		}
//		if(val!=null){
//			String projDq = dao.getHuNanAreaByParentId(val.getParent_id());
//			val.setParent_name(projDq);
//		}
//		return val;
//	}
//
//	/**
//	 * 解析保证金截止时间
//	 * @param text
//	 * @return
//	 */
//	@Override
//	public String analyzeAssureEndDate(String text) {
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
//		String rangeHtml = "";
//		String assureEndDate = "";  //日期
////        String assureEndTime = "";  //时间
//		List<String> listStr = new ArrayList<String>(); // 时间....时间止(用于得到截止时间)
//		List<Map<String, Object>> list = this.dao.queryAnalyzeRangeByField("assureEndDate");
//		for (int i = 0; i < list.size(); i++) {
//			String start = list.get(i).get("rangeStart").toString();
//			String end = list.get(i).get("rangeEnd").toString();
//			int indexStart = 0;
//			int indexEnd = 0;
//			if (!"".equals(start)) {
//				indexStart = text.indexOf(start);//范围开始位置
//			}
//			if (!"".equals(end)) {
//				indexEnd = text.indexOf(end);//范围结束位置
//			}
//			if (indexStart != -1 && indexEnd != -1) { //截取范围之间的文本
//				if (indexEnd > indexStart) {
//					rangeHtml = text.substring(indexStart, indexEnd + 1);
//				}else if(indexStart > indexEnd) {
//					if (text.length() - indexStart >= 80) {
//						rangeHtml = text.substring(indexStart, indexStart + 80);
//					} else {
//						rangeHtml = text.substring(indexStart, text.length());
//					}
//				}
//                /*String regTime = "(\\d{2}:\\d{2})|(\\d{1,2}时)";    //匹配时间
//                Pattern patTime = Pattern.compile(regTime);
//                Matcher matTime = patTime.matcher(rangeHtml);
//                while (matTime.find()) {
//                    if ("".equals(assureEndTime)) {
//                        try {
//                            assureEndTime = dftime.format(dftime.parse(matTime.group().replaceAll("时", ":00")));    //得到时间
//                        } catch (ParseException e) {
//                            e.printStackTrace();
//                            continue;
//                        }
//                        if(assureEndTime != ""){
//                          break;
//                        }
//                    }
//                }*/
//				for(int k = 0; k < EndDate.length; k++) {   //包含一下关键字直接返回
//					if(rangeHtml.indexOf(EndDate[k]) != -1) {
//						return "投标截止前一天";
//					}
//				}
//				if (rangeHtml.indexOf("投标截止时间") != -1 ||rangeHtml.indexOf("递交截止时间") != -1) {
//					return "投标截止时间";
//				}
//				if (rangeHtml.indexOf("投标截止前") != -1 || rangeHtml.indexOf("投标报名截止前") != -1) {
//					return "投标截止前";
//				}
//				if (rangeHtml.indexOf("开标前一天") != -1) {
//					return "开标前一天";
//				}
//				if(rangeHtml.indexOf("报名截止时间前") != -1 || rangeHtml.indexOf("报名前") != -1|| rangeHtml.indexOf("报名期间") != -1) {
//					return "报名截止时间";
//				}
//				String regEx = "(\\d{4}-\\d{1,2}-\\d{1,2})|(\\d{4}年\\d{1,2}月\\d{1,2})"; //匹配日期
//				Pattern pat = Pattern.compile(regEx);
//				rangeHtml = rangeHtml.replaceAll("\\s*", "");	//去空格
//				Matcher mat = pat.matcher(rangeHtml);
//				while (mat.find()) {
//					if (assureEndDate.length() < 1) {
//						try {
//							assureEndDate = df.format(df.parse(mat.group().replaceAll("年", "-").replaceAll("月", "-")));
//						} catch (ParseException e) {
//							e.printStackTrace();
//							continue;
//						}
//					}
//				}
//				if (assureEndDate.length() > 0) {
//					break;
//				}
//			}
//		}
//		return assureEndDate;
//	}
//
//	/**
//	 * 解析资金来源
//	 * @param text
//	 * @return
//	 */
//	@Override
//	public String analyzeMoneySource(String text) {
//		String moneySource = "";
//		List<String> moneyList = this.dao.queryAnalyzeMoneySource();
//		for (int i = 0; i < moneyList.size(); i++) {
//			int indexNum = text.indexOf(moneyList.get(i));
//			if (indexNum > -1) {
//				moneySource = moneyList.get(i);
//				if (moneySource != null && !moneySource.equals("") && !moneySource.contains("自筹")) {
//					moneySource = AnalysisUtils.matchZJBYArray(moneySource, ZJArr, ZJArr1, ZJArr2);
//				}
//			}
//		}
//		return moneySource;
//	}
//
//	/**
//	 * 解析资质要求
//	 * @param text
//	 * @return
//	 */
//	@Override
//	public String analyzeZZRank(String text) {
//		String zzRank = "";
//		String rangeHtml = "";
//		List<Map<String, Object>> arList = dao.queryAnalyzeRangeByField("zzRank");
//		for (int i = 0; i <arList.size() ; i++) {
//			String start = arList.get(i).get("rangeStart").toString();
//			String end = arList.get(i).get("rangeEnd").toString();
//			int indexStart = 0;
//			int indexEnd = 0;
//			if(!"".equals(start)){
//				indexStart = text.indexOf(start);//范围开始位置
//			}
//			if(!"".equals(end)){
//				indexEnd = text.lastIndexOf(end);
//			}
//			if(indexStart != -1 && indexEnd != -1) {
//				if(indexEnd > indexStart) {
//					rangeHtml = text.substring(indexStart, indexEnd);
//					rangeHtml = rangeHtml.replaceAll("\\s*", "");	//去空格
//					if(rangeHtml.length() > 30) {
//						rangeHtml = rangeHtml.substring(0,30);
//					}
//					zzRank = rangeHtml.replace("颁发的","").replace("核发的","").replace("具备","");
//					if(zzRank.indexOf("级") == -1) {
//						zzRank = "";
//					}
//					if(zzRank.length() > 0) {
//						break;
//					}
//				}
//			}
//		}
//		return zzRank;
//	}
//
//	@Override
//	public AreaType analyzeHuNanCity(String text) {
//		String rangeHtml = "";
//		AreaType val = null;
//		List<Map<String, Object>> arList = dao.queryAnalyzeRangeByField("projectArea");
//		for (int i = 0; i < arList.size(); i++) {
//			String start = arList.get(i).get("rangeStart").toString();
//			String end = arList.get(i).get("rangeEnd").toString();
//			int indexStart = 0;
//			int indexEnd = 0;
//			if (!"".equals(start)) {
//				indexStart = text.indexOf(start);//范围开始位置
//			}
//			if (!"".equals(end)) {
//				indexEnd = text.indexOf(end);//范围结束位置
//			}
//			if (indexStart > -1 && indexEnd > -1) { //截取范围之间的文本
//				if (indexEnd > indexStart) {
//					rangeHtml = text.substring(indexStart, indexEnd + 1);
//				} else if (indexStart > indexEnd) {
//					if (text.length() - indexStart >= 30) {
//						rangeHtml = text.substring(indexStart, indexStart + 30);
//					} else {
//						rangeHtml = text.substring(indexStart, text.length());
//					}
//				}
//				List<AreaType> list = dao.querysCity();//查找湖南县市
//				for (int j = 0; j < list.size(); j++) {
//					if(rangeHtml.indexOf(String.valueOf(list.get(j).getDisplay_name())) != -1) {	//匹配别名
//						val = list.get(j);
//						break;
//					}
//					if (rangeHtml.indexOf(String.valueOf(list.get(j).getName())) != -1) {	//匹配正式名称
//						val = list.get(j);
//						break;
//					}
//				}
//				if (!"".equals(val.getName())) {
//					break;
//				}
//			}
//		}
////		if(val == null) {	//为空全文匹配
////			List<AreaType> list = dao.querysCity();//查找湖南县市
////			for (int i = 0; i < list.size(); i++) {
////				if(text.indexOf(String.valueOf(list.get(i).getDisplay_name())) != -1){	//匹配别名
////					val = list.get(i);
////					break;
////				}
////				if(text.indexOf(String.valueOf(list.get(i).getName())) != -1){	//匹配正式名称
////					val = list.get(i);
////					break;
////				}
////			}
////		}
//		return val;
//	}
//
//}
