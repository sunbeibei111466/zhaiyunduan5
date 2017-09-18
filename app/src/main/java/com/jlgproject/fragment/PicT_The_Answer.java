package com.jlgproject.fragment;

import android.content.Context;
import android.os.Handler;
import android.view.View;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jlgproject.R;
import com.jlgproject.adapter.DebtManerAdapter;
import com.jlgproject.adapter.DebtManerSearchAdapter;
import com.jlgproject.adapter.Pic_Text_Adapter;
import com.jlgproject.adapter.The_Answer_Adapter;
import com.jlgproject.base.BaseFragment;
import com.jlgproject.http.AddHeaders;
import com.jlgproject.http.BaseUrl;
import com.jlgproject.http.GetParmars;
import com.jlgproject.http.HttpMessageInfo;
import com.jlgproject.http.OkHttpUtils;
import com.jlgproject.model.AnswerMode;
import com.jlgproject.model.Debts_Manger;
import com.jlgproject.model.Pic_Text_Bean;
import com.jlgproject.util.L;
import com.jlgproject.util.NetUtils;
import com.jlgproject.util.ToastUtil;
import com.jlgproject.util.UserInfoState;

import java.io.IOException;
import java.util.IllegalFormatCodePointException;
import java.util.List;

import okhttp3.Call;

/**
 * 答疑解惑列表
 */

public class PicT_The_Answer extends BaseFragment implements View.OnClickListener, HttpMessageInfo.IMessage, PullToRefreshBase.OnRefreshListener2 {
    private int pn = 1;
    private int ps = 8;
    // 1.设置几个状态码方便我们进行状态的判断
    private static final int NORMAL = 1;
    //2.是刷新的状态
    private static final int REFRESH = 2;
    //3.上啦刷新加载更多
    private static final int LOADMORE = 3;
    private int status = 1;
    private PullToRefreshListView listView;
    private The_Answer_Adapter answer_adapter;
    private List<AnswerMode.DataBean.ItemsBean> items;


    @Override
    public int getLoadViewId() {
        return R.layout.pic_the_answer;
    }

    @Override
    public void initDatas() {
        super.initDatas();
    }


    @Override
    public View initView(View view) {
        listView = (PullToRefreshListView) view.findViewById(R.id.listview);
        listView.setOnRefreshListener(this);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        status = NORMAL;
        requestTheAnswer();
    }

    private void requestTheAnswer() {
        if (ShowDrawDialog(getActivity())) {
            HttpMessageInfo<AnswerMode> info = new HttpMessageInfo<>(BaseUrl.DYJH_LIST, new AnswerMode());
            info.setiMessage(this);
            AddHeaders headers = new AddHeaders();
            headers.add("Authorization", UserInfoState.getToken());
            GetParmars parmars = new GetParmars();
            parmars.add("pn", pn);
            parmars.add("ps", ps);
            OkHttpUtils.getInstance().getServiceMassage(OkHttpUtils.TYPE_GET, info, parmars, headers, 1);
        }
    }

    @Override
    public void onClick(View v) {

    }

    //抽取显示数据的方法；
    private void displayData(AnswerMode data) {
        if (status == NORMAL) {
            items = data.getData().getItems();
            answer_adapter = new The_Answer_Adapter(getActivity(),items);
            listView.setAdapter(answer_adapter);

        } else if (status == REFRESH) {

            items.clear();
            items = data.getData().getItems();
            if (items != null) {
                answer_adapter.setItems(items);
                answer_adapter.notifyDataSetChanged();
            } else {
                ToastUtil.showShort(getActivity(), "暂无数据");
            }
        } else if (status == LOADMORE) {

            int size = items.size();
            if (size != 0) {
                List<AnswerMode.DataBean.ItemsBean> items1 = data.getData().getItems();
                items.addAll(items1);
                answer_adapter.setItems(items);
                answer_adapter.notifyDataSetChanged();
                L.e("-----备案--2--");
            } else {
                ToastUtil.showShort(getActivity(), "暂无数据");
            }
        }
    }

    @Override
    public void getServiceData(Object o) {
        if (o instanceof AnswerMode) {
            AnswerMode data = (AnswerMode) o;
            if (data != null) {
                DismissDialog();
                listView.onRefreshComplete();
                if (data.getState().equals("ok")) {
                    displayData(data);
                } else {
                    ToastUtil.showShort(getActivity(), data.getMessage());
                }
            }
        }
    }

    @Override
    public void getErrorData(Call call, IOException e) {
        listView.onRefreshComplete();
        DismissDialog();
        ToastUtil.showShort(getActivity(), "服务器繁忙,请稍后再试");
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        status = REFRESH;
        pn = 1;
        requestTheAnswer();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        status = LOADMORE;
        pn = pn + 1;
        requestTheAnswer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        pn = 1;
        ps = 8;
    }
}
