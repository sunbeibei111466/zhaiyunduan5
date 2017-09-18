package com.jlgproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CalendarView;
import android.widget.TextView;

import com.jlgproject.R;
import com.jlgproject.model.Debts_Maner_Details;
import com.jlgproject.view.AlwaysMarqueeTextView;

import java.util.List;

/**
 * Created by sunbeibei on 2017/8/21.
 */

public class Debt_Assert_Adapter extends BaseAdapter {
    private Context context;

    public Debt_Assert_Adapter(Context context, List<Debts_Maner_Details.DataBean.AssetBean> asset) {
        this.context = context;
        this.asset = asset;
    }

    private List<Debts_Maner_Details.DataBean.AssetBean> asset;
    @Override
    public int getCount() {
        return asset.size();
    }

    @Override
    public Object getItem(int position) {
        return asset.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder_Ass viewHolder_ass;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.debt_assert_list,null);
            viewHolder_ass=new ViewHolder_Ass();
            viewHolder_ass.number= (AlwaysMarqueeTextView) convertView.findViewById(R.id.ass_number);
            viewHolder_ass.ass_name= (AlwaysMarqueeTextView) convertView.findViewById(R.id.ass_name);
            viewHolder_ass.ass_type= (TextView) convertView.findViewById(R.id.ass_type);
            viewHolder_ass.guzhi= (AlwaysMarqueeTextView) convertView.findViewById(R.id.ass_guzhi);
            viewHolder_ass.shuliang= (AlwaysMarqueeTextView) convertView.findViewById(R.id.shuliang);
            convertView.setTag(viewHolder_ass);

        }else {
            viewHolder_ass= (ViewHolder_Ass) convertView.getTag();
        }
        Debts_Maner_Details.DataBean.AssetBean assetBean = asset.get(position);
        viewHolder_ass.number.setText(position+1+"");
        viewHolder_ass.ass_name.setText(assetBean.getName());
        viewHolder_ass.ass_type.setText(assetBean.getModel());
        viewHolder_ass.guzhi.setText(assetBean.getTotalAmout());
        viewHolder_ass.shuliang.setText(assetBean.getAssetNum());

        return convertView;
    }
    public class ViewHolder_Ass{
        private AlwaysMarqueeTextView number,ass_name,guzhi,shuliang;
        private TextView ass_type;
    }
}
