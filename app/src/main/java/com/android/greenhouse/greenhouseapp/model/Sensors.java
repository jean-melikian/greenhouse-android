package com.android.greenhouse.greenhouseapp.model;

/**
 * Created by antoinepelletier on 10/07/2017.
 */

public class Sensors {

	private String created_date;
	private String _id;
	private String __v;
	private float luminosity;
	private float hygrometer;

	/**
	 * Constructor
	 *
	 * @param created_date
	 * @param _id
	 * @param __v
	 * @param luminosity
	 * @param hygrometer
	 */
	public Sensors(String created_date, String _id, String __v, float luminosity, float hygrometer) {
		this.created_date = created_date;
		this._id = _id;
		this.__v = __v;
		this.luminosity = luminosity;
		this.hygrometer = hygrometer;
	}

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

	public float getLuminosity() {
		return luminosity;
	}

	public void setLuminosity(int luminosity) {
		this.luminosity = luminosity;
	}

	public float getHygrometer() {
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
