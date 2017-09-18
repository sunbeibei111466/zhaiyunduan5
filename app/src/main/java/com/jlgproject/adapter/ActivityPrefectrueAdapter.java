package com.jlgproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jlgproject.R;
import com.jlgproject.activity.NewsDetails;
import com.jlgproject.model.ActivityPrefectrueMode;
import com.jlgproject.util.ToastUtil;

import java.util.List;

/**
 * @author 王锋 on 2017/9/14.
 */

public class ActivityPrefectrueAdapter extends BaseAdapter {

    private Context context;
    private List<ActivityPrefectrueMode.DataBean.ItemsBean>  itemsBeanList;

    public ActivityPrefectrueAdapter(Context context, List<ActivityPrefectrueMode.DataBean.ItemsBean>  itemsBeanList) {
        this.context = context;
        this.itemsBeanList = itemsBeanList;
    }
    public ActivityPrefectrueAdapter(Context context) {
        this.context = context;
    }

    public void setItemsBeanList(List<ActivityPrefectrueMode.DataBean.ItemsBean> itemsBeanList) {
        this.itemsBeanList = itemsBeanList;
    }

    @Override
    public int getCount() {
        return itemsBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemsBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder comHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.sxy_other, null);
            comHolder = new ViewHolder();
            comHolder.iv_item_sxy2 = (ImageView) convertView.findViewById(R.id.iv_item_sxy2);
            comHolder.tv_title_sxy2 = (TextView) convertView.findViewById(R.id.tv_title_sxy2);
            comHolder.tv_jianjian_sxy2 = (TextView) convertView.findViewById(R.id.tv_jianjian_sxy2);
            comHolder.tv_jianshi = (TextView) convertView.findViewById(R.id.tv_jianshi);
            comHolder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            comHolder.buness_liner= (LinearLayout) convertView.findViewById(R.id.buness_liner);
            convertView.setTag(comHolder);
        } else {
            comHolder = (ViewHolder) convertView.getTag();
        }
        final ActivityPrefectrueMode.DataBean.ItemsBean itemsBean=itemsBeanList.get(position);
        Glide.with(context).load(itemsBean.getImg()).into(comHolder.iv_item_sxy2);
        comHolder.tv_title_sxy2.setText(itemsBean.getTitle());
        comHolder.tv_jianjian_sxy2.setText(itemsBean.getContent());
        if(TextUtils.isEmpty(itemsBean.getAuthor())){
            comHolder.tv_jianshi.setVisibility(View.GONE);
        }else{
            comHolder.tv_jianshi.setText(itemsBean.getAuthor());
    }
        comHolder.tv_time.setText(itemsBean.getUpdateTime());
        comHolder.buness_liner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, NewsDetails.class);
                intent.putExtra("url",itemsBean.getUrl());
                intent.putExtra("name",itemsBean.getTitle());
                context.startActivity(intent);

            }
        });

        return convertView;
    }

    class ViewHolder {
        LinearLayout buness_liner;
        ImageView iv_item_sxy2;
        TextView tv_title_sxy2, tv_jianjian_sxy2, tv_jianshi, tv_time;
    }
}
