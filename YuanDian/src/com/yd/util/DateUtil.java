package com.yd.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;



/**
 *日期工具类
 */
public class DateUtil {
	public static String nowStringDate(String pattern) {
		return dateToString(new Date(), pattern);
	}

	public static Date nowDate(String pattern) {
		String nowStringDate = nowStringDate(pattern);
		return stringToDate(nowStringDate, pattern);
	}

	public static String dateToString(Date date, String pattern, Locale locale) {
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(pattern, locale);
			return sdf.format(date); 
		}
		catch(Exception e){
			return "";
		}
	}

	public static String dateToString(Date date, String pattern) {
		Locale locale = Locale.getDefault();
		return dateToString(date, pattern, locale);
	}

	public static long stringToLong(String strDate, String pattern, Locale locale)throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern, locale);
		Date date = sdf.parse(strDate);
		return date.getTime();
	}

	public static long stringToLong(String strDate, String pattern) throws ParseException {
		Locale locale = Locale.CHINESE;
		return stringToLong(strDate, pattern, locale);
	}

	public static Date stringToDate(String strDate, String pattern) {
		try{
			long ltime = stringToLong(strDate, pattern);
			return new Date(ltime);
		}
		catch(Exception ex){
			return null;
		}
	}

	public static Date stringToDate(String strDate, String pattern, String otherPattern) {
		try{
			long ltime = stringToLong(strDate, pattern);
			return new Date(ltime);
		}
		catch(Exception ex){
			try{
				long ltime = stringToLong(strDate, otherPattern);
				return new Date(ltime);
			}
			catch(Exception e){
				return null;
			}
		}
	}

	public static Date formatDate(Date date, String pattern) {
		String s = dateToString(date, pattern);
		return stringToDate(s, pattern);
	}
	
}
