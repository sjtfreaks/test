package com.example.jet.test.application;

/**
 * Created by jet on 2018-07-21.
 */

import android.app.Application;

import com.example.jet.test.utils.StaticClass;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.CrashReport;

import cn.bmob.v3.Bmob;

public class BaseApplication extends Application{
    //create 创建
    @Override
    public void onCreate() {
        super.onCreate();
        //Bugly
        Bugly.init(getApplicationContext(),StaticClass.BUGLY_ID, false);
        //bmob
        Bmob.initialize(this,StaticClass.BMOB_ID);
    }
}
