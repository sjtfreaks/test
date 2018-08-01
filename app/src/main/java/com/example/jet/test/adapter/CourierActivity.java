package com.example.jet.test.adapter;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jet.test.R;
import com.example.jet.test.entity.CourierData;
import com.example.jet.test.ui.BaseActivity;
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

public class CourierActivity extends BaseActivity implements View.OnClickListener {
    private EditText et_phone;
    private EditText et_mail;
    private Button bt_phone;
    private ListView list_phone;

    private List<CourierData>mList = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courier);

        initView();
    }
//初始化
    private void initView() {//修改
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_mail = (EditText) findViewById(R.id.et_mail);
        bt_phone = (Button) findViewById(R.id.bt_phone);
        bt_phone.setOnClickListener(this);
        list_phone = (ListView) findViewById(R.id.list_phone);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_phone:
                //get
                String number = et_phone.getText().toString().trim();
                String name = et_mail.getText().toString().trim();
                String url = "http://v.juhe.cn/exp/index?key="+StaticClass.COURIER_ID+"&com="+name+"&no="+number;
                //是否为空
                if(!TextUtils.isEmpty(name)){
                    RxVolley.get(url, new HttpCallback() {
                        @Override
                        public void onSuccess(String t) {
                            Toast.makeText(CourierActivity.this,t,Toast.LENGTH_SHORT).show();
                            L.i("json:"+t);
                            parsingJson(t);
                        }
                    });
                }else{
                    Toast.makeText(this,"输入框不得为空",Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
//解析数据
    private void parsingJson(String t) {
        try {
            JSONObject jsonObject = new JSONObject(t);
            JSONObject jsonResult = jsonObject.getJSONObject("result");
            JSONArray jsonArray = jsonResult.getJSONArray("list");
            for (int i = 0; i< jsonArray.length();i++){
               JSONObject json = (JSONObject) jsonArray.get(i);

                CourierData data = new CourierData();
                data.setDatetime(json.getString("datetime"));
                data.setRemark(json.getString("remark"));
                mList.add(data);
            }
            CourierAdapter adapter = new CourierAdapter(this,mList);
            list_phone.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
