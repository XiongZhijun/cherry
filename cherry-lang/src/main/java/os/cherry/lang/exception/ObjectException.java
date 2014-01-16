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
public class ObjectException extends BaseException {

	/**
	 * 
	 */
	public ObjectException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ObjectException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public ObjectException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ObjectException(Throwable cause) {
		super(cause);
	}

	private static final long serialVersionUID = -8189137537954556852L;

}
