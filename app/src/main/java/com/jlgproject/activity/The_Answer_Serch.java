package com.jlgproject.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.internal.Utils;
import com.jlgproject.R;
import com.jlgproject.adapter.Pic_Text_Adapter;
import com.jlgproject.adapter.The_Answer_Tu_Adapter;
import com.jlgproject.adapter.The_Answer_Tu_Search_Adapter;
import com.jlgproject.base.BaseActivity;
import com.jlgproject.http.AddHeaders;
import com.jlgproject.http.BaseUrl;
import com.jlgproject.http.GetParmars;
import com.jlgproject.http.HttpMessageInfo;
import com.jlgproject.http.OkHttpUtils;
import com.jlgproject.model.Answer_Tu_Model;
import com.jlgproject.model.Pic_Text_Bean;
import com.jlgproject.util.L;
import com.jlgproject.util.ToastUtil;
import com.jlgproject.util.UserInfoState;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;

/**
 * Created by sunbeibei on 2017/8/4.
 */

public class The_Answer_Serch extends BaseActivity implements View.OnClickListener, PullToRefreshBase.OnRefreshListener2, HttpMessageInfo.IMessage {

    private ImageView title_left;
    private EditText ser_contect;
    private ImageView title_right;
    private TextView the_answer_jieguo;
    private PullToRefreshListView listView;
    private int pn = 1;
    private int ps = 5;
    // 1.设置几个状态码方便我们进行状态的判断
    private static final int NORMAL = 1;
    //2.是刷新的状态
    private static final int REFRESH = 2;
    //3.上啦刷新加载更多
    private static final int LOADMORE = 3;
    private int status = 0;
    private String contect;
    private String answer;
    private List<Answer_Tu_Model.DataBean.ItemsBean> items;
    private The_Answer_Tu_Adapter adapter;

    @Override
    public int loadWindowLayout() {
        return R.layout.the_answer_serch;
    }

    @Override
    public void initDatas() {
        super.initDatas();
        Intent intent = getIntent();
        answer = intent.getStringExtra("answer");
        L.e("+++++++++++++++++", answer);
    }

    @Override
    public void initViews() {
        super.initViews();
        title_left = (ImageView) findViewById(R.id.finsh_back_answer);
        title_left.setOnClickListener(this);
        ser_contect = (EditText) findViewById(R.id.serch_answer);
        title_right = (ImageView) findViewById(R.id.the_answer_ser_bar);
        title_right.setOnClickListener(this);
        the_answer_jieguo = (TextView) findViewById(R.id.the_answer_jieguo);
        listView = (PullToRefreshListView) findViewById(R.id.listview);
        listView.setOnRefreshListener(this);
        listView.setMode(PullToRefreshBase.Mode.BOTH);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onClick(View v) {
        if (v == title_left) {
            finish();
        } else if (v == title_right) {
            status = NORMAL;
            contect = ser_contect.getText().toString();
            if (!TextUtils.isEmpty(contect)) {
                 com.jlgproject.util.Utils.closeKeyboard(this);
                requertTheAnswer();
            } else {
                ToastUtil.showShort(this, "请输入你所要搜索的问题");
                return;
            }

        }

    }

    private void requertTheAnswer() {

        if (ShowDrawDialog(this)) {
            HttpMessageInfo<Answer_Tu_Model> info = new HttpMessageInfo<>(BaseUrl.PIC_TEXT_SERCH, new Answer_Tu_Model());
            info.setiMessage(this);
            AddHeaders headers = new AddHeaders();
            headers.add("Authorization", UserInfoState.getToken());
            GetParmars parmars = new GetParmars();
            parmars.add("wd", contect);
            parmars.add("videoId", answer);
            parmars.add("pn", pn);
            parmars.add("ps", ps);
            OkHttpUtils.getInstance().getServiceMassage(OkHttpUtils.TYPE_GET, info, parmars, headers, 1);
        }

    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        pn = 1;
        status=REFRESH;
        if (!TextUtils.isEmpty(contect)){
            requertTheAnswer();
        }


    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        pn = pn + 1;
        status=LOADMORE;
        if (!TextUtils.isEmpty(contect)){
            requertTheAnswer();
        }


    }

    @Override
    public void getServiceData(Object o) {
        if (o instanceof Answer_Tu_Model) {
            Answer_Tu_Model data = (Answer_Tu_Model) o;
            if (data != null) {
                DismissDialog();
                listView.onRefreshComplete();

                if (data.getState().equals("ok")) {
                    displayData(data);
                } else {
                    ToastUtil.showShort(this, data.getMessage());
                }
            }
        }

    }

    //抽取的展示数据的方法
    private void displayData(final Answer_Tu_Model manger) {

        if (status == NORMAL) {
            items = manger.getData().getItems();
            adapter = new The_Answer_Tu_Adapter(this, items);
            listView.setAdapter(adapter);
        }
        if (status == REFRESH) {
            L.e("--------刷新——————");
            items.clear();
            items = manger.getData().getItems();
            adapter.setItems(items);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if (status == LOADMORE) {
            List<Answer_Tu_Model.DataBean.ItemsBean> items2 = manger.getData().getItems();
            items.addAll(items2);
            adapter.setItems(items);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }


    @Override
    public void getErrorData(Call call, IOException e) {
        listView.onRefreshComplete();
        ToastUtil.showShort(this, "服务器繁忙，请稍后再试");
    }
}
