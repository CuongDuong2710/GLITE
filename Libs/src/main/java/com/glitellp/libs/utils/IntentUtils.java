/*
 * Copyright (C) 2013 The Android Open Source Project 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */

package com.glitellp.libs.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

public class IntentUtils {

	public static void call(Activity curContext, String number) {
		Intent intent = new Intent(Intent.ACTION_CALL);
		intent.setData(Uri.parse("tel:" + number));
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		curContext.startActivity(intent);
	}

	public static void sendMail(Activity curContext, String email,
			String subject, String content) {
		Intent emailIntent = new Intent(Intent.ACTION_SENDTO,
				Uri.parse("mailto:" + email));
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
		emailIntent.putExtra(Intent.EXTRA_TEXT, content);
		emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		curContext.startActivity(Intent.createChooser(emailIntent, "Options"));
	}

	public static void openGooglePlay(Context curContext, String packageName) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setData(Uri.parse("market://details?id=" + packageName));
		curContext.startActivity(intent);
	}

	public static void openFacebook(Context curContext, String fbID) {
		Intent intent = new Intent(Intent.ACTION_VIEW,
				Uri.parse("fb://profile/" + fbID));
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		curContext.startActivity(intent);
	}

	public static void openTwitter(Context curContext, String userName) {
		try {
			Intent intent = new Intent(Intent.ACTION_VIEW,
					Uri.parse("twitter://user?screen_name=" + userName));
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			curContext.startActivity(intent);

		} catch (Exception e) {
			Intent intent = new Intent(Intent.ACTION_VIEW,
					Uri.parse("https://twitter.com/#!/" + userName));
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			curContext.startActivity(intent);
		}

	}

	public static void share(Context curContext, String content) {
		Intent shareIntent = new Intent(Intent.ACTION_SEND);
		shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		shareIntent.setType("text/plain");
		shareIntent.putExtra(Intent.EXTRA_TEXT, content);

		curContext.startActivity(shareIntent);

	}

	public static void shareTwitter(Context context, String link, String content) {
		String tweetUrl = "https://twitter.com/intent/tweet?text= " + content
				+ " - &url=" + link;
		Uri uri = Uri.parse(tweetUrl);
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}

	public static void openWeb(Context curContext, String link) {
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		curContext.startActivity(intent);
	}

	public static void shareToInstagram(Context curContext, Bitmap bitmap,
			String title, String description) {
		Intent intent = curContext.getPackageManager()
				.getLaunchIntentForPackage("com.instagram.android");
		if (intent != null) {

			File path = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
			File imageFile = new File(path, "temp.png");

			FileOutputStream fileOutPutStream;
			try {
				imageFile.createNewFile();
				fileOutPutStream = new FileOutputStream(imageFile);
				bitmap.compress(Bitmap.CompressFormat.PNG, 80, fileOutPutStream);
				fileOutPutStream.flush();
				fileOutPutStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
//			ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//			bitmap.compress(Bitmap.CompressFormat.JPEG, 40, bytes);
//			File imageFile = new File(curContext.getFilesDir() + "tmp.jpg");
//			try {
//				imageFile.createNewFile();
//				//write the bytes in file
//				FileOutputStream fo = new FileOutputStream(imageFile);
//				fo.write(bytes.toByteArray());
//				// remember close de FileOutput
//				fo.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			
			Intent shareIntent = new Intent(Intent.ACTION_SEND);
			shareIntent.setType("image/*");
			shareIntent.putExtra(Intent.EXTRA_SUBJECT, "" + title + " - "
					+ description);
			shareIntent.setPackage("com.instagram.android");
			shareIntent.putExtra(Intent.EXTRA_STREAM,
					Uri.parse("file://"+imageFile.getAbsolutePath()));
			shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			curContext.startActivity(shareIntent);
		} else {
			intent = new Intent(Intent.ACTION_VIEW);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.setData(Uri.parse("market://details?id="
					+ "com.instagram.android"));
			curContext.startActivity(intent);
		}
	}

	public static void shareFacebook(Context context, String link,
			String title, String description) {
		// String shareBody = title + " - " + description + " - " + link;
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_TEXT, link);
		// See if official Facebook app is found
		boolean facebookAppFound = false;
		List<ResolveInfo> matches = context.getPackageManager()
				.queryIntentActivities(intent, 0);
		for (ResolveInfo info : matches) {
			if (info.activityInfo.packageName.toLowerCase().startsWith(
					"com.facebook.katana")) {
				intent.setPackage(info.activityInfo.packageName);
				facebookAppFound = true;
				break;
			}
		}
		if (!facebookAppFound) {
			String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u="
					+ link;
			intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		}

		context.startActivity(intent);
	}

	public static boolean isFacebookExist(Context context) {
		Intent intent = context.getPackageManager().getLaunchIntentForPackage(
				"com.facebook.katana");
		return intent != null;
	}

	public static void changePage(Context context, Class<?> cls) {
		Intent intent = new Intent().setClass(context, cls);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}

}
