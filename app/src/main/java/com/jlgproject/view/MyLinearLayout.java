package com.jlgproject.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jlgproject.R;

/**
 * @author 王锋 on 2017/9/6.
 */

public class MyLinearLayout extends LinearLayout {

    private ImageView image;
    private TextView text;
    private Context context;


    public MyLinearLayout(Context context) {
        this(context,null);
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        LayoutInflater.from(context).inflate(R.layout.sxy_shiping, this);
        init();
    }

    private void init() {
        image= (ImageView) findViewById(R.id.tv_src);
        text= (TextView) findViewById(R.id.tv_leibie);

    }

    public void setImage(String src) {
//        image.setImageResource(src);
        Glide.with(context).load(src).into(image);
    }

    public void setText(String str) {
       text.setText(str);
    }
}
