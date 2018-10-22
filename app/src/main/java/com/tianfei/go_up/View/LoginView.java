package com.tianfei.go_up.View;

public interface LoginView {
    String getEmail();
    String getPasswords();
    void checkInformation();
    void loginSuccess();
    void checkInternet();
    void InvalidInformation();
    void processBar();
}
