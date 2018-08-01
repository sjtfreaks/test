package com.example.jet.test.ui;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;

import com.example.jet.test.R;
import com.example.jet.test.utils.L;

/**
 * Created by jet on 2018-07-22.
 */

public class WebActivity1 extends BaseActivity {
private WebView mweb;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web1);

        initView();
    }
//初始化
    private void initView() {
        mweb = (WebView) findViewById(R.id.mweb);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        final String link = intent.getStringExtra("link");
        L.i("link"+link);
        //标题
        getSupportActionBar().setTitle(title);
        //加载网页
        //支持javascript
        mweb.getSettings().setJavaScriptEnabled(true);
        //支持缩放
        mweb.getSettings().setSupportZoom(true);
        //控制器
        mweb.getSettings().setBuiltInZoomControls(true);
        //mac port
        //mweb.setWebChromeClient(new WebViewClient());
        //loading web
        mweb.loadUrl(link);
        //本地显示
        mweb.setWebViewClient(new android.webkit.WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(link);
                return true;
            }
        });

    }
//    public class WebViewClient extends WebChromeClient{
//
//        //进度条
//        @Override
//        public void onProgressChanged(WebView view, int newProgress) {
//            super.onProgressChanged(view, newProgress);
//        }
//    }
}
