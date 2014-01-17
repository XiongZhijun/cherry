/*
 * Copyright © 2013-2014 Xiong Zhijun, All Rights Reserved.
 */
package os.cherry.android.lang;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import os.cherry.lang.ClassUtils;
import os.cherry.lang.DateUtils;
import os.cherry.lang.ObjectUtils;
import os.cherry.lang.StringUtils;
import android.content.Context;

/**
 * 
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 */
public class FormHelper {
	private Map<Class<?>, Serializer> serializerMap = new HashMap<Class<?>, Serializer>();
	private Map<Class<?>, Deserializer> deserializerMap = new HashMap<Class<?>, Deserializer>();
	private String[] from;
	private int[] to;
	private ViewFindable viewFindable;
	private Map<String, Method> getterMap = new HashMap<String, Method>();

	public FormHelper(String[] from, int[] to, Class<?> clazz) {
		this(from, to, clazz, null);
	}

	public FormHelper(String[] from, int[] to, Class<?> clazz,
			ViewFindable viewFindable) {
		super();
		this.from = from;
		this.to = to;
		this.viewFindable = viewFindable;
		initGetterMap(from, clazz);
	}

	protected void initGetterMap(String[] from, Class<?> clazz) {
		for (String property : from) {
			Method getter = ClassUtils.findGetter(clazz, property);
			getterMap.put(property, getter);
		}
	}

	public void setValues(Object target) {
		setValues(target, viewFindable);
	}

	public void setValues(Object target, ViewFindable viewFindable) {
		ViewHelper viewHelper = new ViewHelper(viewFindable);
		for (int i = 0; i < to.length; i++) {
			Object value = ObjectUtils.getProperty(target,
					getterMap.get(from[i]));
			Serializer serializer = findSerializer(value);
			viewHelper.setValue(to[i], serializer.serialize(value));
		}
	}

	protected Serializer findSerializer(Object value) {
		if (value == null) {
			return DEFAULT_SERIALIZER;
		}
		for (Entry<Class<?>, Serializer> entry : serializerMap.entrySet()) {
			if (entry.getKey().isAssignableFrom(value.getClass())) {
				return entry.getValue();
			}
		}
		return DEFAULT_SERIALIZER;
	}

	public void addSerializer(Class<?> clazz, Serializer serializer) {
		serializerMap.put(clazz, serializer);
	}

	public void addDeserializer(Class<?> clazz, Deserializer deserializer) {
		deserializerMap.put(clazz, deserializer);
	}

	public void setViewFindable(ViewFindable viewFindable) {
		this.viewFindable = viewFindable;
	}

	private static final Serializer DEFAULT_SERIALIZER = new Serializer() {
		public String serialize(Object value) {
			return value != null ? value.toString() : StringUtils.EMPTY;
		}
	};

	public static interface Serializer {
		String serialize(Object value);
	}

	public static interface Deserializer {
		Object serialize(String value);
	}

	public static class Builder {
		private FormHelper formHelper;
		private Context context;

		public Builder(Context context, String[] from, int[] to, Class<?> clazz) {
			this(context, from, to, clazz, null);
		}

		public Builder(Context context, String[] from, int[] to,
				Class<?> clazz, ViewFindable viewFindable) {
			this.context = context.getApplicationContext();
			formHelper = new FormHelper(from, to, clazz, viewFindable);
			addEnumSerializer();
		}

		public Builder addSerializer(Class<?> clazz, Serializer serializer) {
			formHelper.addSerializer(clazz, serializer);
			return this;
		}

		public Builder addDateSerializer(String pattern) {
			return addSerializer(Date.class, new DateSerializer(pattern));
		}

		private Builder addEnumSerializer() {
			return addSerializer(Enum.class, new EnumSerializer(context));
		}

		public FormHelper build() {
			return formHelper;
		}
	}

	public static class DateSerializer implements Serializer {
		private String pattern;

		public DateSerializer(String pattern) {
			super();
			this.pattern = pattern;
		}

		public String serialize(Object value) {
			if (value == null) {
				return StringUtils.EMPTY;
			}
			return DateUtils.format(pattern, (Date) value);
		}
	}

	public static class EnumSerializer implements Serializer {
		private Context context;

		public EnumSerializer(Context context) {
			this.context = context;
		}

		public String serialize(Object value) {
			return ResourcesUtils.getEnumShowName(context, (Enum<?>) value);
		}
	}
}
