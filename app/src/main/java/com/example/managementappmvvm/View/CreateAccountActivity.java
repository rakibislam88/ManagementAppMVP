package com.example.managementappmvvm.View;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.managementappmvvm.R;
import com.example.managementappmvvm.Presenter.SignUpPresenter;
import com.example.managementappmvvm.Presenter.SignUpPresenterInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CreateAccountActivity extends AppCompatActivity implements CreateSignUpInterface{

    public static ArrayList<String> loginDataList = new ArrayList<>();
    EditText name, phone;
    Button signUpBtn;
    private SignUpPresenterInterface signUpPresenterInterface;
    public static SharedPreferences signUpSharedPre;
    public static SharedPreferences.Editor editor;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone_number);
        signUpBtn = findViewById(R.id.Create_btn);

        signUpPresenterInterface = SignUpPresenter.getSignUpRepositoryInstance(this);

        signUpSharedPre   = getSharedPreferences("signupsp", MODE_PRIVATE);
        editor            =    signUpSharedPre.edit();

        getData();

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpPresenterInterface.doSignUp(name.getText().toString().trim(), phone.getText().toString().trim());
            }
        });


    }

    private void getData(){

        loginDataList = new ArrayList<>();
        String url = "https://raquib.000webhostapp.com/apps/arr_of_group_name_data.php";
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i=0; i<response.length(); i++){
                        JSONObject jsonObject = response.getJSONObject(i);
                        String name = jsonObject.getString("groupname");
                        String phone = jsonObject.getString("userid");
                        loginDataList.add(phone);
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(arrayRequest);
    }

    @Override
    public void SignUpErrorMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void SignUpSuccessMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}