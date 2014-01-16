/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.cherry.test;

import static os.cherry.test.SystemOutHelper.LINE_SEPARATOR;

import org.junit.Before;
import org.junit.Test;

import os.cherry.test.SystemOutHelper;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class SystemOutHelperTest {

	private SystemOutHelper helper;

	@Before
	public void setUp() {
		helper = new SystemOutHelper();
	}

	/**
	 * Test method for {@link os.cherry.test.SystemOutHelper#redirectOut()}.
	 */
	@Test
	public void testRedirectOut() {
		helper.redirectOut();
		System.out.println("hello world.");
		helper.assertOutString("hello world." + LINE_SEPARATOR);
		System.out.print("haha");
		helper.assertOutString("hello world." + LINE_SEPARATOR + "haha");

		helper.reset();
		helper.assertOutString("");
	}

}
