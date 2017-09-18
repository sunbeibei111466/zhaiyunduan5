package com.jlgproject.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jlgproject.R;
import com.jlgproject.adapter.Buness_Video_Adapter;
import com.jlgproject.base.BaseActivity;
import com.jlgproject.http.AddHeaders;
import com.jlgproject.http.BaseUrl;
import com.jlgproject.http.GetParmars;
import com.jlgproject.http.HttpMessageInfo;
import com.jlgproject.http.OkHttpUtils;
import com.jlgproject.model.Video_List_Bean;
import com.jlgproject.util.ToastUtil;
import com.jlgproject.util.UserInfoState;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;

public class Buness_Video_Player2 extends BaseActivity implements View.OnClickListener,PullToRefreshBase.OnRefreshListener2,HttpMessageInfo.IMessage{

    private TextView title;
    private ImageView title_left;
    private ImageView title_right;
    private PullToRefreshListView listview;
    private int pn = 1;
    private int ps = 8;
    // 1.设置几个状态码方便我们进行状态的判断
    private static final int NORMAL = 0;
    //2.是刷新的状态
    private static final int REFRESH = 2;
    //3.上啦刷新加载更多
    private static final int LOADMORE = 3;
    private int status = 1;
    private Video_List_Bean video_list_bean;
    private List<Video_List_Bean.DataBean.ItemsBean> items;
    private Buness_Video_Adapter adapter;

    @Override
    public int loadWindowLayout() {
        return R.layout.activity_buness__video__player2;
    }

    @Override
    public void initViews() {
        title = (TextView) findViewById(R.id.tv_title_name);
        title.setText("视频课程");
        title_left = (ImageView) findViewById(R.id.iv_title_left);
        title_left.setVisibility(View.VISIBLE);
        title_left.setOnClickListener(this);
        title_right = (ImageView) findViewById(R.id.iv_title_right2);
        title_right.setVisibility(View.VISIBLE);
        title_right.setOnClickListener(this);
        listview= (PullToRefreshListView) findViewById(R.id.listview);
        listview.setOnRefreshListener(this);
        listview.setMode(PullToRefreshBase.Mode.BOTH);
        status=NORMAL;
        requestVideoList();
    }

    @Override
    public void onClick(View v) {
        if (v == title_left) {
            finish();
        } else if (v == title_right) {
            startActivity(new Intent(this, Serch_Video.class));
        }
    }

    private void requestVideoList() {
        if (ShowDrawDialog(this)){
            HttpMessageInfo<Video_List_Bean> info = new HttpMessageInfo<>(BaseUrl.VIDEO_LIST,new Video_List_Bean());
            info.setiMessage(this);
            GetParmars parmars = new GetParmars();
            parmars.add("pn", pn);
            parmars.add("ps", ps);
            OkHttpUtils.getInstance().getServiceMassage(OkHttpUtils.TYPE_GET, info, parmars, null, 1);
        }
    }

    @Override
    public void getServiceData(Object o) {
        if (o instanceof Video_List_Bean) {
            video_list_bean = (Video_List_Bean) o;
            if(video_list_bean!=null){
                listview.onRefreshComplete();
                if (video_list_bean.getState().equals("ok")) {
                    DismissDialog();
                    displayData(video_list_bean);
                } else {
                    DismissDialog();
                    ToastUtil.showShort(this, video_list_bean.getMessage());
                }
            }
        }
    }


    //抽取的展示数据的方法
    private void displayData(final Video_List_Bean info) {

        if (info != null) {

            if (status == NORMAL) {
                items = info.getData().getItems();
                adapter = new Buness_Video_Adapter(this, items);
                listview.setAdapter(adapter);
            } else if (status == REFRESH) {
                items.clear();
                items = info.getData().getItems();
                adapter.setItems(items);
                adapter.notifyDataSetChanged();
            } else if (status == LOADMORE) {
                List<Video_List_Bean.DataBean.ItemsBean> items2 = info.getData().getItems();
                items.addAll(items2);
                adapter.setItems(items);
                adapter.notifyDataSetChanged();
            }
        }
    }


    @Override
    public void getErrorData(Call call, IOException e) {
        listview.setRefreshing(false);
        DismissDialog();


    }


    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        status = REFRESH;
        pn=1;
        requestVideoList();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        status=LOADMORE;
        pn=pn+1;
        requestVideoList();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        pn = 1;
        ps = 8;
    }


}
