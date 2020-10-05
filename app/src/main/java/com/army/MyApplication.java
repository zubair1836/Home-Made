package com.army;

import android.app.Application;
import android.content.Context;

import com.onesignal.OneSignal;

public class MyApplication extends Application {
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

    }
}