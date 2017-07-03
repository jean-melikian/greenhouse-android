package com.android.greenhouse.greenhouseapp.model;

import java.util.Date;

/**
 * Created by antoinepelletier on 04/07/2017.
 */

public class SensorEntry {

    private int temperature;
    private int humidity;
    private Date date;

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Constructor
     *
     * @param temperature
     * @param humidity
     * @param date
     */
    public SensorEntry(int temperature, int humidity, Date date) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.date = date;
    }
}
