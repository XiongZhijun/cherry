/*
 * Copyright © 2013-2014 Xiong Zhijun, All Rights Reserved. 
 */
package os.cherry.android.lang;

import static os.cherry.lang.StringUtils.UNDERLINE;

import java.lang.reflect.Field;

import android.content.Context;
import android.util.Log;

/**
 * 通过反射获取资源的工具类。
 * 
 * @author Xiong Zhijun
 * 
 */
public abstract class ResourcesUtils {

	private static final String ID = "id";
	private static final String STRING = "string";
	private static final String LAYOUT = "layout";
	private static final String DRAWABLE = "drawable";

	/**
	 * 枚举类型是系统中常用的状态变量。该方法的目的是可以根据枚举类型对象获取该对象在Android系统资源中的字符串资源。
	 * 每一个枚举对象对应的字符串资源格式是:
	 * <p>
	 * <b>{枚举类型类名} + '_' + {枚举对象的name}</b>
	 * <p>
	 * 比如:
	 * 
	 * <pre>
	 * TableStatus.java
	 * {@code
	 * package com.mobi
	 * enum TableStatus {
	 * 	OPEN, CLOSED, IDLE
	 * }
	 * string.xml:
	 * 
	 * {@code
	 * <string name="TableStatus_OPEN">开台</string>
	 * <string name="TableStatus_CLOSED">已清台</string>
	 * <string name="TableStatus_IDLE">空闲</string>
	 * 
	 * }
	 * </pre>
	 * 
	 * @param context
	 *            上下文对象
	 * @param _enum
	 *            枚举对象
	 * @return
	 */
	public static String getEnumShowName(Context context, Enum<?> _enum) {
		return ResourcesUtils.getString(context, _enum.getClass()
				.getSimpleName() + UNDERLINE + _enum.name());
	}

	/**
	 * 根据string资源的名称来获取字符串值。
	 * 
	 * @param context
	 *            上下文对象
	 * @param resourceName
	 *            字符串资源的名称
	 * @return 返回字符串资源的值，如果没有找到则返回null。
	 */
	public static String getString(Context context, String resourceName) {
		int resId = getStringResourcesId(context, resourceName);
		if (resId == Integer.MIN_VALUE) {
			return null;
		}
		return context.getString(resId);
	}

	/**
	 * 根据layout资源的名称来获取layout资源的id值。
	 * 
	 * @param context
	 *            上下文对象
	 * @param resourceName
	 *            资源名称
	 * @return
	 */
	public static int getLayoutResourcesId(Context context, String resourceName) {
		return getResourcesIdByType(context, resourceName, LAYOUT);
	}

	/**
	 * 根据drawable资源的名称来获取drawable资源的id值。
	 * 
	 * @param context
	 *            上下文对象
	 * @param resourceName
	 *            资源名称
	 * @return
	 */
	public static int getDrawableResourcesId(Context context,
			String resourceName) {
		return getResourcesIdByType(context, resourceName, DRAWABLE);
	}

	/**
	 * 根据string资源的名称来获取资源值。 <b>注意：不是字符串的值，而是资源Id的值</b>
	 * 
	 * @param context
	 *            上下文对象
	 * @param resourceName
	 *            资源名称
	 * @return
	 */
	public static int getStringResourcesId(Context context, String resourceName) {
		return getResourcesIdByType(context, resourceName, STRING);
	}

	/**
	 * 根据id资源的名称来获取资源id值。
	 * 
	 * @param context
	 *            上下文对象
	 * @param resourceName
	 *            资源名称
	 * @return
	 */
	public static int getIdResourcesId(Context context, String resourceName) {
		return getResourcesIdByType(context, resourceName, ID);
	}

	/**
	 * 根据资源名称来获取对应类型的资源的id值
	 * 
	 * @param context
	 *            上下文对象
	 * @param resourceName
	 *            资源名称
	 * @param type
	 *            资源类型，一般有：layout/string/id等android支持的资源类型，也就是在R.java文件中支持的资源类型。
	 * @return
	 */
	public static int getResourcesIdByType(Context context,
			String resourceName, String type) {
		Field field = null;
		Class<?> clazz = getResouceClass(context, type);
		try {
			field = clazz.getField(resourceName);
			return (Integer) field.get(null);
		} catch (Exception e) {
			Log.w(TAG, "get resource id failed. resource name : "
					+ resourceName);
			return Integer.MIN_VALUE;
		}
	}

	private static Class<?> getResouceClass(Context context, String type) {
		String packageName = context.getPackageName();
		StringBuilder sb = new StringBuilder();
		sb.append(packageName).append(".R$").append(type);
		String className = sb.toString();
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new os.cherry.lang.exception.ClassNotFoundException(className);
		}
	}

	private static final String TAG = ResourcesUtils.class.getName();
}
