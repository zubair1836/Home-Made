package com.army.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.army.R;
import com.army.utils.SessionManager;


public class FirstActivity extends AppCompatActivity {
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        int SPLASH_TIME_OUT = 1;
        //int SPLASH_TIME_OUT = 1000;

        new Handler().postDelayed(() -> {

            Intent i = new Intent(FirstActivity.this, LoginActivity.class);
            startActivity(i);




            finish();

        }, SPLASH_TIME_OUT);

    }


}
