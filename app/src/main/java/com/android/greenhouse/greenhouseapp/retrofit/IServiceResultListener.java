package com.android.greenhouse.greenhouseapp.retrofit;

/**
 * Created by antoinepelletier on 04/07/2017.
 */


public interface IServiceResultListener<T> {

    /**
     * Called when a request is finished
     *
     * @param result
     */
    void onSuccess(ServiceResult<T> result);

    void onFailure(Throwable t);
}
