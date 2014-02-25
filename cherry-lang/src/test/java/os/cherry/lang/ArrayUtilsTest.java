/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.cherry.lang;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static os.cherry.lang.ArrayUtils.INDEX_NOT_FOUND;

import org.junit.Test;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class ArrayUtilsTest {

	private static byte[] ARRAY = new byte[] { 0x11, 0x13, 0x23, 0x32, 0x44,
			0x52, 0x23, 0x34 };

	@Test
	public void testIsEmpty() {
		assertTrue(ArrayUtils.isEmpty(new String[0]));
		assertFalse(ArrayUtils.isEmpty(new Integer[] { 1 }));
	}

	@Test
	public void testByteArrayIsEmpty() {
		assertTrue(ArrayUtils.isEmpty(new byte[0]));
		assertFalse(ArrayUtils.isEmpty(new byte[] { 1 }));
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

	@Test
	public void testIndexOf() {
		assertEquals(INDEX_NOT_FOUND, ArrayUtils.indexOf(ARRAY, (byte) 0x63));
		assertEquals(0, ArrayUtils.indexOf(ARRAY, (byte) 0x11));
		assertEquals(ARRAY.length - 1, ArrayUtils.indexOf(ARRAY, (byte) 0x34));
		assertEquals(2, ArrayUtils.indexOf(ARRAY, (byte) 0x23));
		assertEquals(2, ArrayUtils.indexOf(ARRAY, (byte) 0x23, 1));
		assertEquals(INDEX_NOT_FOUND, ArrayUtils.indexOf(ARRAY, (byte) 0x11, 1));
		assertEquals(0, ArrayUtils.indexOf(ARRAY, (byte) 0x11, 0));
		assertEquals(3, ArrayUtils.indexOf(ARRAY, (byte) 0x32, 3));
		assertEquals(-1, ArrayUtils.indexOf(ARRAY, (byte) 0x32, 4));
		assertEquals(6, ArrayUtils.indexOf(ARRAY, (byte) 0x23, 3));
	}

	@Test
	public void testIndexOfArray() {
		assertEquals(INDEX_NOT_FOUND, ArrayUtils.indexOf(ARRAY, new byte[0]));
		assertEquals(0, ArrayUtils.indexOf(ARRAY, new byte[] { 0x11 }));
		assertEquals(INDEX_NOT_FOUND,
				ArrayUtils.indexOf(ARRAY, new byte[] { 0x12 }));
		assertEquals(INDEX_NOT_FOUND,
				ArrayUtils.indexOf(ARRAY, new byte[] { 0x11, 0x12 }));
		assertEquals(0, ArrayUtils.indexOf(ARRAY, new byte[] { 0x11, 0x13 }));
		assertEquals(1, ArrayUtils.indexOf(ARRAY, new byte[] { 0x13, 0x23 }));
		assertEquals(2, ArrayUtils.indexOf(ARRAY, new byte[] { 0x23, 0x32 }));
		assertEquals(INDEX_NOT_FOUND,
				ArrayUtils.indexOf(ARRAY, new byte[] { 0x32, 0x23 }));

		assertEquals(2, ArrayUtils.indexOf(ARRAY, new byte[] { 0x23, 0x32 }, 2));
		assertEquals(INDEX_NOT_FOUND,
				ArrayUtils.indexOf(ARRAY, new byte[] { 0x23, 0x32 }, 3));
		assertEquals(2, ArrayUtils.indexOf(ARRAY, new byte[] { 0x23, 0x32 }, 1));
		assertEquals(6, ArrayUtils.indexOf(ARRAY, new byte[] { 0x23, 0x34 }, 1));
	}

}
