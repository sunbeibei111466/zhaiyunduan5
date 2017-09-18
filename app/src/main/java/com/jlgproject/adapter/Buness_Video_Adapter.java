package com.jlgproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.jlgproject.R;
import com.jlgproject.activity.BusinessVideoPlay;
import com.jlgproject.model.Video_List_Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunbeibei on 2017/7/18.
 */

public class Buness_Video_Adapter extends BaseAdapter {
    private Context context;
    private List<Video_List_Bean.DataBean.ItemsBean> items;
    private String url;

    public Buness_Video_Adapter(Context context, List<Video_List_Bean.DataBean.ItemsBean> items) {
        this.context = context;
        this.items = items;
        notifyDataSetChanged();
    }

    public void setItems(List<Video_List_Bean.DataBean.ItemsBean> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VideoHolder viewHodle;
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.sxy_other, null);
            viewHodle = new VideoHolder();
            viewHodle .iv_item_sxy2 = (ImageView) convertView.findViewById(R.id.iv_item_sxy2);
            viewHodle .tv_title_sxy2 = (TextView) convertView.findViewById(R.id.tv_title_sxy2);
            viewHodle .tv_jianjian_sxy2 = (TextView) convertView.findViewById(R.id.tv_jianjian_sxy2);
            viewHodle .tv_jianshi = (TextView) convertView.findViewById(R.id.tv_jianshi);
            viewHodle .tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            viewHodle .buness_liner= (LinearLayout) convertView.findViewById(R.id.buness_liner);
            convertView.setTag(viewHodle);
        } else {
            viewHodle = (VideoHolder) convertView.getTag();
        }
        final Video_List_Bean.DataBean.ItemsBean itemsBean = items.get(position);
        url = itemsBean.getUrl();
        Glide.with(context).load(itemsBean.getImg()).priority(Priority.HIGH).centerCrop().fitCenter().into(viewHodle.iv_item_sxy2);
        viewHodle.tv_title_sxy2.setText(itemsBean.getTitle());
        viewHodle.tv_jianjian_sxy2.setText(itemsBean.getBrief());
        viewHodle.tv_time.setText(itemsBean.getUpdateTime());
        viewHodle.tv_jianshi.setText("讲师："+itemsBean.getAuthor());
        viewHodle.buness_liner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, BusinessVideoPlay.class).putExtra("id",itemsBean.getId()).putExtra("url",itemsBean.getUrl()));
            }
        });

        return convertView;
    }


}

class VideoHolder {
    LinearLayout buness_liner;
    ImageView iv_item_sxy2;
    TextView tv_title_sxy2, tv_jianjian_sxy2, tv_jianshi, tv_time;
}