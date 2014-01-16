/*
 * Copyright © 2013-2014 Xiong Zhijun, All Rights Reserved. 
 */
package os.cherry.lang;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import os.cherry.lang.exception.ClassMemberFindException;

/**
 * Class的工具类。主要用来进行反射。
 * 
 * @author Xiong Zhijun
 * 
 */
public abstract class ClassUtils {

	/**
	 * 获取类的所有字段，包括该类的父类包含的所有字段，除了Object.class包含的字段。
	 * <p>
	 * 另外可以通过includePackages进行过滤:
	 * <ul>
	 * <li>
	 * 如果设置了includePackages，则只有父类属于这些包及子包的才会进行查找，否则将不进行查找。
	 * <li>
	 * 如果没有设置includePackages，则查找所有父类的。
	 * <p>
	 * 
	 * @param clazz
	 * @param includesPackages
	 * @return
	 */
	public static List<Field> getDeclaredFields(Class<?> clazz,
			String... includesPackages) {
		List<Field> fields = new ArrayList<Field>();
		Field[] declaredFields = clazz.getDeclaredFields();
		fields.addAll(Arrays.asList(declaredFields));

		Class<?> superclass = clazz.getSuperclass();
		if (superclass != null && include(superclass, includesPackages)
				&& clazz != Object.class) {
			fields.addAll(getDeclaredFields(superclass, includesPackages));
		}
		return fields;
	}

	private static boolean include(Class<?> clazz, String... includesPackages) {
		if (includesPackages == null || includesPackages.length == 0) {
			return true;
		}
		String className = clazz.getName();
		for (String includesPackage : includesPackages) {
			if (className.startsWith(includesPackage)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 根据类名找到类定义对象。
	 * 
	 * @param className
	 *            类名
	 * @return
	 */
	public static Class<?> forName(String className) {
		try {
			return Class.forName(className.trim());
		} catch (ClassNotFoundException e) {
			throw new os.cherry.lang.exception.ClassNotFoundException(
					className, e);
		}
	}

	/**
	 * 查找类的字段。
	 * 
	 * @param clazz
	 * @param fieldName
	 * @return
	 */
	public static Field findField(Class<?> clazz, String fieldName) {
		try {
			return clazz.getDeclaredField(fieldName);
		} catch (SecurityException e) {
			throw ClassMemberFindException.createFieldException(clazz,
					fieldName, e);
		} catch (NoSuchFieldException e) {
			Class<?> superclass = clazz.getSuperclass();
			if (superclass != null && superclass != Object.class) {
				return findField(superclass, fieldName);
			}
			return null;
		}
	}

	/**
	 * 查找类的方法。
	 * 
	 * @param clazz
	 * @param methodName
	 * @param parameterTypes
	 * @return
	 */
	public static Method findMethod(Class<?> clazz, String methodName,
			Class<?>... parameterTypes) {
		try {
			return clazz.getDeclaredMethod(methodName, parameterTypes);
		} catch (SecurityException e) {
			throw ClassMemberFindException.createMethodException(clazz,
					methodName, e);
		} catch (NoSuchMethodException e) {
			Class<?> superclass = clazz.getSuperclass();
			if (superclass != null && superclass != Object.class) {
				return findMethod(superclass, methodName);
			}
			return null;
		}
	}

	/**
	 * 查找属性的getter方法。
	 * 
	 * @param clazz
	 * @param name
	 * @return
	 */
	public static Method findGetter(Class<?> clazz, String name) {
		String getterName = buildGetterName(name);
		return findMethod(clazz, getterName);
	}

	/**
	 * 得到属性的getter方法的方法名。
	 * 
	 * @param name
	 * @return
	 */
	public static String buildGetterName(String name) {
		if (name == null) {
			return StringUtils.BLANK;
		}
		String prefix = "is";
		if (name.startsWith(prefix)) {
			return name;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("get").append(name.substring(0, 1).toUpperCase())
				.append(name.substring(1, name.length()));
		return sb.toString();
	}

	public static boolean isPrimitive(Class<?> clazz) {
		return clazz.isPrimitive() || clazz.equals(Long.class)
				|| clazz.equals(Double.class) || clazz.equals(Float.class)
				|| clazz.equals(Integer.class) || clazz.equals(Short.class)
				|| clazz.equals(String.class);
	}
}
