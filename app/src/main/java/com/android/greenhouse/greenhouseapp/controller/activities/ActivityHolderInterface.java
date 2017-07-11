package com.android.greenhouse.greenhouseapp.controller.activities;

/**
 * Created by Antoine Pelletier on 11/07/2017.
 */

public interface ActivityHolderInterface {

    public int getContentViewId();

    public void initView();

    public String getTitleBarTitle();

    public String getHexActionbarColor();

    public int getTitleActionBarColor();
}