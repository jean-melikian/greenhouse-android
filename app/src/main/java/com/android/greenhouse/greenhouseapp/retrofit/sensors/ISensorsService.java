package com.android.greenhouse.greenhouseapp.retrofit.sensors;

import com.android.greenhouse.greenhouseapp.model.SensorsEntries;
import com.android.greenhouse.greenhouseapp.retrofit.IServiceResultListener;

/**
 * Created by antoinepelletier on 04/07/2017.
 */

public interface ISensorsService {

    void create(SensorsEntries sensors, IServiceResultListener<String> resultListener);

    void getAll(IServiceResultListener<SensorsEntries> resultListener);
}
