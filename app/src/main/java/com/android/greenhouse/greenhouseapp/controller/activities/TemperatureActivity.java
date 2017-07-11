package com.android.greenhouse.greenhouseapp.controller.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.greenhouse.greenhouseapp.R;
import com.android.greenhouse.greenhouseapp.util.Constants;

public class TemperatureActivity extends DrawerBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        super.addContentView(R.layout.activity_luminosity);
        super.setTitleBarTitle(getResources().getString(R.string.menu_temperature));
    }
}
