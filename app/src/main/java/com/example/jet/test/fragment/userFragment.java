package com.example.jet.test.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.jet.test.R;
import com.example.jet.test.adapter.CourierActivity;
import com.example.jet.test.ui.JieshaoActivity;
import com.example.jet.test.ui.WebActivity;
import com.example.jet.test.ui.WebActivity2;

/**
 * Created by jet on 2018-07-21.
 */

public class userFragment extends Fragment implements View.OnClickListener{

    private TextView courier;
    private Button bt_2zxing;
    private Button bt_map;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user,null);
        findView(view);
        return view;
    }
    //初始化
    private void findView(View view) {
        courier = (TextView) view.findViewById(R.id.courier);
        courier.setOnClickListener(this);
        bt_2zxing = (Button) view.findViewById(R.id.bt_2zxing);
        bt_2zxing.setOnClickListener(this);
        bt_map =(Button) view.findViewById(R.id.bt_map);
        bt_map.setOnClickListener(this);
    }

public void onClick(View v){
    switch (v.getId()){
        case R.id.courier:
            startActivity(new Intent(getActivity(),CourierActivity.class));
            break;
        case R.id.bt_2zxing:
            startActivity(new Intent(getActivity(), JieshaoActivity.class));
            break;
        case R.id.bt_map:
            startActivity(new Intent(getActivity(), WebActivity2.class));
            break;
    }
}

}












