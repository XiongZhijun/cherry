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
public class ClassException extends BaseException {

	/**
	 * 
	 */
	public ClassException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ClassException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public ClassException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ClassException(Throwable cause) {
		super(cause);
	}

	private static final long serialVersionUID = -4447285946413080616L;

}
