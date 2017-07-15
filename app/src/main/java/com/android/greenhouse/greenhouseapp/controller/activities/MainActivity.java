package com.android.greenhouse.greenhouseapp.controller.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.android.greenhouse.greenhouseapp.BuildConfig;
import com.android.greenhouse.greenhouseapp.R;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends DrawerBaseActivity {


	BroadcastReceiver tokenReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			String token = intent.getStringExtra("token");
			if (token != null) {
				//send token to your server or what you want to do
				// Log and toast
				Log.w("token fcm", token);
				Toast.makeText(context, String.format("Token: %s", token), Toast.LENGTH_SHORT).show();
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.addContentView(R.layout.activity_main);

		LocalBroadcastManager.getInstance(this).registerReceiver(tokenReceiver, new IntentFilter("tokenReceiver"));
		FirebaseMessaging.getInstance().subscribeToTopic(BuildConfig.GREENDUINO_TOPIC);

	}

}
