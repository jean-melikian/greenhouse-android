package com.android.greenhouse.greenhouseapp.controller.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.greenhouse.greenhouseapp.R;

/**
 * Created by Ozone on 14/07/2017.
 */

public abstract class BaseSensorFragment extends Fragment {

	public static final int GRAPH_CONTAINER_ID = R.id.graph_container;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = super.onCreateView(inflater, container, savedInstanceState);

		FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
		fragmentTransaction.add(GRAPH_CONTAINER_ID, new GraphFragment(), null).commit();

		return inflater.inflate(R.layout.fragment_sensor, container, false);
	}
}
