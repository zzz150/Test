package com.yunerp.base.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.util.Assert;



/**
 * @author:xiaolong
 * @version:1.0
 * @date:2016年6月4日下午3:17:21
 * @description:<li>业务描述：时间格式化公共类</li>
 */
public class DateUtil {

	public static final String PATTERN_STANDARD = "yyyy-MM-dd HH:mm:ss";

	public static final String PATTERN_DATE = "yyyy-MM-dd";
	/**
	 * 
	 * @param timestamp
	 * @param pattern
	 * @return
	 */
	public static String timestamp2String(Timestamp timestamp, String pattern) {
		if (timestamp == null) {
			throw new java.lang.IllegalArgumentException(
					"timestamp null illegal");
		}
		if (pattern == null || pattern.equals("")) {
			pattern = PATTERN_STANDARD;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(new Date(timestamp.getTime()));
	}
	/**
	 * 
	 * @author:wzh
	 * @version:1.0
	 * @date:2016年7月21日下午8:45:45
	 * @description:<li>方法描述：获取提前时间<>
	 * @param time
	 * @param hour 提前小时数 针对当前时间
	 * @return  String
	 */
	public static String upTime(Long time,int hour){
		Date date = new Date(time-hour*60*60*1000);
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_STANDARD);
		return sdf.format(date);
	}
	

	/**
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String date2String(java.util.Date date, String pattern) {
		if (date == null) {
			throw new java.lang.IllegalArgumentException(
					"timestamp null illegal");
		}
		if (pattern == null || pattern.equals("")) {
			pattern = PATTERN_STANDARD;
			;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}


	/**
	 * 
	 * @return
	 */
	public static Date getTimeNow() {
		String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(Calendar.getInstance().getTime());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date datatime = new Date();
		try {
			datatime = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return datatime;
	}

	/**
	 * Date转string 格式化日期  标准年月日
	 * 
	 * @param date
	 * @return 2000-01-24
	 */
	public static String date2String(java.util.Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATE);
		return sdf.format(date);
	}

