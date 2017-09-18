package com.jlgproject.adapter.newDebt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.jlgproject.R;
import com.jlgproject.activity.NewDebt_Property;
import com.jlgproject.model.newDebt.ListItemMode;

import java.util.List;

/**
 * @author 王锋 on 2017/8/15.
 */

public class ZongAdapter extends BaseAdapter {


    private Context context;
    private List<com.jlgproject.model.newDebt.AssetNew> listFC;
    private NewDebt_Property newDebt_property;

    public void setNewDebt_property(NewDebt_Property newDebt_property) {
        this.newDebt_property = newDebt_property;
    }

    public ZongAdapter(Context context) {
        this.context = context;
    }

    public void setListFC(List<com.jlgproject.model.newDebt.AssetNew> listFC) {
        this.listFC = listFC;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listFC.size();
    }

    @Override
    public Object getItem(int i) {
        return listFC.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }



    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        ComViewHolder comViewHolder;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_list_zong, null);
            comViewHolder = new ComViewHolder();
            comViewHolder.name_dialog_z = (TextView) view.findViewById(R.id.name_dialog_z);
            comViewHolder.leixin_dialog_z = (TextView) view.findViewById(R.id.leixin_dialog_z);
            comViewHolder.jiazhi_dialog_z = (TextView) view.findViewById(R.id.jiazhi_dialog_z);
            comViewHolder.shuliang_dialog_z = (TextView) view.findViewById(R.id.shuliang_dialog_z);
            view.setTag(comViewHolder);
        } else {
            comViewHolder = (ComViewHolder) view.getTag();
        }

        comViewHolder.name_dialog_z.setText(listFC.get(i).getName());
        comViewHolder.leixin_dialog_z.setText(listFC.get(i).getModel());
        comViewHolder.jiazhi_dialog_z.setText(listFC.get(i).getTotalAmout());
        comViewHolder.shuliang_dialog_z.setText(listFC.get(i).getAssetNum());

        return view;
    }


    class ComViewHolder {
        TextView name_dialog_z, leixin_dialog_z, jiazhi_dialog_z, shuliang_dialog_z;
    }


}
