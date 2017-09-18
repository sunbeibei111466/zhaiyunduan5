package com.jlgproject.adapter.newDebt;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.jlgproject.R;
import com.jlgproject.activity.NewDebt_Demand;
import com.jlgproject.model.newDebt.Xu;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sunbeibei on 2017/8/15.
 */

public class ImageAdapter4 extends BaseAdapter {
    private boolean isChice[];
    private Context context;
    List<com.jlgproject.model.newDebt.SonLevelListBean> xuList;
    private Map<Integer, String> map;
    private List<String> list;


    public ImageAdapter4(Context context, List<com.jlgproject.model.newDebt.SonLevelListBean> xuList) {
        this.xuList = xuList;
        this.context = context;
        this.map = new HashMap<>();
        isChice = new boolean[xuList.size()];
        for (int i = 0; i < xuList.size(); i++) {
            isChice[i] = false;
        }

    }
    public List<String> getList() {
        return list;
    }


    @Override
    public int getCount() {
        return xuList.size();
    }


    @Override
    public Object getItem(int position) {
        return xuList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View converView, ViewGroup arg2) {

        GetView getView;
        if (converView == null) {
            converView = LayoutInflater.from(context).inflate(R.layout.list_item_demand, null);
            getView = new GetView();
            getView.imageView = (ImageView) converView.findViewById(R.id.fuxuan);
            getView.type = (TextView) converView.findViewById(R.id.type);
            converView.setTag(getView);
        } else {
            getView = (GetView) converView.getTag();
        }
        Log.e("路径的大小", xuList.size() + "");
        getView.imageView.setImageDrawable(getView(position));
        getView.type.setText(xuList.get(position).getName());
        getView.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean b = chiceState(position);
                if (b) {//选中状态
                    map.put(position, xuList.get(position).getId());
                } else {
                    if (map != null) {
                        map.remove(position);
                    }
                }
                list = new ArrayList<String>(map.values());
                for (int i = 0; i < list.size(); i++) {
                    Log.e("********--voleu---*****", list.get(i) + "");
                }
                Log.e("********", "*******************************");
                for (String a : map.values()) {
                    Log.e("=====voleu", a + "");
                }

            }
        });
        return converView;
    }


    static class GetView {
        ImageView imageView;
        TextView type;
    }


    public Bitmap returnBitMap(String url) {
        URL myFileUrl = null;
        Bitmap bitmap = null;
        try {
            myFileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;


       /* final Bitmap[] bm = new Bitmap[1];
        Glide.with(context).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                bm[0]=resource;
            }
        });
        return bm[0];*/
    }


    //主要就是下面的代码了
    private LayerDrawable getView(int post) {
        Bitmap bitmap2 = null;
        LayerDrawable la = null;
        // Bitmap   bitmap = getBitmap(image.get(post));
        Bitmap bitmap = returnBitMap(xuList.get(post).getUrl());
        // Bitmap   bitmap = BitmapFactory.decodeResource(context.getResources(), image.get(post));
        if (isChice[post] == true) {
            bitmap2 = BitmapFactory.decodeResource(context.getResources(), R.mipmap.duiguo);
        }
        if (bitmap2 != null) {
            BitmapDrawable[] array = new BitmapDrawable[2];
            array[0] = new BitmapDrawable(bitmap);
            array[1] = new BitmapDrawable(bitmap2);
            la = new LayerDrawable(array);
            la.setLayerInset(0, 0, 0, 0, 0);   //第几张图离各边的间距
            la.setLayerInset(1, 0, 0, 0, 0);
        } else {
            Drawable[] array = new Drawable[1];
            array[0] = new BitmapDrawable(bitmap);
            la = new LayerDrawable(array);
            la.setLayerInset(0, 0, 0, 0, 0);
        }
        return la; // 返回叠加后的图
    }

    public boolean chiceState(int post) {
        isChice[post] = isChice[post] == true ? false : true;
        this.notifyDataSetChanged();
        Log.e("放回数据=======", isChice[post] + "");
        return isChice[post];
    }


}

