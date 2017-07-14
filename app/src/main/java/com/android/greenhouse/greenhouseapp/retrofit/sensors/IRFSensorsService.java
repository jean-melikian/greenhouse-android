package com.android.greenhouse.greenhouseapp.retrofit.sensors;

import com.android.greenhouse.greenhouseapp.model.SensorsEntries;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by antoinepelletier on 04/07/2017.
 */

public interface IRFSensorsService {

	@POST("sensorsEntry/")
	Call<ResponseBody> create(@Body SensorsEntries sensors);

	@GET("/sensors")
	Call<SensorsEntries> getAll();


}
