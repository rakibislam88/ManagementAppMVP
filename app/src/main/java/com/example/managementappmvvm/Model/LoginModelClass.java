package com.example.managementappmvvm.Model;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.managementappmvvm.View.LoginActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Retrofit;

public class LoginModelClass implements LoginModelInterface{


    private Context context;
    private String username;
    private String userphonenumber;


    public LoginModelClass(Context context, String username, String userphonenumber) {
        this.context = context;
        this.username = username;
        this.userphonenumber = userphonenumber;
    }

    @Override
    public String getName() {
        return username;
    }

    @Override
    public String getPhone() {
        return userphonenumber;
    }

    @Override
    public int checkAllreadyHaveAnAccount() {
        if (LoginActivity.loginDataList.contains(userphonenumber)){
            return 2;
        }else {
            return 1;
        }
    }
}


