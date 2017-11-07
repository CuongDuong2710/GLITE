package com.glite.popeyes.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by HP on 24/05/2016.
 */

public class ToastUtil {

    private static Toast mToast;

    public static void showSingleToast(Context context, String message) {
        if (mToast != null) {
            mToast.cancel();
            mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        } else {
            mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

}
