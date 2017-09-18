package com.jlgproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jlgproject.R;
import com.jlgproject.model.Buness_School_Bean;
import com.jlgproject.view.JDAdverView;

import java.util.List;

/**
 * Created by sunbeibei on 2017/9/6.
 */

public class JDViewAdapter {
    private List<Buness_School_Bean.DataBean.ForeshowsBean> foreshows;
    private Context context;

    public JDViewAdapter(Context context, List<Buness_School_Bean.DataBean.ForeshowsBean> foreshows) {
        this.foreshows = foreshows;
        this.context = context;
        if (foreshows == null || foreshows.isEmpty()) {
            throw new RuntimeException("nothing to show");
        }
    }

    /**
     * 获取数据的条数
     *
     * @return
     */
    public int getCount() {
        return foreshows == null ? 0 : foreshows.size();
    }

    /**
     * 获取摸个数据
     *
     * @param position
     * @return
     */
    public Buness_School_Bean.DataBean.ForeshowsBean getItem(int position) {
        return foreshows.get(position);
    }

    /**
     * 获取条目布局
     *
     * @param parent
     * @return
     */
    public View getView(JDAdverView parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null);
    }

    /**
     * 条目数据适配
     *
     * @param view
     * @param data
     */
    public void setItem(final View view, final Buness_School_Bean.DataBean.ForeshowsBean data) {
        TextView tv = (TextView) view.findViewById(R.id.title);
        tv.setText(data.getTitle());
        TextView year = (TextView) view.findViewById(R.id.year);
        TextView teacher = (TextView) view.findViewById(R.id.teacher);
        year.setText(data.getUpdateTime());
        teacher.setText(data.getAuthor());
        //你可以增加点击事件
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //比如打开url
                Toast.makeText(view.getContext(), data.getAuthor(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
