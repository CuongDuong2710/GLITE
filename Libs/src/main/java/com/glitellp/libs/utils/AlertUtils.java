package com.glitellp.libs.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

public class AlertUtils {

	/** Show alert dialog */
	public static void alertDialog(Activity context, String title,
			String message, String button, DialogInterface.OnClickListener listenerPositive) {
		AlertDialog.Builder fAlertDialog = new AlertDialog.Builder(context);
		fAlertDialog.setTitle(title);
		fAlertDialog
				.setMessage(message)
				.setCancelable(false)
				.setPositiveButton(button, listenerPositive);
		fAlertDialog.show();
	}

	/** Show alert dialog with custom listener */
	public static void alertDialogWithListener(Activity context, String title,
								   String message,
											   DialogInterface.OnClickListener listenerNegative,
											   DialogInterface.OnClickListener listenerPositive) {
		AlertDialog.Builder fAlertDialog = new AlertDialog.Builder(context);
		fAlertDialog.setTitle(title);
		fAlertDialog
				.setMessage(message)
				.setCancelable(false)
				.setNegativeButton("Cancel", listenerNegative)
				.setPositiveButton("OK", listenerPositive);
		fAlertDialog.show();
	}

	/** Show alert dialog with custom layout */
	public static AlertDialog alertDialogWithCustomLayout(final Activity context, String title,
												   String message, View view) {
		AlertDialog.Builder fAlertDialog = new AlertDialog.Builder(context);
		fAlertDialog.setTitle(title);
		fAlertDialog.setView(view);
		fAlertDialog
				.setCancelable(true);
		AlertDialog dialog = fAlertDialog.create();
		dialog.show();
		return dialog;
	}

	/** Alert when network is not available */
	public static void alertNoNetworkConnection(Activity context) {
		AlertDialog.Builder fAlertDialog = new AlertDialog.Builder(context);
		fAlertDialog.setTitle("No Internet Connection");
		fAlertDialog
				.setMessage(
						"No connection available. Please check your internet settings")
				.setCancelable(false)
				.setPositiveButton("Try Again",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.cancel();

							}
						});
		fAlertDialog.show();
	}

	/** Alert confirmation when user exit application */
	public static void alertExit(final Activity activity) {
		AlertDialog.Builder fAlertDialog = new AlertDialog.Builder(activity);
		fAlertDialog.setTitle("Exit Application");
		fAlertDialog
				.setMessage("Do you want to exit the application?")
				.setCancelable(false)
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.cancel();
							}
						})
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						exitApplication(activity);
					}
				});
		fAlertDialog.show();
	}

	/** Exit application */
	public static void exitApplication(Activity activity) {
		// activity.finish();
		Intent fIntent = new Intent(Intent.ACTION_MAIN);
		fIntent.addCategory(Intent.CATEGORY_HOME);
		fIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		activity.getApplicationContext().startActivity(fIntent);
	}

	

}
