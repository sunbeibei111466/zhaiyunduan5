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
import com.jlgproject.model.Debts_Maner_Details;
import com.nostra13.universalimageloader.utils.L;

import java.util.List;

/**
 * Created by sunbeibei on 2017/8/21.
 */

public class Debt_Demend_Adapter extends BaseAdapter {
    private Context context;

    public Debt_Demend_Adapter(Context context, List<Debts_Maner_Details.DataBean.DemandNewOneBean> demandNewOne) {
        this.context = context;
        this.demandNewOne = demandNewOne;
    }

    private List<Debts_Maner_Details.DataBean.DemandNewOneBean> demandNewOne;
    @Override
    public int getCount() {
        return demandNewOne.size();
    }

    @Override
    public Object getItem(int position) {
        return demandNewOne.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder_Demend viewHolder_demend;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.debt_demend_item,null);
            viewHolder_demend=new ViewHolder_Demend();
            viewHolder_demend.demend_image= (ImageView) convertView.findViewById(R.id.demend_image);
            viewHolder_demend.demend_text = (TextView) convertView.findViewById(R.id.demend_text);
            convertView.setTag(viewHolder_demend);
        }else{
            viewHolder_demend= (ViewHolder_Demend) convertView.getTag();
        }
        Debts_Maner_Details.DataBean.DemandNewOneBean demandNewOneBean = demandNewOne.get(position);
        Glide.with(context).load(demandNewOneBean.getUrl()).placeholder(R.mipmap.logo).into(viewHolder_demend.demend_image);
        viewHolder_demend.demend_text.setText(demandNewOneBean.getName());
        return convertView;
    }
    public class  ViewHolder_Demend{
        private ImageView demend_image;
        private TextView demend_text;
    }
}
