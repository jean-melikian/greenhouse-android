package com.android.greenhouse.greenhouseapp.controller.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.greenhouse.greenhouseapp.R;

public class HumidityActivity extends DrawerBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        super.addContentView(R.layout.activity_humidity);
        super.setTitleBarTitle(getResources().getString(R.string.menu_humidity));
    }
}
