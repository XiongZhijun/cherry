/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.cherry.android.lang;

import android.content.AsyncTaskLoader;
import android.content.Context;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public abstract class AsyncTaskLoaderAdapter<D> extends AsyncTaskLoader<D> {

	public AsyncTaskLoaderAdapter(Context context) {
		super(context);
	}

	@Override
	protected void onStartLoading() {
		super.onStartLoading();
		forceLoad();
	}

}
