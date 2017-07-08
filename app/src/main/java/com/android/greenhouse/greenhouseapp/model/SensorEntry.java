package com.android.greenhouse.greenhouseapp.model;

/**
 * Created by antoinepelletier on 04/07/2017.
 */

public class SensorEntry {

    private String _id;
    private int hygrometer;
    private int luminosity;
    private int temperature;
    private int __v;
    private String created_date;

    /**
     * Constructor
     *
     * @param _id
     * @param hygrometer
     * @param luminosity
     * @param temperature
     * @param __v
     * @param created_date
     */
    public SensorEntry(String _id, int hygrometer, int luminosity, int temperature, int __v, String created_date) {
        this._id = _id;
        this.hygrometer = hygrometer;
        this.luminosity = luminosity;
        this.temperature = temperature;
        this.__v = __v;
        this.created_date = created_date;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getHygrometer() {
        return hygrometer;
    }

    public void setHygrometer(int hygrometer) {
        this.hygrometer = hygrometer;
    }

    public int getLuminosity() {
        return luminosity;
    }

    public void setLuminosity(int luminosity) {
        this.luminosity = luminosity;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    @Override
    public String toString() {
        return "SensorEntry{" +
                "_id='" + _id + '\'' +
                ", hygrometer=" + hygrometer +
                ", luminosity=" + luminosity +
                ", temperature=" + temperature +
                ", __v=" + __v +
                ", created_date=" + created_date +
                '}';
    }

}
