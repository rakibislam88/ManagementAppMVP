package com.example.managementappmvvm.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.managementappmvvm.R;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sp1, sp2;
    Button accountBtn, loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        accountBtn = findViewById(R.id.create_account);
        loginBtn   = findViewById(R.id.login_in);

        sp1   = getSharedPreferences("signupsp", MODE_PRIVATE);
        String s = sp1.getString("token", "");
        if (s.length() > 0){
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
        }

        accountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CreateAccountActivity.class));
            }
        });

        /**
         * start login button
         *
         */
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
    }
}