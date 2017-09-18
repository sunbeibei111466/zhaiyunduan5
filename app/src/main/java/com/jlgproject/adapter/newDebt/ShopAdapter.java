package com.jlgproject.adapter.newDebt;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jlgproject.R;
import com.jlgproject.model.newDebt.Item;

import java.util.List;


/**
 * Created by yarolegovich on 07.03.2017.
 */

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {

    private List<com.jlgproject.model.newDebt.SonLevelListBean> data;
    private Context context;
    private OnClickImageViewListener onClickImageViewListener;

    public ShopAdapter(List<com.jlgproject.model.newDebt.SonLevelListBean> data, Context context) {
        this.data = data;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_shop_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(holder.itemView.getContext())
                .load(data.get(position).getUrl())
                .into(holder.image);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickImageViewListener.onClick(data.get(position).getPId(),data.get(position).getId(),data.get(position).getName());

            }
        });
        holder.textView.setText(data.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            textView= (TextView) itemView.findViewById(R.id.tv_title);
        }
    }


    public interface OnClickImageViewListener {

        void onClick(String erjiId, String sanjiId, String snajiStr);
    }

    public void setOnClickImageViewListener(OnClickImageViewListener onClickImageViewListener) {
        this.onClickImageViewListener = onClickImageViewListener;
    }
}
