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
}
