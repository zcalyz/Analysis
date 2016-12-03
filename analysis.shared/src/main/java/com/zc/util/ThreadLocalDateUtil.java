package com.zc.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadLocalDateUtil {
	
	private static final String date_format = "yyyy-MM-dd HH:mm:ss";
	
	private static ThreadLocal<DateFormat> threadLocalDate = new ThreadLocal<DateFormat>();
	
	/**
	 * 获取线程安全的simpleDateFormat
	 * @return
	 */
	public static DateFormat getDateFormat(){
		DateFormat dateFormat = threadLocalDate.get();
		
		if(dateFormat == null){
			dateFormat = new SimpleDateFormat(date_format);
			threadLocalDate.set(dateFormat);
		}
		
		return dateFormat;
	}
	
	/**
	 * 获取格式化的日期
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date){
		return getDateFormat().format(date);
	}
	
	/**
	 * 获取Date类型
	 * @param strDate
	 * @return
	 * @throws ParseException
	 */
	public static Date parse(String strDate) {
		Date formatDate = null;
		try {
			formatDate = getDateFormat().parse(strDate);
		} catch (ParseException e) {
			return null;
		}
		return formatDate;
	}
}
