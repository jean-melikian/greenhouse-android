package com.android.greenhouse.greenhouseapp.retrofit;

import com.android.greenhouse.greenhouseapp.retrofit.errors.ServiceException;

/**
 * Created by antoinepelletier on 04/07/2017.
 */

public class ServiceResult<T> {

	T data;
	ServiceException error;

	public ServiceResult() {

	}

	public ServiceResult(T data, ServiceException error) {
		this.data = data;
		this.error = error;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public ServiceException getError() {
		return error;
	}

	public void setError(ServiceException error) {
		this.error = error;
	}

}
