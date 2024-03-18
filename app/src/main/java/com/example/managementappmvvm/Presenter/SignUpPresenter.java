package com.example.managementappmvvm.Presenter;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;

import com.example.managementappmvvm.Model.SignupModelClass;
import com.example.managementappmvvm.View.CreateAccountActivity;
import com.example.managementappmvvm.View.CreateSignUpInterface;

public class SignUpPresenter implements SignUpPresenterInterface {

    private static CreateSignUpInterface mcreateSignUpInterface;
    private static SignUpPresenter signUpRepositoryInstance;



    public static synchronized SignUpPresenter getSignUpRepositoryInstance(CreateSignUpInterface createSignUpInterface) {

        if (signUpRepositoryInstance == null){
            mcreateSignUpInterface = createSignUpInterface;
            signUpRepositoryInstance = new SignUpPresenter();
        }

        return signUpRepositoryInstance;
    }

    @Override
    public void doSignUp(String name, String phone) {
        SignupModelClass modelClass = new SignupModelClass(name, phone);
        int signCode = modelClass.checkSignUpValidity();

        if (signCode==2){
            mcreateSignUpInterface.SignUpErrorMsg("Enter a valid Name");
        }else if (signCode==4){
            mcreateSignUpInterface.SignUpErrorMsg("Enter a valid Number");
        }else if (signCode==6){
            mcreateSignUpInterface.SignUpErrorMsg("Number Should be 11 Digit?");
        }else if (signCode==8){
            mcreateSignUpInterface.SignUpErrorMsg("Already have an Account?");
        }else{
            CreateAccountActivity.editor.putString("token", phone);
            CreateAccountActivity.editor.apply();
            mcreateSignUpInterface.SignUpSuccessMsg("sing up");
        }
    }




}
