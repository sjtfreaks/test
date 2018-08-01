package com.example.jet.test.entity;

/**
 * Created by jet on 2018-07-21.
 */

public class CourierData {
    //时间
    private String datetime;
    //地址
    private String remark;



    public String getDatetime (){return datetime;}
    public void setDatetime(String datetime){this.datetime = datetime;}

    public String getRemark (){return remark;}
    public void setRemark(String remark){this.remark = remark;}

    @Override
    public String toString() {
        return "CourierData{" +
                "datetime='" + datetime + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
