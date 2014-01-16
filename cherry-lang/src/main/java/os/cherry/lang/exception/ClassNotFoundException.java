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
public class ClassNotFoundException extends ClassException {

	private String className;

	/**
	 * @param className
	 */
	public ClassNotFoundException(String className) {
		super(toMessage(className));
		this.className = className;
	}

	/**
	 * @param className
	 * @param cause
	 */
	public ClassNotFoundException(String className, Throwable cause) {
		super(toMessage(className), cause);
		this.className = className;
	}

	public String getClassName() {
		return className;
	}

	private static String toMessage(String className) {
		return join("class", className, "not found!");
	}

	private static final long serialVersionUID = 7469606630613235385L;

}
