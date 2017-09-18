package com.jlgproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jlgproject.R;
import com.jlgproject.model.Bill_Moldel_Bean;

import java.util.List;

/**
 * Created by sunbeibei on 2017/8/23.
 */

public class Bill_Adapter extends BaseAdapter {
    private  Context context;
  private   List<Bill_Moldel_Bean.DataBean.ItemsBean.MypackvoBean> mypackvo;
    public Bill_Adapter(Context context, List<Bill_Moldel_Bean.DataBean.ItemsBean.MypackvoBean> mypackvo) {
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
        Child_Bill_ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.bill_list_item, null);
            viewHolder = new Child_Bill_ViewHolder();
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image_yue);
            viewHolder.week = (TextView) convertView.findViewById(R.id.week);
            viewHolder.day = (TextView) convertView.findViewById(R.id.day);
            viewHolder.money = (TextView) convertView.findViewById(R.id.money);
            viewHolder.type = (TextView) convertView.findViewById(R.id.type);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (Child_Bill_ViewHolder) convertView.getTag();
        }
        Bill_Moldel_Bean.DataBean.ItemsBean.MypackvoBean mypackvoBean = mypackvo.get(position);

//        Glide.with(context).load(mypackvo.get(position).getImage()).placeholder(R.mipmap.logo).into(viewHolder.image);
        Glide.with(context).load(mypackvo.get(position).getImage()).error(R.mipmap.logo).into(viewHolder.image);
        viewHolder.day.setText(mypackvoBean.getDayYearTime());//周几
        viewHolder.week.setText(mypackvoBean.getDayTime());//时间
        viewHolder.type.setText(mypackvoBean.getRemark());//内容
        viewHolder.money.setText(mypackvoBean.getAmount());//金额

        return convertView;
    }


    public class Child_Bill_ViewHolder {
        private ImageView image;
        private TextView week, day, money, type;
    }

}
