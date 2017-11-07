package com.glitellp.libs.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by Hoang on 2/4/2015.
 */
public class ScreenUtils {
    private int width = 0;
    private int height = 0;
    private int density;
    private DisplayMetrics displayMetrics;
    private static ScreenUtils sInstance;

    public static synchronized ScreenUtils getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new ScreenUtils();
            WindowManager wm = (WindowManager) context
                    .getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            DisplayMetrics dm = new DisplayMetrics();
            display.getMetrics(dm);

            sInstance.setHeight(dm.heightPixels);
            sInstance.setWidth(dm.widthPixels);
            sInstance.setDensity(dm.densityDpi);
            sInstance.setDisplayMetrics(dm);
        }
        return sInstance;
    }

    public DisplayMetrics getDisplayMetrics() {
        return displayMetrics;
    }

    public void setDisplayMetrics(DisplayMetrics displayMetrics) {
        this.displayMetrics = displayMetrics;
    }

    public int getDensity() {
        return density;
    }

    public void setDensity(int density) {
        this.density = density;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getScreenSizeInInch() {
        double x = Math.pow(width / convertDpToPixel(displayMetrics.xdpi), 2);
        double y = Math.pow(height / convertDpToPixel(displayMetrics.ydpi), 2);
        return Math.sqrt(x + y);
    }

    public float convertDpToPixel(float dp) {
        float px = dp * (displayMetrics.densityDpi / 160f);
        return px;
    }

    public float convertPixelsToDp(float px) {
        float dp = px / (displayMetrics.densityDpi / 160f);
        return dp;
    }

    public int getHeight169(int width) {
        return (width / 16) * 9;
    }

    public int getWidth169(int height) {
        return (height / 16) * 9;
    }

    public int getHeight916(int width) {
        return (width / 9) * 16;
    }

    public int getWidth916(int height) {
        return (height / 9) * 16;
    }
}
