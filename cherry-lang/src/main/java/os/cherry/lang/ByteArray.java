/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.cherry.lang;

import java.util.Arrays;
import static os.cherry.lang.ArrayUtils.*;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class ByteArray {
	private byte[] element;
	private int size;
	private int initialCapacity;

	public ByteArray() {
		this(10);
	}

	public ByteArray(int initialCapacity) {
		this.initialCapacity = initialCapacity;
		element = new byte[initialCapacity];
	}

	public void add(byte b) {
		ensureCapacity(size + 1);
		element[size++] = b;
	}

	public void addAll(byte[] bytes) {
		if (bytes == null || bytes.length == 0) {
			return;
		}
		int numNew = bytes.length;
		ensureCapacity(size + numNew);
		System.arraycopy(bytes, 0, element, size, numNew);
		size += numNew;
	}

	private void ensureCapacity(int minCapacity) {
		int oldCapacity = element.length;
		if (minCapacity > oldCapacity) {
			int newCapacity = (oldCapacity * 3) / 2 + 1;
			if (newCapacity < minCapacity)
				newCapacity = minCapacity;
			element = Arrays.copyOf(element, newCapacity);
		}
	}

	public int size() {
		return size;
	}

	public void clear() {
		size = 0;
		element = new byte[initialCapacity];
	}

	public byte[] toArray() {
		return Arrays.copyOf(element, size);
	}

	public int indexOf(byte b) {
		return indexOf(b, 0);
	}

	public int indexOf(byte b, int startIndex) {
		return ArrayUtils.indexOf(element, b, startIndex);
	}

	public int indexOf(byte[] array) {
		return indexOf(array, 0);
	}

	public int indexOf(byte[] array, int startIndex) {
		return ArrayUtils.indexOf(element, array, startIndex);
	}

	public byte[] get(int startIndex, int endIndex) {
		if (isEmpty() || startIndex >= size() || endIndex <= 0
				|| startIndex > endIndex) {
			return EMPTY_BYTE_ARRAY;
		}
		if (startIndex < 0) {
			startIndex = 0;
		}
		if (endIndex > size()) {
			endIndex = size();
		}
		int removedLength = endIndex - startIndex;
		byte[] removed = new byte[removedLength];
		System.arraycopy(element, startIndex, removed, 0, removedLength);
		return removed;
	}

	public byte[] remove(int startIndex, int endIndex) {
		if (isEmpty() || startIndex >= size() || endIndex <= 0
				|| startIndex > endIndex) {
			return EMPTY_BYTE_ARRAY;
		}
		if (startIndex < 0) {
			startIndex = 0;
		}
		if (endIndex > size()) {
			endIndex = size();
		}
		int removedLength = endIndex - startIndex;
		byte[] removed = new byte[removedLength];
		System.arraycopy(element, startIndex, removed, 0, removedLength);
		byte[] data = new byte[element.length];
		System.arraycopy(element, 0, data, 0, startIndex);
		System.arraycopy(element, endIndex, data, startIndex, size() - endIndex);
		element = data;
		size = size - removedLength;
		return removed;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

}
