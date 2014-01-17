/*
 * Copyright © 2013-2014 Xiong Zhijun, All Rights Reserved. 
 */
package os.cherry.android.lang;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

import os.cherry.lang.ObjectUtils;
import android.content.Context;
import android.view.View;

/**
 * 可以用来直接注入view对象到activity、dialog、fragment中，避免很多类似findViewById然后类型转换的编码。
 * 
 * @author Xiong Zhijun
 * @since mobi-android-framework 2.0
 */
public class InjectViewHelper {

	/**
	 * 根据注解{@link InjectView}来注入实现了{@link ViewFindable}接口的类型的View类型的成员变量。
	 * 
	 * @param context
	 * @param viewFindable
	 */
	public static void injectViews(Context context, ViewFindable viewFindable) {
		injectViews(context, viewFindable, viewFindable);
	}

	/**
	 * 根据注解{@link InjectView}将viewFinable对象中查找到的view注入到target对象的字段中。
	 * 
	 * @param context
	 * @param viewFindable
	 * @param target
	 */
	public static void injectViews(Context context, ViewFindable viewFindable,
			Object target) {
		if (target == null || viewFindable == null) {
			return;
		}
		Class<? extends Object> clazz = target.getClass();
		injectViews(context, target, clazz, viewFindable);
	}

	private static void injectViews(Context context, Object target,
			Class<? extends Object> clazz, ViewFindable viewFindable) {
		if (clazz == null || clazz.getAnnotation(Inject.class) == null) {
			return;
		}
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			injectField(context, viewFindable, target, field);
		}
		injectViews(context, target, clazz.getSuperclass(), viewFindable);
	}

	protected static void injectField(Context context,
			ViewFindable viewFindable, Object target, Field field) {
		InjectView annotation = field.getAnnotation(InjectView.class);
		if (annotation == null) {
			return;
		}
		int resourcesId = annotation.value();
		if (resourcesId <= 0) {
			String resourceName = annotation.resourceName();
			resourcesId = ResourcesUtils
					.getIdResourcesId(context, resourceName);
		}
		View view = viewFindable.findViewById(resourcesId);
		if (view != null) {
			ObjectUtils.setFieldValue(target, field, view);
		}
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target({ ElementType.TYPE })
	public static @interface Inject {
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target({ ElementType.FIELD })
	public static @interface InjectView {

		/**
		 * 待注入的View的Id，一个是定义在R.java文件中。
		 * 
		 * @return
		 */
		int value() default 0;

		/**
		 * 待注入的View的Id的resource name，当{@link #value()}的值无效（小于等于0）时，通过该Resouce
		 * Name查找到View的ID，这个过程会利用
		 * {@link ResourcesUtils#getIdResourcesId(android.content.Context, String)}
		 * 来反射获取Resource Id。
		 * 
		 * @return
		 * @see {@link ResourcesUtils#getResourcesIdByType(android.content.Context, String, String)}
		 */
		String resourceName() default "";
	}

}
