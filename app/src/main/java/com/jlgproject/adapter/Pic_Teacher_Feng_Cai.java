package com.jlgproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.jlgproject.R;
import com.jlgproject.model.The_Teacher_Moldel;

import java.util.List;

/**
 * Created by sunbeibei on 2017/8/4.
 */

public class Pic_Teacher_Feng_Cai extends BaseAdapter {

    private String img;

    public Pic_Teacher_Feng_Cai(Context context, List<The_Teacher_Moldel.DataBean.ItemsBean> items) {
        this.context = context;
        this.items=items;
        notifyDataSetChanged();
    }

    private Context context;
    private  List<The_Teacher_Moldel.DataBean.ItemsBean> items;
    public   void  setItems(List<The_Teacher_Moldel.DataBean.ItemsBean> items){
        this.items=items;
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
        ViewHolder_Teacher viewHolder_teacher;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.the_teacher_feng_cai,null);
            viewHolder_teacher= new ViewHolder_Teacher();
            viewHolder_teacher.teacher_pic = (ImageView) convertView.findViewById(R.id.teacher_pic);
            viewHolder_teacher.teach_name= (TextView) convertView.findViewById(R.id.teacher_name);
            viewHolder_teacher.zhi_chen= (TextView) convertView.findViewById(R.id.zhi_chen);
            viewHolder_teacher.jieshao= (TextView) convertView.findViewById(R.id.teacher_jieshao);
            convertView.setTag(viewHolder_teacher);

        }else {
            viewHolder_teacher = (ViewHolder_Teacher) convertView.getTag();
        }
        The_Teacher_Moldel.DataBean.ItemsBean itemsBean = items.get(position);

        img = itemsBean.getImg();

         Glide.with(context).load(img).placeholder(R.mipmap.logo).priority(Priority.HIGH).into(viewHolder_teacher.teacher_pic);
          viewHolder_teacher.teach_name.setText(itemsBean.getTitle());
           viewHolder_teacher.zhi_chen.setText(itemsBean.getContent());
        viewHolder_teacher.jieshao.setText(itemsBean.getSubTitle());
        return convertView;
    }
    public  class  ViewHolder_Teacher{
        private ImageView teacher_pic;
        private TextView teach_name,zhi_chen,jieshao;
    }
}
