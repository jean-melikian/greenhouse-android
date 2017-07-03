package com.android.greenhouse.greenhouseapp.retrofit;

import com.android.greenhouse.greenhouseapp.model.SensorEntry;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by antoinepelletier on 04/07/2017.
 */

public interface IRFSensorEntryService {

    @POST("sensorsEntry/")
    Call<ResponseBody> create(@Body SensorEntry sensorEntry);

    @GET("{path}")
    Call<List<SensorEntry>> read(@Path(value = "path", encoded = true) String path);

}
