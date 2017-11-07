package com.glitellp.libs.utils;

import org.json.JSONArray;
import org.json.JSONObject;

public class ParseJson {

    public static String getString(JSONObject obj, String key) {
        String tmp = "";
        try {
            tmp = obj.getString(key);
            if (tmp.equals("") || tmp.equals("null")) {
                tmp = "";
            }
            return tmp;
        } catch (Exception e) {
            e.printStackTrace();
            return tmp;
        }
    }

    public static boolean getBoolean(JSONObject obj, String key) {
        boolean tmp = false;
        try {
            tmp = obj.getBoolean(key);
            return tmp;
        } catch (Exception e) {
            e.printStackTrace();
            return tmp;
        }
    }

    public static int getInt(JSONObject obj, String key) {
        int tmp = 0;
        try {
            tmp = obj.getInt(key);
            return tmp;
        } catch (Exception e) {
            e.printStackTrace();
            return tmp;
        }
    }

    public static float getFloat(JSONObject obj, String key) {
        float tmp = 0f;
        try {
            tmp = (float) obj.getDouble(key);
            return tmp;
        } catch (Exception e) {
            e.printStackTrace();
            return tmp;
        }
    }

    public static double getDouble(JSONObject obj, String key) {
        double tmp = 0d;
        try {
            tmp = obj.getDouble(key);
            return tmp;
        } catch (Exception e) {
            e.printStackTrace();
            return tmp;
        }
    }

    public static JSONObject getObject(JSONObject obj, String key) {
        JSONObject tmp = null;
        try {
            tmp = obj.getJSONObject(key);
            return tmp;
        } catch (Exception e) {
            e.printStackTrace();
            return tmp;
        }
    }

    public static JSONArray getArray(JSONObject obj, String key) {
        JSONArray tmp = null;
        try {
            tmp = obj.getJSONArray(key);
            return tmp;
        } catch (Exception e) {
            e.printStackTrace();
            return tmp;
        }
    }
}
