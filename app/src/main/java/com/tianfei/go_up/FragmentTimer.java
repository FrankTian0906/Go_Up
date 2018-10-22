package com.tianfei.go_up;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import com.tianfei.go_up.Presenter.TimerPresenter;
import com.tianfei.go_up.View.TimerView;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class FragmentTimer extends Fragment implements TimerView,SensorEventListener{
    public final String TAG = "Timer";
    //private Context context;
    private SensorManager sensorManager;
    private Sensor mPressure;
    private float sensorVal;
    private DecimalFormat df;
    private TimerTask timerTask;
    private Timer timer = new Timer();

    private TimerPresenter timerPresenter;

    private Unbinder unbinder;
    @BindView(R.id.chronmeter_timeCounter) Chronometer chronometer;
    @BindView(R2.id.button_counter) Button button_counter;
    @BindView(R.id.textview_distance) TextView textView_distance;

    public static FragmentTimer newInstance() {
        FragmentTimer fragment = new FragmentTimer();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        //this.context = context;
        //register Pressure Sensor
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mPressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        if(mPressure != null)
            sensorManager.registerListener(this,mPressure,SensorManager.SENSOR_DELAY_NORMAL);
        else
            checkSensor();

        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timer, container, false);
        df = new DecimalFormat("0.00");
        df.getRoundingMode();
        timerPresenter = new TimerPresenter();
        timerPresenter.bind(this);

        unbinder = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mPressure != null)
            sensorManager.unregisterListener(this);
        Log.d(TAG,"On Destroy View method call !!!");
        unbinder.unbind();
    }

    @OnClick(R.id.button_counter)
    public void counterClick(){
        if(button_counter.getText().toString().equals("START")){
            button_counter.setText("STOP");
            resetData();
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    Message message = new Message();
                    message.what = 1;
                    sensorHandler.sendMessage(message);
                }
            };
            timer.schedule(timerTask, 1000, 5000);
        }
        else {
            button_counter.setText("START");
            chronometer.stop();
            timerTask.cancel();
        }
    }

    public void checkSensor(){
        button_counter.setEnabled(false);
    }

    @Override
    public float getValue() {
        return sensorVal;
    }

    @Override
    public void resetData() {
        textView_distance.setText("0.00 m");
    }

    @Override
    public void showData( double data) {
            textView_distance.setText(df.format(data).toString() + " m");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        sensorVal = event.values[0];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    Handler sensorHandler = new Handler(){
        public void handleMessage(Message msg) {
            if(msg.what == 1)
                timerPresenter.collector();
            super.handleMessage(msg);
        }
    };

}
