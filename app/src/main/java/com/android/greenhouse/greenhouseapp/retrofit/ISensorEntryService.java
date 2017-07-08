package com.android.greenhouse.greenhouseapp.retrofit;

import android.util.Log;

import com.android.greenhouse.greenhouseapp.model.SensorEntry;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by antoinepelletier on 04/07/2017.
 */

public class ISensorEntryService implements ISensorsEntryService {

    private IRFSensorEntryService irfSensorEntryService;

    public ISensorEntryService() {

    }

    private IRFSensorEntryService getmRfSensorEntryService() {
        if (irfSensorEntryService == null)
            irfSensorEntryService = Session.getDefault().create(IRFSensorEntryService.class);
        return irfSensorEntryService;
    }

    @Override
    public void create(SensorEntry sensorEntry, final IServiceResultListener<String> resultListener) {
        Call<ResponseBody> call = getmRfSensorEntryService().create(sensorEntry);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ServiceResult<String> result = new ServiceResult<>();
                if (response.code() == 201)
                    result.setmData(response.headers().get("Resourceuri"));
                else
                    result.setmError(new ServiceException(response.code()));
                if (resultListener != null)
                    resultListener.OnResult(result);
            }

            /**
             * Invoked when a network exception occurred talking to the server or when an unexpected
             * exception occurred creating the request or processing the response.
             *
             * @param call Call<ResponseBody>
             * @param t Throwable
             */
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ServiceResult<String> result = new ServiceResult<>();
                result.setmError(new ServiceException(t, ServiceExceptionType.UNKNOWN));
                if (resultListener != null)
                    resultListener.OnResult(result);
            }
        });
    }

    @Override
    public void read(IServiceResultListener<List<SensorEntry>> resultListener) {

    }

    @Override
    public Call<List<SensorEntry>> loadSensorsEntries() {
        Call<List<SensorEntry>> call = getmRfSensorEntryService().loadSensorsEntries();
        call.enqueue(new Callback<List<SensorEntry>>() {
            @Override
            public void onResponse(Call<List<SensorEntry>> call, Response<List<SensorEntry>> response) {
                for (SensorEntry sensorEntry : response.body()) {
                    Log.i("SENSOR ENTRY : ", sensorEntry.toString());
                }
            }

            @Override
            public void onFailure(Call<List<SensorEntry>> call, Throwable t) {
                Log.e("onFailure", t.toString());
            }
        });
        return call;
    }
}