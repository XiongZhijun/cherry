/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.cherry.lang;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class ArrayUtilsTest {

	/**
	 * Test method for
	 * {@link os.cherry.lang.ArrayUtils#isEmpty(java.lang.Object[])}.
	 */
	@Test
	public void testIsEmpty() {
		assertTrue(ArrayUtils.isEmpty(new String[0]));
		assertFalse(ArrayUtils.isEmpty(new Integer[] { 1 }));
	}

	@Test
	public void testAdd() {
		assertArrayEquals(new String[] { "h1", "h2" },
				ArrayUtils.add(String.class, "h1", new String[] { "h2" }));
		assertArrayEquals(new String[] { "h1" },
				ArrayUtils.add(String.class, "h1", null));
		assertArrayEquals(new String[] { "h1" },
				ArrayUtils.add(String.class, "h1", new String[0]));
		assertArrayEquals(new String[] { "h1", null, null, null },
				ArrayUtils.add(String.class, "h1", new String[3]));
	}

}
