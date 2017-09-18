package com.jlgproject.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jlgproject.R;
import com.jlgproject.adapter.ActivityPrefectrueAdapter;
import com.jlgproject.adapter.DebtManerAdapter;
import com.jlgproject.base.BaseActivity;
import com.jlgproject.http.BaseUrl;
import com.jlgproject.http.GetParmars;
import com.jlgproject.http.HttpMessageInfo;
import com.jlgproject.http.OkHttpUtils;
import com.jlgproject.model.ActivityPrefectrueMode;
import com.jlgproject.model.Debts_Manger;
import com.jlgproject.util.L;
import com.jlgproject.util.ToastUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;

/**
 * 活动专区
 */
public class ActivityPrefecture extends BaseActivity implements HttpMessageInfo.IMessage, View.OnClickListener, PullToRefreshBase.OnRefreshListener2 {

    private int pn = 1;
    private int ps = 8;
    // 1.设置几个状态码方便我们进行状态的判断
    private static final int NORMAL = 0;
    //2.是刷新的状态
    private static final int REFRESH = 2;
    //3.上啦刷新加载更多
    private static final int LOADMORE = 3;
    private int status = 1;
    private TextView mTv_Title_activity;
    private ImageView mIv_Title_left_activity;
    private LinearLayout mLl_ivParent_title_activity;
    private PullToRefreshListView pullToR_activity_zq;
    private List<ActivityPrefectrueMode.DataBean.ItemsBean> items;
    private ActivityPrefectrueAdapter adapter;

    @Override
    public int loadWindowLayout() {
        return R.layout.activity_prefecture;
    }

    @Override
    public void initViews() {

        //动态设置标题
        mTv_Title_activity = (TextView) findViewById(R.id.tv_title_name);
        mTv_Title_activity.setText(getResources().getText(R.string.activity_zq));
        mIv_Title_left_activity = (ImageView) findViewById(R.id.iv_title_left);
        mIv_Title_left_activity.setVisibility(View.VISIBLE);
        mLl_ivParent_title_activity = (LinearLayout) findViewById(R.id.ll_ivParent_title);
        mLl_ivParent_title_activity.setOnClickListener(this);
        pullToR_activity_zq = (PullToRefreshListView) findViewById(R.id.pullToR_activity_zq);
        pullToR_activity_zq.setMode(PullToRefreshBase.Mode.BOTH);
        pullToR_activity_zq.setOnRefreshListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        status = NORMAL;
        getHttpData();

    }

    private void getHttpData() {
        if (ShowDrawDialog(this)) {
            HttpMessageInfo<ActivityPrefectrueMode> info = new HttpMessageInfo<>(BaseUrl.ACTIVITY_PREFECTRUE, new ActivityPrefectrueMode());
            info.setiMessage(this);
            GetParmars getParmars = new GetParmars();
            getParmars.add("pn", pn);
            getParmars.add("ps", ps);
            OkHttpUtils.getInstance().getServiceMassage(OkHttpUtils.TYPE_GET, info, getParmars, null, 1);
        }
    }

    public void setLayoutData(ActivityPrefectrueMode.DataBean layoutData) {
        if (status == NORMAL) {
            items =layoutData.getItems();
            adapter = new ActivityPrefectrueAdapter(this);
            adapter.setItemsBeanList(items);
            pullToR_activity_zq.setAdapter(adapter);

        } else if (status == REFRESH) {

            items.clear();
            items =layoutData.getItems();
            if (items != null) {
                adapter.setItemsBeanList(items);
                adapter.notifyDataSetChanged();
            } else {
                ToastUtil.showShort(this, "暂无数据");
            }
        } else if (status == LOADMORE) {

            int size = items.size();
            if (size != 0) {
                List<ActivityPrefectrueMode.DataBean.ItemsBean> items1 = layoutData.getItems();
                items.addAll(items1);
                adapter.setItemsBeanList(items);
                adapter.notifyDataSetChanged();
                L.e("-----备案--2--");
            } else {
                ToastUtil.showShort(this, "暂无数据");
            }
        }

    }

    @Override
    public void getServiceData(Object o) {

        if (o instanceof ActivityPrefectrueMode) {
            ActivityPrefectrueMode activityPrefectrueMode = (ActivityPrefectrueMode) o;
            if (activityPrefectrueMode != null) {
                DismissDialog();
                pullToR_activity_zq.onRefreshComplete();
                setLayoutData(activityPrefectrueMode.getData());
            }
        }
    }

    @Override
    public void getErrorData(Call call, IOException e) {
        DismissDialog();
        pullToR_activity_zq.onRefreshComplete();
        ToastUtil.showShort(this,"服务器繁忙，请稍后再试");
    }




    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        status = REFRESH;
        pn = 1;
        getHttpData();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        status = LOADMORE;
        pn = pn + 1;
        getHttpData();
    }

    @Override
    public void onClick(View v) {
        if (v == mLl_ivParent_title_activity) {//返回
            finish();
        }
    }
}
