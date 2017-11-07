package com.glite.popeyes.util;

/**
 * Created by Administrator on 19/05/2016.
 */
public interface Constants {

    boolean DEBUG = true;

    String DEVICE_TYPE_ANDROID = "ANDROID";

    String DEVICE_TOKEN = "00000000-6476-057b-71bf-89e60033c587";

    String DEVICE_FINGER = "android_2a912dc544aedc4a353845051066992";

    String EMPTY = "";


    interface LOGIN_PROVIDER {
        int EMAIL = 0;
        int FACEBOOK = 1;
        int GPLUS = 2;
    }

    interface EXTRA_FROM {
        int GUEST_FRAGMENT = 0;
        int FORGOT_PASS_FRAGMENT = 1;
        int ACCOUNT_REGISTER_FRAGMENT = 2;
        int HOME_DELIVERY_1_FRAGMENT = 3;
        int ORDER_DELIVERY_MENU_FRAGMENT = 6;
    }

    interface FOUND {
        int OK = 200;
        int NOT_AVAILABLE = 404;
        int STORES_CLOSED = 303;
    }

}
