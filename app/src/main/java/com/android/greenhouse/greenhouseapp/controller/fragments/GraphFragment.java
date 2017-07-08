package com.android.greenhouse.greenhouseapp.controller.fragments;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.greenhouse.greenhouseapp.R;
import com.android.greenhouse.greenhouseapp.controller.activities.HumidityActivity;
import com.android.greenhouse.greenhouseapp.controller.activities.HygrometerActivity;
import com.android.greenhouse.greenhouseapp.controller.activities.LuminosityActivity;
import com.android.greenhouse.greenhouseapp.controller.activities.TemperatureActivity;
import com.android.greenhouse.greenhouseapp.model.SensorEntry;
import com.android.greenhouse.greenhouseapp.model.TemperatureValueFormatter;
import com.android.greenhouse.greenhouseapp.model.TimeStampValueFormatter;
import com.android.greenhouse.greenhouseapp.retrofit.IRFSensorEntryService;
import com.android.greenhouse.greenhouseapp.retrofit.Session;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by antoinepelletier on 06/07/2017.
 */

public class GraphFragment extends Fragment {

    private BarChart mLineBarChart;
    private ArrayList<BarEntry> barEntries;
    private BarDataSet barDataset;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_graph, container, false);
        mLineBarChart = (BarChart) view.findViewById(R.id.barChart);
        initDatasChart();
        return view;
    }

    private void initDatasChart() {

        IAxisValueFormatter xAxisFormatter = new TimeStampValueFormatter();

        XAxis xAxis = mLineBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(Typeface.DEFAULT);
        xAxis.setDrawGridLines(true);
        xAxis.setLabelCount(7);
        xAxis.setValueFormatter(xAxisFormatter);


        IAxisValueFormatter yAxisFormatter = new TemperatureValueFormatter();

        YAxis yAxisLeft = mLineBarChart.getAxisLeft();
        yAxisLeft.setTypeface(Typeface.DEFAULT);
        xAxis.setDrawGridLines(true);
        yAxisLeft.setValueFormatter(yAxisFormatter);
        yAxisLeft.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis yAxisRight = mLineBarChart.getAxisRight();
        yAxisRight.setEnabled(false);

        Legend l = mLineBarChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);

        mLineBarChart.setMaxVisibleValueCount(60);
        mLineBarChart.setDrawValueAboveBar(false);
        mLineBarChart.animateXY(3000, 3000);

        testService();

    }

    /*private void setData() {

        // Set values of the graph
        barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(715478400, 37.5f));
        barEntries.add(new BarEntry(683856000, 18.5f));
        barEntries.add(new BarEntry(777081600, 10f));
        barEntries.add(new BarEntry(1057017600, 22f));
        barEntries.add(new BarEntry(1499297059, 40.5f));

        if (mLineBarChart.getData() != null &&
                mLineBarChart.getData().getDataSetCount() > 0) {
            barDataset = (BarDataSet) mLineBarChart.getData().getDataSetByIndex(0);
            barDataset.setValues(barEntries);
            mLineBarChart.getData().notifyDataChanged();
            mLineBarChart.notifyDataSetChanged();
        } else {
            barDataset = new BarDataSet(barEntries, "Temperature Entries");

            barDataset.setDrawIcons(false);

            barDataset.setColors(ColorTemplate.MATERIAL_COLORS);

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(barDataset);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setValueTypeface(Typeface.DEFAULT);
            data.setBarWidth(0.9f);

            mLineBarChart.setData(data);
        }
    }*/

    public void convertInTimestamp(Date date) {
        Log.i("test", String.valueOf(date.getTime()));
    }

    private void testService() {

        barEntries = new ArrayList<>();

        // Service setup
        IRFSensorEntryService service = Session.getDefault().create(IRFSensorEntryService.class);

        // Prepare the HTTP request
        Call<List<SensorEntry>> call = service.loadSensorsEntries();

        // Asynchronously execute HTTP request
        call.enqueue(new Callback<List<SensorEntry>>() {
            @Override
            public void onResponse(Call<List<SensorEntry>> call, Response<List<SensorEntry>> response) {
                // Prepare datas for draw the graph
                for (SensorEntry sensorEntry : response.body()) {
                    convertStringToTimestamp(sensorEntry.getCreated_date());
                    barEntries.add(new BarEntry(convertStringToTimestamp(sensorEntry.getCreated_date()), sensorEntry.getHygrometer()));
                }
                // Set up graph
                setUpGraph();
            }


            @Override
            public void onFailure(Call<List<SensorEntry>> call, Throwable t) {
                Log.i("On Failure ", "FAILED");
            }
        });
    }

    private void setUpGraph() {
        if (mLineBarChart.getData() != null &&
                mLineBarChart.getData().getDataSetCount() > 0) {
            barDataset = (BarDataSet) mLineBarChart.getData().getDataSetByIndex(0);
            barDataset.setValues(barEntries);
            mLineBarChart.getData().notifyDataChanged();
            mLineBarChart.notifyDataSetChanged();

        } else {
            if (getActivity() instanceof HumidityActivity) {
                barDataset = new BarDataSet(barEntries, getActivity().getResources().getString(R.string.humidity_graph_entries));

            } else if (getActivity() instanceof HygrometerActivity) {
                barDataset = new BarDataSet(barEntries, getActivity().getResources().getString(R.string.hygrometer_graph_entries));

            } else if (getActivity() instanceof LuminosityActivity) {
                barDataset = new BarDataSet(barEntries, getActivity().getResources().getString(R.string.luminosity_graph_entries));

            } else if (getActivity() instanceof TemperatureActivity) {
                barDataset = new BarDataSet(barEntries, getActivity().getResources().getString(R.string.temp_graph_entries));
            }

            barDataset = new BarDataSet(barEntries, "Temperature Entries");

            barDataset.setDrawIcons(false);

            barDataset.setColors(ColorTemplate.MATERIAL_COLORS);

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(barDataset);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setValueTypeface(Typeface.DEFAULT);
            data.setBarWidth(0.9f);

            mLineBarChart.setData(data);
        }
    }

    /**
     * Convert a given String into timeStampp format
     *
     * @param str_date (Thu Jul 06 15:59:29 GMT+02:00 2017)
     * @return Long timestampp format (1499349569)
     */
    public static Long convertStringToTimestamp(String str_date) {

        DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        Date d;
        try {
            d = df.parse(str_date);
            return d.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
