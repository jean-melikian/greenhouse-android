package com.android.greenhouse.greenhouseapp.controller.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.greenhouse.greenhouseapp.R;
import com.android.greenhouse.greenhouseapp.util.Constants;


public class HygrometerActivity extends BaseActivity {

    @Override
    public int getContentViewId() {
        return R.layout.activity_hygrometer;
    }

    @Override
    public void onResume() {
        super.onResume();
        setActionBarTitleDynamic(getResources().getString(R.string.menu_hydro));
    }

    @Override
    public void initView() {

    }

    @Override
    public String getTitleBarTitle() {
        return getResources().getString(R.string.menu_hydro);
    }

    @Override
    public String getHexActionbarColor() {
        return Constants.ACTION_BAR_COLOR;
    }

    @Override
    public int getTitleActionBarColor() {
        return getResources().getColor(R.color.dark_gray_text);
    }

}
