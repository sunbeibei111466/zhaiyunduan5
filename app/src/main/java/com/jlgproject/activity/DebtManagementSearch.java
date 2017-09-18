package com.jlgproject.activity;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jlgproject.R;
import com.jlgproject.adapter.Buness_Video_Adapter;
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
import com.jlgproject.util.User;
import com.jlgproject.util.UserInfoState;
import com.jlgproject.util.Utils;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;


public class DebtManagementSearch extends BaseActivity implements View.OnClickListener, HttpMessageInfo.IMessage, PullToRefreshBase.OnRefreshListener2 {


    private ImageView finsh_back_iv, serch_debt_iv;
    private EditText serch_debt_et;
    private PullToRefreshListView listview;
    private int pn = 1;
    private int ps = 5;
    // 1.设置几个状态码方便我们进行状态的判断
    private static final int NORMAL = 1;
    //2.是刷新的状态
    private static final int REFRESH = 2;
    //3.上啦刷新加载更多
    private static final int LOADMORE = 3;
    private int status = 1;
    private List<Debts_Manger.DataBean.ItemsBean> items;
    private DebtManerSearchAdapter adapter;
    private LinearLayout ll_debt_search;
    private String string;
    private TextView jieguo_debt;

    @Override
    public int loadWindowLayout() {
        return R.layout.activity_debt_management_search;
    }

    @Override
    public void initViews() {
        ll_debt_search = (LinearLayout) findViewById(R.id.ll_debt_search);
        finsh_back_iv = (ImageView) findViewById(R.id.finsh_back_iv);//返回
        finsh_back_iv.setOnClickListener(this);
        serch_debt_iv = (ImageView) findViewById(R.id.serch_debt_iv);//搜索
        serch_debt_iv.setOnClickListener(this);
        serch_debt_et = (EditText) findViewById(R.id.serch_debt_et);
        listview = (PullToRefreshListView) findViewById(R.id.listview);
        listview.setMode(PullToRefreshBase.Mode.BOTH);
        listview.setOnRefreshListener(this);
        jieguo_debt = (TextView) findViewById(R.id.jieguo_debt);
    }


    @Override
    public void onClick(View v) {

        if (v == finsh_back_iv) {
            finish();
        } else if (v == serch_debt_iv) {
            string = serch_debt_et.getText().toString().trim();
            if (!TextUtils.isEmpty(string)) {
                Utils.closeKeyboard(this);
                status = NORMAL;
                getSearchInfo();
            } else {
                ToastUtil.showShort(this, "请输入您要搜索的姓名或推荐编码");
            }
        }
    }



    public void getSearchInfo() {
        if (ShowDrawDialog(this)) {
            HttpMessageInfo<Debts_Manger> info = new HttpMessageInfo<>(BaseUrl.DEBTS_MANGER_SEARCH, new Debts_Manger());
            info.setiMessage(this);
            GetParmars get = new GetParmars();
            get.add("pn", pn);
            get.add("ps", ps);
            get.add("condition", string);
            AddHeaders add = new AddHeaders();
            add.add("Authorization", UserInfoState.getToken());
            OkHttpUtils.getInstance().getServiceMassage(OkHttpUtils.TYPE_GET, info, get, add, 1);
        }

    }

    @Override
    public void getServiceData(Object o) {
        if (o instanceof Debts_Manger) {
            Debts_Manger data = (Debts_Manger) o;
            if (data != null) {
                DismissDialog();
                listview.onRefreshComplete();
                if (data.getState().equals("ok")) {
                    displayData(data);
                } else {
                    ToastUtil.showShort(this, data.getMessage());
                }
            }
        }
    }

    //抽取显示数据的方法；
    private void displayData(Debts_Manger data) {
        if (status == NORMAL) {

            items = data.getData().getItems();
            if (items.size() == 0) {
                jieguo_debt.setVisibility(View.VISIBLE);
                listview.setVisibility(View.GONE);
            } else {
                jieguo_debt.setVisibility(View.GONE);
                listview.setVisibility(View.VISIBLE);
                adapter = new DebtManerSearchAdapter(this, items);
                listview.setAdapter(adapter);
            }

        } else if (status == REFRESH) {

            items.clear();
            items = data.getData().getItems();
            if (items != null) {
                if (items.size() != 0) {
                    adapter.setItems(items);
                    adapter.notifyDataSetChanged();
                } else {
                    ToastUtil.showShort(this, "已是最新数据");
                }

            } else {
                ToastUtil.showShort(this, "已是最新数据");
            }
        } else if (status == LOADMORE) {
            int size = items.size();
            if (size != 0) {
                List<Debts_Manger.DataBean.ItemsBean> items1 = data.getData().getItems();
                items.addAll(items1);
                adapter.setItems(items);
                adapter.notifyDataSetChanged();
                L.e("-----备案--2--");
            } else {
                ToastUtil.showShort(this, "暂无更多数据");
            }
        }
    }

    @Override
    public void getErrorData(Call call, IOException e) {
        DismissDialog();
        listview.onRefreshComplete();
        ToastUtil.showShort(this, "服务器繁忙,请稍后再试");
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        if (!TextUtils.isEmpty(string)) {
            pn = 1;
            status = REFRESH;
            getSearchInfo();
        } else {
            listview.onRefreshComplete();
        }
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        if (!TextUtils.isEmpty(string)) {
            pn = pn + 1;
            status = LOADMORE;
            getSearchInfo();
        } else {
            listview.onRefreshComplete();
        }

    }
}
