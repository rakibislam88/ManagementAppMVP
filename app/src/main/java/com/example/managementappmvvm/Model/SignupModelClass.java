package com.example.managementappmvvm.Model;

import android.text.TextUtils;

import com.example.managementappmvvm.View.CreateAccountActivity;
import com.example.managementappmvvm.View.LoginActivity;

public class SignupModelClass implements SignupModelInterface{

    private String name;
    private String phone;




    public SignupModelClass(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    public int checkSignUpValidity() {
        if (TextUtils.isEmpty(getName())){
            return 2;
        }else if (TextUtils.isEmpty(getPhone())){
            return 4;
        }else if(getPhone().length()!=11){
            return 6;
        }else if(CreateAccountActivity.loginDataList.contains(phone)){
            return 8;
        }else {
            return 1;
        }
    }
}
