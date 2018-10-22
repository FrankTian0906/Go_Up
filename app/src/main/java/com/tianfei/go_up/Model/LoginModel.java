package com.tianfei.go_up.Model;


import android.util.Log;

import com.tianfei.go_up.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginModel extends OkHttpClient{
    private OkHttpClient client;
    private Request request;
    private RequestBody requestBody;
    private final String TAG = "Login_model";
    private final String URL_Login = "http://192.168.137.1/login.php";
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    private JSONObject loginData;

    public  void login(String email, String password, final LoginListenor loginListenor){
        client = new OkHttpClient();
        loginData = new JSONObject();
        try {
            loginData.put("username", email).put("password",password);
        } catch (JSONException e) {
            Log.d(TAG,"JSON ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        requestBody = RequestBody.create(JSON,loginData.toString());
        request = new Request.Builder().url(URL_Login).post(requestBody).build();
        try {
            Response response = client.newCall(request).execute();
            if(response.isSuccessful()){
                JSONObject jsonObj = new JSONObject(response.toString());
                if(jsonObj.getString("result").equals("false")){
                    loginListenor.notExist();
                }else
                    loginListenor.success();

            }else
                loginListenor.failure();
        } catch (IOException e) {
            Log.d(TAG,"CALL ERROR: "+e.getMessage());
            loginListenor.failure();
            e.printStackTrace();
        } catch (JSONException e) {
            Log.d(TAG,"JSON ERROR: "+e.getMessage());
            loginListenor.failure();
            e.printStackTrace();
        }

    }
    public interface LoginListenor{
        void failure();
        void notExist();
        void success();
    }
}
