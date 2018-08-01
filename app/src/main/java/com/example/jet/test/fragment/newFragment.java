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
import com.example.jet.test.adapter.NewAdapter;
import com.example.jet.test.entity.NewData;
import com.example.jet.test.ui.WebActivity;
import com.example.jet.test.ui.WebActivity1;
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

public class newFragment extends Fragment{
    private ListView mListView;
    private List<NewData>mList = new ArrayList<>();
    private List<String> mListTitle = new ArrayList<>();
    private List<String> mListUrl = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new,null);

        findView(view);
        return view;
    }

    private void findView(View view) {

        mListView = (ListView) view.findViewById(R.id.mListView);
        //json解析
        String url = StaticClass.NEW_URL;

        RxVolley.get(url, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                //Toast.makeText(getActivity(),t,Toast.LENGTH_SHORT).show();
                L.i("json:"+t);
                parsingJson(t);
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                L.i("position:"+position);
                Intent intent = new Intent(getActivity(), WebActivity1.class);
                //2 way chuan zhi BUNdle

                intent.putExtra("title",mListTitle.get(position));
                intent.putExtra("link",mListUrl.get(position));
                startActivity(intent);
            }
        });
    }

    private void parsingJson(String t) {
        try {
            JSONObject jsonObject = new JSONObject(t);
            JSONObject jsonResult = jsonObject.getJSONObject("data");
            JSONArray jsonList = jsonResult.getJSONArray("tech");

            for(int i = 0; i<jsonList.length();i++){
                JSONObject json = (JSONObject) jsonList.get(i);
                NewData data = new NewData();

                String title = json.getString("title");
                String link = json.getString("link");
                data.setSource(json.getString("source"));
                data.setTitle(title);
                mListTitle.add(title);

                mList.add(data);
                mListUrl.add(link);

            }
            NewAdapter adapter = new NewAdapter(getActivity(),mList);
            mListView.setAdapter(adapter);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
