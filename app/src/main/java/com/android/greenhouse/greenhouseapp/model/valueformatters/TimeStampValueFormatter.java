package com.android.greenhouse.greenhouseapp.model.valueformatters;

import android.util.Log;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by antoinepelletier on 06/07/2017.
 */

public class TimeStampValueFormatter implements IAxisValueFormatter {

    SimpleDateFormat simpleDateFormat;
    Date date;

    @Override
    public String getFormattedValue(float value, AxisBase axis) {

        date = new Date((long) value);

        simpleDateFormat = new SimpleDateFormat("dd:MM");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("fr-FR"));

        return simpleDateFormat.format(date);
    }
}
