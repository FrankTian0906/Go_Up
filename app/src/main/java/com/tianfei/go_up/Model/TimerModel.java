package com.tianfei.go_up.Model;

import android.util.Log;

import java.text.DecimalFormat;

public class TimerModel {

    private double height;
    private double firstVal = 0.0, secondVal;
    private double result;
    private final String TAG = "TimerModel";
    DecimalFormat df;

    public TimerModel(){
        df = new DecimalFormat("0.00");
        df.getRoundingMode();
    }

    public void collect(float value, TimerModelListener timerModelListener){

        secondVal = getHeight(value);
        if (firstVal == 0.0)
            firstVal = secondVal;
        result = Math.abs(secondVal - firstVal);
        firstVal = secondVal;
        height = height + result;
        timerModelListener.getHeight(height);
        Log.d(TAG,"Result: " + result + "    First:" + firstVal + "   Second: " + secondVal);
    }

    private double getHeight(float value){
        float sPV = value;
        //altitude
        return  44330000 * (1 - (Math.pow((Double.parseDouble(df.format(sPV)) / 1013.25),(float) 1.0 / 5255.0)));
    }

    public interface TimerModelListener{
        public void getHeight(Double height);
    }

}
