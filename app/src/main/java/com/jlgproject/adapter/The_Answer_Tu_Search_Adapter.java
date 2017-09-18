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
import com.jlgproject.activity.Pic_Text_Details;
import com.jlgproject.model.Answer_Tu_Model;

import java.util.List;

/**
 * Created by sunbeibei on 2017/8/4.
 */

public class The_Answer_Tu_Search_Adapter extends BaseAdapter {


    private Context context;
    private List<Answer_Tu_Model.DataBean.ItemsBean> items;

    public The_Answer_Tu_Search_Adapter(Context context,List<Answer_Tu_Model.DataBean.ItemsBean> items) {
        this.context = context;
        this.items=items;
    }

    public void setItems(List<Answer_Tu_Model.DataBean.ItemsBean> items) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        VierHolder_Answer_Tu answer_tu ;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.the_answer_tu_item,null);
            answer_tu= new VierHolder_Answer_Tu();
            answer_tu.buju_tu= (LinearLayout) convertView.findViewById(R.id.buju_tu);
            answer_tu.the_answwer_tu = (TextView) convertView.findViewById(R.id.the_answers_tu);
            convertView.setTag(answer_tu);

        }else{
            answer_tu= (VierHolder_Answer_Tu) convertView.getTag();
        }
        answer_tu.the_answwer_tu.setText(items.get(position).getSubtitle());
        answer_tu.buju_tu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, Pic_Text_Details.class).putExtra("url",items.get(position).getUrl()));
            }
        });
        return convertView;
    }
    public class VierHolder_Answer_Tu{
        private LinearLayout buju_tu;
        private TextView the_answwer_tu;
    }
}
