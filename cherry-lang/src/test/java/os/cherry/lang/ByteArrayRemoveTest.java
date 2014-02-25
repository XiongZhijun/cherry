/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.cherry.lang;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
@RunWith(Parameterized.class)
public class ByteArrayRemoveTest {
	private static byte[] ARRAY = new byte[] { 0x11, 0x13, 0x23, 0x32, 0x44,
			0x52, 0x23, 0x34 };

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays
				.asList(new Object[][] {
						{ new byte[0], new byte[0], new byte[0], 0, 0 },
						{ new byte[0], new byte[0], new byte[0], 0, 1 },
						{ new byte[0], new byte[0], new byte[0], -1, 10 },
						{ new byte[0], new byte[0], new byte[0], -1, -15 },
						{ new byte[0], new byte[] {}, new byte[] {}, 0, 0 },
						{ ARRAY, new byte[0], ARRAY, 0, 0 },
						{ ARRAY, new byte[0], ARRAY, 1, -1 },
						{ ARRAY, new byte[0], ARRAY, 3, 2 },
						{ ARRAY, new byte[0], ARRAY, -1, -1 },
						{ ARRAY, new byte[0], ARRAY, ARRAY.length, ARRAY.length },
						{ ARRAY, new byte[0], ARRAY, 100, 200 },
						{ ARRAY, ARRAY, new byte[0], 0, ARRAY.length },
						{ ARRAY, ARRAY, new byte[0], 0, 100 },
						{ ARRAY, ARRAY, new byte[0], -1, 100 },
						{ ARRAY, ARRAY, new byte[0], -15, ARRAY.length },
						{
								ARRAY,
								new byte[] { 0x11 },
								new byte[] { 0x13, 0x23, 0x32, 0x44, 0x52,
										0x23, 0x34 }, 0, 1 },
						{ ARRAY, new byte[] { 0x11, 0x13, 0x23 },
								new byte[] { 0x32, 0x44, 0x52, 0x23, 0x34 }, 0,
								3 },
						{ ARRAY, new byte[] { 0x11, 0x13, 0x23 },
								new byte[] { 0x32, 0x44, 0x52, 0x23, 0x34 },
								-1, 3 },
						{ ARRAY, new byte[0], ARRAY, 3, 3 },
						{
								ARRAY,
								new byte[] { 0x32 },
								new byte[] { 0x11, 0x13, 0x23, 0x44, 0x52,
										0x23, 0x34 }, 3, 4 },
						{ ARRAY, new byte[] { 0x32, 0x44, 0x52, 0x23 },
								new byte[] { 0x11, 0x13, 0x23, 0x34 }, 3, 7 } });
	}

	private byte[] array;
	private byte[] removed;
	private byte[] remaining;
	private int startIndex;
	private int endIndex;
	private ByteArray byteArray;

	public ByteArrayRemoveTest(byte[] array, byte[] removed, byte[] remaining,
			int startIndex, int endIndex) {
		super();
		this.array = array;
		this.removed = removed;
		this.remaining = remaining;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		this.byteArray = new ByteArray();
		this.byteArray.addAll(array);
	}

	@Test
	public void testRemove() {
		assertEquals(array.length, byteArray.size());
		assertArrayEquals(removed, byteArray.remove(startIndex, endIndex));
		assertArrayEquals(remaining, byteArray.toArray());
		assertEquals(array.length, byteArray.size() + removed.length);
	}

}
