/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.cherry.lang.exception;

import java.lang.reflect.Method;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class MethodInvokeException extends ObjectException {
	private Object target;
	private Method method;
	private Object[] args;

	public MethodInvokeException(Object target, Method method, Object[] args,
			Throwable cause) {
		super(toMessage(target, method, args), cause);
		this.target = target;
		this.method = method;
		this.args = args;
	}

	public Object getTarget() {
		return target;
	}

	public Method getMethod() {
		return method;
	}

	public Object[] getArgs() {
		return args;
	}

	private static String toMessage(Object target, Method method, Object[] args) {
		return join("Invoke method", method.getName(), "by target", target,
				"failed.");
	}

	private static final long serialVersionUID = -1518876768879803819L;

}
