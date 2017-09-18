package com.jlgproject.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jlgproject.R;
import com.jlgproject.adapter.DebtManerAdapter;
import com.jlgproject.adapter.DebtManerSearchAdapter;
import com.jlgproject.adapter.The_Answer_Tu_Adapter;
import com.jlgproject.adapter.The_Answer_Tu_Search_Adapter;
import com.jlgproject.base.BaseActivity;
import com.jlgproject.http.AddHeaders;
import com.jlgproject.http.BaseUrl;
import com.jlgproject.http.GetParmars;
import com.jlgproject.http.HttpMessageInfo;
import com.jlgproject.http.OkHttpUtils;
import com.jlgproject.model.Answer_Tu_Model;
import com.jlgproject.model.Debts_Manger;
import com.jlgproject.util.ActivityCollector;
import com.jlgproject.util.ConstUtils;
import com.jlgproject.util.L;
import com.jlgproject.util.ToastUtil;
import com.jlgproject.util.UserInfoState;
import com.jlgproject.util.Utils;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;

/**
 * 答疑解惑 子类别 列表
 */

public class The_Answer_Tu extends BaseActivity implements View.OnClickListener, PullToRefreshBase.OnRefreshListener2, HttpMessageInfo.IMessage {


    private int pn = 1;
    private int ps = 8;
    // 1.设置几个状态码方便我们进行状态的判断
    private static final int NORMAL = 1;
    //2.是刷新的状态
    private static final int REFRESH = 2;
    //3.上啦刷新加载更多
    private static final int LOADMORE = 3;
    private int status = 1;
    private PullToRefreshListView listView, listview_dyjh;
    private The_Answer_Tu_Adapter answer_tu_adapter;
    private ImageView title_left;
    private ImageView title_serch, iv_serch_fdj, iv_quxiao_dyjh;
    private TextView title_name;
    private LinearLayout ll_leibie_liebiao, ll_dyjh_sousuo;
    private EditText et_dyjh;
    private boolean isSearch = false;
    private String id;
    private List<Answer_Tu_Model.DataBean.ItemsBean> items;
    private The_Answer_Tu_Search_Adapter the_answer_tu_search_adapter;
    private String searchContent;
    private String title;
    private TextView type_answer;

    @Override
    public int loadWindowLayout() {
        return R.layout.the_answer_tu;
    }

    @Override
    public void initDatas() {
        super.initDatas();
        int index = getIntent().getIntExtra(ConstUtils.DYJH_ID, 0);
        id = String.valueOf(index);
        L.e("----答疑--id--" + id);
        title = getIntent().getStringExtra("title");
    }

    @Override
    public void initViews() {
        super.initViews();
        title_left = (ImageView) findViewById(R.id.iv_title_left);
        title_left.setVisibility(View.VISIBLE);
        title_left.setImageResource(R.mipmap.back);
        title_left.setOnClickListener(this);
        title_serch = (ImageView) findViewById(R.id.iv_title_right2);
        title_serch.setVisibility(View.VISIBLE);
        title_serch.setImageResource(R.mipmap.search_bar);
        title_serch.setOnClickListener(this);
        title_name = (TextView) findViewById(R.id.tv_title_name);
        title_name.setVisibility(View.VISIBLE);
        title_name.setText("图文课程");
        ll_leibie_liebiao = (LinearLayout) findViewById(R.id.ll_leibie_liebiao);//列表
        listView = (PullToRefreshListView) findViewById(R.id.listview);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        listView.setOnRefreshListener(this);
        ll_dyjh_sousuo = (LinearLayout) findViewById(R.id.ll_dyjh_sousuo);//搜索
        iv_serch_fdj = (ImageView) findViewById(R.id.iv_serch_fdj);//搜索按钮
        iv_serch_fdj.setOnClickListener(this);
        iv_quxiao_dyjh = (ImageView) findViewById(R.id.iv_quxiao_dyjh);//取消
        iv_quxiao_dyjh.setOnClickListener(this);
        et_dyjh = (EditText) findViewById(R.id.et_dyjh);//搜索输入框
        listview_dyjh = (PullToRefreshListView) findViewById(R.id.listview_dyjh);
        listview_dyjh.setMode(PullToRefreshBase.Mode.BOTH);
        listview_dyjh.setOnRefreshListener(this);
        type_answer = (TextView) findViewById(R.id.the_answer_type);
        type_answer.setText(title);




    }

    @Override
    protected void onResume() {
        super.onResume();
        status = NORMAL;
        getDyjhList();
    }

