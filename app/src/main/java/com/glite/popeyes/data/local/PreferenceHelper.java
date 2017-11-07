package com.glite.popeyes.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.glite.popeyes.PPYApplication;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Brian
 * @date: 31/08/2016
 */
@Singleton
public class PreferenceHelper {

    private static final String PREF_FILE_NAME = "popeyes_pref";

    private SharedPreferences mPref;

    @Inject
    PreferenceHelper() {
        Context context = PPYApplication.get();
        mPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    public void delete(String key) {
        if (mPref.contains(key)) {
            mPref.edit().remove(key).apply();
        }
    }

    public void clearAll() {
        mPref.edit().clear().apply();
    }

    public void putString(String key, String value) {
        mPref.edit().putString(key, value).apply();
    }

    public String getString(String key, String defaultValue) {
        return mPref.getString(key, defaultValue);
    }

    public void putBoolean(String key, boolean value) {
        mPref.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return mPref.getBoolean(key, defaultValue);
    }

    public void putInt(String key, int value) {
        mPref.edit().putInt(key, value).apply();
    }

    public int getInt(String key, int defaultValue) {
        return mPref.getInt(key, defaultValue);
    }

}
