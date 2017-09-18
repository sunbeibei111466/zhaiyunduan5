package com.jlgproject.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jlgproject.R;
import com.jlgproject.activity.Debt_Matter_Management_Details;
import com.jlgproject.activity.Details_Demand_Information;
import com.jlgproject.adapter.Demand_Information_Adapter;
import com.jlgproject.adapter.Demand_Information_Adapter_New;
import com.jlgproject.base.BaseFragment;
import com.jlgproject.http.AddHeaders;
import com.jlgproject.http.BaseUrl;
import com.jlgproject.http.GetParmars;
import com.jlgproject.http.HttpMessageInfo;
import com.jlgproject.http.OkHttpUtils;
import com.jlgproject.model.Debts_Manger;
import com.jlgproject.model.Demand_Informations;
import com.jlgproject.model.Demand_Informations_New;
import com.jlgproject.util.L;
import com.jlgproject.util.ToastUtil;
import com.jlgproject.util.UserInfoState;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by sunbeibei on 2017/6/29.
 * 企业需求信息
 */

public class Company_Demand_Information extends BaseFragment implements HttpMessageInfo.IMessage, PullToRefreshBase.OnRefreshListener2 {
    private PullToRefreshGridView listView;
    private Long id;
    // 1.设置几个状态码方便我们进行状态的判断
    private static final int NORMAL = 1;
    //2.下拉刷新的状态
    private static final int REFRESH = 2;
    //3.上啦加载更多
    private static final int LOADMORE = 3;
    private int status = 1;
    private List<Demand_Informations_New.DataBean.ItemsBean> items;
    private String name;
    private int ps = 6;
    private int pn = 1;
    private Demand_Information_Adapter_New information_adapter;
    private List<Demand_Informations.DataBean.ItemsBean> itemsCopy;//用来存储列表数据


    @Override
    public int getLoadViewId() {
        return R.layout.demand_information;
    }

    @Override
    public void initDatas() {
        super.initDatas();
        id = Debt_Matter_Management_Details.id;
        name = Debt_Matter_Management_Details.name;
    }

    @Override
    public View initView(View view) {
        information_adapter = new Demand_Information_Adapter_New(getActivity());
        listView = (PullToRefreshGridView) view.findViewById(R.id.listview_new);
        listView.setOnRefreshListener(this);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        itemsCopy = new ArrayList<>();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        status = NORMAL;
        requestDemand();
    }


    private void requestDemand() {
        if (ShowDrawDialog(getActivity())) {
            Log.e("------企业需求------","");
            GetParmars parmars = new GetParmars();
            parmars.add("debtid", id);
            parmars.add("pn", pn);
            parmars.add("ps", ps);
            AddHeaders headers = new AddHeaders();
            headers.add("Authorization", UserInfoState.getToken());
            HttpMessageInfo<Demand_Informations_New> info = new HttpMessageInfo<>(BaseUrl.DEMAND_INFROMATIION_NEW, new Demand_Informations_New());
            info.setiMessage(this);
            OkHttpUtils.getInstance().getServiceMassage(OkHttpUtils.TYPE_GET, info, parmars, headers, 1);
        }
    }

    @Override
    public void getServiceData(Object o) {

        if (o instanceof Demand_Informations_New) {
            Demand_Informations_New infromations = (Demand_Informations_New) o;
            if (infromations != null) {
                Log.e("-----企业需求------","");
                if (infromations.getState().equals("ok")) {
                    DismissDialog();
                    listView.onRefreshComplete();
                    displayData(infromations);
                }
            }
        }
    }

    @Override
    public void getErrorData(Call call, IOException e) {
        DismissDialog();
        listView.onRefreshComplete();
        ToastUtil.show(getActivity(), "服务器繁忙，请稍后再试", Toast.LENGTH_SHORT);

    }

    //抽取的展示数据的方法
    private void displayData(final Demand_Informations_New info) {

        if (status == NORMAL) {
                items = info.getData().getItems();
                information_adapter.setItems(items);
                listView.setAdapter(information_adapter);
            } else if (status == REFRESH) {
                items.clear();
                items = info.getData().getItems();
                information_adapter.setItems(items);
                information_adapter.notifyDataSetChanged();
            } else if (status == LOADMORE) {
                List<Demand_Informations_New.DataBean.ItemsBean> items1 = info.getData().getItems();
                items.addAll(items1);
                information_adapter.setItems(items);
                information_adapter.notifyDataSetChanged();

            }
        }



    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        status = REFRESH;
        pn = 1;
        requestDemand();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        status = LOADMORE;
        pn =pn+1;
        requestDemand();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ps = 6;
        pn = 1;
    }
}
