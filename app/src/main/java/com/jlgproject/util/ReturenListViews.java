package com.jlgproject.util;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jlgproject.R;
import com.jlgproject.activity.NewsDetails;
import com.jlgproject.model.Buness_School_Bean;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;

/**
 * Created by sunbeibei on 2017/9/13.
 */

public class ReturenListViews {

    public   static  ArrayList<View>  getListViews(final Context context, List<Buness_School_Bean.DataBean.ForeshowsBean> foreshows){
        ArrayList<View> views=new ArrayList<>();
        for (int i = 0; i <foreshows.size() ; i++) {
            //设置滚动的单个布局
            final Buness_School_Bean.DataBean.ForeshowsBean foreshowsBean = foreshows.get(i);
            LinearLayout moreView = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.item, null);
            TextView tv = (TextView) moreView.findViewById(R.id.title);
            tv.setText(foreshowsBean.getTitle());
            TextView year= (TextView) moreView.findViewById(R.id.year);
            TextView teacher= (TextView) moreView.findViewById(R.id.teacher);
            year.setText(foreshowsBean.getUpdateTime());

            teacher.setText(foreshowsBean.getAuthor());
            views.add(moreView);
            moreView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (foreshowsBean!=null){
                        Intent intent = new Intent();
                        intent.putExtra("url",  foreshowsBean.getUrl());
                        intent.putExtra("name",foreshowsBean.getTitle());
                        intent.setClass(context, NewsDetails.class);
                        context.startActivity(intent);
                    }

                }
            });

        }
        return views;
    }
}
