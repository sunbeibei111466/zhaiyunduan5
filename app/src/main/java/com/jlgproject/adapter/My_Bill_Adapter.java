package com.jlgproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jlgproject.R;
import com.jlgproject.activity.UpdownPhotoAdapter;
import com.jlgproject.model.Bill_Moldel_Bean;
import com.jlgproject.model.MyBillMode;

import java.util.List;

/**
 * Created by sunbeibei on 2017/9/13.
 */

public class My_Bill_Adapter extends BaseAdapter {

    private List<MyBillMode.DataBean.ItemsBean.MypackvoBean> mypackvo;
    private Context context;

    public My_Bill_Adapter(Context context,List<MyBillMode.DataBean.ItemsBean.MypackvoBean> mypackvo) {
        this.context = context;
        this.mypackvo=mypackvo;
    }



    @Override
    public int getCount() {
        return mypackvo.size();
    }

    @Override
    public Object getItem(int position) {
        return mypackvo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderBill viewHolderBill;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.my_blii_adapter,null);
            viewHolderBill=new ViewHolderBill();
            viewHolderBill.image = (ImageView) convertView.findViewById(R.id.image_yue);
            viewHolderBill.week = (TextView) convertView.findViewById(R.id.week);
            viewHolderBill.day = (TextView) convertView.findViewById(R.id.day);
            viewHolderBill.money = (TextView) convertView.findViewById(R.id.money);
            viewHolderBill.type = (TextView) convertView.findViewById(R.id.type);
            viewHolderBill.pay_type= (TextView) convertView.findViewById(R.id.pay_type);
            convertView.setTag(viewHolderBill);
        }else {
             viewHolderBill= (ViewHolderBill) convertView.getTag();
        }
        MyBillMode.DataBean.ItemsBean.MypackvoBean mypackvoBean=mypackvo.get(position);
        Glide.with(context).load(mypackvoBean.getImage()).error(R.mipmap.logo).into(viewHolderBill.image);
        viewHolderBill.day.setText(mypackvoBean.getDayYearTime());//周几
        viewHolderBill.week.setText(mypackvoBean.getDayTime());//时间
        viewHolderBill.type.setText(mypackvoBean.getRemark());//内容
        viewHolderBill.money.setText(mypackvoBean.getAmount());//金额
        viewHolderBill.pay_type.setText(mypackvoBean.getProperty());
        return convertView;
    }

    public  class ViewHolderBill{
        private ImageView image;
        private TextView week, day, money, type,pay_type;
    }
}
