package com.example.jet.test.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jet.test.R;
import com.example.jet.test.adapter.HelloAdapter;
import com.example.jet.test.entity.HelloData;
import com.example.jet.test.ui.WebActivity;
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

public class helloFragment extends Fragment{
    private ListView mListView;
    private List<HelloData> mList = new ArrayList<>();

    private List<String> mListTitle = new ArrayList<>();
    private List<String> mListUrl = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hello,null);
        findView(view);
        return view;
    }

    private void findView(View view) {
        mListView =(ListView) view.findViewById(R.id.mListView);

        //解析 port
        String url = "http://v.juhe.cn/weixin/query?key="+ StaticClass.HELLO_ID;
        RxVolley.get(url, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                //Toast.makeText(getActivity(),t,Toast.LENGTH_SHORT).show();
                parsingJson(t);
                L.i("json:"+t);
            }
        });

        //点击趣事
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                L.i("position:"+position);
                Intent intent = new Intent(getActivity(), WebActivity.class);
                //2 way chuan zhi BUNdle

                intent.putExtra("title",mListTitle.get(position));
                intent.putExtra("url",mListUrl.get(position));
                startActivity(intent);
            }
        });
    }

//解析json
    private void parsingJson(String t) {
        try {
            JSONObject jsonObject = new JSONObject(t);
            JSONObject jsonResult = jsonObject.getJSONObject("result");
            JSONArray jsonList = jsonResult.getJSONArray("list");

            for (int i = 0;i<jsonList.length();i++){
                JSONObject json = (JSONObject) jsonList.get(i);
                HelloData data = new HelloData();
                //保存
                String title = json.getString("title");
                String url = json.getString("url");
                data.setSource(json.getString("source"));
                data.setImg_url(json.getString("firstImg"));
                data.setTitle(title);

                mListTitle.add(title);
                mList.add(data);
                mListUrl.add(url);

            }
            HelloAdapter adapter = new HelloAdapter(getActivity(), mList);
            mListView.setAdapter(adapter);
        }catch (JSONException e){
            e.printStackTrace();
        }

    }
}
