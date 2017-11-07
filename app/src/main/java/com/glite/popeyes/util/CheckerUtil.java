package com.glite.popeyes.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

import com.glite.popeyes.PPYApplication;

import java.util.List;

/**
 * @author Brian
 * @date: 05/09/2016
 */

public final class CheckerUtil {

    private CheckerUtil() {
    }

    public static boolean isEmptyList(List<?> list) {
        return (list == null || list.size() == 0);
    }

    public static String checkIfNull(String value) {
        return TextUtils.isEmpty(value) ? Constants.EMPTY : value;
    }

    public static String trimmed(String value) {
        return checkIfNull(value.trim());
    }

    /**
     * For checking network connection
     * @return true - device online
     */
    public static boolean isDeviceOnline() {
        Context context = PPYApplication.get();
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}
