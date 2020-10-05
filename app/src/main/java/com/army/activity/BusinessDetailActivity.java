package com.army.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.army.R;

public class BusinessDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_detail);
    }

    public void ShowPaswrdDG(View view) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dg_password);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        TextView btnSubmit=dialog.findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(view1 -> {
            Intent i = new Intent(BusinessDetailActivity.this, HomeActivity.class);
            startActivity(i);
        });
    }
}