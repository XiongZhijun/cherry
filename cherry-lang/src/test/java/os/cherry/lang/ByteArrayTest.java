/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.cherry.lang;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static os.cherry.lang.ArrayUtils.INDEX_NOT_FOUND;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class ByteArrayTest {

	private ByteArray byteArray;

	@Before
	public void setUp() {
		byteArray = new ByteArray();
		assertTrue(byteArray.isEmpty());
		byteArray.add((byte) 0x34);
		assertFalse(byteArray.isEmpty());
		byteArray.add((byte) 0x94);
		byteArray.add((byte) 0x23);
		byteArray.addAll(new byte[] { 0x13, 0x23 });
		assertFalse(byteArray.isEmpty());
	}

	@Test
	public void testAdd() {
		assertEquals(5, byteArray.size());
		assertArrayEquals(new byte[] { 0x34, (byte) 0x94, 0x23, 0x13, 0x23 },
				byteArray.toArray());

	}

	@Test
	public void testIndexOf() {
		assertEquals(3, byteArray.indexOf((byte) 0x13));
		assertEquals(INDEX_NOT_FOUND, byteArray.indexOf((byte) 0x14));
		assertEquals(2, byteArray.indexOf((byte) 0x23));
		assertEquals(2, byteArray.indexOf((byte) 0x23, 2));
		assertEquals(4, byteArray.indexOf((byte) 0x23, 3));

		assertEquals(INDEX_NOT_FOUND, byteArray.indexOf(new byte[0]));
		assertEquals(3, byteArray.indexOf(new byte[] { 0x13, 0x23 }));
		assertEquals(INDEX_NOT_FOUND, byteArray.indexOf(new byte[] { 0x14 }));
		assertEquals(INDEX_NOT_FOUND,
				byteArray.indexOf(new byte[] { 0x13, 0x24 }));

		assertEquals(3, byteArray.indexOf(new byte[] { 0x13, 0x23 }, 0));
		assertEquals(3, byteArray.indexOf(new byte[] { 0x13, 0x23 }, 3));
		assertEquals(INDEX_NOT_FOUND,
				byteArray.indexOf(new byte[] { 0x13, 0x23 }, 4));
	}

	@Test
	public void testGet() {
		ByteArray array = new ByteArray();
		array.addAll(new byte[] { 0x23, 0x34, 0x43, 012, 0x32 });
		assertArrayEquals(new byte[0], array.get(0, 0));
		assertArrayEquals(new byte[0], array.get(0, -1));
		assertArrayEquals(new byte[0], array.get(-1, -1));
		assertArrayEquals(new byte[0], array.get(-1, -99));
		assertArrayEquals(new byte[0], array.get(10, 5));

		assertArrayEquals(new byte[] { 0x23 }, array.get(0, 1));
		assertArrayEquals(new byte[] { 0x23, 0x34, 0x43 }, array.get(0, 3));
		assertArrayEquals(new byte[] { 0x23, 0x34, 0x43, 012, 0x32 },
				array.get(0, 10));
		assertArrayEquals(new byte[] { 0x23, 0x34, 0x43, 012, 0x32 },
				array.toArray());
		assertEquals(5, array.size());
	}

	@Test
	public void testClear() {
		byteArray.clear();
		assertTrue(byteArray.isEmpty());
		assertEquals(0, byteArray.size());
		assertArrayEquals(new byte[0], byteArray.toArray());
	}

}
