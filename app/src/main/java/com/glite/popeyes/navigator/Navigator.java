package com.glite.popeyes.navigator;

import android.content.Context;

import com.glite.popeyes.view.login.LoginActivity;
import com.glite.popeyes.view.main.MainActivity;

/**
 * @author Brian
 * @date: 31/08/2016
 */

public final class Navigator {

    private Navigator() {
    }

    public static void navigateToLoginActivity(Context context) {
        if (context != null) {
            context.startActivity(LoginActivity.getStartIntent(context));
        }
    }

    public static void navigateToMainActivity(Context context) {
        if (context != null) {
            context.startActivity(MainActivity.getStartIntent(context));
        }
    }

    public static void addFragmentToMainActivity(Context context, int from) {
        if (context != null) {
            MainActivity.start(context, from);
        }
    }

}
