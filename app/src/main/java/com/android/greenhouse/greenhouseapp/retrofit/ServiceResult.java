package com.android.greenhouse.greenhouseapp.retrofit;

/**
 * Created by antoinepelletier on 04/07/2017.
 */

public class ServiceResult<T> {

    T mData;
    ServiceException mError;

    public T getmData() {
        return mData;
    }

    public void setmData(T mData) {
        this.mData = mData;
    }

    public ServiceException getmError() {
        return mError;
    }

    public void setmError(ServiceException mError) {
        this.mError = mError;
    }

    public ServiceResult() {

    }

    public ServiceResult(T mData, ServiceException mError) {
        this.mData = mData;
        this.mError = mError;
    }

}
