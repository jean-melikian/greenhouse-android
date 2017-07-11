package com.android.greenhouse.greenhouseapp.controller.activities;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.greenhouse.greenhouseapp.R;
import com.android.greenhouse.greenhouseapp.util.Constants;


public class HygrometerActivity extends DrawerBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        super.addContentView(R.layout.activity_hygrometer);
        super.setTitleBarTitle(getResources().getString(R.string.menu_hydro));
    }
}
