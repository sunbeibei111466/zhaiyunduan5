package com.jlgproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jlgproject.R;
import com.jlgproject.model.Asserts_Infromations_new;
import com.jlgproject.model.AssetNew;

import java.util.List;

/**
 * Created by sunbeibei on 2017/5/7.
 */

public class Asset_Information_Adapter extends BaseAdapter  {

    public OnClickUpdata onClickUpdata;
    private Context context;
    List<Asserts_Infromations_new.DataBean.ItemsBean> items;
    private Long id;

    public Asset_Information_Adapter(Context context) {
        this.context = context;
    }

    public List<Asserts_Infromations_new.DataBean.ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<Asserts_Infromations_new.DataBean.ItemsBean> items) {
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
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_asset_item_new, null);
            vh = new ViewHolder();
            vh.image_text_asset= (TextView) convertView.findViewById(R.id.image_text_asset);
            vh.tv_asset_name= (TextView) convertView.findViewById(R.id.tv_asset_name);
            vh.tv_asset_leixin= (TextView) convertView.findViewById(R.id.tv_asset_leixin);
            vh.tv_asset_guzhi= (TextView) convertView.findViewById(R.id.tv_asset_guzhi);
            vh.tv_asset_shulian= (TextView) convertView.findViewById(R.id.tv_asset_shulian);
            vh.tv_asset_bianji= (TextView) convertView.findViewById(R.id.tv_asset_bianji);

            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.image_text_asset.setText(position+1+"");
        vh.tv_asset_name.setText(items.get(position).getName());
        vh.tv_asset_leixin.setText(items.get(position).getModel());
        vh.tv_asset_guzhi.setText(items.get(position).getTotalAmout());
        vh.tv_asset_shulian.setText(items.get(position).getAssetNum());

        vh.tv_asset_bianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AssetNew assetNew=new AssetNew();
                assetNew.setId(items.get(position).getId());
                assetNew.setName(items.get(position).getName());
                assetNew.setModel(items.get(position).getModel());
                assetNew.setAssetNum(items.get(position).getAssetNum()+"");
                assetNew.setTotalAmout(items.get(position).getTotalAmout());
                onClickUpdata.clickUpdata(assetNew);

            }
        });

        return convertView;
    }




    public interface OnClickUpdata{
        void clickUpdata(AssetNew assetNew);
    }

    public void setOnClickUpdata(OnClickUpdata onClickUpdata) {
        this.onClickUpdata = onClickUpdata;
    }

    public class ViewHolder {//资产名称，数量，总价值，流通价值；删除，编辑

        private TextView tv_asset_name,tv_asset_leixin,tv_asset_guzhi,tv_asset_shulian,tv_asset_bianji,image_text_asset;
    }
}
