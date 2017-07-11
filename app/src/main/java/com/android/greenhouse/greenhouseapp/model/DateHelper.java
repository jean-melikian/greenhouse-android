package com.android.greenhouse.greenhouseapp.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by antoinepelletier on 11/07/2017.
 */

public class DateHelper {

    /**
     * Convert a given String into timeStampp format
     *
     * @param str_date (2017-07-08T23:00:38.169Z)
     * @return Long timestampp format
     */
    public static long convertStringToTimestamp(String str_date) {

        Date date;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);

        try {
            date = simpleDateFormat.parse(str_date);
            return date.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0l;
    }

    /**
     * Function which test if a given time Stamp reprensents a day before the current time
     *
     * @param currentTimeStamp
     * @return
     */
    public static Boolean isOneDayBefore(long currentTimeStamp) {
        return (currentTimeStamp < (new Date().getTime() - 86400000)) ? true : false;
    }
}
