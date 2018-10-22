package com.tianfei.go_up;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.tianfei.go_up.View.PersonView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class FragmentPerson extends Fragment implements PersonView{
    private Unbinder unbinder;

    @BindView(R.id.user_setting) Button button_userSetting;
    @BindView(R.id.user_name) EditText editText_userName;
    @BindView(R.id.user_email) EditText editText_userEmail;
    @BindView(R.id.user_age) EditText editText_userAge;
    @BindView(R.id.user_height) EditText editText_userHeight;
    @BindView(R.id.user_weight) EditText editText_userWeight;
    public static FragmentPerson newInstance() {
        FragmentPerson fragment = new FragmentPerson();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_person, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    @OnClick(R.id.user_setting)
    public void setting(){
        if(button_userSetting.getText().equals("Edit")){
            button_userSetting.setText("Save");
        }
        else {
            button_userSetting.setText("Edit");
        }
        //TODO: edit personal information
        /*
        editText_userName.setEnabled(true);
        editText_userEmail.setEnabled(true);
        editText_userAge.setEnabled(true);
        editText_userHeight.setEnabled(true);
        editText_userWeight.setEnabled(true);
        */
    }
}
