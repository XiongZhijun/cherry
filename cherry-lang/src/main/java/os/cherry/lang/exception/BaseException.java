/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.cherry.lang.exception;

import os.cherry.lang.StringUtils;

/**
 * 最基本的Exception。
 * 
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public abstract class BaseException extends RuntimeException {

	public BaseException() {
		super();
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(Throwable cause) {
		super(cause);
	}

	protected static String join(Object... array) {
		return StringUtils.join(array, StringUtils.BLANK);
	}

	private static final long serialVersionUID = -6673702479767522120L;

}
