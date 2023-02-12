package com.app.repairo.app.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class TimeUtils {
    public static String getDate(long time) {
        TimeZone torontoTimeZone = TimeZone.getTimeZone("America/Toronto");
            Calendar cal = Calendar.getInstance();
            cal.setTimeZone(torontoTimeZone);
            cal.setTimeInMillis(time * 1000);
//        SimpleDateFormat format2 =new  SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        SimpleDateFormat format2 =new  SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String date = format2.format(cal.getTime());
            return date;

    }
    public static String getDate2(long time) {
        TimeZone torontoTimeZone = TimeZone.getTimeZone("America/Toronto");
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(torontoTimeZone);
        cal.setTimeInMillis(time);
//        SimpleDateFormat format2 =new  SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        SimpleDateFormat format2 =new  SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String date = format2.format(cal.getTime());
        return date;

    }

    public static String getDate4(long time) {
        TimeZone torontoTimeZone = TimeZone.getTimeZone("America/Toronto");
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(torontoTimeZone);
        cal.setTimeInMillis(time);
//        SimpleDateFormat format2 =new  SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
        SimpleDateFormat format2 =new  SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String date = format2.format(cal.getTime());
        return date;

    }
    public static String getDate3(long time) {
        TimeZone torontoTimeZone = TimeZone.getTimeZone("America/Toronto");
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(torontoTimeZone);
        cal.setTimeInMillis(time);
//        SimpleDateFormat format2 =new  SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        SimpleDateFormat format2 =new  SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String date = format2.format(cal.getTime());
        return date;

    }

    public static String trimLeadingZeros(String source) {
        for (int i = 0; i < source.length(); ++i) {
            char c = source.charAt(i);
            if (c != '0') {
                return source.substring(i);
            }
        }
        return ""; // or return "0";
    }
}
