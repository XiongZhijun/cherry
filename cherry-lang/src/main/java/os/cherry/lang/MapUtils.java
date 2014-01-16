/*
 * Copyright © 2013-2014 Xiong Zhijun, All Rights Reserved. 
 */
package os.cherry.lang;

import java.util.Map;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public abstract class MapUtils {

	public static <K, V> double getDouble(Map<K, V> map, K key) {
		V value = map.get(key);
		if (value == null) {
			return 0.0d;
		}
		if (value instanceof Double || value.getClass().equals(double.class)) {
			return (Double) value;
		}
		return Double.parseDouble(value.toString());
	}

	public static <K, V> long getLong(Map<K, V> map, K key) {
		V value = map.get(key);
		if (value == null) {
			return 0;
		}
		if (value instanceof Long || value.getClass().equals(long.class)) {
			return (Long) value;
		}
		return Long.parseLong(value.toString());
	}

	public static <K, V> int getInt(Map<K, V> map, K key) {
		V value = map.get(key);
		if (value == null) {
			return 0;
		}
		if (value instanceof Integer || value.getClass().equals(int.class)) {
			return (Integer) value;
		}
		return Integer.parseInt(value.toString());
	}

	public static <K, V> String getString(Map<K, V> map, K key) {
		V value = map.get(key);
		if (value == null) {
			return null;
		}
		if (value instanceof String) {
			return (String) value;
		}
		return value.toString();
	}

}
