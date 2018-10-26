package com.tianfei.go_up;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.tianfei.go_up.Presenter.PieChartPresenter;
import com.tianfei.go_up.View.PieChartView;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class FragmentPie extends Fragment implements PieChartView{
    private PieChartPresenter pieChartPresenter;
    private String username;
    int model;
    private SimpleDateFormat simpleDateFormat;
    private JSONObject chartData;

    Unbinder unbinder;
    @BindView(R.id.pieChart) PieChart pieChart;

    public static FragmentPie newInstance() {
        FragmentPie fragment = new FragmentPie();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        pieChartPresenter = new PieChartPresenter();
        pieChartPresenter.bind(this);
        View view = inflater.inflate(R.layout.fragment_pie, container, false);
        unbinder = ButterKnife.bind(this,view);

        initPieChart();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
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

    public void initPieChart(){
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setTransparentCircleRadius(61f);
        pieChart.animateY(2000, Easing.EasingOption.EaseInOutCirc);

        ArrayList<PieEntry> arrayList = new ArrayList<>();
        arrayList.add(new PieEntry(40f, "Mon"));
        arrayList.add(new PieEntry(49f,"Tue"));
        arrayList.add(new PieEntry(12f,"Wed"));
        arrayList.add(new PieEntry(30f,"Thu"));
        arrayList.add(new PieEntry(55f,"Fri"));
        arrayList.add(new PieEntry(60f,"Sat"));
        arrayList.add(new PieEntry(0f,"Sun"));

        PieDataSet pieDataSet = new PieDataSet(arrayList,"week");
        pieDataSet.setSliceSpace(3f);
        pieDataSet.setSelectionShift(5f);
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData pieData =  new PieData(pieDataSet);
        pieData.setValueTextSize(10f);
        pieData.setValueTextColor(Color.YELLOW);

        pieChart.setData(pieData);

    }
}
