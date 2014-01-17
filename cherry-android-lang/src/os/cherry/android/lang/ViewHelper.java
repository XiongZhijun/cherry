/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.cherry.android.lang;

import java.util.regex.Pattern;

import os.cherry.lang.ObjectUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class ViewHelper {
	private static final Pattern PATTERN = Pattern.compile("[0-9]*");
	private ViewFindable viewFindable;

	public ViewHelper(ViewFindable viewFindable) {
		this.viewFindable = viewFindable;
	}

	public void setValue(int id, Object value) {
		View view = viewFindable.findViewById(id);
		if (view instanceof TextView) {
			setTextViewValue(id, value);
		} else if (view instanceof ImageView) {
			setImageViewValue(id, value);
		} else if (view instanceof CheckBox) {
			setCheckBoxValue(id, value);
		}
	}

	public void setTextViewValue(int id, Object text) {
		TextView textView = (TextView) viewFindable.findViewById(id);
		if (textView != null) {
			textView.setText(ObjectUtils.toString(text));
		}
	}

	public void setTextViewError(int id, Object error) {
		TextView textView = (TextView) viewFindable.findViewById(id);
		if (textView != null) {
			textView.setError(ObjectUtils.toString(error));
		}
	}

	public void setCheckBoxValue(int id, Object value) {
		CheckBox checkBox = (CheckBox) viewFindable.findViewById(id);
		boolean checked;
		if (value == null) {
			checked = false;
		} else {
			checked = Boolean.parseBoolean(value.toString());
		}
		if (checkBox != null) {
			checkBox.setChecked(checked);
		}
	}

	public void setImageViewValue(int id, Object res) {
		if (res == null) {
			return;
		}
		ImageView imageView = (ImageView) viewFindable.findViewById(id);
		int resId;
		if (res.getClass() == int.class || res.getClass() == Integer.class) {
			resId = (Integer) res;
		} else {
			String text = res.toString();
			if (!isInteger(text)) {
				resId = Integer.parseInt(text);
			} else {
				throw new IllegalArgumentException(
						"Parameter res is not integer resouce id.");
			}
		}
		if (imageView != null) {
			imageView.setImageResource(resId);
		}
	}

	public void setOnClickListener(int id, OnClickListener listener) {
		View view = viewFindable.findViewById(id);
		if (view != null) {
			view.setClickable(true);
			view.setOnClickListener(listener);
		}
	}

	private static boolean isInteger(String text) {
		return PATTERN.matcher(text).matches();
	}

}
