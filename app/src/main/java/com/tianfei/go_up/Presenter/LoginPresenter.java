package com.tianfei.go_up.Presenter;

import com.tianfei.go_up.LoginActivity;
import com.tianfei.go_up.Model.LoginModel;
import com.tianfei.go_up.View.LoginView;

public class LoginPresenter {
    LoginModel loginModel;
    public LoginPresenter() {
        loginModel = new LoginModel();
    }

    LoginView loginView;
    public void bind(LoginActivity loginActivity){
        loginView = loginActivity;
    }

    public void login(){
        String email = loginView.getEmail();
        String password = loginView.getPasswords();
        if(isEmailValid(email) && isPasswordValid(password)){
            //TODO : show process bar
            loginView.processBar();
            loginModel.login(email, password, new LoginModel.LoginListenor() {
                @Override
                public void failure() {
                    loginView.checkInternet();
                }

                @Override
                public void notExist() {
                    loginView.InvalidInformation();
                }

                @Override
                public void success() {
                    loginView.loginSuccess();
                }
            });
        }else {
            loginView.checkInformation();
        }

    }

    private boolean isEmailValid(String email) {

        if(email.isEmpty())
            return false;

        return true;
        //return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        if(password.isEmpty())
            return false;

        return true;
        //return password.length() > 4;
    }
}
