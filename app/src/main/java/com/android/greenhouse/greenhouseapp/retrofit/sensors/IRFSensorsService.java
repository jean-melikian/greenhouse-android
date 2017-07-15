package com.android.greenhouse.greenhouseapp.retrofit.sensors;

import com.android.greenhouse.greenhouseapp.model.SensorsEntries;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by antoinepelletier on 04/07/2017.
 */

public interface IRFSensorsService {

	@GET("/sensors")
	Call<SensorsEntries> getAll();


}
