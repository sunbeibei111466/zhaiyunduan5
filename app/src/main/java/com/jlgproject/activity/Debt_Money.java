package com.jlgproject.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.jlgproject.R;
import com.jlgproject.adapter.Bill_Adapter;
import com.jlgproject.base.BaseActivity;
import com.jlgproject.http.AddHeaders;
import com.jlgproject.http.BaseUrl;
import com.jlgproject.http.GetParmars;
import com.jlgproject.http.HttpMessageInfo;
import com.jlgproject.http.OkHttpUtils;
import com.jlgproject.model.Bill_Moldel_Bean;
import com.jlgproject.util.L;
import com.jlgproject.util.ToastUtil;
import com.jlgproject.util.UserInfoState;
import com.jlgproject.view.HomeListView;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;

/**
 * Created by sunbeibei on 2017/8/28.
 */

public class Debt_Money extends BaseActivity implements View.OnClickListener, HttpMessageInfo.IMessage, PullToRefreshBase.OnRefreshListener2 {

    private ImageView title_left;
    private TextView title_name;
    private int pn = 1;
    private int ps = 9;
    // 1.设置几个状态码方便我们进行状态的判断
    private static final int NORMAL = 1;
    //2.是刷新的状态
    private static final int REFRESH = 2;
    //3.上啦刷新加载更多
    private static final int LOADMORE = 3;
    private int status = 1;
    private PullToRefreshScrollView listview;
    private Bill_Adapter adapter;
    private List<Bill_Moldel_Bean.DataBean.ItemsBean> items;
    private LinearLayout content;
    private boolean isNull = false;

    @Override
    public int loadWindowLayout() {
        return R.layout.debt_money;


    }

    @Override
    public void initDatas() {
        super.initDatas();
        status = NORMAL;

        requestDebt_Money();


    }

    @Override
    public void initViews() {
        super.initViews();

        title_left = (ImageView) findViewById(R.id.iv_title_left);
        title_left.setVisibility(View.VISIBLE);
        title_left.setImageResource(R.mipmap.back);
        title_left.setOnClickListener(this);
        title_name = (TextView) findViewById(R.id.tv_title_name);
        // listView  = (ExpandableListView) findViewById(R.id.listview);
        listview = (PullToRefreshScrollView) findViewById(R.id.listview);
        listview.setOnRefreshListener(this);
        listview.setMode(PullToRefreshBase.Mode.BOTH);
        title_name.setVisibility(View.VISIBLE);
        title_name.setText("债币账务明细");
        content = (LinearLayout) findViewById(R.id.bill_content);
        L.e("shuixin", "------刷新-------");


    }

    private void requestDebt_Money() {
        if (ShowDrawDialog(this)) {
            HttpMessageInfo<Bill_Moldel_Bean> info = new HttpMessageInfo<>(BaseUrl.MY_ZHAIBI, new Bill_Moldel_Bean());
            info.setiMessage(this);
            AddHeaders header = new AddHeaders();
            header.add("Authorization", UserInfoState.getToken());
            GetParmars parmars = new GetParmars();
            parmars.add("pn", pn);
            parmars.add("ps", ps);
            OkHttpUtils.getInstance().getServiceMassage(OkHttpUtils.TYPE_GET, info, parmars, header, 1);
        }


    }


    private void initview(Bill_Moldel_Bean data) {
        items = data.getData().getItems();
        if (status == NORMAL) {
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            linearLayout.setOrientation(LinearLayout.VERTICAL);


            for (int i = 0; i < items.size(); i++) {
                View head = LayoutInflater.from(this).inflate(R.layout.bill_heaed, null);
//                ImageView rili = (ImageView) head.findViewById(R.id.bii_date_image);
                TextView date = (TextView) head.findViewById(R.id.bill_date);
                date.setText(items.get(i).getMouthtime());
//
//                rili.setOnClickListener(new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View v) {
//                        ToastUtil.showShort(Debt_Money.this, "点击查看日历");
//                    }
//                });
//                if (i == 0) {
//                    rili.setVisibility(View.VISIBLE);
//                }

                View bodylistview = LayoutInflater.from(this).inflate(R.layout.bill_listveiw, null);
                HomeListView listview2 = (HomeListView) bodylistview.findViewById(R.id.bill_listview);
                listview2.addHeaderView(head);
                List<Bill_Moldel_Bean.DataBean.ItemsBean.MypackvoBean> mypackvo = items.get(i).getMypackvo();
                Bill_Adapter adapter = new Bill_Adapter(this, items.get(i).getMypackvo());
                listview2.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                linearLayout.addView(bodylistview);

            }
            content.addView(linearLayout);
        }
        if (status == REFRESH) {
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            items.clear();
            items = data.getData().getItems();

            for (int i = 0; i < data.getData().getItems().size(); i++) {
                View head = LayoutInflater.from(this).inflate(R.layout.bill_heaed, null);
//                ImageView rili = (ImageView) head.findViewById(R.id.bii_date_image);
                TextView date = (TextView) head.findViewById(R.id.bill_date);
                date.setText(data.getData().getItems().get(i).getMouthtime());
//
//                rili.setOnClickListener(new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View v) {
//                        ToastUtil.showShort(Debt_Money.this, "点击查看日历");
//                    }
//                });
//                if (i == 0) {
//                    rili.setVisibility(View.VISIBLE);
//                }

                View bodylistview = LayoutInflater.from(this).inflate(R.layout.bill_listveiw, null);
                HomeListView listview2 = (HomeListView) bodylistview.findViewById(R.id.bill_listview);
                listview2.addHeaderView(head);
                List<Bill_Moldel_Bean.DataBean.ItemsBean.MypackvoBean> mypackvo = items.get(i).getMypackvo();
                Bill_Adapter adapter = new Bill_Adapter(this, items.get(i).getMypackvo());
                listview2.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                linearLayout.addView(bodylistview);

            }

            content.addView(linearLayout);
        }
        if (status == LOADMORE) {
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            List<Bill_Moldel_Bean.DataBean.ItemsBean> items2 = data.getData().getItems();
//            items.addAll(items2);
            for (int i = 0; i < items2.size(); i++) {

                View bodylistview = LayoutInflater.from(this).inflate(R.layout.bill_listveiw, null);
                HomeListView listview2 = (HomeListView) bodylistview.findViewById(R.id.bill_listview);
                Bill_Adapter adapter = new Bill_Adapter(this, items2.get(i).getMypackvo());
                listview2.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                linearLayout.addView(bodylistview);

            }

            content.addView(linearLayout);
        }


    }

    @Override
    public void onClick(View v) {
        if (v == title_left) {
            finish();
        }
    }

    @Override
    public void getServiceData(Object o) {
        if (o instanceof Bill_Moldel_Bean) {
            Bill_Moldel_Bean data = (Bill_Moldel_Bean) o;
            if(data!=null){
                listview.onRefreshComplete();
                DismissDialog();
                if (data.getState().equals("ok")) {
                    initview(data);
                } else {
                    ToastUtil.showShort(this, data.getMessage());
                }
            }
        }


    }

    @Override
    public void getErrorData(Call call, IOException e) {
        DismissDialog();
        listview.onRefreshComplete();
        ToastUtil.showShort(this, "服务器繁忙，请稍后再试");

    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        status = REFRESH;
        pn = 1;

        requestDebt_Money();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        status = LOADMORE;
        pn = pn + 1;
        requestDebt_Money();
    }

}
