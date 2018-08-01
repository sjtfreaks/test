package com.example.jet.test.ui;

/**
 * Created by jet on 2018-07-31.
 */
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.jet.test.MainActivity;
import com.example.jet.test.R;
import com.example.jet.test.entity.MyUser;
import com.example.jet.test.utils.ShareUtils;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    //注册
    private Button bt_registered;
    private Button bt_login;
    private TextView ui_login;
    private EditText et_name;
    private EditText et_password1;
    private CheckBox keep_password;
    private TextView textview_forget;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }
    private void initView(){
        bt_registered = (Button) findViewById(R.id.bt_registered);
        bt_registered.setOnClickListener(this);
        et_name = (EditText) findViewById(R.id.et_name);
        et_password1 = (EditText) findViewById(R.id.et_password1);
        bt_login = (Button) findViewById(R.id.bt_login);
        bt_login.setOnClickListener(this);
        keep_password = (CheckBox) findViewById(R.id.keep_password);
        textview_forget = (TextView) findViewById(R.id.textview_forget);
        textview_forget.setOnClickListener(this);
        //选中状态
        boolean isCheck = ShareUtils.getBoolean(this,"keeppass",false);
        keep_password.setChecked(isCheck);
        if (isCheck){
            //设置密码
            et_name.setText(ShareUtils.getString(this,"name",""));
            et_password1.setText(ShareUtils.getString(this,"password",""));
        }


        ui_login = (TextView) findViewById(R.id.ui_login);
        //字体
        Typeface fontType = Typeface.createFromAsset(getAssets(),"fonts/font2.ttf");
        ui_login.setTypeface(fontType);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_registered:
                startActivity(new Intent(this,RegisteredActivity.class));
                break;
            case R.id.textview_forget:
                startActivity(new Intent(this,ForgetPasswordActivity.class));
                break;
            case R.id.bt_login:
                //获取输入值
                String name = et_name.getText().toString().trim();
                String password = et_password1.getText().toString().trim();
                //判断是否为空
                if(!TextUtils.isEmpty(name)&!TextUtils.isEmpty(password)){
                    //登录
                    final MyUser user = new MyUser();
                    user.setUsername(name);
                    user.setPassword(password);
                    user.login(new SaveListener<MyUser>() {
                        @Override
                        public void done(MyUser myUser, BmobException e) {
                            //判断user和pass
                            if (e== null){
                                //邮箱是否验证
//                                if (user.getEmailVerified()){
                                    //跳转
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    finish();
//                                }else{
//                                    Toast.makeText(LoginActivity.this,"登陆失败,失败原因：邮箱未验证",Toast.LENGTH_SHORT).show();
//                                }
                            }else {
                                Toast.makeText(LoginActivity.this,"登陆失败,失败原因："+e.toString(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(this,"输入框不能为空",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //保存
        ShareUtils.putBoolean(this,"keeppass",keep_password.isChecked());
        //是否记住
        if (keep_password.isChecked()){
            //记住密码
            ShareUtils.putString(this,"name",et_name.getText().toString().trim());
            ShareUtils.putString(this,"password",et_password1.getText().toString().trim());
        }else{
            ShareUtils.deleShare(this,"name");
            ShareUtils.deleShare(this,"password");
        }
    }
}
