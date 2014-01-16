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
public class CreateInstanceException extends ObjectException {

	private Class<?> clazz;

	/**
	 * @param clazz
	 */
	public CreateInstanceException(Class<?> clazz, Throwable cause) {
		super(toMessage(clazz), cause);
		this.clazz = clazz;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	private static String toMessage(Class<?> clazz) {
		return join("Create instance of", clazz.getName(), "failed!");
	}

	private static final long serialVersionUID = -1553780326370620250L;

}
