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
import com.jlgproject.model.Video_Information_Bean;
import com.jlgproject.model.Video_List_Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 王锋 on 2017/7/20.
 */

public class BusinessVideoPlayAdapter extends BaseAdapter {
    private Context context;
    private List<Video_Information_Bean.DataBean.RecommendDetailBean> items;
    private String img;
    private String url;

    public BusinessVideoPlayAdapter(Context context,  List<Video_Information_Bean.DataBean.RecommendDetailBean> items) {
        this.context = context;
        this.items = items;
    }

    public void setItems( List<Video_Information_Bean.DataBean.RecommendDetailBean> items) {
        this.items = items;
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
    public View getView(int position, View convertView, final ViewGroup parent) {
        VideoHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.sxy_other, null);
            vh = new VideoHolder();
            vh.iv_item_sxy2 = (ImageView) convertView.findViewById(R.id.iv_item_sxy2);
            vh.tv_title_sxy2 = (TextView) convertView.findViewById(R.id.tv_title_sxy2);
            vh.tv_jianjian_sxy2 = (TextView) convertView.findViewById(R.id.tv_jianjian_sxy2);
            vh.tv_jianshi = (TextView) convertView.findViewById(R.id.tv_jianshi);
            vh.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            convertView.setTag(vh);
        } else {
            vh = (VideoHolder) convertView.getTag();
        }

        Video_Information_Bean.DataBean.RecommendDetailBean recommendDetailBean = items.get(position);

        vh.tv_title_sxy2.setText( recommendDetailBean.getTitle());
        vh.tv_jianjian_sxy2.setText(recommendDetailBean.getBrief());
        vh.tv_time.setText(recommendDetailBean.getUpdateTime());
        vh.tv_jianshi.setText("讲师："+recommendDetailBean.getAuthor());
        Glide.with(context).load(recommendDetailBean.getImg()).placeholder(R.mipmap.logo).priority(Priority.HIGH).into(vh.iv_item_sxy2);

        return convertView;
    }

    public class VideoHolder {
        ImageView iv_item_sxy2;
        TextView tv_title_sxy2, tv_jianjian_sxy2, tv_jianshi, tv_time;
    }

}
