package com.android.greenhouse.greenhouseapp.model;

/**
 * Created by antoinepelletier on 10/07/2017.
 */

public class Entries {

    private String created_date;
    private String _id;
    private String __v;
    private int luminosity;
    private int hygrometer;

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get__v() {
        return __v;
    }

    public void set__v(String __v) {
        this.__v = __v;
    }

    public int getLuminosity() {
        return luminosity;
    }

    public void setLuminosity(int luminosity) {
        this.luminosity = luminosity;
    }

    public int getHygrometer() {
        return hygrometer;
    }

    public void setHygrometer(int hygrometer) {
        this.hygrometer = hygrometer;
    }

    @Override
    public String toString() {
        return "Entry : [created_date = " + created_date + ", _id = " + _id + ", __v = " + __v + ", luminosity = " + luminosity + ", hygrometer = " + hygrometer + "]";
    }
}
