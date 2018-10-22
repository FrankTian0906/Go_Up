package com.tianfei.go_up.Presenter;


import com.tianfei.go_up.FragmentPie;
import com.tianfei.go_up.Model.ChartModel;
import com.tianfei.go_up.View.PieChartView;

import org.json.JSONObject;

import java.text.SimpleDateFormat;

public class PieChartPresenter {
    private ChartModel chartModel;
    public PieChartPresenter(){
        chartModel = new ChartModel();
    }

    private PieChartView pieChartView;
    public void bind(FragmentPie fragmentPie){
        pieChartView = fragmentPie;
    }

    public void getData(){
        String username = pieChartView.getUsername();
        int model = pieChartView.getModel();
        SimpleDateFormat simpleDateFormat = pieChartView.getSimpleDate();
        chartModel.getData(username, simpleDateFormat, model, new ChartModel.ChartListener() {
            @Override
            public void failure() {

            }

            @Override
            public void getData(JSONObject jsonObj) {
                pieChartView.getData(jsonObj);
            }
        });
    }
}
