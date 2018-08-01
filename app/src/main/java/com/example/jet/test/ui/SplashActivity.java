package com.example.jet.test.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextDirectionHeuristic;
import android.widget.TextView;

import com.example.jet.test.MainActivity;
import com.example.jet.test.R;
import com.example.jet.test.utils.ShareUtils;
import com.example.jet.test.utils.StaticClass;
import com.example.jet.test.utils.UtilTools;

/**
 * Created by jet on 2018-07-21.
 * 首页
 * 延迟2000ms
 * 全屏
 */


public class SplashActivity extends AppCompatActivity {
    private TextView mSplash;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case StaticClass.HANDLER_TIEM:
                    //是否为第一次运行
                    if (isFirst()) {
                        startActivity(new Intent(SplashActivity.this,GuideActivity.class));
                    } else {
                        startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                    }
                    finish();
                    break;
            }
        }
    };

    private boolean isFirst() {
        boolean isFirst = ShareUtils.getBoolean(this,StaticClass.SHARE_IS_FIRST,true);
        ShareUtils.putBoolean(this,StaticClass.SHARE_IS_FIRST,false);
        if(isFirst){
            return true;
        }else {
            return false;
        }
    }
    //j禁止返回
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();
    }
//初始化
    private void initView() {
        //延迟
        handler.sendEmptyMessageDelayed(StaticClass.HANDLER_TIEM,2000);
        mSplash = (TextView) findViewById(R.id.mSplash);
        //fonts
        UtilTools.setFont(this,mSplash);
    }
}
