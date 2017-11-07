package com.glite.popeyes.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author Brian
 * @date: 05/09/2016
 */

public final class DateTimeUtil {

    private static final SimpleDateFormat DOB_DATE_FORMAT = new SimpleDateFormat("yyyy-MMM-dd", Locale.getDefault());
    private static final Calendar mCalendar = Calendar.getInstance();

    private DateTimeUtil() {}

    public static Date now() {
        return mCalendar.getTime();
    }

    public static Date parseDOBDate(String date) {
        try {
            return DOB_DATE_FORMAT.parse(date);
        } catch (Throwable e) {
            Logger.error(e.getMessage());
            return null;
        }
    }

    public static String formatDOBDate(Date date) {
        return DOB_DATE_FORMAT.format(date);
    }

    public static String todayDate() {
        return DOB_DATE_FORMAT.format(now());
    }

}
