/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.cherry.lang;

import java.io.Closeable;
import java.io.IOException;

import os.cherry.lang.log.Logger;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public abstract class IOUtils {
	private static Logger logger = LoggerUtils.getLogger(IOUtils.class);

	public static void close(Closeable closeable) {
		if (closeable == null) {
			return;
		}
		try {
			closeable.close();
		} catch (IOException e) {
			logger.warn("close " + closeable + " failed.", e);
		}
	}
}
