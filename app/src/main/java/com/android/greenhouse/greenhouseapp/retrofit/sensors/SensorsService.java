package com.android.greenhouse.greenhouseapp.retrofit.sensors;

import com.android.greenhouse.greenhouseapp.model.SensorsEntries;
import com.android.greenhouse.greenhouseapp.retrofit.IServiceResultListener;
import com.android.greenhouse.greenhouseapp.retrofit.ServiceResult;
import com.android.greenhouse.greenhouseapp.retrofit.Session;
import com.android.greenhouse.greenhouseapp.retrofit.errors.ServiceException;
import com.android.greenhouse.greenhouseapp.retrofit.errors.ServiceExceptionType;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by antoinepelletier on 04/07/2017.
 */

public class SensorsService implements ISensorsService {

    private IRFSensorsService irfSensorsService;

    private IRFSensorsService getmRfSensorsService() {
        if (irfSensorsService == null)
            irfSensorsService = Session.getDefault().create(IRFSensorsService.class);
        return irfSensorsService;
    }

    @Override
    public void create(SensorsEntries sensors, final IServiceResultListener<String> resultListener) {
        Call<ResponseBody> call = getmRfSensorsService().create(sensors);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ServiceResult<String> result = new ServiceResult<>();
                if (response.code() == 201)
                    result.setData(response.headers().get("Resourceuri"));
                else
                    result.setError(new ServiceException(response.code()));
                if (resultListener != null)
                    resultListener.onSuccess(result);
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
                result.setError(new ServiceException(t, ServiceExceptionType.UNKNOWN));
                if (resultListener != null)
                    resultListener.onSuccess(result);
            }
        });
    }

    @Override
    public void getAll(final IServiceResultListener<SensorsEntries> resultListener) {
        Call<SensorsEntries> call = getmRfSensorsService().getAll();

        call.enqueue(new Callback<SensorsEntries>() {
            @Override
            public void onResponse(Call<SensorsEntries> call, Response<SensorsEntries> response) {
                ServiceResult<SensorsEntries> result = new ServiceResult<SensorsEntries>();

                if (response.code() == 200) {
                    result.setData(response.body());

                    if (resultListener != null) {
                        resultListener.onSuccess(result);
                    }
                } else {
                    result.setError(new ServiceException(response.code()));
                }
            }

            @Override
            public void onFailure(Call<SensorsEntries> call, Throwable t) {
                if (resultListener != null) {
                    resultListener.onFailure(t);
                }
            }
        });

    }

}