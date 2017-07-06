package com.android.greenhouse.greenhouseapp.controller.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.greenhouse.greenhouseapp.R;
import com.android.greenhouse.greenhouseapp.model.SensorEntry;
import com.android.greenhouse.greenhouseapp.retrofit.IRFSensorEntryService;
import com.android.greenhouse.greenhouseapp.retrofit.Session;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HygrometerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hygrometer);
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
        finish();
    }

    public void prepareDatas(List<SensorEntry> sensorEntries) {
        for (SensorEntry sensorEntry : sensorEntries) {

        }
    }
}
