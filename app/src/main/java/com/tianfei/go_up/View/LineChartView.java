package com.tianfei.go_up.View;

import org.json.JSONObject;

import java.text.SimpleDateFormat;

public interface LineChartView {
    String getUsername();
    SimpleDateFormat getSimpleDate();
    int getModel();
    void getData(JSONObject jsonObject);
}