	/**
	 * 获取当前系统时间
	 * 
	 * @param date
	 * @return 2000-01-24
	 */
	public static String sysdateString() {
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATE);
		return sdf.format(new Date());
	}

	/**
	 * 获取当前系统时间
	 * 
	 * @param date
	 * @return 2000年01月24日
	 */
	public static String sysdateString1() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		return sdf.format(new Date());
	}

	/**
	 * 获取当前系统时间
	 * 
	 * @param date
	 * @return 2000-01-24 05:05:05
	 */
	public static String sysdatequanString() {
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_STANDARD);
		return sdf.format(new Date());
	}

	/**
	 * 获取当前系统时间(年月)
	 * 
	 * @param date
	 * @return 200011
	 */
	public static String dateYmString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		return sdf.format(new Date());
	}

	/**
	 * 获取当前系统时间(年月)
	 * 
	 * @param date
	 * @return 2000-11
	 */
	public static String dateYmString1() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		return sdf.format(new Date());
	}

	/**
	 * 获取当前系统时间(年月日)
	 * 
	 * @param date
	 * @return 20001101
	 */
	public static String dateYmdString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(new Date());
	}

	/**
	 * 获取当前系统时间(年月)
	 * 
	 * @param date
	 *            20140102 10:10:10
	 * @return 2014-01-02 10:10:10
	 */
	public static String substrDateFormat(String date) {
		String str = "";
		if (date != null && date.length() > 8) {
			str = date.substring(0, 4) + "-" + date.substring(4, 6) + "-"
					+ date.substring(6, 8) + date.substring(8, date.length());
		}
		return str;
	}

	/**
	 * 
	 * @return
	 */
	public static Timestamp currentTimestamp() {
		return new Timestamp(new Date().getTime());
	}

	/**
	 * 
	 * @param pattern
	 * @return
	 */
	public static String currentTimestamp2String(String pattern) {
		return timestamp2String(currentTimestamp(), pattern);
	}

	/**
	 * 
	 * @param strDateTime
	 * @param pattern
	 * @return
	 */
	public static Timestamp string2Timestamp(String strDateTime, String pattern) {
		if (strDateTime == null || strDateTime.equals("")) {
			throw new java.lang.IllegalArgumentException(
					"Date Time Null Illegal");
		}
		if (pattern == null || pattern.equals("")) {
			pattern = PATTERN_STANDARD;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = sdf.parse(strDateTime);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return new Timestamp(date.getTime());
	}

	/**
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date string2Date(String strDate) {
		if (strDate == null || strDate.equals("")) {
			throw new RuntimeException("str date null");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;

		try {
			date = sdf.parse(strDate);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return date;
	}

	/**
	 * 字符串类型时间比较
	 * 
	 * @param strDate1
	 *            开始时间
	 * @param strDate2
	 *            结束时间
	 * @return
	 */
	public static boolean strEqStri(String strDate1, String strDate2) {
		if (strDate1 == null || "".equals(strDate1) || strDate2 == null
				|| "".equals(strDate2)) {
			return false;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Date date1 = null;
		Date date2 = null;
		try {
			date1 = sdf.parse(strDate1);
			date2 = sdf.parse(strDate2);
			if (date1.getTime() > date2.getTime()) {
				return true;
			} else {
				return false;
			}
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * 
	 * @param strDate
	 * @param pattern
	 * @return
	 */
	public static Date string2Date(String strDate, String pattern) {
		if (strDate == null || strDate.equals("")) {
			throw new RuntimeException("str date null");
		}
		if (pattern == null || pattern.equals("")) {
			pattern = DateUtil.PATTERN_DATE;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;

		try {
			date = sdf.parse(strDate);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return date;
	}

	/**
	 * 
	 * @param strDest
	 * @return
	 */
	public static String stringToYear(String strDest) {
		if (strDest == null || strDest.equals("")) {
			throw new java.lang.IllegalArgumentException("str dest null");
		}

		Date date = string2Date(strDest, DateUtil.PATTERN_DATE);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return String.valueOf(c.get(Calendar.YEAR));
	}

	/**
	 * 
	 * @param strDest
	 * @return
	 */
	public static String stringToMonth(String strDest) {
		if (strDest == null || strDest.equals("")) {
			throw new java.lang.IllegalArgumentException("str dest null");
		}

		Date date = string2Date(strDest, DateUtil.PATTERN_DATE);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int month = c.get(Calendar.MONTH);
		month = month + 1;
		if (month < 10) {
			return "0" + month;
		}
		return String.valueOf(month);
	}

	/**
	 * 
	 * @param strDest
	 * @return
	 */
	public static String stringToDay(String strDest) {
		if (strDest == null || strDest.equals("")) {
			throw new java.lang.IllegalArgumentException("str dest null");
		}

		Date date = string2Date(strDest, DateUtil.PATTERN_DATE);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int day = c.get(Calendar.DAY_OF_MONTH);
		if (day < 10) {
			return "0" + day;
		}
		return "" + day;
	}

	/**
	 * 
	 * @param strDest
	 * @param strPattern
	 * @param pattern
	 * @return
	 */
	public static String string2String(String strDest, String strPattern,
			String pattern) {
		if (strDest == null || strDest.equals("")) {
			throw new java.lang.IllegalArgumentException("str dest null");
		}
		Date date = string2Date(strDest, strPattern);
		return date2String(date, pattern);
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public static Date getFirstDayOfMonth(Calendar c) {
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = 1;
		c.set(year, month, day, 0, 0, 0);
		return c.getTime();
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public static Date getLastDayOfMonth(Calendar c) {
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = 1;
		if (month > 11) {
			month = 0;
			year = year + 1;
		}
		c.set(year, month, day - 1, 0, 0, 0);
		return c.getTime();
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static String date2GregorianCalendarString(Date date) {
		if (date == null) {
			throw new java.lang.IllegalArgumentException("Date is null");
		}
		long tmp = date.getTime();
		GregorianCalendar ca = new GregorianCalendar();
		ca.setTimeInMillis(tmp);
		try {
			XMLGregorianCalendar t_XMLGregorianCalendar = DatatypeFactory
					.newInstance().newXMLGregorianCalendar(ca);
			return t_XMLGregorianCalendar.normalize().toString();
		} catch (DatatypeConfigurationException e) {
			throw new java.lang.IllegalArgumentException("Date is null");
		}
	}

	/**
	 * 
	 * @param firstDate
	 * @param secondDate
	 * @return
	 */
	public static boolean compareDate(Date firstDate, Date secondDate) {
		if (firstDate == null || secondDate == null) {
			throw new java.lang.RuntimeException();
		}
		String strFirstDate = date2String(firstDate, "yyyy-MM-dd");
		String strSecondDate = date2String(secondDate, "yyyy-MM-dd");
		if (strFirstDate.equals(strSecondDate)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @param currentDate
	 * @return
	 */
	public static Date getStartTimeOfDate(Date currentDate) {
		Assert.notNull(currentDate);
		String strDateTime = date2String(currentDate, "yyyy-MM-dd")
				+ " 00:00:00";
		return string2Date(strDateTime, "yyyy-MM-dd hh:mm:ss");
	}

	/**
	 * 
	 * @param currentDate
	 * @return
	 */
	public static Date getEndTimeOfDate(Date currentDate) {
		Assert.notNull(currentDate);
		String strDateTime = date2String(currentDate, "yyyy-MM-dd")
				+ " 59:59:59";
		return string2Date(strDateTime, "yyyy-MM-dd hh:mm:ss");
	}

	/**
	 * /获取下个月
	 * 
	 * @return
	 */
	public static String getNextMonth() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, +1);
		Date date = c.getTime();
		return date2String(date, "yyyyMM");
	}
	
	/**
	 * /获取上个月
	 * 
	 * @return
	 */
	public static String getTopMonth() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		Date date = c.getTime();
		return date2String(date, "yyyyMM");
	}

	/**
	 * /获取指定日期上个月
	 * 
	 * @param printny
	 * @return
	 */
	public static String getPringNyMonth(String printny) {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Date date = null;
		try {
			date = sdf.parse(printny);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		c.add(Calendar.MONTH, -1);
		String strDate = sdf.format(c.getTime());
		return strDate;
	}

	/**
	 * 日期比较大小 2012-03-02 2012-05-06
	 * 
	 * @param date1
	 *            大日期
	 * @param date2
	 *            小日期
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean compareForDateString(String date1, String date2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt1 = df.parse(date1);
			Date dt2 = df.parse(date2);
			if (dt1.getTime() > dt2.getTime()) {
				return true;
			} else {
				return false;
			}
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * 日期比较大小 2014-11-7
	 * 
	 * @param date1
	 *            大日期
	 * @param date2
	 *            小日期
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean compareDateString(String date1, String date2) {
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		try {
			Date dt1 = df.parse(date1);
			Date dt2 = df.parse(date2);
			if (dt1.getTime() > dt2.getTime()) {
				return true;
			} else {
				return false;
			}
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * 
	 * @return
	 */
	public static String getTimeNowtwo() {
		String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(Calendar.getInstance().getTime());
		return time;
	}

	/**
	 * 
	 * @return
	 */
	public static String getDateNotSep() {
		GregorianCalendar gc = new GregorianCalendar();
		String ymd = "";
		int ye = gc.get(Calendar.YEAR);
		int mo = gc.get(Calendar.MONTH) + 1;
		int da = gc.get(Calendar.DATE);
		ymd = "" + ye;
		if (mo < 10)
			ymd = ymd + "0" + mo;
		else
			ymd += mo;
		if (da < 10)
			ymd = ymd + "0" + da;
		else
			ymd += da;
		return ymd;
	}

	/**
	 * 
	 * @return
	 */
	public static String getDateYer() {
		GregorianCalendar gc = new GregorianCalendar();
		String ymd = "";
		int ye = gc.get(Calendar.YEAR);
		ymd = "" + ye + "01" + "01";
		return ymd;
	}

	/**
	 * 
	 * @return
	 */
	public static String getNextDateYer() {
		Calendar curr = Calendar.getInstance();
		curr.set(Calendar.YEAR, curr.get(Calendar.YEAR) + 1);
		Date date = curr.getTime();
		return date2String(date);
	}

	/**
	 * 转换时间.将yyyyMMdd 格式转化成 yyyy-mm-dd
	 * 
	 * @param strDate
	 * @return
	 */
	public static String stringToString(String strDate) {
		if (strDate == null || strDate.equals("")) {
			throw new RuntimeException("str date null");
		}
		strDate = strDate.substring(0, 4) + "-" + strDate.substring(4, 6) + "-"
				+ strDate.substring(6, 8);
		return strDate;
	}

	/**
	 * 
	 * @param dwbh
	 * @param username
	 * @return
	 */
	public static String genFilename(String dwbh, String username) {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddkkmmss");
		String today = sdf.format(d);
		Random rnd = new Random();
		int iRndnum = rnd.nextInt();
		String random = dwbh + "_" + username + "_" + today + "_" + iRndnum;
		return random;
	}

	/**
	 * 获取日（date）
	 * 
	 * @return
	 */
	public static int getDateToInt() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 获取中国标准时间
	 * 
	 * @return
	 */
	public static String getTimeZone() {
		Calendar cd = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss' GMT'Z' (中国标准时间)'",Locale.US);
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+8:00")); // 设置时区为GMT
	 	return sdf.format(cd.getTime());
	}

}