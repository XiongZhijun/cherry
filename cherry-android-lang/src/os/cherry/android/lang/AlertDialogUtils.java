/**
 * Copyright © 2014 Xiong Zhijun, All Rights Reserved.
 * Email : hust.xzj@gmail.com
 */
package os.cherry.android.lang;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;

/**
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class AlertDialogUtils {
	public static final OnClickListener DEFAULT_NEGATIVE_BUTTON_LISTENER = new OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			dialog.dismiss();
		}
	};

	public static AlertDialog create(Context context, int title, int message,
			int positiveButtonText, OnClickListener positiveButtonListener) {
		return create(context, context.getString(title),
				context.getString(message),
				context.getString(positiveButtonText), positiveButtonListener);
	}

	public static AlertDialog create(Context context, String title,
			String message, String positiveButtonText,
			OnClickListener positiveButtonListener) {
		return create(context, title, message,
				context.getString(R.string.NegativeButtonText),
				DEFAULT_NEGATIVE_BUTTON_LISTENER, positiveButtonText,
				positiveButtonListener);
	}

	public static AlertDialog create(Context context, int title, int message,
			int negativeButtonText, OnClickListener negativeButtonListener,
			int positiveButtonText, OnClickListener positiveButtonListener) {
		return create(context, context.getString(title),
				context.getString(message),
				context.getString(negativeButtonText), negativeButtonListener,
				context.getString(positiveButtonText), positiveButtonListener);
	}

	public static AlertDialog create(Context context, String title,
			String message, String negativeButtonText,
			OnClickListener negativeButtonListener, String positiveButtonText,
			OnClickListener positiveButtonListener) {
		return new AlertDialog.Builder(context).setTitle(title)
				.setMessage(message)
				.setNegativeButton(negativeButtonText, negativeButtonListener)
				.setPositiveButton(positiveButtonText, positiveButtonListener)
				.create();
	}

	public static AlertDialog create(Context context, int title, View view,
			int positiveButtonText, OnClickListener positiveButtonListener) {
		return create(context, context.getString(title), view,
				context.getString(positiveButtonText), positiveButtonListener);
	}

	public static AlertDialog create(Context context, String title, View view,
			String positiveButtonText, OnClickListener positiveButtonListener) {
		return create(context, title, view,
				context.getString(R.string.NegativeButtonText),
				DEFAULT_NEGATIVE_BUTTON_LISTENER, positiveButtonText,
				positiveButtonListener);
	}

	public static AlertDialog create(Context context, int title, View view,
			int negativeButtonText, OnClickListener negativeButtonListener,
			int positiveButtonText, OnClickListener positiveButtonListener) {
		return create(context, context.getString(title), view,
				context.getString(negativeButtonText), negativeButtonListener,
				context.getString(positiveButtonText), positiveButtonListener);
	}

	public static AlertDialog create(Context context, String title, View view,
			String negativeButtonText, OnClickListener negativeButtonListener,
			String positiveButtonText, OnClickListener positiveButtonListener) {
		return new AlertDialog.Builder(context).setTitle(title).setView(view)
				.setNegativeButton(negativeButtonText, negativeButtonListener)
				.setPositiveButton(positiveButtonText, positiveButtonListener)
				.create();
	}
}
