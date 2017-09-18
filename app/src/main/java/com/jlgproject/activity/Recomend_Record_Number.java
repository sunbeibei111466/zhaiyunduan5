package com.jlgproject.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jlgproject.R;
import com.jlgproject.adapter.DebtManerAdapter;
import com.jlgproject.adapter.DebtManerSearchAdapter;
import com.jlgproject.base.BaseActivity;
import com.jlgproject.http.AddHeaders;
import com.jlgproject.http.BaseUrl;
import com.jlgproject.http.GetParmars;
import com.jlgproject.http.HttpMessageInfo;
import com.jlgproject.http.OkHttpUtils;
import com.jlgproject.model.Debts_Manger;
import com.jlgproject.util.L;
import com.jlgproject.util.ToastUtil;
import com.jlgproject.util.UserInfoState;
import com.jlgproject.util.Utils;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;

/**
 * Created by sunbeibei on 2017/8/2.
 * 推荐备案数
 */

public class Recomend_Record_Number extends BaseActivity implements View.OnClickListener, HttpMessageInfo.IMessage, PullToRefreshBase.OnRefreshListener2 {

    private PullToRefreshListView listView;
    private PullToRefreshListView listview_search;
    private TextView title;
    private ImageView title_left;
    private ImageView title_right;
    private int pn = 1;
    private int ps = 5;
    // 1.设置几个状态码方便我们进行状态的判断
    private static final int NORMAL = 1;
    //2.是刷新的状态
    private static final int REFRESH = 2;
    //3.上啦刷新加载更多
    private static final int LOADMORE = 3;
    private int status = 1;
    private int a = 1;
    private LinearLayout serliner;
    private EditText et_name;
    private ImageView serch_beian;
    private boolean isSerch = false;
    private List<Debts_Manger.DataBean.ItemsBean> items;
    private DebtManerAdapter adapter;
    private DebtManerSearchAdapter adapter_Search;
    private String beian;
    private ImageView iv_back_search;

    @Override
    public int loadWindowLayout() {
        return R.layout.recomend_record_number;
    }

    @Override
    public void initDatas() {
        super.initDatas();
    }

    @Override
    public void initViews() {
        super.initViews();
        listView = (PullToRefreshListView) findViewById(R.id.listview_liebiao);//列表
        listView.setOnRefreshListener(this);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        listview_search = (PullToRefreshListView) findViewById(R.id.listview_search);//搜索列表
        listview_search.setOnRefreshListener(this);
        listview_search.setMode(PullToRefreshBase.Mode.BOTH);
        listview_search.setVisibility(View.GONE);
        title = (TextView) findViewById(R.id.tv_title_name);
        title.setVisibility(View.VISIBLE);
        title.setText("推荐备案数");
        title_left = (ImageView) findViewById(R.id.iv_title_left);
        title_left.setVisibility(View.VISIBLE);
        title_left.setImageResource(R.mipmap.back);
        title_left.setOnClickListener(this);
        title_right = (ImageView) findViewById(R.id.iv_title_right2);
        title_right.setVisibility(View.VISIBLE);
        title_right.setImageResource(R.mipmap.search_bar);
        title_right.setOnClickListener(this);
        serliner = (LinearLayout) findViewById(R.id.serch_liner);
        et_name = (EditText) findViewById(R.id.et_na_phone);
        serch_beian = (ImageView) findViewById(R.id.serch_beian);
        serch_beian.setOnClickListener(this);
        iv_back_search = (ImageView) findViewById(R.id.iv_back_search);
        iv_back_search.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        status = NORMAL;
        requertSerchBeiAn();
    }

    @Override
    public void onClick(View v) {
        if (v == title_left) {
            finish();
        } else if (v == title_right) {
            switch (a) {
                case 1:
                    serliner.setVisibility(View.VISIBLE);
                    a = 2;
                    break;
                case 2:
                    serliner.setVisibility(View.GONE);
                    a = 1;
                    break;
            }
        } else if (v == serch_beian) {
            listView.setVisibility(View.GONE);
            listview_search.setVisibility(View.VISIBLE);
            isSerch = true;
            status = NORMAL;
            beian = et_name.getText().toString().trim();
            if (!TextUtils.isEmpty(beian)) {
                Utils.closeKeyboard(this);
                serchBaiAn();
            } else {
                ToastUtil.showShort(this, "请输入您要搜索的姓名或推荐编码");
            }
        } else if (v == iv_back_search) {
            et_name.setText("");
            serliner.setVisibility(View.GONE);
            a = 1;
            isSerch = false;
            listView.setVisibility(View.VISIBLE);
            listview_search.setVisibility(View.GONE);
            Utils.closeKeyboard(this);
            status =REFRESH;
            requertSerchBeiAn();
        }
    }


