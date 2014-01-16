/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package io.cherry.test;

import static org.junit.Assert.assertEquals;

import java.io.PrintStream;

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

	public void assertOutString(String expected) {
		assertEquals(expected, outStream.toString());
	}

	public void reset() {
		outStream.reset();
	}
}
