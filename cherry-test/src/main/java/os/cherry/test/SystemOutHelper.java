/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.cherry.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.PrintStream;

import os.cherry.lang.io.StringBuilderOutStream;
import os.cherry.lang.io.StringBuilderOutStream.Filter;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class SystemOutHelper {

	private StringBuilderOutStream outStream;

	public static final String LINE_SEPARATOR = System
			.getProperty("line.separator");

	public void redirectOut() {
		outStream = new StringBuilderOutStream();
		System.setOut(new PrintStream(outStream));
	}

	public void addFilter(Filter filter) {
		outStream.addFilter(filter);
	}

	public void removeFilter(Filter filter) {
		outStream.removeFilter(filter);
	}

	public void assertOutString(String expected) {
		assertNotNull(outStream);
		assertEquals(expected, outStream.toString());
	}

	public void reset() {
		outStream.reset();
	}
}
