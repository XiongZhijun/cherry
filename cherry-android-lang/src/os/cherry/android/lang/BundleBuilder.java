/*
 * Copyright © 2013-2014 Xiong Zhijun, All Rights Reserved.
 */
package os.cherry.android.lang;

import java.io.Serializable;

import android.os.Bundle;

/**
 * 
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 */
public class BundleBuilder {
	private Bundle bundle = new Bundle();

	public BundleBuilder putAll(Bundle map) {
		bundle.putAll(map);
		return this;
	}

	public BundleBuilder putString(String key, String value) {
		bundle.putString(key, value);
		return this;
	}

	public BundleBuilder putInt(String key, int value) {
		bundle.putInt(key, value);
		return this;
	}

	public BundleBuilder putLong(String key, long value) {
		bundle.putLong(key, value);
		return this;
	}

	public BundleBuilder putBoolean(String key, boolean value) {
		bundle.putBoolean(key, value);
		return this;
	}

	public BundleBuilder putSerializable(String key, Serializable value) {
		bundle.putSerializable(key, value);
		return this;
	}

	public Bundle build() {
		return bundle;
	}

	public static Bundle create(String key, String value) {
		return new BundleBuilder().putString(key, value).build();
	}
}
