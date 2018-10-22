package com.tianfei.go_up;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.tianfei.go_up.Presenter.LoginPresenter;
import com.tianfei.go_up.View.LoginView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoginView {
    private final String TAG = "Login_activity";

    private final int SIGN_IN_SUCCESS = 0;
    private final int INTERNET_FAILURE = 1;
    private final int SIGN_IN_FAILURE = 2;
    private final int INPUT_CHECK = 3;
    private final int SIGN_IN_PROGRESS = 4;

    // UI references.
    @BindView(R2.id.email) AutoCompleteTextView mEmailView;
    @BindView(R2.id.password) EditText mPasswordView;
    @BindView(R2.id.login_form) View mLoginFormView;
    @BindView(R2.id.login_progress) View mProgressView;
    @BindView(R2.id.remember_checkbox) CheckBox mRememberCheckBox;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private LoginPresenter loginPresenter;
    Message mas;
    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case SIGN_IN_SUCCESS:
                    showProgress(false);
                    Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                    Toast.makeText(LoginActivity.this,"Success!",Toast.LENGTH_LONG).show();
                    startActivity(intent);
                    break;
                case INTERNET_FAILURE:
                    showProgress(false);
                    Toast.makeText(LoginActivity.this,"Failure! Please check the Internet!",Toast.LENGTH_LONG).show();
                    break;
                case SIGN_IN_FAILURE:
                    showProgress(false);
                    Toast.makeText(LoginActivity.this,"The account or password is incorrect!",Toast.LENGTH_LONG).show();
                    break;
                case INPUT_CHECK:
                    Toast.makeText(LoginActivity.this,"Please input correct content",Toast.LENGTH_LONG).show();
                    break;
                case SIGN_IN_PROGRESS:
                    showProgress(true);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter();
        loginPresenter.bind(LoginActivity.this);
        sharedPreferences = getSharedPreferences("UserInfor", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        getInfo();
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setScrollBarFadeDuration(1000);
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mPasswordView.setScrollBarFadeDuration(1000);
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    /**
     * button onClick Listener
     *  1. sign in
     *  2. sign up
     * @param view
     */
    @OnClick({R.id.email_sign_in_button, R.id.register_button})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.email_sign_in_button:
                new Thread() {
                    public void run(){
                        loginPresenter.login();
                    }
                }.start();
                break;
            case R.id.register_button:
                Intent intent = new Intent(LoginActivity.this,UserActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * checkbox-> remember user's username and passwords
     * @param ischanged
     */
    @OnCheckedChanged(R.id.remember_checkbox)
    public void onCheckedChangedListener(boolean ischanged){
                if(ischanged){
                    if(getEmail() != null && getPasswords() != null) {

                        editor.putString("userName", getEmail());
                        editor.putString("passwords", getPasswords());
                        editor.apply();
                    }
                    else {
                        Toast.makeText(this,"Empty",Toast.LENGTH_LONG).show();
                        mRememberCheckBox.setChecked(false);
                    }
                }else{
                    editor.putString("userName", "");
                    editor.putString("passwords", "");
                    editor.apply();
                }

    }

    public void getInfo(){
        String name = sharedPreferences.getString("userName","");
        String passwords = sharedPreferences.getString("passwords", "");
        if(name.length() > 0 && passwords.length() > 0) {
            Log.d(TAG,"saved name: " + name + " password: " + passwords);
            mEmailView.setText(name);
            mPasswordView.setText(passwords);
            mRememberCheckBox.setChecked(true);
        }

    }


    @Override
    public String getEmail() {
        return mEmailView.getText().toString();
    }

    @Override
    public String getPasswords() {
        return mPasswordView.getText().toString();
    }

    @Override
    public void checkInformation() {
        mas = new Message();
        mas.what = INPUT_CHECK;
        handler.sendMessage(mas);
    }

    @Override
    public void loginSuccess() {
        mas = new Message();
        mas.what = SIGN_IN_SUCCESS;
        handler.sendMessage(mas);
    }

    @Override
    public void checkInternet() {
        mas = new Message();
        mas.what = INTERNET_FAILURE;
        handler.sendMessage(mas);
    }

    @Override
    public void InvalidInformation() {
        mas = new Message();
        mas.what = SIGN_IN_FAILURE;
        handler.sendMessage(mas);
    }

    @Override
    public void processBar() {
        mas = new Message();
        mas.what = SIGN_IN_PROGRESS;
        handler.sendMessage(mas);
    }

}

