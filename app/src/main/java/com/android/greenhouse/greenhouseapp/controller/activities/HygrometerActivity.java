package com.android.greenhouse.greenhouseapp.controller.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.greenhouse.greenhouseapp.R;


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

}
