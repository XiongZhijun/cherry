/*
 * Copyright © 2013-2014 Xiong Zhijun, All Rights Reserved. 
 */
package os.cherry.lang;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import os.cherry.lang.exception.DateParseException;

/**
 * 
 * 
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public abstract class DateUtils {

	private static ThreadLocal<Map<String, DateFormat>> threadLocal = new ThreadLocal<Map<String, DateFormat>>() {
		protected Map<String, DateFormat> initialValue() {
			return new HashMap<String, DateFormat>();
		}
	};

	public static String format(String pattern, Date date) {
		if (date == null) {
			return null;
		}
		DateFormat dateFormat = findDateFormat(pattern);
		return dateFormat.format(date);
	}

	public static Date parse(String pattern, String date) {
		if (StringUtils.isBlank(date)) {
			return null;
		}
		DateFormat dateFormat = findDateFormat(pattern);
		try {
			return dateFormat.parse(date);
		} catch (ParseException e) {
			throw new DateParseException(pattern, date, e);
		}
	}

	private static DateFormat findDateFormat(String pattern) {
		Map<String, DateFormat> map = threadLocal.get();
		DateFormat dateFormat = map.get(pattern);
		if (dateFormat == null) {
			dateFormat = new SimpleDateFormat(pattern);
			map.put(pattern, dateFormat);
		}
		return dateFormat;
	}
}
