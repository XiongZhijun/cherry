/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.cherry.android.lang;

import android.view.View;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public interface ViewFindable {

	View findViewById(int id);

	public static class ViewWrapper implements ViewFindable {
		private View view;

		public ViewWrapper(View view) {
			super();
			this.view = view;
		}

		public View findViewById(int id) {
			return view.findViewById(id);
		}
	}
}
