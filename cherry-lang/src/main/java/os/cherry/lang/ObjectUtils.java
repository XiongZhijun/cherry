/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 * You may obtain a copy of the License at
 *      http://www.apache.org/licenses/LICENSE-2.0
 */
package os.cherry.lang;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import os.cherry.lang.exception.CreateInstanceException;
import os.cherry.lang.exception.FieldValueOperatException;
import os.cherry.lang.exception.MethodInvokeException;

/**
 * 参考自apache的commons项目，只是保留了一些最常用的工具方法，目的是可以在android项目中使用，减少包的大小。
 * 
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * @see org.apache.commons.lang.ObjectUtils
 * 
 */
public abstract class ObjectUtils {
	public static String toString(Object obj) {
		return obj == null ? "" : obj.toString();
	}

	/**
	 * 设置字段的值。
	 * 
	 * @param target
	 *            待设置的对象
	 * @param field
	 *            待设置的对象字段
	 * @param value
	 *            值
	 */
	public static void setFieldValue(Object target, Field field, Object value) {
		try {
			field.setAccessible(true);
			field.set(target, value);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw FieldValueOperatException.createSetException(target, field,
					null, e);
		}
	}

	/**
	 * 读取字段的值
	 * 
	 * @param target
	 *            待读取的对象
	 * @param fieldName
	 *            字段的名称
	 * @return 字段的值
	 */
	public static Object getFieldValue(Object target, String fieldName) {
		if (target == null) {
			return null;
		}
		if (fieldName.contains(StringUtils.DOT)) {
			String[] splits = fieldName.split("\\.");
			Object value = target;
			for (int i = 0; i < splits.length; i++) {
				value = getFieldValue(value, splits[i]);
				if (value == null) {
					return null;
				}
			}
			return value;
		}
		Field field = ClassUtils.findField(target.getClass(), fieldName);
		if (field == null) {
			return null;
		}
		return getFieldValue(target, field);
	}

	public static Object getFieldValue(Object target, Field field) {
		try {
			field.setAccessible(true);
			return field.get(target);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw FieldValueOperatException.createGetException(target, field,
					null, e);
		}
	}

	/**
	 * 读取属性的值。
	 * 
	 * @param target
	 * @param propertyName
	 * @return
	 */
	public static Object getProperty(Object target, String propertyName) {
		if (target == null || StringUtils.isBlank(propertyName)) {
			return null;
		}
		Class<?> clazz = target.getClass();
		Method getter = ClassUtils.findGetter(clazz, propertyName);
		if (getter != null) {
			return getProperty(target, getter);
		}
		return getFieldValue(target, propertyName);
	}

	public static Object getProperty(Object target, Method getter) {
		try {
			return getter.invoke(target);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new MethodInvokeException(target, getter, null, e);
		}
	}

	/**
	 * 根据class name用默认构造函数创建对象。
	 * 
	 * @param className
	 * @return
	 */
	public static Object createBean(String className) {
		Class<?> clazz = ClassUtils.forName(className);
		return createInstance(clazz);
	}

	/**
	 * 根据class用默认构造函数创建对象
	 * 
	 * @param clazz
	 * @return
	 */
	public static <T> T createInstance(Class<T> clazz) {
		try {
			return clazz.newInstance();
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new CreateInstanceException(clazz, e);
		}
	}

	public static Object invoke(Object target, Method method, Object... args) {
		try {
			return method.invoke(target, args);
		} catch (IllegalArgumentException e) {
			throw e;
		} catch (Exception e) {
			throw new MethodInvokeException(target, method, args, e);
		}
	}
}
