package com.example.jet.test.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.jet.test.R;
import com.example.jet.test.adapter.GirlAdapter;
import com.example.jet.test.entity.GirlData;
import com.example.jet.test.utils.L;
import com.example.jet.test.utils.StaticClass;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jet on 2018-07-21.
 */

public class girlFragment extends Fragment {
    private GridView mGridView;
    private List<GirlData>mList = new ArrayList<>();
    private GirlAdapter mAdapter;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_girl,null);

        findView(view);
        return view;
    }

    private void findView(View view) {
        mGridView =(GridView) view.findViewById(R.id.mGridView);
        //解析
        RxVolley.get(StaticClass.GIRL_URL, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                L.i("json:"+ t);
                //json
                parsingJson(t);
            }
        });
    }

    private void parsingJson(String t) {
        try {
            JSONObject jsonObject = new JSONObject(t);
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            for(int i=0;i < jsonArray.length();i++){
                JSONObject json = (JSONObject) jsonArray.get(i);
                String url = json.getString("url");

                GirlData data = new GirlData();
                data.setGirlUrl(url);
                mList.add(data);
            }
            //适配器
            mAdapter = new GirlAdapter(getActivity(),mList);
            mGridView.setAdapter(mAdapter);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}

