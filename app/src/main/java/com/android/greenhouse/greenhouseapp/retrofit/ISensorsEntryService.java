package com.android.greenhouse.greenhouseapp.retrofit;

import com.android.greenhouse.greenhouseapp.model.SensorEntry;

import java.util.List;

import retrofit2.Call;

/**
 * Created by antoinepelletier on 04/07/2017.
 */

public interface ISensorsEntryService {

    void create(SensorEntry sensorEntry, IServiceResultListener<String> resultListener);

    void read(IServiceResultListener<List<SensorEntry>> resultListener);

    Call<List<SensorEntry>> loadSensorsEntries();
}
