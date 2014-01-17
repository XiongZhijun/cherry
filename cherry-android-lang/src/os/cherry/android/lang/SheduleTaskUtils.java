/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.cherry.android.lang;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class SheduleTaskUtils {

	public static void shedule(Context context, Intent intent, int requestCode,
			 long triggerAtTime, long interval) {
		PendingIntent operation = PendingIntent.getService(context, requestCode,
				intent, 0);
		AlarmManager am = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		am.setRepeating(AlarmManager.RTC_WAKEUP, triggerAtTime,
				interval, operation);
	}

	public static void cancel(Context context, Intent intent, int requestCode) {
		PendingIntent sender = PendingIntent.getService(context, requestCode,
				intent, 0);
		AlarmManager am = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		am.cancel(sender);
	}
}
