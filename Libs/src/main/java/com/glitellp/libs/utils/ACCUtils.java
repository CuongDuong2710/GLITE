///*
// * Copyright (C) 2013 The Android Open Source Project
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package com.glitellp.libs.utils;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.List;
//import java.util.regex.Pattern;
//
//import org.apache.http.NameValuePair;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.content.SharedPreferences.Editor;
//import android.content.pm.PackageInfo;
//import android.content.pm.PackageManager;
//import android.content.pm.PackageManager.NameNotFoundException;
//import android.content.pm.Signature;
//import android.content.res.Configuration;
//import android.content.res.Resources;
//import android.database.Cursor;
//import android.graphics.Bitmap;
//import android.graphics.Bitmap.Config;
//import android.graphics.BitmapFactory;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.drawable.BitmapDrawable;
//import android.graphics.drawable.Drawable;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.net.Uri;
//import android.preference.PreferenceManager;
//import android.provider.MediaStore;
//import android.util.Base64;
//import android.util.DisplayMetrics;
//import android.util.Log;
//import android.util.TypedValue;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.View.OnTouchListener;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//@SuppressLint({"DefaultLocale", "SimpleDateFormat",
//        "ClickableViewAccessibility"})
//public class ACCUtils {
//    public static Bitmap decodeAndResizeDrawable(int id, Context context,
//                                                 int size) {
//
//        // Decode image size
//        BitmapFactory.Options o = new BitmapFactory.Options();
//        o.inJustDecodeBounds = true;
//        BitmapFactory.decodeResource(context.getResources(), id, o);
//
//        // The new size we want to scale to
//        final int REQUIRED_SIZE = size;
//
//        // Find the correct scale value. It should be the power of 2.
//        int width_tmp = o.outWidth, height_tmp = o.outHeight;
//        int scale = 1;
//        while (true) {
//            if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE)
//                break;
//            width_tmp /= 2;
//            height_tmp /= 2;
//            scale *= 2;
//        }
//
//        // Decode with inSampleSize
//        BitmapFactory.Options o2 = new BitmapFactory.Options();
//        o2.inSampleSize = scale;
//        return BitmapFactory.decodeResource(context.getResources(), id, o2);
//
//    }
//
//    public static void CopyStream(InputStream is, OutputStream os) {
//        final int buffer_size = 1024;
//        try {
//            byte[] bytes = new byte[buffer_size];
//            for (; ; ) {
//                int count = is.read(bytes, 0, buffer_size);
//                if (count == -1)
//                    break;
//                os.write(bytes, 0, count);
//            }
//        } catch (Exception ex) {
//        }
//    }
//
//    /**
//     * Check network status
//     */
//    public static boolean isNetworkAvailable(Context context) {
//        ConnectivityManager fConnectiveManager = (ConnectivityManager) context
//                .getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo fNetworkInfo = fConnectiveManager.getActiveNetworkInfo();
//        if (fNetworkInfo != null && fNetworkInfo.isConnected()
//                && fConnectiveManager.getActiveNetworkInfo().isAvailable()) {
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * Create the effect button when user touch in and release
//     */
//    public static void createEffectTouch(View v, final int inActiveDrawble,
//                                         final int activeDrawble) {
//        v.setOnTouchListener(new OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_DOWN)
//                    v.setBackgroundResource(activeDrawble);
//                if (event.getAction() == MotionEvent.ACTION_OUTSIDE
//                        || event.getAction() == MotionEvent.ACTION_UP)
//                    v.setBackgroundResource(inActiveDrawble);
//                return false;
//            }
//        });
//    }
//
//    public static void createEffectTouch(final ImageView v1,
//                                         final int inActiveDrawble, final int activeDrawble) {
//        v1.setOnTouchListener(new OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_DOWN)
//                    v1.setImageResource(activeDrawble);
//                if (event.getAction() == MotionEvent.ACTION_OUTSIDE
//                        || event.getAction() == MotionEvent.ACTION_UP)
//                    v1.setImageResource(inActiveDrawble);
//                return false;
//            }
//        });
//    }
//
//    /**
//     * Create the effect button when user touch in and release
//     */
//    public static void createEffectTouch(View view, final String inColor,
//                                         final String activeColor) {
//        view.setOnTouchListener(new OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_DOWN)
//                    v.setBackgroundColor(Color.parseColor(activeColor));
//                if (event.getAction() == MotionEvent.ACTION_OUTSIDE
//                        || event.getAction() == MotionEvent.ACTION_UP)
//                    v.setBackgroundColor(Color.parseColor(inColor));
//                return false;
//            }
//        });
//    }
//
//    /**
//     * Create the effect button when user touch in and release
//     */
//    public static void createTextEffectTouch(final TextView view,
//                                             final String inColor, final String activeColor) {
//        view.setOnTouchListener(new OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_DOWN)
//                    view.setTextColor(Color.parseColor(activeColor));
//                if (event.getAction() == MotionEvent.ACTION_OUTSIDE
//                        || event.getAction() == MotionEvent.ACTION_UP)
//                    view.setTextColor(Color.parseColor(inColor));
//                return false;
//            }
//        });
//    }
//
//    /**
//     * Create the effect button forlist when user touch in and release
//     */
//    public static void createEffectTouchForList(View v, final String inColor,
//                                                final String activeColor) {
//        v.setOnTouchListener(new OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_DOWN)
//                    v.setBackgroundColor(Color.parseColor(activeColor));
//                else
//                    v.setBackgroundColor(Color.parseColor(inColor));
//                return false;
//            }
//        });
//    }
//
//    public static void createEffectTouchForList(final ImageView v1,
//                                                final int inColor, final int activeColor) {
//        v1.setOnTouchListener(new OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_DOWN)
//                    v1.setImageResource(activeColor);
//                else
//                    v1.setImageResource(inColor);
//                return false;
//            }
//        });
//    }
//
//    /**
//     * Create the effect button forlist when user touch in and release
//     */
//    public static void createEffectTouchForList(View v, final int inColor,
//                                                final String activeColor) {
//        v.setOnTouchListener(new OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_DOWN)
//                    v.setBackgroundColor(Color.parseColor(activeColor));
//                else
//                    v.setBackgroundResource(inColor);
//                return false;
//            }
//        });
//    }
//
//    /**
//     * Create the effect button forlist when user touch in and release
//     */
//    public static void createEffectTouchForList(View v,
//                                                final int inActiveDrawble, final int activeDrawble) {
//        v.setOnTouchListener(new OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_DOWN)
//                    v.setBackgroundResource(activeDrawble);
//                else
//                    v.setBackgroundResource(inActiveDrawble);
//                return false;
//            }
//        });
//    }
//
//    /**
//     * Convert millisecond to date time
//     */
//    public static String durationToString(int duration) {
//        int fd = duration / 86400;
//        int fh = (duration % 86400) / 3600;
//        int fm = (duration % 3600) / 60;
//        int fs = duration % 60;
//        String fstr;
//        if (fd == 0) {
//            fstr = String.format("%02d:%02d:%02d", fh, fm, fs);
//        } else {
//            fstr = String.format("%dd %02d:%02d:%02d", fd, fh, fm, fs);
//        }
//        return fstr;
//    }
//
//    /**
//     * Verify email format
//     */
//    public static boolean verifyEmail(String email) {
//        String fReg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
//        return Pattern.matches(fReg, email);
//    }
//
//    /**
//     * Verify number format
//     */
//    public static boolean verifyPhoneNumber(String number) {
//        try {
//            Integer.parseInt(number);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    public static boolean verifyFullTextSpace(String text) {
//        char[] charName = text.toCharArray();
//        for (int i = 0; i < charName.length; i++)
//            if (charName[i] != ' ')
//                return true;
//        return false;
//    }
//
//    public static boolean verifyTextLimit(String text, int min, int max) {
//        if (text.length() < min || text.length() > max)
//            return false;
//        return true;
//    }
//
//    public static boolean verifyBlankText(String text) {
//
//        if (text.length() > 0)
//            return true;
//        return false;
//    }
//
//    public static boolean verifyIsEqualText(String text1, String text2) {
//        if (text1.equals(text2))
//            return true;
//        return false;
//    }
//
//    /**
//     * Convert dp to px
//     */
//    public static int convertDpToPixels(float dp, Context context) {
//        Resources resources = context.getResources();
//        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
//                resources.getDisplayMetrics());
//    }
//
//    /**
//     * Convert px to dp
//     *
//     * @param px
//     * @param context
//     * @return
//     */
//    public static float convertPixelsToDp(float px, Context context) {
//        Resources resources = context.getResources();
//        DisplayMetrics metrics = resources.getDisplayMetrics();
//        float dp = px / (metrics.densityDpi / 160f);
//        return dp;
//    }
//
//    public static Bitmap drawableToBitmap(Drawable drawable) {
//        if (drawable instanceof BitmapDrawable) {
//            return ((BitmapDrawable) drawable).getBitmap();
//        }
//
//        int width = drawable.getIntrinsicWidth();
//        width = width > 0 ? width : 1;
//        int height = drawable.getIntrinsicHeight();
//        height = height > 0 ? height : 1;
//
//        Bitmap bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
//        Canvas canvas = new Canvas(bitmap);
//        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
//        drawable.draw(canvas);
//
//        return bitmap;
//    }
//
//    public static boolean getFirstTimeLauch(Context context) {
//        SharedPreferences fSharedPrefs = PreferenceManager
//                .getDefaultSharedPreferences(context);
//        return fSharedPrefs.getBoolean("isFirstTimeLauch", true);
//    }
//
//    public static void setFirstTimeLauch(Context context, boolean isFirstTime) {
//        Editor fEditor = PreferenceManager.getDefaultSharedPreferences(context)
//                .edit();
//        fEditor.putBoolean("isFirstTimeLauch", isFirstTime);
//        fEditor.commit();
//    }
//
//    public static String getStringPreferences(Context context, String key) {
//        SharedPreferences fSharedPrefs = PreferenceManager
//                .getDefaultSharedPreferences(context);
//        return fSharedPrefs.getString(key, "");
//    }
//
//    public static void setStringPreferences(Context context, String key,
//                                            String content) {
//        Editor fEditor = PreferenceManager.getDefaultSharedPreferences(context)
//                .edit();
//        fEditor.putString(key, content);
//        fEditor.commit();
//    }
//
//    public static int getIntPreferences(Context context, String key) {
//        SharedPreferences fSharedPrefs = PreferenceManager
//                .getDefaultSharedPreferences(context);
//        return fSharedPrefs.getInt(key, 0);
//    }
//
//    public static void setIntPreferences(Context context, String key,
//                                         int content) {
//        Editor fEditor = PreferenceManager.getDefaultSharedPreferences(context)
//                .edit();
//        fEditor.putInt(key, content);
//        fEditor.commit();
//    }
//
//    public static boolean getBooleanPreferences(Context context, String key) {
//        SharedPreferences fSharedPrefs = PreferenceManager
//                .getDefaultSharedPreferences(context);
//        return fSharedPrefs.getBoolean(key, false);
//    }
//
//    public static void setBooleanPreferences(Context context, String key,
//                                             Boolean content) {
//        Editor fEditor = PreferenceManager.getDefaultSharedPreferences(context)
//                .edit();
//        fEditor.putBoolean(key, content);
//        fEditor.commit();
//    }
//
//    // public static String getDeviceID(Context context) {
//    //
//    // GCMRegistrar.checkDevice(context);
//    // String regId = GCMRegistrar.getRegistrationId(context);
//    // if (regId.equals("")) {
//    // // Registration is not present, register now with GCM
//    // GCMRegistrar.register(context, CommonUtilities.SENDER_ID);
//    // }
//    // regId = GCMRegistrar.getRegistrationId(context);
//    // return regId;
//    //
//    // // return Secure
//    // // .getString(context.getContentResolver(), Secure.ANDROID_ID);
//    //
//    // }
//
//    @SuppressLint("NewApi")
//    public static void printHashKey(Context context) {
//
//        try {
//            PackageInfo info = context.getPackageManager().getPackageInfo(
//                    context.getPackageName(), PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                String something = new String(Base64.encode(md.digest(), 0));
//                Log.e("hash key", something);
//            }
//        } catch (NameNotFoundException e) {
//
//        } catch (NoSuchAlgorithmException e) {
//
//        }
//
//    }
//
//    public static long convertTimeToMilisecond(int year, int month, int day,
//                                               int hour, int minute, int second) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(year, month, day, hour, minute, second);
//        return calendar.getTimeInMillis();
//    }
//
//    public static String convertStringToTime(String date, String character) {
//        String[] fTimes = date.split("\\" + character);
//        SimpleDateFormat dateShowFormat = new SimpleDateFormat("MMM dd, yyyy");
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Integer.parseInt(fTimes[0]), Integer.parseInt(fTimes[1]),
//                Integer.parseInt(fTimes[2]));
//        return dateShowFormat.format(calendar.getTime());
//    }
//
//    public static String convertMilisecondToDate(long milliseconds) {
//        SimpleDateFormat dateShowFormat = new SimpleDateFormat("MMM dd, yyyy");
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(milliseconds);
//        return dateShowFormat.format(calendar.getTime());
//    }
//
//    public static String convertMilisecondToTime(long milliseconds) {
//        SimpleDateFormat dateShowFormat = new SimpleDateFormat("HH:mm");
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(milliseconds);
//        return dateShowFormat.format(calendar.getTime()) + "hrs";
//    }
//
//    public static String convertStringToTime(String dateTime) {
//        final String inputFormat = "yyyy-MM-dd HH:mm:ss";
//        final String outputFormat = "HH:mm";
//        String result = "";
//        try {
//            result = new SimpleDateFormat(outputFormat)
//                    .format(new SimpleDateFormat(inputFormat).parse(dateTime));
//        } catch (ParseException e) {
//
//        }
//        return result + "hrs";
//
//    }
//
//    // public static Date convertMilisecondToDate(long milliseconds) {
//    // SimpleDateFormat dateShowFormat = new SimpleDateFormat("MMM dd, yyyy");
//    // Calendar calendar = Calendar.getInstance();
//    // calendar.setTimeInMillis(milliseconds);
//    // return calendar.getTime().;
//    // }
//
//    @SuppressWarnings("deprecation")
//    public static String getRealPathFromURI(Activity context, Uri contentUri) {
//
//        // can post image
//        String[] proj = {MediaStore.Images.Media.DATA};
//        Cursor cursor = context.managedQuery(contentUri, proj, // Which columns
//                // to return
//                null, // WHERE clause; which rows to return (all rows)
//                null, // WHERE clause selection arguments (none)
//                null); // Order-by clause (ascending by name)
//        int column_index = cursor
//                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//        cursor.moveToFirst();
//        String temp = cursor.getString(column_index);
//        // cursor.close();
//        return temp;
//    }
//
//    public static Bitmap decodeAndResizeFile(String path, int size) {
//        File f = new File(path);
//        try {
//            // Decode image size
//            BitmapFactory.Options o = new BitmapFactory.Options();
//            o.inJustDecodeBounds = true;
//            BitmapFactory.decodeStream(new FileInputStream(f), null, o);
//
//            // The new size we want to scale to
//            final int REQUIRED_SIZE = size;
//
//            // Find the correct scale value. It should be the power of 2.
//            int width_tmp = o.outWidth, height_tmp = o.outHeight;
//            int scale = 1;
//            while (true) {
//                if (width_tmp / 2 < REQUIRED_SIZE
//                        || height_tmp / 2 < REQUIRED_SIZE)
//                    break;
//                width_tmp /= 2;
//                height_tmp /= 2;
//                scale *= 2;
//            }
//
//            // Decode with inSampleSize
//            BitmapFactory.Options o2 = new BitmapFactory.Options();
//            o2.inSampleSize = scale;
//            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
//        } catch (FileNotFoundException e) {
//        }
//        return null;
//    }
//
//    public static Bitmap processBitmap(String path) {
//        BitmapFactory.Options options = new BitmapFactory.Options();
//
//        options.inJustDecodeBounds = false;
//        options.inPreferredConfig = Config.RGB_565;
//        options.inDither = true;
//
//        Bitmap imageBitmap = BitmapFactory.decodeFile(path, options);
//        return imageBitmap;
//    }
//
//    public static void getPhoto(final Activity actvity) {
//        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(actvity);
//        myAlertDialog.setTitle("Upload Pictures Option");
//        myAlertDialog.setMessage("How do you want to set your picture?");
//
//        myAlertDialog.setPositiveButton("Gallery",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface arg0, int arg1) {
//                        Intent pictureActionIntent = new Intent(
//                                Intent.ACTION_GET_CONTENT, null);
//                        pictureActionIntent.setType("image/*");
//                        pictureActionIntent.putExtra("return-data", true);
//                        actvity.startActivityForResult(pictureActionIntent,
//                                1000);
//                    }
//                });
//
//        myAlertDialog.setNegativeButton("Camera",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface arg0, int arg1) {
//                        Intent pictureActionIntent = new Intent(
//                                android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                        actvity.startActivityForResult(pictureActionIntent,
//                                2000);
//                    }
//                });
//        myAlertDialog.show();
//    }
//
//    public static JSONObject makeJson(List<NameValuePair> params) {
//        // Add parameter data
//        JSONObject json = new JSONObject();
//
//        for (NameValuePair param : params) {
//            try {
//
//                json.put(param.getName(), param.getValue());
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//        return json;
//    }
//
//    public static boolean isTablet(Context context) {
//        boolean xlarge = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == 4);
//        boolean large = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
//        return (xlarge || large);
//    }
//
//    public static String sha1(String s) {
//        try {
//            MessageDigest digest = MessageDigest.getInstance("SHA-1");
//            digest.update(s.getBytes());
//            byte messageDigest[] = digest.digest();
//
//            StringBuffer hexString = new StringBuffer();
//            for (int i = 0; i < messageDigest.length; i++) {
//                hexString.append(Integer.toHexString(
//                        (0xFF & messageDigest[i]) | 0x100).substring(1));
//            }
//            return hexString.toString();
//        } catch (NoSuchAlgorithmException e) {
//            return "";
//        } catch (NullPointerException e) {
//            return "";
//        }
//    }
//
//}
