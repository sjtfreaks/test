package com.example.jet.test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jet.test.R;
import com.example.jet.test.entity.CourierData;

import java.util.List;

/**
 * Created by jet on 2018-07-21.
 */

public class CourierAdapter extends BaseAdapter{
    private Context mContext;
    private List<CourierData> mList;
    private CourierData data;
    //加载
    private LayoutInflater inflater;

    public CourierAdapter(Context mContext,List<CourierData> mList){
        this.mContext = mContext;
        this.mList = mList;
        //获取系统服务
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
            convertView = inflater.inflate(R.layout.layout_courier_item,null);
            viewHolder.tv_datetime = (TextView) convertView.findViewById(R.id.tv_datetime);
            viewHolder.tv_remark = (TextView) convertView.findViewById(R.id.tv_remark);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //设置数据
        data = mList.get(position);
        viewHolder.tv_datetime.setText(data.getDatetime());
        viewHolder.tv_remark.setText(data.getRemark());

        return convertView;
    }
    class ViewHolder{
        private TextView tv_datetime;
        private TextView tv_remark;

    }
}