    @Override
    public void onClick(View v) {
        if (v == title_left) {
            finish();
        } else if (v == title_serch) {
            ll_leibie_liebiao.setVisibility(View.GONE);//隐藏列表
            ll_dyjh_sousuo.setVisibility(View.VISIBLE);//显示搜索列表
            isSearch = true;
        } else if (v == iv_serch_fdj) {//搜索
            if (!TextUtils.isEmpty(et_dyjh.getText().toString().trim())) {
                searchContent = et_dyjh.getText().toString().trim();
                Utils.closeKeyboard(this);
                getSearchMeassage();
            } else {
                ToastUtil.showShort(this, "请输入您要搜索的问题");
            }
        } else if (v == iv_quxiao_dyjh) {//取消
            ll_leibie_liebiao.setVisibility(View.VISIBLE);//显示列表
            ll_dyjh_sousuo.setVisibility(View.GONE);//隐藏搜索列表
            et_dyjh.setText("");
            isSearch = false;
            status = NORMAL;
            getDyjhList();
        }
    }


    @Override
    public void getServiceData(Object o) {
        if (o instanceof Answer_Tu_Model) {
            Answer_Tu_Model data = (Answer_Tu_Model) o;
            if (data != null) {
                DismissDialog();
                listView.onRefreshComplete();
                listview_dyjh.onRefreshComplete();
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

    }

    //抽取显示数据的方法；
    private void displayData(Answer_Tu_Model data) {
        if (status == NORMAL) {

            if (isSearch) {//搜索列表
                L.e("------NORMAL-----搜索----");
                items = data.getData().getItems();
                if (items != null) {
                    the_answer_tu_search_adapter = new The_Answer_Tu_Search_Adapter(this, items);
                    listview_dyjh.setAdapter(the_answer_tu_search_adapter);
                } else {
                    ToastUtil.showShort(this, "暂无数据");
                }

            } else {//展示列表
                L.e("------NORMAL-----列表----");

                if (items == null) {
                    items = data.getData().getItems();
                    answer_tu_adapter = new The_Answer_Tu_Adapter(this, items);
                    listView.setAdapter(answer_tu_adapter);
                }
            }

        } else if (status == REFRESH) {

            if (isSearch) {//搜索列表
                L.e("------REFRESH-----搜索----");
                if (items != null) {
                    items.clear();
                    items = data.getData().getItems();
                    the_answer_tu_search_adapter.setItems(items);
                    the_answer_tu_search_adapter.notifyDataSetChanged();
                } else {
                    ToastUtil.showShort(this, "暂无数据");
                }
            } else {
                L.e("------REFRESH-----列表----");
                if (items != null) {
                    items.clear();
                    items = data.getData().getItems();
                    answer_tu_adapter.setItems(items);
                    answer_tu_adapter.notifyDataSetChanged();
                } else {
                    ToastUtil.showShort(this, "暂无数据");
                }
            }
        } else if (status == LOADMORE) {

            if (isSearch) {
                L.e("------LOADMORE-----搜索----");
                if (items != null) {
                    int size = items.size();
                    if (size != 0) {
                        List<Answer_Tu_Model.DataBean.ItemsBean> items1 = data.getData().getItems();
                        items.addAll(items1);
                        the_answer_tu_search_adapter.setItems(items);
                        the_answer_tu_search_adapter.notifyDataSetChanged();
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
                        List<Answer_Tu_Model.DataBean.ItemsBean> items1 = data.getData().getItems();
                        items.addAll(items1);
                        answer_tu_adapter.setItems(items);
                        answer_tu_adapter.notifyDataSetChanged();
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
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {

        if (isSearch) {
            getSearchMeassage();
        } else {
            getDyjhList();
        }
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        if (isSearch) {
            getSearchMeassage();
        } else {
            getDyjhList();
        }
    }

    public void getDyjhList() {
        if (ShowDrawDialog(this)) {
            HttpMessageInfo<Answer_Tu_Model> info = new HttpMessageInfo<>(BaseUrl.DYJH_FL_LIST, new Answer_Tu_Model());
            info.setiMessage(this);
            AddHeaders headers = new AddHeaders();
            headers.add("Authorization", UserInfoState.getToken());
            GetParmars parmars = new GetParmars();
            parmars.add("pn", pn);
            parmars.add("ps", ps);
            parmars.add("id", id);
            OkHttpUtils.getInstance().getServiceMassage(OkHttpUtils.TYPE_GET, info, parmars, headers, 1);
        }
    }

    public void getSearchMeassage() {
        if (ShowDrawDialog(this)) {
            HttpMessageInfo<Answer_Tu_Model> info = new HttpMessageInfo<>(BaseUrl.PIC_TEXT_SERCH, new Answer_Tu_Model());
            info.setiMessage(this);
            AddHeaders headers = new AddHeaders();
            headers.add("Authorization", UserInfoState.getToken());
            GetParmars parmars = new GetParmars();
            parmars.add("pn", pn);
            parmars.add("ps", ps);
            parmars.add("wd", searchContent);
            parmars.add("videoId", "答疑解惑");
            OkHttpUtils.getInstance().getServiceMassage(OkHttpUtils.TYPE_GET, info, parmars, headers, 1);
        }
    }
}
