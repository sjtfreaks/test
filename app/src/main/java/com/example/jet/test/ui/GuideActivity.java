package com.example.jet.test.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jet.test.MainActivity;
import com.example.jet.test.R;
import com.example.jet.test.utils.UtilTools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jet on 2018-07-21.
 */

public class GuideActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager mViewPager;
    private TextView ui_guide;
    //容器
    private List<View> mList = new ArrayList<>();
    private View view1,view2,view3;
    //圆点
    private ImageView point1,point2,point3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
    }
    private void initView(){
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        view1 = View.inflate(this,R.layout.pager_item_one,null);
        view2 = View.inflate(this,R.layout.pager_item_two,null);
        view3 = View.inflate(this,R.layout.pager_item_three,null);

        mList.add(view1);
        mList.add(view2);
        mList.add(view3);

        point1 = (ImageView) findViewById(R.id.point_one);
        point2 = (ImageView) findViewById(R.id.point_two);
        point3 = (ImageView) findViewById(R.id.point_three);

        setPointImg(true,false,false);
        //监听滑动
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                //L.i("position"+position);
                switch (position){
                    case 0:
                        setPointImg(true,false,false);
                        break;
                    case 1:
                        setPointImg(false,true,false);
                        break;
                    case 2:
                        setPointImg(false,false,true);
                        break;
                }
            }
        });
        view3.findViewById(R.id.bt_start).setOnClickListener(this);

        mViewPager.setAdapter(new GuideAdapter());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_start:
                startActivity(new Intent(this,LoginActivity.class));
                finish();
                break;
        }
    }

    class GuideAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mList.size();
        }
        public boolean isViewFromObject(View view,Object object){
            return view == object;
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((ViewPager)container).addView(mList.get(position));
            return mList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager)container).removeView(mList.get(position));
            //super.destroyItem(container, position, object);
        }
    }
    private void setPointImg(boolean isCheck1,boolean isCheck2,boolean isCheck3){
        if(isCheck1){
            point1.setBackgroundResource(R.drawable.point_on);
        }else {
            point1.setBackgroundResource(R.drawable.point_off);
        }
        if(isCheck2){
            point2.setBackgroundResource(R.drawable.point_on);
        }else {
            point2.setBackgroundResource(R.drawable.point_off);
        }
        if(isCheck3){
            point3.setBackgroundResource(R.drawable.point_on);
        }else {
            point3.setBackgroundResource(R.drawable.point_off);
        }
    }
}
