package com.example.managementappmvvm.Presenter;

import android.content.Context;

import com.example.managementappmvvm.Model.LoginModelClass;
import com.example.managementappmvvm.View.LoginInterface;

public class LoginPresenter implements LoginPresenterInterface{

    private static LoginInterface loginInterface;
    private  Context context;
    private static LoginPresenter loginPresenterInstance;

    public static LoginPresenter getInstance(LoginInterface login){
        if (loginPresenterInstance == null){
            loginInterface = login;
            loginPresenterInstance = new LoginPresenter();
        }

        return loginPresenterInstance;
    }

    @Override
    public void doLogin(String username, String userphonenumber) {
        LoginModelClass loginModelClass = new LoginModelClass(context, username, userphonenumber);
        int login_code = loginModelClass.checkAllreadyHaveAnAccount();


        if (login_code==2){
            loginInterface.haveAccount("Already have an account?");
        }else if (login_code == 1){
            loginInterface.haveNotAccount("haven't any account create");
        }
    }
}
