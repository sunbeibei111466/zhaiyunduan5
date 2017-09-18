package com.jlgproject.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jlgproject.R;
import com.jlgproject.adapter.ActivityPrefectrueAdapter;
import com.jlgproject.adapter.Last_New_Pic_Adapter;
import com.jlgproject.base.BaseActivity;
import com.jlgproject.http.AddHeaders;
import com.jlgproject.http.BaseUrl;
import com.jlgproject.http.GetParmars;
import com.jlgproject.http.HttpMessageInfo;
import com.jlgproject.http.OkHttpUtils;
import com.jlgproject.model.ActivityPrefectrueMode;
import com.jlgproject.model.Video_List_Bean;
import com.jlgproject.util.L;
import com.jlgproject.util.ToastUtil;
import com.jlgproject.util.UserInfoState;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;

/**
 * Created by sunbeibei on 2017/9/12.
 */

public class LastNew_Pic_Text extends BaseActivity implements View.OnClickListener,PullToRefreshBase.OnRefreshListener2,HttpMessageInfo.IMessage{

    private ImageView title_left;
    private TextView title_name;
    private PullToRefreshListView listView;
    private int pn = 1;
    private int ps = 8;
    // 1.设置几个状态码方便我们进行状态的判断
    private static final int NORMAL = 0;
    //2.是刷新的状态
    private static final int REFRESH = 2;
    //3.上啦刷新加载更多
    private static final int LOADMORE = 3;
    private int status = 1;
    private List<ActivityPrefectrueMode.DataBean.ItemsBean> items;
    private Last_New_Pic_Adapter adapter;

    @Override
    public int loadWindowLayout() {
        return R.layout.lastnew_pic_text;
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
        title_name.setText("课程预告");
        listView = (PullToRefreshListView) findViewById(R.id.courses_notice);
        listView.setOnRefreshListener(this);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
    }

    @Override
    protected void onResume() {
        super.onResume();
        status=NORMAL;
        requestLastNew_pic();
    }

    private void requestLastNew_pic() {
        if (ShowDrawDialog(this)){
            HttpMessageInfo<ActivityPrefectrueMode> info = new HttpMessageInfo<>(BaseUrl.COURSES_NOTICE,new ActivityPrefectrueMode());
            info.setiMessage(this);
            GetParmars parmars = new GetParmars();
            parmars.add("pn", pn);
            parmars.add("ps", ps);
            AddHeaders headers = new AddHeaders();
            headers.add("Authorization", UserInfoState.getToken());
            OkHttpUtils.getInstance().getServiceMassage(OkHttpUtils.TYPE_GET, info, parmars, headers, 1);
        }
    }

    public void setLayoutData(ActivityPrefectrueMode.DataBean layoutData) {

        if (status == NORMAL) {
            items =layoutData.getItems();
            adapter = new Last_New_Pic_Adapter(this);
            adapter.setItemsBeanList(items);
            listView.setAdapter(adapter);

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

        if(o instanceof ActivityPrefectrueMode){
            ActivityPrefectrueMode mode= (ActivityPrefectrueMode) o;
            if(mode!=null){
                DismissDialog();
                listView.onRefreshComplete();
                if(mode.getState().equals("ok")){
                    setLayoutData(mode.getData());
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

    }


    @Override
    public void onClick(View v) {
        if (v==title_left){
            finish();
        }
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        status = REFRESH;
        pn=1;
        requestLastNew_pic();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        status=LOADMORE;
        pn=pn+1;
       requestLastNew_pic();
    }



}
