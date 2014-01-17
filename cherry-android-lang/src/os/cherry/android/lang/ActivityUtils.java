/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.cherry.android.lang;

import android.app.ActionBar;
import android.app.Activity;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class ActivityUtils {

	public static void showBackAction(Activity activity, boolean show) {
		ActionBar actionBar = activity.getActionBar();
		if (actionBar != null) {
			actionBar.setHomeButtonEnabled(show);
			actionBar.setDisplayHomeAsUpEnabled(show);
			actionBar.setDisplayShowTitleEnabled(true);
		}
	}
}
