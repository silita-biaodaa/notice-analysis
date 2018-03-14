package com.silita.biaodaa.utils;

/**
 *
 * Description:Class说明：日期格式化类
 *
 */
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {

	public DateUtils() {
	}

	public final static Calendar myc = Calendar.getInstance();

	private static SimpleDateFormat sdf = new SimpleDateFormat();

    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 获得当天时间
	 *
	 * @param parrten
	 *            输出的时间格式
	 * @return 返回时间
	 */
	public static String getTime(String parrten) {
		String timestr;
		if (parrten == null || parrten.equals("")) {
			parrten = "yyyyMMddHHmmss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(parrten);
		Date cday = new Date();
		timestr = sdf.format(cday);
		return timestr;
	}

	/**
	 * 获得当天日期
	 * @param parrten
	 * @return
	 */
	public static String getDate(String parrten){
		if (parrten == null || parrten.equals("")) {
			parrten = "yyyy-MM-dd";
		}
		return DateUtils.getTime(parrten);
	}

	/**
	 * 时间格式转换
	 *
	 * @param cday
	 * @param parrten
	 * @return
	 */
	public static String getTime(Date cday, String parrten) {
		String timestr;
		if (parrten == null || parrten.equals("")) {
			parrten = "yyyyMMddHHmmss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(parrten);
		timestr = sdf.format(cday);
		return timestr;
	}

	/**
	 * 日期格式转换
	 * @param parrten
	 * @return
	 */
	public static String getDate(Date date, String parrten){
		if (parrten == null || parrten.equals("")) {
			parrten = "yyyy/MM/dd";
		}
		return DateUtils.getTime(date,parrten);
	}

	/**
	 * 日期格式转换
	 * @param parrten
	 * @return
	 */
	public static String getLiteDate(Date date, String parrten){
		if (parrten == null || parrten.equals("")) {
			parrten = "yyyy-M-d";
		}
		return DateUtils.getTime(date,parrten);
	}

	/**
	 * 得到昨天的时间,以缓存时间为基准,小心试用
	 *
	 * @return
	 */
	public static String getYestday(String parrten) {
        if (parrten == null || parrten.equals("")) {
            parrten = "yyyy-MM-dd";
        }
		String timestr;
		Calendar cc = myc;
		cc.roll(Calendar.DAY_OF_YEAR, -1);
		SimpleDateFormat sdf = new SimpleDateFormat(parrten);
		if ((cc.get(Calendar.MONTH) + 1) == 1
				&& cc.get(Calendar.DAY_OF_MONTH) == 1) {
			cc.roll(Calendar.YEAR, 1);
		}

		timestr = sdf.format(cc.getTime());
		return timestr;
	}

	/**
	 * 将字串转换为指定格式的日期
	 * @param time		时间
	 * @param parrten	为空时，将使用yyyy-MM-dd格式
	 * @return
	 */
	public static Date strToDate(String time, String parrten) {
		if (parrten == null || parrten.equals("")) {
			parrten = "yyyy-MM-dd";
		}
		SimpleDateFormat formatter = new SimpleDateFormat(parrten);
		ParsePosition pos = new ParsePosition(0);
		Date dt1 = formatter.parse(time, pos);
		return dt1;
	}

	/**
	 * 将Long类型时间转换为指定格式的日期
	 * @param time		时间
	 * @param parrten	为空时，将使用yyyy-MM-dd格式
	 * @return
	 */
	public static String longDateToStr(Long time, String parrten) {
		if (parrten == null || parrten.equals("")) {
			parrten = "yyyy-MM-dd";
		}
		SimpleDateFormat formatter = new SimpleDateFormat(parrten);
		Date date= new Date(time);
		return formatter.format(date).toString();
	}

	/**
	 * 将时间转换为xxxx年xx月xx日格式
	 *
	 * @param t1
	 *            原时间
	 * @param parrten
	 *            原时间格式
	 * @return
	 */
	public static String getTime(String t1, String parrten) {
		SimpleDateFormat formatter = new SimpleDateFormat(parrten);
		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy年MM月dd日");
		ParsePosition pos = new ParsePosition(0);
		Date dt1 = formatter.parse(t1, pos);
		return formatter2.format(dt1);
	}

	/**
	 * 将时间转换为parrten2格式
	 *
	 * @param t1 时间字符串
	 * @param parrten 原时间格式
	 * @param parrten2 要转化的格式
	 * @return
	 */
	public static String getTime(String t1, String parrten, String parrten2) {
		SimpleDateFormat formatter = new SimpleDateFormat(parrten);
		SimpleDateFormat formatter2 = new SimpleDateFormat(parrten2);
		ParsePosition pos = new ParsePosition(0);
		Date dt1 = formatter.parse(t1, pos);
		return formatter2.format(dt1);
	}

	/**
	 * 比较两个字符串时间的大小
	 *
	 * @param t1
	 *            时间1
	 * @param t2
	 *            时间2
	 * @param parrten
	 *            时间格式 :yyyy-MM-dd
	 * @return 返回long =0相等，>0 t1>t2，<0 t1<t2
	 */
	public static int compareStringTime(String t1, String t2, String parrten) {
		if(parrten == null){
			parrten = "yyyy-MM-dd";
		}
		SimpleDateFormat formatter = new SimpleDateFormat(parrten);
		ParsePosition pos = new ParsePosition(0);
		ParsePosition pos1 = new ParsePosition(0);
		Date dt1 = formatter.parse(t1, pos);
		Date dt2 = formatter.parse(t2, pos1);

		Calendar calendar= Calendar.getInstance();
		calendar.setTime(dt1);
		int day1=calendar.get(Calendar.DAY_OF_YEAR);
		calendar.setTime(dt2);
		int day2=calendar.get(Calendar.DAY_OF_YEAR);
		return Math.abs(day1-day2);

	}
	//同上面的方法，不取结果的绝对值
	public static int compareStringTimeEx(String t1, String t2, String parrten) {
		SimpleDateFormat formatter = new SimpleDateFormat(parrten);
		ParsePosition pos = new ParsePosition(0);
		ParsePosition pos1 = new ParsePosition(0);
		Date dt1 = formatter.parse(t1, pos);
		Date dt2 = formatter.parse(t2, pos1);

		Calendar calendar= Calendar.getInstance();
		calendar.setTime(dt1);
		int day1=calendar.get(Calendar.DAY_OF_YEAR);
		calendar.setTime(dt2);
		int day2=calendar.get(Calendar.DAY_OF_YEAR);
		return day1-day2;

	}


	/**
	 * 比较两个日期相差的天数
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static int compareTime(String time1, String time2) {
		return compareStringTime(time1, time2, "yyyy-MM-dd");
	}
	public static String addTime(String datetime, String parrten, long days){
		SimpleDateFormat formatter = new SimpleDateFormat(parrten);
		ParsePosition pos = new ParsePosition(0);
		Date dt1 = formatter.parse(datetime, pos);
		long l=dt1.getTime()/1000+days*24*60*60;
		dt1.setTime(l*1000);
		String mydate = formatter.format(dt1);

		return mydate;
	}

	/**
	 * 取得昨天的日期,以今天为基准
	 * @return
	 */
	public static String getYestdayBaseToday(){

		String timestr;
		Calendar cc =  Calendar.getInstance();
		cc.roll(Calendar.DAY_OF_YEAR, -1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		timestr = sdf.format(cc.getTime());
		return timestr;
	}

	/**
	 * 得到指定日期的后一天日期
	 * @param time
	 * @return
	 */
	public static String getAfterBaseTime(String time, String pattern){
		String timestr = "";
		if(pattern == null){
			pattern = "yyyy-MM-dd";
		}
		try{
			Calendar cc = DateUtils.parseCalendarFormat(time, pattern);
			cc.roll(Calendar.DAY_OF_YEAR, 1);
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			timestr = sdf.format(cc.getTime());
		}catch(Exception e){

		}
		return timestr;
	}

	/**
	 * 得到指定日期的前一天日期
	 *
	 * @param time
	 * @param pattern
	 * @return
	 */
	public static String getBeforeBaseTime(String time, String pattern){
		String timestr = "";
		if(pattern == null){
			pattern = "yyyy-MM-dd";
		}
		try{
			Calendar cc = DateUtils.parseCalendarFormat(time, pattern);
			cc.roll(Calendar.DAY_OF_YEAR, -1);
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			timestr = sdf.format(cc.getTime());
		}catch(Exception e){

		}
		return timestr;
	}

	/**
	 * 把字符串按照一定的格式转换成Calendar对象
	 * @param strDate
	 * @param pattern
	 * @return
	 */
	public static synchronized Calendar parseCalendarFormat(String strDate, String pattern ){
		synchronized ( sdf ){
		Calendar cal = null;
		sdf.applyPattern( pattern );
		try{
		sdf.parse( strDate );
		cal = sdf.getCalendar();
		}
		catch ( Exception e ){
		}
		return cal;
		}
	}

	/**
	 * 把 Date 类型转换成 XMLGregorianCalendar
	 * @param date
	 * @return
	 */
	public static XMLGregorianCalendar convertToXMLGregorianCalendar(Date date) {

        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        XMLGregorianCalendar gc = null;
        try {
            gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        } catch (DatatypeConfigurationException e) {
        }
        return gc;
    }

	/**
	 * 取当前日期的相距的？天的日期
	 * @param days
	 * @return
	 */
	public static Date getBeforeCurrentDate(int days){

		Date end = new Date() ;
		Calendar cal = Calendar.getInstance();
		cal.setTime(end) ;
		cal.add(Calendar.DAY_OF_YEAR, days ) ;
		return cal.getTime() ;

	}

	/**
	 * 取当前日期的相距的？天的日期
	 * @param hours
	 * @return
	 */
	public static Date getBeforeCurrentTime(int hours){

		Date end = new Date() ;
		Calendar cal = Calendar.getInstance();
		cal.setTime(end) ;
		cal.add(Calendar.HOUR_OF_DAY, hours ) ;
		return cal.getTime() ;

	}

	/**
	 * 取当前日期的相距的？天的日期
	 * @param minutes
	 * @return
	 */
	public static Date getBeforeCurrentMinute(int minutes){

		Date end = new Date() ;
		Calendar cal = Calendar.getInstance();
		cal.setTime(end) ;
		cal.add(Calendar.MINUTE, minutes ) ;
		return cal.getTime() ;
	}
    /**
     * 輸入日期，可以轉換成星期幾。
     *
     * @param dateString 日期字串
     * @return 星期幾
     * @throws ParseException 無法將字串轉換成java.util.Date類別
     */
    public static String date2Day(String dateString ) throws ParseException {
        SimpleDateFormat dateStringFormat = new SimpleDateFormat( "yyyy-MM-dd" );
        Date date = dateStringFormat.parse( dateString );

        SimpleDateFormat date2DayFormat = new SimpleDateFormat( "u" );
        return date2DayFormat.format( date );
    }

	/**
	 * 获取两个时间之间的小时数
	 *
	 * @param
	 * @return
	 */
	public static Long dateDiff(String startTime, String endTime, String format, String str) {
		// 按照传入的格式生成一个simpledateformate对象
		SimpleDateFormat sd = new SimpleDateFormat(format);
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long nh = 1000 * 60 * 60;// 一小时的毫秒数
		long nm = 1000 * 60;// 一分钟的毫秒数
		long ns = 1000;// 一秒钟的毫秒数
		long diff;
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		// 获得两个时间的毫秒时间差异
		try {
			diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
			day = diff / nd;// 计算差多少天
			hour = diff % nd / nh + day * 24;// 计算差多少小时
			min = diff % nd % nh / nm + hour * 60;// 计算差多少分钟
			sec = diff % nd % nh % nm / ns;// 计算差多少秒
			// 输出结果
			System.out.println("时间相差：" + day + "天" + (hour - day * 24) + "小时"
					+ (min - hour * 60) + "分钟" + sec + "秒。");
			System.out.println("hour=" + hour + ",min=" + min);
			if (str.equalsIgnoreCase("d")) {
				return day;
			} else if (str.equalsIgnoreCase("h")) {
				return hour;
			} else {
				return min;
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (str.equalsIgnoreCase("h")) {
			return hour;
		} else {
			return min;
		}
	}

    /**
     * 指定日期是否大于当前时间,如果是空或者转换出现异常则认为时间毫秒值无限大 返回true
     *
     * @param date 2016-06-25
     * @return true 不到统计时间 false 到了统计时间
     */
    public static boolean isLaterThanNow(String date) {
        if (org.apache.commons.lang3.StringUtils.isBlank(date)) {
            return true;
        }
        try {
            return SIMPLE_DATE_FORMAT.parse(date).getTime() > System.currentTimeMillis();
        } catch (ParseException e) {
            return true;
        }
    }

	/**
	 * 获取指定时间对应的毫秒数
	 * @param time "HH:mm:ss"
	 * @return
	 */
	public static long getTimeMillis(String time) {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
			DateFormat dayFormat = new SimpleDateFormat("yy-MM-dd");
			Date curDate = dateFormat.parse(dayFormat.format(new Date()) + " " + time);
			return curDate.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 取当前日期的相距的？天的日期
	 * @param targetDate
	 * @return
	 */
	public static Date getBeforeTargetDate(String targetDate, int days){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(targetDate));
			cal.add(Calendar.DAY_OF_YEAR, days );
			return cal.getTime() ;
		}catch (Exception e){
			return null;
		}

	}

	public static String getWeek(Date date){
		String[] weeks = {"周日","周一","周二","周三","周四","周五","周六"};
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if(week_index<0){
			week_index = 0;
		}
		return weeks[week_index];
	}

	public static String getTimes(Date date){
		DateFormat df3 = DateFormat.getTimeInstance();
		return df3.format(date);

	}

	public static String getChinaWeek(String week){
		String weekName="";
		if(week.matches("^[a-zA-Z]*")) {
			if (week.equals("Monday")) {
				weekName = "周一";
			} else if (week.equals("Tuesday")) {
				weekName = "周二";
			} else if (week.equals("Wednesday")) {
				weekName = "周三";
			} else if (week.equals("Thursday")) {
				weekName = "周四";
			} else if (week.equals("Friday")) {
				weekName = "周五";
			} else if (week.equals("Saturday")) {
				weekName = "周六";
			} else if (week.equals("Sunday")) {
				weekName = "周日";
			}
		}else{
			weekName=week;
		}
		return weekName;
	}


	//获取本月上一月yyyyMM
	public static String getYearMonth(){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		SimpleDateFormat format =  new SimpleDateFormat("yyyyMM");
		String time = format.format(c.getTime());
		return time;
	}

	//获取本月上一月yyyyMM
	public static String getYearMonth(String date){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(date));
			c.add(Calendar.MONTH, 1);
			SimpleDateFormat format =  new SimpleDateFormat("yyyyMM");
			String time = format.format(c.getTime());
			return time;
		}catch (Exception e){
			return null;
		}
	}

	/**
	 * 获取当月的 天数
	 * */
	public static int getCurrentMonthDay() {

		Calendar a = Calendar.getInstance();
		a.set(Calendar.DATE, 1);
		a.roll(Calendar.DATE, -1);
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	/**
	 * 根据年 月 获取对应的月份 天数
	 * */
	public static int getDaysByYearMonth(int year, int month) {

		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);
		a.roll(Calendar.DATE, -1);
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	/**
	 * 比较2个时间差
	 * @return
	 */
	public static int compareDateStr(String str1, String str2) {
		if(str1.equals("公告时间") || str2.equals("公告时间")) {
			return 1;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(sdf.parse(str1));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long time1 = cal.getTimeInMillis();
		try {
			cal.setTime(sdf.parse(str2));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return (int) Math.abs(between_days);
	}

	/**
	 * 得到指定日期字符串前一天
	 * @author 葛明逸
	 * @param specifiedDay
	 * @return
	 */
	public static String getSpecifiedDayBefore(String specifiedDay){
		if(specifiedDay == null || "".equals(specifiedDay)) {
			return "";
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		Date date=null;
		try {
			date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date); int day=c.get(Calendar.DATE);
		c.set(Calendar.DATE,day-1);
		String dayBefore=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		return dayBefore;
	}

	/**
	 * 比较那个时间大 第二个大于第一个返回2
	 * @return
	 */
	public static int compareDateStr2(String str1, String str2) {
		if(str1.length() < 8 || str2.length() < 8) {
			return 0;
		}
		if(str1.contains("/")) {
			str1 = str1.substring(0, str1.indexOf("/"));
		}
		if(str2.contains("/")) {
			str2 = str2.substring(0, str2.indexOf("/"));
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(sdf.parse(str1));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long time1 = cal.getTimeInMillis();
		try {
			cal.setTime(sdf.parse(str2));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long time2 = cal.getTimeInMillis();
		if(time2 > time1) {
			return 2;
		} else {
			return 1;
		}
	}

	public static void main(String[] args){
		/*// 获取当月第一天和最后一天
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String firstday, lastday;
		// 获取前月的第一天
		Calendar cale = null;
		cale = Calendar.getInstance();
		cale = Calendar.getInstance();
		cale.add(Calendar.MONTH, 0);
		cale.set(Calendar.DAY_OF_MONTH, 1);
		firstday = format.format(cale.getTime());
		// 获取前月的最后一天
		cale = Calendar.getInstance();
		cale.add(Calendar.MONTH, 1);
		cale.set(Calendar.DAY_OF_MONTH, 0);
		lastday = format.format(cale.getTime());
		System.out.println("本月第一天和最后一天分别是 ： " + firstday + " and " + lastday);*/

		int days = DateUtils.getDaysByYearMonth(Integer.parseInt("201803".substring(0,4))
				,Integer.parseInt("201805".substring(4,6)));
		System.out.println(days);

		System.out.println(DateUtils.getYearMonth("201802"));

	}

}
