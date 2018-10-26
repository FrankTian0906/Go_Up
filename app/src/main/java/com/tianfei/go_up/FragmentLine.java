package com.tianfei.go_up;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.tianfei.go_up.Presenter.LineChartPresenter;
import com.tianfei.go_up.View.LineChartView;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class FragmentLine extends Fragment implements LineChartView{
    LineChartPresenter lineChartPresenter;
    String username;
    int model;
    SimpleDateFormat simpleDateFormat;
    JSONObject chartData;
    private Unbinder unbinder;
    @BindView(R.id.barChart) BarChart barChart;

    public static FragmentLine newInstance() {
        FragmentLine fragment = new FragmentLine();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_line, container, false);
        // Inflate the layout for this fragment
        lineChartPresenter = new LineChartPresenter();
        lineChartPresenter.bind(this);

        unbinder = ButterKnife.bind(this,view);
        initChartData();

        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public SimpleDateFormat getSimpleDate() {
        return simpleDateFormat;
    }

    @Override
    public int getModel() {
        return model;
    }

    @Override
    public void getData(JSONObject jsonObject) {
        chartData = jsonObject;
    }

    public void initChartData(){
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.setMaxVisibleValueCount(50);
        barChart.setPinchZoom(false);
        barChart.setDrawGridBackground(false);
        barChart.setTouchEnabled(false);
        barChart.animateY(2000);
        barChart.setDrawBorders(false);


        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);

        ArrayList<BarEntry> arrayList = new ArrayList<>();
        arrayList.add(new BarEntry(1,40f,"Mon"));
        arrayList.add(new BarEntry(2,49f));
        arrayList.add(new BarEntry(3,12f));
        arrayList.add(new BarEntry(4,30f));
        arrayList.add(new BarEntry(5,55f));
        arrayList.add(new BarEntry(6,60f));
        arrayList.add(new BarEntry(7,0f));

        BarDataSet barDataSet = new BarDataSet(arrayList,"week");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData barData = new BarData(barDataSet);

        barChart.setData(barData);
    }
}


