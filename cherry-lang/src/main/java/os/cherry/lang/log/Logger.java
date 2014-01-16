/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.cherry.lang.log;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public interface Logger {
	void trace(Object message);

	void trace(Object message, Throwable t);

	void debug(Object message);

	void debug(Object message, Throwable t);

	void info(Object message);

	void info(Object message, Throwable t);

	void warn(Object message);

	void warn(Object message, Throwable t);

	void error(Object message);

	void error(Object message, Throwable t);

	void fatal(Object message);

	void fatal(Object message, Throwable t);

}
