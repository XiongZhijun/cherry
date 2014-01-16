/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.cherry.lang.exception;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class DateParseException extends BaseException {
	private String pattern;
	private String date;

	/**
	 * @param pattern
	 * @param date
	 */
	public DateParseException(String pattern, String date, Throwable cause) {
		super(toMessage(pattern, date), cause);
		this.pattern = pattern;
		this.date = date;
	}

	public String getPattern() {
		return pattern;
	}

	public String getDate() {
		return date;
	}

	private static String toMessage(String pattern, String date) {
		return join("Parse date", date, "by pattern", pattern, "failed.");
	}

	private static final long serialVersionUID = -606333972272903159L;

}
