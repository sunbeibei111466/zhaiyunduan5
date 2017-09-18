package com.jlgproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jlgproject.R;
import com.jlgproject.activity.The_Answer_Tu;
import com.jlgproject.base.BaseActivity;
import com.jlgproject.model.AnswerMode;
import com.jlgproject.util.ConstUtils;

import java.util.List;

/**
 * Created by sunbeibei on 2017/8/4.
 */

public class The_Answer_Adapter extends BaseAdapter {


    private List<AnswerMode.DataBean.ItemsBean> items;
    private Context context;

    public The_Answer_Adapter(Context context,List<AnswerMode.DataBean.ItemsBean> items) {
        this.context = context;
        this.items=items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public void setItems(List<AnswerMode.DataBean.ItemsBean> items) {
        this.items = items;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHoler_Answer answer;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.the_anwer_item, null);
            answer = new ViewHoler_Answer();
            answer.buju = (LinearLayout) convertView.findViewById(R.id.buju);
            answer.the_anwers = (TextView) convertView.findViewById(R.id.the_answers);
            convertView.setTag(answer);
        } else {
            answer = (ViewHoler_Answer) convertView.getTag();
        }

        answer.the_anwers.setText(items.get(position).getSubtitle());

        answer.buju.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, The_Answer_Tu.class);
                intent.putExtra(ConstUtils.DYJH_ID,items.get(position).getId());
                intent.putExtra("title",items.get(position).getSubtitle());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    public class ViewHoler_Answer {
        private LinearLayout buju;
        private TextView the_anwers;

    }
}
