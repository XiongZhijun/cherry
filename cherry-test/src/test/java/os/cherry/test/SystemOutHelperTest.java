/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.cherry.test;

import static os.cherry.test.SystemOutHelper.LINE_SEPARATOR;

import org.junit.Before;
import org.junit.Test;

import os.cherry.test.StringBuilderOutStream.Filter;

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
		helper.redirectOut();
	}

	/**
	 * Test method for {@link os.cherry.test.SystemOutHelper#redirectOut()}.
	 */
	@Test
	public void testRedirectOut() {
		System.out.println("hello world.");
		helper.assertOutString("hello world." + LINE_SEPARATOR);
		System.out.print("haha");
		helper.assertOutString("hello world." + LINE_SEPARATOR + "haha");

		helper.reset();
		helper.assertOutString("");
	}

	@Test
	public void testRedirectOutWithFilter() {
		Filter filter1 = new Filter() {
			public boolean ignore(String str) {
				return str.contains("hello") || str.equals(LINE_SEPARATOR);
			}
		};
		helper.addFilter(filter1);
		System.out.println("hello world.");
		helper.assertOutString("");
		System.out.print("haha");
		helper.assertOutString("haha");

		helper.removeFilter(filter1);
		System.out.println(" hello world.");
		helper.assertOutString("haha hello world." + LINE_SEPARATOR);

		helper.reset();
		helper.assertOutString("");
	}

}
