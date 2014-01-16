/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.cherry.lang;

import os.cherry.lang.log.LogFactory;
import os.cherry.lang.log.Logger;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class LoggerUtils {
	private static LogFactory logFactory;

	public static Logger getLogger(Class<?> clazz) {
		return logFactory.getLogger(clazz);
	}

	public static Logger getLog(String name) {
		return logFactory.getLogger(name);
	}

	public static void setLoggerFactory(LogFactory logFactory) {
		LoggerUtils.logFactory = logFactory;
	}

}
