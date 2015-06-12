package com.cjlu.newspublish.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtils {


	private DateUtils() {

	}

	/**
	 * 把string转换为Date
	 * 
	 * @param str
	 * @return
	 * @throws ParseException
	 */
	public static Date stringToDate(String str) throws ParseException {
		if (ValidateUtils.isValid(str)) {
			DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = (Date) format.parse(str);
			return date;
		}
		return null;
	}

	/**
	 * 计算当前系统时间与方法参数Date的天数差
	 * 
	 * @param date
	 * @return
	 */
	public static long daysOfDateDifference(Date date) {
		long days = 0l;
		long difference = System.currentTimeMillis() - date.getTime();
		days = difference / (1000 * 60 * 60 * 24);
		return days;
	}
	
}
