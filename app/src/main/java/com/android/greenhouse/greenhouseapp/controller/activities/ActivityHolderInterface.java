package com.android.greenhouse.greenhouseapp.controller.activities;

/**
 * Created by Antoine Pelletier on 11/07/2017.
 */

public interface ActivityHolderInterface {

	int getContentViewId();

	void initView();

	String getTitleBarTitle();

	String getHexActionbarColor();

	int getTitleActionBarColor();
}