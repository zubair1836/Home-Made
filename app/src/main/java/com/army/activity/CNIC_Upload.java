package com.army.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.army.R;

public class CNIC_Upload extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cnic_upload);
    }

    public void UploadCnic(View view) {
        Intent intent = new Intent(CNIC_Upload.this, BusinessDetailActivity.class);
        startActivity(intent);
    }
}