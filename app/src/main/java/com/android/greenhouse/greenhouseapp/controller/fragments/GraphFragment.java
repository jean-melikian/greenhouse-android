package com.android.greenhouse.greenhouseapp.controller.fragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.greenhouse.greenhouseapp.R;
import com.android.greenhouse.greenhouseapp.controller.activities.DrawerBaseActivity;
import com.android.greenhouse.greenhouseapp.model.DateHelper;
import com.android.greenhouse.greenhouseapp.model.Sensors;
import com.android.greenhouse.greenhouseapp.model.SensorsEntries;
import com.android.greenhouse.greenhouseapp.model.valueformatters.HumidityValueFormatter;
import com.android.greenhouse.greenhouseapp.model.valueformatters.LuminosityValueFormatter;
import com.android.greenhouse.greenhouseapp.model.valueformatters.SoilHumidityValueFormatter;
import com.android.greenhouse.greenhouseapp.model.valueformatters.TemperatureValueFormatter;
import com.android.greenhouse.greenhouseapp.model.valueformatters.TimeStampValueFormatter;
import com.android.greenhouse.greenhouseapp.retrofit.IServiceResultListener;
import com.android.greenhouse.greenhouseapp.retrofit.ServiceResult;
import com.android.greenhouse.greenhouseapp.retrofit.sensors.SensorsService;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

/**
 * Created by antoinepelletier on 06/07/2017.
 */

public class GraphFragment extends Fragment {

	private LineChart mLineBarChart;
	private ArrayList<Entry> entries;
	private LineDataSet lineDataset;
	private DrawerBaseActivity activity;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_graph, container, false);
		activity = (DrawerBaseActivity) getActivity();
		mLineBarChart = (LineChart) view.findViewById(R.id.line_chart);
		initDatasChart();
		return view;
	}

	private void initDatasChart() {

		IAxisValueFormatter xAxisFormatter = new TimeStampValueFormatter();

		XAxis xAxis = mLineBarChart.getXAxis();
		xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
		xAxis.setTypeface(Typeface.DEFAULT);
		xAxis.setDrawGridLines(true);
		xAxis.setValueFormatter(xAxisFormatter);

		IAxisValueFormatter yAxisFormatter = null;

		if (activity.getSensorGraphFragment() instanceof TemperatureFragment) {
			yAxisFormatter = new TemperatureValueFormatter();

		} else if (activity.getSensorGraphFragment() instanceof HumidityFragment) {
			yAxisFormatter = new HumidityValueFormatter();

		} else if (activity.getSensorGraphFragment() instanceof SoilHumidityFragment) {
			yAxisFormatter = new SoilHumidityValueFormatter();

		} else if (activity.getSensorGraphFragment() instanceof LuminosityFragment) {
			yAxisFormatter = new LuminosityValueFormatter();
		}

		YAxis yAxisLeft = mLineBarChart.getAxisLeft();
		yAxisLeft.setTypeface(Typeface.DEFAULT);
		xAxis.setDrawGridLines(true);
		yAxisLeft.setValueFormatter(yAxisFormatter);
		yAxisLeft.setAxisMinimum(0f); // this replaces setStartAtZero(true)

		YAxis yAxisRight = mLineBarChart.getAxisRight();
		yAxisRight.setEnabled(false);

		// no description text
		mLineBarChart.getDescription().setEnabled(false);

		// enable touch gestures
		mLineBarChart.setTouchEnabled(true);

		// enable scaling and dragging
		mLineBarChart.setDragEnabled(true);
		mLineBarChart.setScaleEnabled(true);

		// if disabled, scaling can be done on x- and y-axis separately
		mLineBarChart.setPinchZoom(true);

		mLineBarChart.animateXY(2500, 2500);

		launchService();

		Legend l = mLineBarChart.getLegend();
		l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
		l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
		l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
		l.setDrawInside(false);
		l.setForm(Legend.LegendForm.SQUARE);
		l.setFormSize(9f);
		l.setTextSize(11f);
		l.setXEntrySpace(4f);

	}

	private void launchService() {

		entries = new ArrayList<>();

		// Service setup
		SensorsService sensorsService = new SensorsService();

		// Asynchronously execute HTTP request
		sensorsService.getAll(new IServiceResultListener<SensorsEntries>() {
			@Override
			public void onSuccess(ServiceResult<SensorsEntries> result) {

				// Prepare datas for draw the graph
				for (Sensors entry : result.getData().getEntries()) {
					if (!DateHelper.isOneDayBefore(DateHelper.convertStringToTimestamp(entry.getCreated_date()))) {
						if (activity.getSensorGraphFragment() instanceof HumidityFragment) {
							entries.add(new Entry(DateHelper.convertStringToTimestamp(entry.getCreated_date()), entry.getAirHumidity()));

						} else if (activity.getSensorGraphFragment() instanceof SoilHumidityFragment) {
							entries.add(new Entry(DateHelper.convertStringToTimestamp(entry.getCreated_date()), entry.getSoilHumidity()));

						} else if (activity.getSensorGraphFragment() instanceof LuminosityFragment) {
							entries.add(new Entry(DateHelper.convertStringToTimestamp(entry.getCreated_date()), entry.getLuminosity()));

						} else if (activity.getSensorGraphFragment() instanceof TemperatureFragment) {
							// Temperature
							entries.add(new Entry(DateHelper.convertStringToTimestamp(entry.getCreated_date()), entry.getTemperature()));
						}
					}
				}
				// Set up graph
				setUpGraph();
			}

			@Override
			public void onFailure(Throwable t) {
				Log.i("On Failure", "FAILED");
				t.printStackTrace();
			}
		});
	}

	private void setUpGraph() {
		if (mLineBarChart.getData() != null && mLineBarChart.getData().getDataSetCount() > 0) {
			lineDataset.setValues(entries);
			mLineBarChart.getData().notifyDataChanged();
			mLineBarChart.notifyDataSetChanged();

		} else {
			if (activity.getSensorGraphFragment() instanceof HumidityFragment) {
				lineDataset = new LineDataSet(entries, activity.getResources().getString(R.string.air_humidity_graph_entries));

			} else if (activity.getSensorGraphFragment() instanceof SoilHumidityFragment) {
				lineDataset = new LineDataSet(entries, activity.getResources().getString(R.string.soil_humidity_graph_entries));

			} else if (activity.getSensorGraphFragment() instanceof LuminosityFragment) {
				lineDataset = new LineDataSet(entries, activity.getResources().getString(R.string.luminosity_graph_entries));

			} else if (activity.getSensorGraphFragment() instanceof TemperatureFragment) {
				lineDataset = new LineDataSet(entries, activity.getResources().getString(R.string.temperature_graph_entries));
			}

			lineDataset.setDrawIcons(true);
			lineDataset.setDrawValues(true);
			lineDataset.setDrawCircles(true);

			int[] colorGraph = {ContextCompat.getColor(getContext(), R.color.dark_green)};

			lineDataset.setColors(colorGraph);

			ArrayList<ILineDataSet> dataSets = new ArrayList<>();
			dataSets.add(lineDataset);

			LineData data = new LineData(dataSets);

			mLineBarChart.setData(data);
		}
	}

}
