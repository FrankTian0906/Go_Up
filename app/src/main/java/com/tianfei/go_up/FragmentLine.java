package com.tianfei.go_up;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tianfei.go_up.Presenter.LineChartPresenter;
import com.tianfei.go_up.View.LineChartView;

import org.json.JSONObject;

import java.text.SimpleDateFormat;


public class FragmentLine extends Fragment implements LineChartView{
    LineChartPresenter lineChartPresenter;
    String username;
    int model;
    SimpleDateFormat simpleDateFormat;
    JSONObject chartData;

    public static FragmentLine newInstance() {
        FragmentLine fragment = new FragmentLine();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        lineChartPresenter = new LineChartPresenter();
        lineChartPresenter.bind(this);
        return inflater.inflate(R.layout.fragment_line, container, false);
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
