package com.android.greenhouse.greenhouseapp.controller.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.greenhouse.greenhouseapp.R;
import com.android.greenhouse.greenhouseapp.controller.fragments.HumidityFragment;
import com.android.greenhouse.greenhouseapp.controller.fragments.LuminosityFragment;
import com.android.greenhouse.greenhouseapp.controller.fragments.SoilHumidityFragment;
import com.android.greenhouse.greenhouseapp.controller.fragments.TemperatureFragment;

public class DrawerBaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

	public static final int MAIN_CONTAINER_ID = R.id.main_container;
	private Toolbar toolbar;
	private DrawerLayout drawer;
	private NavigationView navigationView;
	private ActionBarDrawerToggle toggle;
	private TextView txt_action_title;
	private Fragment sensorGraphFragment;
	private FragmentManager fragmentManager;
	private FragmentTransaction fragmentTransaction;
	private HumidityFragment humidityFragment;
	private SoilHumidityFragment soilHumidityFragment;
	private LuminosityFragment luminosityFragment;
	private TemperatureFragment temperatureFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_drawer_base);

		fragmentManager = getSupportFragmentManager();
		setSensorFragment(SensorGraph.SoilHumidity);

		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.setDrawerListener(toggle);
		toggle.syncState();

		txt_action_title = (TextView) findViewById(R.id.txt_action_title);

		navigationView = (NavigationView) findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);
	}

	/**
	 * called in extending activities instead of setContentView...
	 *
	 * @param layoutId The content Layout Id of extending activities
	 */
	public void addContentView(int layoutId) {
		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View contentView = inflater.inflate(layoutId, null, false);
		drawer.addView(contentView, 0);
	}

	@Override
	public void onBackPressed() {
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		// Handle navigation view item clicks here.
		int id = item.getItemId();

		Intent intent;
		if (id == R.id.nav_temperature) {
			// Load Temperature Fragment
			setSensorFragment(SensorGraph.Temperature);

		} else if (id == R.id.nav_air_humidity) {
			// Load AirHumidity Fragment
			setSensorFragment(SensorGraph.AirHumidity);

		} else if (id == R.id.nav_soil_humidity) {
			// Load Hidro Fragment
			setSensorFragment(SensorGraph.SoilHumidity);

		} else if (id == R.id.nav_luminosity) {
			// Load Luminosity Fragment
			setSensorFragment(SensorGraph.Luminosity);
		}

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}

	private void setSensorFragment(SensorGraph sensor) {

		boolean shouldAddFragment = false;
		if (sensorGraphFragment == null) {
			shouldAddFragment = true;
		}
		Log.d("sensorfragment", "first debug");

		switch (sensor) {
			case AirHumidity:
				if (humidityFragment == null) {
					humidityFragment = new HumidityFragment();
				}
				if (sensorGraphFragment == humidityFragment) {
					return;
				}
				Log.d("sensorfragment", sensor.name);
				sensorGraphFragment = humidityFragment;

				break;
			case SoilHumidity:
				if (soilHumidityFragment == null) {
					soilHumidityFragment = new SoilHumidityFragment();
				}
				if (sensorGraphFragment == soilHumidityFragment) {
					return;
				}
				Log.d("sensorfragment", sensor.name);
				sensorGraphFragment = soilHumidityFragment;

				break;
			case Luminosity:
				if (luminosityFragment == null) {
					luminosityFragment = new LuminosityFragment();
				}
				if (sensorGraphFragment == luminosityFragment) {
					return;
				}
				Log.d("sensorfragment", sensor.name);
				sensorGraphFragment = luminosityFragment;

				break;
			case Temperature:
				if (temperatureFragment == null) {
					temperatureFragment = new TemperatureFragment();
				}
				if (sensorGraphFragment == temperatureFragment) {
					return;
				}
				Log.d("sensorfragment", sensor.name);
				sensorGraphFragment = temperatureFragment;
				break;
			default:
				Log.d("sensorfragment", sensor.name);
				break;
		}

		fragmentTransaction = fragmentManager.beginTransaction();

		if (shouldAddFragment) {
			fragmentTransaction.add(MAIN_CONTAINER_ID, sensorGraphFragment, sensorGraphFragment.getTag());
		} else {
			fragmentTransaction.replace(MAIN_CONTAINER_ID, sensorGraphFragment, sensorGraphFragment.getTag());
		}
		fragmentTransaction.commit();
	}

	public Fragment getSensorGraphFragment() {
		return sensorGraphFragment;
	}

	public void setTitleBarTitle(String title) {
		txt_action_title.setText(title);
	}


	private enum SensorGraph {
		AirHumidity("AirHumidity"),
		SoilHumidity("SoilHumidity"),
		Luminosity("Luminosity"),
		Temperature("Temperature");

		private String name = "";

		SensorGraph(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name + "Fragment";
		}
	}

}
