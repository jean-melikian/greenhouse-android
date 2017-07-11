package com.android.greenhouse.greenhouseapp.controller.fragments;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.greenhouse.greenhouseapp.R;
import com.android.greenhouse.greenhouseapp.controller.activities.HumidityActivity;
import com.android.greenhouse.greenhouseapp.controller.activities.HygrometerActivity;
import com.android.greenhouse.greenhouseapp.controller.activities.LuminosityActivity;
import com.android.greenhouse.greenhouseapp.controller.activities.TemperatureActivity;
import com.android.greenhouse.greenhouseapp.model.DateHelper;
import com.android.greenhouse.greenhouseapp.model.Entries;
import com.android.greenhouse.greenhouseapp.model.SensorEntry;
import com.android.greenhouse.greenhouseapp.model.valueformatters.HumidityValueFormatter;
import com.android.greenhouse.greenhouseapp.model.valueformatters.HygrometerValueFormatter;
import com.android.greenhouse.greenhouseapp.model.valueformatters.LuminosityValueFormatter;
import com.android.greenhouse.greenhouseapp.model.valueformatters.TemperatureValueFormatter;
import com.android.greenhouse.greenhouseapp.model.valueformatters.TimeStampValueFormatter;
import com.android.greenhouse.greenhouseapp.retrofit.IRFSensorEntryService;
import com.android.greenhouse.greenhouseapp.retrofit.Session;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by antoinepelletier on 06/07/2017.
 */

public class GraphFragment extends Fragment {

    private LineChart mLineBarChart;
    private ArrayList<Entry> entries;
    private LineDataSet lineDataset;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_graph, container, false);
        mLineBarChart = (LineChart) view.findViewById(R.id.barChart);
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

        IAxisValueFormatter yAxisFormatter;

        if (getContext() instanceof TemperatureActivity) {
            yAxisFormatter = new TemperatureValueFormatter();

        } else if (getContext() instanceof HumidityActivity) {
            yAxisFormatter = new HumidityValueFormatter();

        } else if (getContext() instanceof HygrometerActivity) {
            yAxisFormatter = new HygrometerValueFormatter();

        } else {
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
        IRFSensorEntryService service = Session.getDefault().create(IRFSensorEntryService.class);

        // Prepare the HTTP request
        Call<SensorEntry> call = service.loadSensorsEntries();

        // Asynchronously execute HTTP request
        call.enqueue(new Callback<SensorEntry>() {
            @Override
            public void onResponse(Call<SensorEntry> call, Response<SensorEntry> response) {
                // Prepare datas for draw the graph
                for (Entries entry : response.body().getEntries()) {
                    if (!DateHelper.isOneDayBefore(DateHelper.convertStringToTimestamp(entry.getCreated_date()))) {
                        if (getActivity() instanceof HumidityActivity) {
                            entries.add(new Entry(DateHelper.convertStringToTimestamp(entry.getCreated_date()), entry.getHygrometer()));

                        } else if (getActivity() instanceof HygrometerActivity) {
                            entries.add(new Entry(DateHelper.convertStringToTimestamp(entry.getCreated_date()), entry.getHygrometer()));

                        } else if (getActivity() instanceof LuminosityActivity) {
                            entries.add(new Entry(DateHelper.convertStringToTimestamp(entry.getCreated_date()), entry.getLuminosity()));

                        } else {
                            // Temperature
                            entries.add(new Entry(DateHelper.convertStringToTimestamp(entry.getCreated_date()), entry.getHygrometer()));
                        }
                    }
                }
                // Set up graph
                setUpGraph();
            }


            @Override
            public void onFailure(Call<SensorEntry> call, Throwable t) {
                Log.i("On Failure", "FAILED");
            }
        });
    }

    private void setUpGraph() {
        if (mLineBarChart.getData() != null && mLineBarChart.getData().getDataSetCount() > 0) {
            lineDataset.setValues(entries);
            mLineBarChart.getData().notifyDataChanged();
            mLineBarChart.notifyDataSetChanged();

        } else {
            if (getActivity() instanceof HumidityActivity) {
                lineDataset = new LineDataSet(entries, getActivity().getResources().getString(R.string.humidity_graph_entries));

            } else if (getActivity() instanceof HygrometerActivity) {
                lineDataset = new LineDataSet(entries, getActivity().getResources().getString(R.string.hygrometer_graph_entries));

            } else if (getActivity() instanceof LuminosityActivity) {
                lineDataset = new LineDataSet(entries, getActivity().getResources().getString(R.string.luminosity_graph_entries));

            } else if (getActivity() instanceof TemperatureActivity) {
                lineDataset = new LineDataSet(entries, getActivity().getResources().getString(R.string.temp_graph_entries));
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
