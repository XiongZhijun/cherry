/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.cherry.lang;

import java.lang.reflect.Array;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public abstract class ArrayUtils {
	public static final int INDEX_NOT_FOUND = -1;
	public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

	public static boolean isEmpty(byte[] array) {
		return array == null || array.length == 0;
	}

	public static <T> boolean isEmpty(T[] array) {
		return array == null || array.length == 0;
	}

	@SuppressWarnings("unchecked")
	public static <T> T[] add(Class<T> type, T obj, T[] oldArray) {
		boolean oldIsNotEmpty = oldArray != null;
		int oldLength = oldIsNotEmpty ? oldArray.length : 0;
		T[] newArray = (T[]) Array.newInstance(type, oldLength + 1);
		newArray[0] = obj;
		if (oldIsNotEmpty) {
			System.arraycopy(oldArray, 0, newArray, 1, oldLength);
		}
		return newArray;
	}

	public static int indexOf(byte[] array, byte valueToFind) {
		return indexOf(array, valueToFind, 0);
	}

	public static int indexOf(byte[] array, byte valueToFind, int startIndex) {
		return indexOf(array, new byte[] { valueToFind }, startIndex);
	}

	public static int indexOf(byte[] array, byte[] valueToFind) {
		return indexOf(array, valueToFind, 0);
	}

	public static int indexOf(byte[] array, byte[] valueToFind, int startIndex) {
		if (isEmpty(array) || isEmpty(valueToFind)) {
			return INDEX_NOT_FOUND;
		}
		if (startIndex < 0) {
			startIndex = 0;
		}
		boolean success = true;
		for (int i = startIndex; i < array.length; i++) {
			success = true;
			for (int j = 0; j < valueToFind.length; j++) {
				if (array[i + j] != valueToFind[j]) {
					success = false;
					break;
				}
			}
			if (success) {
				return i;
			}
		}
		return INDEX_NOT_FOUND;
	}

}
