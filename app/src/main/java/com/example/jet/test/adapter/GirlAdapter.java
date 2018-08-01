package com.example.jet.test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.jet.test.R;
import com.example.jet.test.entity.GirlData;
import com.example.jet.test.utils.PicassoUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by jet on 2018-07-27.
 */

public class GirlAdapter extends BaseAdapter {

    private Context mContext;
    private List<GirlData>mList;
    private LayoutInflater inflater;
    private GirlData data;
    //图片
    private WindowManager windowManager;
    private int width;

    public GirlAdapter (Context mContext,List<GirlData>mList){
        this.mContext = mContext;
        this.mList = mList;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //get系统服务
        windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        //获取屏幕宽度
        width = windowManager.getDefaultDisplay().getWidth();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.girl_item,null);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            //保存缓存
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //设置数据
        data = mList.get(position);
        String url = data.getGirlUrl();
        //封装
        PicassoUtils.loadImageViewSize(mContext,url,viewHolder.imageView,width/2,700);

        return convertView;
    }

    class ViewHolder{
        private ImageView imageView;
    }
}
