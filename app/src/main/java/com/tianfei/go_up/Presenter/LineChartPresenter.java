package com.tianfei.go_up.Presenter;

import com.tianfei.go_up.FragmentLine;
import com.tianfei.go_up.Model.ChartModel;
import com.tianfei.go_up.View.LineChartView;

import org.json.JSONObject;

import java.text.SimpleDateFormat;

public class LineChartPresenter {
    private ChartModel chartModel;
    public LineChartPresenter(){
        chartModel = new ChartModel();
    }

    private LineChartView lineChartView;
    public void bind(FragmentLine fragmentLine){
        lineChartView = fragmentLine;
    }

    public void getData(){
        String username = lineChartView.getUsername();
        int model = lineChartView.getModel();
        SimpleDateFormat simpleDateFormat = lineChartView.getSimpleDate();
        chartModel.getData(username, simpleDateFormat, model, new ChartModel.ChartListener() {
            @Override
            public void failure() {

            }

            @Override
            public void getData(JSONObject jsonObj) {
                lineChartView.getData(jsonObj);
            }
        });
    }
}
