package com.jlgproject.activity;

import android.media.Image;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.internal.Utils;
import com.jlgproject.R;
import com.jlgproject.adapter.Pic_Teacher_Feng_Cai;
import com.jlgproject.base.BaseActivity;
import com.jlgproject.http.AddHeaders;
import com.jlgproject.http.BaseUrl;
import com.jlgproject.http.GetParmars;
import com.jlgproject.http.HttpMessageInfo;
import com.jlgproject.http.OkHttpUtils;
import com.jlgproject.model.The_Teacher_Moldel;
import com.jlgproject.util.L;
import com.jlgproject.util.ToastUtil;
import com.jlgproject.util.UserInfoState;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;

/**
 * Created by sunbeibei on 2017/8/7.
 * 名师风采搜索页
 */

public class Teacher_Feng_Cai_Serch extends BaseActivity implements View.OnClickListener,HttpMessageInfo.IMessage,PullToRefreshBase.OnRefreshListener2{

    private ImageView iv_left;
    private EditText serch_teacher;
    private ImageView teacher_serch_bar;
    private int pn = 1;
    private int ps = 5;
    // 1.设置几个状态码方便我们进行状态的判断
    private static final int NORMAL = 1;
    //2.是刷新的状态
    private static final int REFRESH = 2;
    //3.上啦刷新加载更多
    private static final int LOADMORE = 3;
    private  int status=0;
    private String string;
    private PullToRefreshListView pic_text;
    private Pic_Teacher_Feng_Cai adapter;
    private List<The_Teacher_Moldel.DataBean.ItemsBean> items;

    @Override
    public int loadWindowLayout() {
        return R.layout.teacher_feng_cai_serch;
    }

    @Override
    public void initViews() {
        super.initViews();
        iv_left = (ImageView) findViewById(R.id.finsh_back_teacher);
        iv_left.setOnClickListener(this);
        serch_teacher = (EditText) findViewById(R.id.serch_teacher);
        teacher_serch_bar = (ImageView) findViewById(R.id.the_teacher_ser_bar);
        teacher_serch_bar.setOnClickListener(this);
        pic_text = (PullToRefreshListView)findViewById(R.id.listview);
        pic_text.setOnRefreshListener(this);
        pic_text.setMode(PullToRefreshBase.Mode.BOTH);



    }

    @Override
    public void onClick(View v) {
        if (v==iv_left){
            finish();
        }else if(v==teacher_serch_bar){
           status=NORMAL;
            com.jlgproject.util.Utils.closeKeyboard(this);
            requert_teacher_bar();


        }

    }
    private  void  requert_teacher_bar(){
         string = serch_teacher.getText().toString();
        if (!TextUtils.isEmpty(string)){
            if (ShowDrawDialog(this)){
                HttpMessageInfo<The_Teacher_Moldel>info= new HttpMessageInfo<>(BaseUrl.PIC_TEXT_SERCH,new The_Teacher_Moldel());
                info.setiMessage(this);
                AddHeaders headers = new AddHeaders();
                headers.add("Authorization", UserInfoState.getToken());
                GetParmars parmars = new GetParmars();
                parmars.add("pn",pn);
                parmars.add("ps",ps);
                parmars.add("wd", this.string);
                parmars.add("videoId","名师风采");
                L.e("00000000", this.string);
                OkHttpUtils.getInstance().getServiceMassage(OkHttpUtils.TYPE_GET,info,parmars,headers,1);
            }
        }else {
            ToastUtil.showShort(this,"请输入您搜索老师的姓名");
        }


    }

    @Override
    public void getServiceData(Object o) {
        if (o instanceof The_Teacher_Moldel){
            The_Teacher_Moldel data= (The_Teacher_Moldel) o;
            if (data!=null){
                if (data.getState().equals("ok")){
                    DismissDialog();
                    pic_text.onRefreshComplete();
                    displayData(data);
                }else {
                    ToastUtil.showShort(this,data.getMessage());
                }
            }
        }
    }
    //抽取的展示数据的方法
    private void displayData(final The_Teacher_Moldel manger) {

        if (status==NORMAL) {
            items = manger.getData().getItems();
            adapter = new Pic_Teacher_Feng_Cai(this,items);
            pic_text.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }if (status == REFRESH) {
            L.e("--------刷新——————");
            items.clear();
            items = manger.getData().getItems();
            adapter.setItems(items);
            adapter.notifyDataSetChanged();
        }if (status == LOADMORE) {
            List<The_Teacher_Moldel.DataBean.ItemsBean> items2 = manger.getData().getItems();
            items.addAll(items2);
            adapter.setItems(items);
            adapter.notifyDataSetChanged();
        }
    }


    @Override
    public void getErrorData(Call call, IOException e) {
        pic_text.onRefreshComplete();
        DismissDialog();
        ToastUtil.showLong(this, "服务器繁忙，请稍后再试");
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        status = REFRESH;
        pn = 1;
        requert_teacher_bar();


    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        status = LOADMORE;
        pn = pn + 1;
        requert_teacher_bar();



    }
}
