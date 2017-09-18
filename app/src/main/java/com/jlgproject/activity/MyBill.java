package com.jlgproject.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.jlgproject.R;
import com.jlgproject.adapter.Bill_Adapter;
import com.jlgproject.adapter.My_Bill_Adapter;
import com.jlgproject.base.BaseActivity;
import com.jlgproject.http.AddHeaders;
import com.jlgproject.http.BaseUrl;
import com.jlgproject.http.GetParmars;
import com.jlgproject.http.HttpMessageInfo;
import com.jlgproject.http.OkHttpUtils;
import com.jlgproject.model.Bill_Moldel_Bean;
import com.jlgproject.model.MyBillMode;
import com.jlgproject.util.ToastUtil;
import com.jlgproject.util.UserInfoState;
import com.jlgproject.view.HomeListView;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;

public class MyBill extends BaseActivity implements View.OnClickListener,PullToRefreshBase.OnRefreshListener2,HttpMessageInfo.IMessage {


    private ImageView title_left;
    private TextView title_name;
    private PullToRefreshScrollView listView;
    private int pn = 1;
    private int ps = 8;
    // 1.设置几个状态码方便我们进行状态的判断
    private static final int NORMAL = 0;
    //2.是刷新的状态
    private static final int REFRESH = 2;
    //3.上啦刷新加载更多
    private static final int LOADMORE = 3;
    private int status = 1;
    private List<MyBillMode.DataBean.ItemsBean> items;
    private LinearLayout mybill_content;

    @Override
    public int loadWindowLayout() {
        return R.layout.mybill_layout;
    }

    @Override
    public void initViews() {
        super.initViews();
        title_left = (ImageView) findViewById(R.id.iv_title_left);
        title_name = (TextView) findViewById(R.id.tv_title_name);
        title_left.setVisibility(View.VISIBLE);
        title_left.setImageResource(R.mipmap.back);
        title_left.setOnClickListener(this);
        title_name.setVisibility(View.VISIBLE);
        title_name.setText("我的账单");
        listView = (PullToRefreshScrollView) findViewById(R.id.listview_myBill);
        listView.setOnRefreshListener(this);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        mybill_content= (LinearLayout) findViewById(R.id.my_bill_content);
    }

    @Override
    protected void onResume() {
        super.onResume();
        request_Mybill();
        status = NORMAL;
    }

    private void request_Mybill() {
        if (ShowDrawDialog(this)){
            HttpMessageInfo<MyBillMode> info = new HttpMessageInfo<>(BaseUrl.MY_ZONGBILL,new MyBillMode());
            info.setiMessage(this);
            GetParmars parmars = new GetParmars();
            parmars.add("pn", pn);
            parmars.add("ps", ps);
            AddHeaders headers = new AddHeaders();
            headers.add("Authorization", UserInfoState.getToken());
            OkHttpUtils.getInstance().getServiceMassage(OkHttpUtils.TYPE_GET, info, parmars, headers,1);
        }
    }

    @Override
    public void onClick(View v) {
        if (v==title_left){
            finish();
        }
    }


    public void setLayoutData(MyBillMode.DataBean layoutData) {
        items=layoutData.getItems();
        if (status == NORMAL) {
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            for (int i = 0; i < items.size(); i++) {
                View head = LayoutInflater.from(this).inflate(R.layout.bill_heaed, null);
//                ImageView rili = (ImageView) head.findViewById(R.id.bii_date_image);
                TextView date = (TextView) head.findViewById(R.id.bill_date);
                date.setText(items.get(i).getMouthtime());

//                rili.setOnClickListener(new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View v) {
//                        ToastUtil.showShort(Billing_Details.this, "点击查看日历");
//                    }
//                });
//                if (i == 0) {
//                    rili.setVisibility(View.VISIBLE);
//                }

                View bodylistview = LayoutInflater.from(this).inflate(R.layout.bill_listveiw, null);
                HomeListView listview2 = (HomeListView) bodylistview.findViewById(R.id.bill_listview);
                listview2.addHeaderView(head);
                List<MyBillMode.DataBean.ItemsBean.MypackvoBean> mypackvo=items.get(i).getMypackvo();
                My_Bill_Adapter my_bill_adapter=new My_Bill_Adapter(this,mypackvo);
                listview2.setAdapter(my_bill_adapter);
                my_bill_adapter.notifyDataSetChanged();
                linearLayout.addView(bodylistview);
            }

            mybill_content.addView(linearLayout);
        }
        if (status == REFRESH) {
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            items.clear();
            items = layoutData.getItems();
            for (int i = 0; i < items.size(); i++) {
                View head = LayoutInflater.from(this).inflate(R.layout.bill_heaed, null);
//                ImageView rili = (ImageView) head.findViewById(R.id.bii_date_image);
                TextView date = (TextView) head.findViewById(R.id.bill_date);
                date.setText(items.get(i).getMouthtime());

//                rili.setOnClickListener(new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View v) {
//                        ToastUtil.showShort(Billing_Details.this, "点击查看日历");
//                    }
//                });
//                if (i == 0) {
//                    rili.setVisibility(View.VISIBLE);
//                }

                View bodylistview = LayoutInflater.from(this).inflate(R.layout.bill_listveiw, null);
                HomeListView listview2 = (HomeListView) bodylistview.findViewById(R.id.bill_listview);
                listview2.addHeaderView(head);
                My_Bill_Adapter adapter = new My_Bill_Adapter(this, items.get(i).getMypackvo());
                listview2.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                linearLayout.addView(bodylistview);
            }

            mybill_content.addView(linearLayout);
        }
        if (status == LOADMORE) {
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            List<MyBillMode.DataBean.ItemsBean> items2 = layoutData.getItems();

            for (int i = 0; i < items2.size(); i++) {
                View bodylistview = LayoutInflater.from(this).inflate(R.layout.bill_listveiw, null);
                HomeListView listview2 = (HomeListView) bodylistview.findViewById(R.id.bill_listview);
                My_Bill_Adapter adapter = new My_Bill_Adapter(this, items.get(i).getMypackvo());
                listview2.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                linearLayout.addView(bodylistview);
            }
            mybill_content.addView(linearLayout);
        }
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        status = REFRESH;
        pn=1;
        request_Mybill();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        status=LOADMORE;
        pn=pn+1;
        request_Mybill();
    }

    @Override
    public void getServiceData(Object o) {

        if(o instanceof MyBillMode){
            MyBillMode myBillMode= (MyBillMode) o;
            if(myBillMode!=null){
                DismissDialog();
                listView.onRefreshComplete();
                if(myBillMode.getState().equals("ok")){
                    setLayoutData(myBillMode.getData());
                }else{
                    ToastUtil.showShort(this,"服务器繁忙，请稍后再试");
                }
            }else{
                ToastUtil.showShort(this,"服务器繁忙，请稍后再试");
            }
        }

    }

    @Override
    public void getErrorData(Call call, IOException e) {
        DismissDialog();
        listView.onRefreshComplete();
        ToastUtil.showShort(this, "服务器繁忙，请稍后再试");

    }


}
