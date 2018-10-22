package com.tianfei.go_up.Presenter;

import com.tianfei.go_up.FragmentTimer;
import com.tianfei.go_up.Model.TimerModel;
import com.tianfei.go_up.View.TimerView;

public class TimerPresenter {
    TimerModel timerModel;
    public TimerPresenter() {
        timerModel = new TimerModel();
    }

    TimerView timerView;
    public void bind(FragmentTimer fragmentTimer){
        timerView = fragmentTimer;
    }

    public void collector(){
        timerModel.collect(timerView.getValue(), new TimerModel.TimerModelListener() {
            @Override
            public void getHeight(Double height) {
                timerView.showData(height);
            }
        });
    }


}
