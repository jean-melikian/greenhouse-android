package com.android.greenhouse.greenhouseapp.model;

import android.util.Log;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by antoinepelletier on 06/07/2017.
 */

public class TimeStampValueFormatter implements IAxisValueFormatter {
    /**
     * Called when a value from an axis is to be formatted
     * before being drawn. For performance reasons, avoid excessive calculations
     * and memory allocations inside this method.
     *
     * @param value the value to be formatted : Here it's a timestamp
     * @param axis  the axis the value belongs to
     * @return String formatted value
     */
    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        // *1000 is to convert seconds to milliseconds
        Date date = new Date((long) (value * 1000L));

        // Format of the date
        SimpleDateFormat simpleDateFormatdf = new SimpleDateFormat("yyyy");

        // give a timezone reference for formating
        simpleDateFormatdf.setTimeZone(TimeZone.getTimeZone("fr-FR"));

        String formattedDate = simpleDateFormatdf.format(date);

        // Log into console
        Log.d("Formatted date :", formattedDate);
        return formattedDate;
    }
}
