package com.tianfei.go_up;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tianfei.go_up.View.RegisterView;

public class RegisterActivity extends AppCompatActivity implements RegisterView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
}
