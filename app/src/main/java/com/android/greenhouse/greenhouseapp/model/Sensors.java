package com.android.greenhouse.greenhouseapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by antoinepelletier on 10/07/2017.
 */

public class Sensors {

	private String created_date;
	private String _id;
	private String __v;

	@SerializedName("soil_humidity")
	private float soilHumidity;
	@SerializedName("air_humidity")
	private float airHumidity;
	private float luminosity;
	private float temperature;

	public Sensors(String created_date, String _id, String __v, float luminosity, float soilHumidity, float airHumidity, float temperature) {
		this.created_date = created_date;
		this._id = _id;
		this.__v = __v;
		this.luminosity = luminosity;
		this.soilHumidity = soilHumidity;
		this.airHumidity = airHumidity;
		this.temperature = temperature;
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

	public float getSoilHumidity() {
		return soilHumidity;
	}

	public void setSoilHumidity(int soilHumidity) {
		this.soilHumidity = soilHumidity;
	}

	public float getAirHumidity() {
		return airHumidity;
	}

	public void setAirHumidity(float airHumidity) {
		this.airHumidity = airHumidity;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	@Override
	public String toString() {
		return "Entry : [created_date = " + created_date + ", _id = " + _id + ", __v = " + __v + ", luminosity = " + luminosity + ", soilHumidity = " + soilHumidity + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Sensors sensors = (Sensors) o;

		if (Float.compare(sensors.luminosity, luminosity) != 0) return false;
		if (Float.compare(sensors.soilHumidity, soilHumidity) != 0) return false;
		if (Float.compare(sensors.airHumidity, airHumidity) != 0) return false;
		if (Float.compare(sensors.temperature, temperature) != 0) return false;
		if (!created_date.equals(sensors.created_date)) return false;
		if (!_id.equals(sensors._id)) return false;
		return __v.equals(sensors.__v);

	}

	@Override
	public int hashCode() {
		int result = created_date.hashCode();
		result = 31 * result + _id.hashCode();
		result = 31 * result + __v.hashCode();
		result = 31 * result + (luminosity != +0.0f ? Float.floatToIntBits(luminosity) : 0);
		result = 31 * result + (soilHumidity != +0.0f ? Float.floatToIntBits(soilHumidity) : 0);
		result = 31 * result + (airHumidity != +0.0f ? Float.floatToIntBits(airHumidity) : 0);
		result = 31 * result + (temperature != +0.0f ? Float.floatToIntBits(temperature) : 0);
		return result;
	}
}
