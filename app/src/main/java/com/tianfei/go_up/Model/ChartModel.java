package com.tianfei.go_up.Model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ChartModel {
    protected static final String path_DAY = "http://192.168.137.1/day.php";
    protected static final String path_WEEK = "http://192.168.137.1/week.php";
    protected static final String path_YEAR = "http:/192.168.137.1/month.php";

    private final int DAY_MODEL = 1;
    private final int WEEK_MODEL = 2;
    private final int YEAR_MODEL = 3;

    private OkHttpClient client;
    private Request request;
    private RequestBody requestBody;
    private String url;
    private final String TAG = "Chart_model";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private JSONObject chartData;

    public void getData(String username, SimpleDateFormat simpleDateFormat, int model, ChartListener chartListener) {
        client = new OkHttpClient();
        chartData = new JSONObject();
        try {
            chartData.put("username", username).put("date",simpleDateFormat);
        } catch (JSONException e) {
            Log.d(TAG,"JSON ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        requestBody = RequestBody.create(JSON,chartData.toString());
        switch (model){

        }
        request = new Request.Builder().url(choosePath(model)).post(requestBody).build();
        try {
            Response response = client.newCall(request).execute();
            if(response.isSuccessful()){
                JSONObject jsonObj = new JSONObject(response.toString());
                //TODO: get data
                chartListener.getData(jsonObj);

            }else
                chartListener.failure();
        } catch (IOException e) {
            Log.d(TAG,"CALL ERROR: "+e.getMessage());
            chartListener.failure();
            e.printStackTrace();
        } catch (JSONException e) {
            Log.d(TAG,"JSON ERROR: "+e.getMessage());
            chartListener.failure();
            e.printStackTrace();
        }
    }

    private String choosePath(int model){
        switch (model){
            case DAY_MODEL:
                return path_DAY;
            case WEEK_MODEL:
                return path_WEEK;
            case YEAR_MODEL:
                return path_YEAR;
            default:
                return path_DAY;
        }
    }

    public interface ChartListener{
        void failure();
        void getData(JSONObject jsonObj);
    }
}
