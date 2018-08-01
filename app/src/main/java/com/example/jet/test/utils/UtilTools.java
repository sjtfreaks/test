package com.example.jet.test.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;


/**
 * Created by jet on 2018-07-21.
 * 工具
 */

public class UtilTools {
    //设置字体
    public static void setFont(Context mContext, TextView textview){
        Typeface fonttype = Typeface.createFromAsset(mContext.getAssets(),"fonts/font2.ttf");
        textview.setTypeface(fonttype);
    }
}
