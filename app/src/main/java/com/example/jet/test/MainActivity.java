package com.example.jet.test;

import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.jet.test.fragment.girlFragment;
import com.example.jet.test.fragment.helloFragment;
import com.example.jet.test.fragment.newFragment;
import com.example.jet.test.fragment.userFragment;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
//tab
    private TabLayout mTablayout;
    //view
    private ViewPager mViewPager;
    //title
    private List<String> mTitle;
    //Fragment
    private List<Fragment> mFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();

        //测试bugly-error功能
        //CrashReport.testJavaCrash();
    }
//初始化
    private void initData() {
        mTitle = new ArrayList<>();
        mTitle.add("新闻");
        mTitle.add("趣事");
        mTitle.add("美图");
        mTitle.add("小工具");

        mFragment = new ArrayList<>();
        mFragment.add(new newFragment());
        mFragment.add(new helloFragment());
        mFragment.add(new girlFragment());
        mFragment.add(new userFragment());
    }
    private void initView() {
        mTablayout = (TabLayout) findViewById(R.id.mTablayout);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        //预加载
        mViewPager.setOffscreenPageLimit(mFragment.size());
        //监听

        //设置适配器
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            //选中的item
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }
            //返回item个数
            @Override
            public int getCount() {
                return mFragment.size();
            }
            //title
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitle.get(position);
            }
        });
        //绑定
        mTablayout.setupWithViewPager(mViewPager);
    }
}
