/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package io.cherry.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class StringBuilderOutStreamTest {

	private static StringBuilderOutStream os;

	@BeforeClass
	public static void beforeClass() {
		os = new StringBuilderOutStream();
	}

	@Before
	public void setUp() {
		os.reset();
		assertEquals("", os.toString());
		assertEquals(0, os.toString().length());
	}

	@Test
	public void testWriteInt() throws IOException {
		os.write(0x31);
		assertEquals("1", os.toString());
	}

	@Test
	public void testWriteByteArray() throws IOException {
		os.write(new byte[] { 0x31, 0x32, 0x33 });
		assertEquals("123", os.toString());
		os.write(new byte[] { (byte) 0x41 });
		assertEquals("123A", os.toString());
		os.write(0x42);
		assertEquals("123AB", os.toString());
	}

}