    //获取列表接口
    private void requertSerchBeiAn() {
        if (ShowDrawDialog(this)) {
            HttpMessageInfo<Debts_Manger> info = new HttpMessageInfo<>(BaseUrl.RECORD_NUMBER, new Debts_Manger());
            info.setiMessage(this);
            AddHeaders headers = new AddHeaders();
            headers.add("Authorization", UserInfoState.getToken());
            GetParmars parmars = new GetParmars();
            parmars.add("pn", pn);
            parmars.add("ps", ps);
            OkHttpUtils.getInstance().getServiceMassage(OkHttpUtils.TYPE_GET, info, parmars, headers, 1);
        }
    }

    //列表搜索接口
    private void serchBaiAn() {
        if (ShowDrawDialog(this)) {
            HttpMessageInfo<Debts_Manger> info = new HttpMessageInfo<>(BaseUrl.RECORD_NUMBER, new Debts_Manger());
            info.setiMessage(this);
            AddHeaders headers = new AddHeaders();
            headers.add("Authorization", UserInfoState.getToken());
            GetParmars parmars = new GetParmars();
            parmars.add("pn", pn);
            parmars.add("ps", ps);
            parmars.add("condition", beian);
            OkHttpUtils.getInstance().getServiceMassage(OkHttpUtils.TYPE_GET, info, parmars, headers, 1);
        }
    }


    //抽取显示数据的方法；
    private void displayData(Debts_Manger data) {
        if (status == NORMAL) {

            if (isSerch) {//搜索列表
                L.e("------NORMAL-----搜索----");
                items = data.getData().getItems();
                if(items!=null){
                    adapter_Search = new DebtManerSearchAdapter(this, items);
                    listview_search.setAdapter(adapter_Search);
                } else {
                    ToastUtil.showShort(this, "您搜索的人-没有推荐备案数");
                }
            } else {//展示列表
                L.e("------NORMAL-----列表----");
                if (items == null) {
                    List<Debts_Manger.DataBean.ItemsBean> itemsBeen=data.getData().getItems();
                    if(itemsBeen.size()!=0){
                        items = itemsBeen;
                        adapter = new DebtManerAdapter(this, items);
                        listView.setAdapter(adapter);
                    }else {
                        ToastUtil.showShort(this, "您没有推荐备案数");
                    }
                }
            }

        } else if (status == REFRESH) {

            if (isSerch) {//搜索列表
                L.e("------REFRESH-----搜索----");
                if (items != null) {
                    items.clear();
                    items = data.getData().getItems();
                    adapter_Search.setItems(items);
                    adapter_Search.notifyDataSetChanged();
                } else {
                    ToastUtil.showShort(this, "暂无数据");
                }
            } else {
                L.e("------REFRESH-----列表----");
                if (items != null) {
                    items.clear();
                    items = data.getData().getItems();
                    adapter.setItems(items);
                    adapter.notifyDataSetChanged();
                } else {
                    ToastUtil.showShort(this, "暂无数据");
                }
            }
        } else if (status == LOADMORE) {

            if (isSerch) {
                L.e("------LOADMORE-----搜索----");
                if (items != null) {
                    int size = items.size();
                    if (size != 0) {
                        List<Debts_Manger.DataBean.ItemsBean> items1 = data.getData().getItems();
                        items.addAll(items1);
                        adapter_Search.setItems(items);
                        adapter_Search.notifyDataSetChanged();
                    } else {
                        ToastUtil.showShort(this, "暂无数据");
                    }
                } else {
                    ToastUtil.showShort(this, "暂无数据");
                }
            } else {
                L.e("------LOADMORE-----列表----");
                if (items != null) {
                    int size = items.size();
                    if (size != 0) {
                        List<Debts_Manger.DataBean.ItemsBean> items1 = data.getData().getItems();
                        items.addAll(items1);
                        adapter.setItems(items);
                        adapter.notifyDataSetChanged();
                    } else {
                        ToastUtil.showShort(this, "暂无数据");
                    }
                } else {
                    ToastUtil.showShort(this, "暂无数据");
                }
            }


        }
    }

    @Override
    public void getServiceData(Object o) {
        if (o instanceof Debts_Manger) {
            Debts_Manger data = (Debts_Manger) o;
            if (data != null) {
                DismissDialog();
                listView.onRefreshComplete();
                listview_search.onRefreshComplete();
                if (data.getState().equals("ok")) {
                    displayData(data);
                } else {
                    ToastUtil.showShort(this, data.getMessage());
                }
            }
        }
    }

    @Override
    public void getErrorData(Call call, IOException e) {
        listView.onRefreshComplete();
        listview_search.onRefreshComplete();
        DismissDialog();
        ToastUtil.showShort(this, "服务器繁忙,请稍后再试");
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        pn = 1;
        status = REFRESH;
        if (isSerch) {
            if (!TextUtils.isEmpty(beian)) {
                serchBaiAn();
            }
            L.e("-------搜索---刷新-------");
        } else {
            requertSerchBeiAn();
            L.e("-------列表---刷新-------");
        }
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        pn = pn + 1;
        status = LOADMORE;
        if (isSerch) {
            if (!TextUtils.isEmpty(beian)) {
                serchBaiAn();
            }
            L.e("-------搜索---加载-------");
        } else {
            requertSerchBeiAn();
            L.e("-------列表---加载-------");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pn = 1;
        ps = 5;
    }
}
