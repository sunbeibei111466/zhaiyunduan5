package com.jlgproject.adapter.newDebt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.jlgproject.R;
import com.jlgproject.activity.NewDebt_Property;
import com.jlgproject.model.newDebt.ListItemMode;
import com.jlgproject.view.AlwaysMarqueeTextView;

import java.util.List;

/**
 * @author 王锋 on 2017/8/21.
 */

public class BaseAdapter_jl extends BaseAdapter {


    private Context context;
    private List<com.jlgproject.model.newDebt.AssetNew> listFC;
    private NewDebt_Property newDebt_property;

    public BaseAdapter_jl(Context context) {
        this.context = context;
    }

    public void setListFC(List<com.jlgproject.model.newDebt.AssetNew> listFC) {
        this.listFC = listFC;
    }

    public void setNewDebt_property(NewDebt_Property newDebt_property) {
        this.newDebt_property = newDebt_property;
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
            view = LayoutInflater.from(context).inflate(R.layout.item_list_company, null);
            comViewHolder = new ComViewHolder();
            comViewHolder.name_dialog = (TextView) view.findViewById(R.id.name_dialog);
            comViewHolder.leixin_dialog = (TextView) view.findViewById(R.id.leixin_dialog);
            comViewHolder.jiazhi_dialog = (TextView) view.findViewById(R.id.jiazhi_dialog);
            comViewHolder.shuliang_dialog = (TextView) view.findViewById(R.id.shuliang_dialog);
            comViewHolder.delete_dialog = (LinearLayout) view.findViewById(R.id.delete_dialog);
            view.setTag(comViewHolder);
        } else {
            comViewHolder = (ComViewHolder) view.getTag();
        }

//        final List<ListItemMode> listItemModes=listFC.get(i).getListItemModes();
        comViewHolder.name_dialog.setText(listFC.get(i).getName());
        comViewHolder.leixin_dialog.setText(listFC.get(i).getModel());
        comViewHolder.jiazhi_dialog.setText(listFC.get(i).getTotalAmout());
        comViewHolder.shuliang_dialog.setText(listFC.get(i).getAssetNum());
        comViewHolder.delete_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newDebt_property.onClickDeleteItem(listFC.get(i).getErjiId(),listFC.get(i).getIdasset(),listFC.get(i).getModel(),i);
            }
        });

        return view;
    }


    class ComViewHolder {
        TextView name_dialog,jiazhi_dialog, shuliang_dialog;
        LinearLayout delete_dialog;
        TextView leixin_dialog;
    }

}
