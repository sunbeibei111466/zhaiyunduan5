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
import com.jlgproject.R;
import com.jlgproject.activity.Buness_School2;
import com.jlgproject.activity.BusinessVideoPlay;
import com.jlgproject.activity.NewsDetails;
import com.jlgproject.model.Buness_School_Bean;
import com.jlgproject.util.ToastUtil;

import java.util.List;

/**
 * @author 王锋 on 2017/9/6.
 */

public class Buness_School2_Adapter extends BaseAdapter {
    private Context context;
    private List<Buness_School_Bean.DataBean.CoursesBean.CourseDetailsBean> leiBiaoLists;
    private String type;

    public Buness_School2_Adapter(Context context) {
        this.context = context;
    }

    public void setType(String type) {
        this.type = type;
    }


    public void setLeiBiaoLists(List<Buness_School_Bean.DataBean.CoursesBean.CourseDetailsBean> leiBiaoLists) {
        this.leiBiaoLists = leiBiaoLists;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return leiBiaoLists.size();
    }

    @Override
    public Object getItem(int position) {
        return leiBiaoLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        ComViewHolder comHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.sxy_other, null);
            comHolder = new ComViewHolder();
            comHolder.iv_item_sxy2 = (ImageView) convertView.findViewById(R.id.iv_item_sxy2);
            comHolder.tv_title_sxy2 = (TextView) convertView.findViewById(R.id.tv_title_sxy2);
            comHolder.tv_jianjian_sxy2 = (TextView) convertView.findViewById(R.id.tv_jianjian_sxy2);
            comHolder.tv_jianshi = (TextView) convertView.findViewById(R.id.tv_jianshi);
            comHolder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            comHolder.buness_liner= (LinearLayout) convertView.findViewById(R.id.buness_liner);
            convertView.setTag(comHolder);
        } else {
            comHolder = (ComViewHolder) convertView.getTag();
        }

        Glide.with(context).load(leiBiaoLists.get(position).getImg()).placeholder(R.mipmap.logo).into(comHolder.iv_item_sxy2);//图片
        comHolder.tv_title_sxy2.setText(leiBiaoLists.get(position).getTitle());//标题
        comHolder.tv_jianjian_sxy2.setText(leiBiaoLists.get(position).getBrief());
        comHolder.buness_liner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.equals("1")){
                    Intent intent = new Intent();
                    intent.putExtra("id", leiBiaoLists.get(position).getId());
                    intent.putExtra("url",leiBiaoLists.get(position).getUrl());
                    intent.setClass(context, BusinessVideoPlay.class);
                    context.startActivity(intent);
                }else if (type.equals("2")){
                    Intent intent = new Intent();
                    intent.putExtra("url", leiBiaoLists.get(position).getUrl());
                    intent.putExtra("name", leiBiaoLists.get(position).getTitle());
                    intent.setClass(context, NewsDetails.class);
                    context.startActivity(intent);
                }else if (type.equals("3")){
                    Intent intent = new Intent();
                    intent.putExtra("url", leiBiaoLists.get(position).getUrl());
                    intent.putExtra("name", leiBiaoLists.get(position).getTitle());
                    intent.setClass(context, NewsDetails.class);
                    context.startActivity(intent);
                }
            }
        });
        if (type.equals("1")) {
            comHolder.tv_jianshi.setText("讲师: "+leiBiaoLists.get(position).getAuthor());
        }else if(type.equals("2")){
            comHolder.tv_jianshi.setText("作者: "+leiBiaoLists.get(position).getAuthor());
        }else if(type.equals("3")){
            comHolder.tv_jianshi.setVisibility(View.GONE);
        }
        comHolder.tv_time.setText(leiBiaoLists.get(position).getUpdateTime());

        return convertView;

    }


    class ComViewHolder {
        LinearLayout buness_liner;
        ImageView iv_item_sxy2;
        TextView tv_title_sxy2, tv_jianjian_sxy2, tv_jianshi, tv_time;
    }
}
