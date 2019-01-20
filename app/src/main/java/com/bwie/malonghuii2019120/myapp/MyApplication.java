package com.bwie.malonghuii2019120.myapp;

import android.app.Application;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //UMeng初始化
        UMShareAPI.get(this);
        PlatformConfig.setQQZone("1106036236","mjFCi0oxXZKZEWJs");
        UMConfigure.init(this,"5c089159b465f59767000066","小米",UMConfigure.DEVICE_TYPE_PHONE,"");

    }
}
