package com.tianfei.go_up;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tianfei.go_up.Presenter.LineChartPresenter;
import com.tianfei.go_up.Presenter.PieChartPresenter;
import com.tianfei.go_up.View.LineChartView;
import com.tianfei.go_up.View.PieChartView;

import org.json.JSONObject;

import java.text.SimpleDateFormat;


public class FragmentPie extends Fragment implements PieChartView{
    private PieChartPresenter pieChartPresenter;
    private String username;
    int model;
    private SimpleDateFormat simpleDateFormat;
    private JSONObject chartData;

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
        return inflater.inflate(R.layout.fragment_pie, container, false);
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
}
