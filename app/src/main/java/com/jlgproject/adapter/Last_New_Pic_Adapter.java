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
 * Created by sunbeibei on 2017/9/12.
 */

public class Last_New_Pic_Adapter extends BaseAdapter {

    private Context context;
    private List<ActivityPrefectrueMode.DataBean.ItemsBean> itemsBeanList;

    public Last_New_Pic_Adapter(Context context) {
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewViewHolder newViewHolder;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.last_pic_item,null);
            newViewHolder=new NewViewHolder();
            newViewHolder.iv_item_sxy2 = (ImageView) convertView.findViewById(R.id.iv_item_sxy2);
            newViewHolder.tv_title_sxy2 = (TextView) convertView.findViewById(R.id.tv_title_sxy2);
            newViewHolder.tv_jianjian_sxy2 = (TextView) convertView.findViewById(R.id.tv_jianjian_sxy2);
            newViewHolder.tv_jianshi = (TextView) convertView.findViewById(R.id.tv_jianshi);
            newViewHolder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            newViewHolder.buness_liner= (LinearLayout) convertView.findViewById(R.id.buness_liner);
            convertView.setTag(newViewHolder);
        }else {
            newViewHolder= (NewViewHolder) convertView.getTag();
        }

        final ActivityPrefectrueMode.DataBean.ItemsBean itemsBean=itemsBeanList.get(position);
        Glide.with(context).load(itemsBean.getImg()).into(newViewHolder.iv_item_sxy2);
        newViewHolder.tv_title_sxy2.setText(itemsBean.getTitle());
        newViewHolder.tv_jianjian_sxy2.setText(itemsBean.getContent());
        if(TextUtils.isEmpty(itemsBean.getAuthor())){
            newViewHolder.tv_jianshi.setVisibility(View.GONE);
        }else{
            newViewHolder.tv_jianshi.setText(itemsBean.getAuthor());
        }
        newViewHolder.tv_time.setText(itemsBean.getUpdateTime());
        newViewHolder.buness_liner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//查看详情
                Intent intent=new Intent(context, NewsDetails.class);
                intent.putExtra("url",itemsBean.getUrl());
                intent.putExtra("name",itemsBean.getTitle());
                context.startActivity(intent);
            }
        });

        return convertView;
    }
    class NewViewHolder {
        LinearLayout buness_liner;
        ImageView iv_item_sxy2;
        TextView tv_title_sxy2, tv_jianjian_sxy2, tv_jianshi, tv_time;
    }
}
