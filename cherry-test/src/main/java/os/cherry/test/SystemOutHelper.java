/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.cherry.test;

import static org.junit.Assert.assertEquals;

import java.io.PrintStream;

import os.cherry.test.StringBuilderOutStream.Filter;

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
		assertEquals(expected, outStream.toString());
	}

	public void reset() {
		outStream.reset();
	}
}
