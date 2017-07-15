package com.android.greenhouse.greenhouseapp.retrofit.sensors;

import com.android.greenhouse.greenhouseapp.model.SensorsEntries;
import com.android.greenhouse.greenhouseapp.retrofit.IServiceResultListener;
import com.android.greenhouse.greenhouseapp.retrofit.ServiceResult;
import com.android.greenhouse.greenhouseapp.retrofit.Session;
import com.android.greenhouse.greenhouseapp.retrofit.errors.ServiceException;

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