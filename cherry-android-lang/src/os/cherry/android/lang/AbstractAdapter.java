/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.cherry.android.lang;

import java.util.List;

import os.cherry.android.lang.ViewFindable.ViewWrapper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public abstract class AbstractAdapter<T> extends BaseAdapter {
	private LayoutInflater inflater;
	private List<T> datas;
	private int resource;

	public AbstractAdapter(Context context, List<T> datas, int resource) {
		super();
		this.inflater = LayoutInflater.from(context);
		this.datas = datas;
		this.resource = resource;
	}

	@Override
	public int getCount() {
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		return datas.get(position);
	}

	public T getItemWithType(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v;
		if (convertView == null) {
			v = inflater.inflate(resource, parent, false);
		} else {
			v = convertView;
		}
		ViewHelper viewHelper = new ViewHelper(new ViewWrapper(v));
		bindView(position, v, viewHelper);

		return v;
	}

	protected abstract void bindView(int position, View view,
			ViewHelper viewHelper);

}
