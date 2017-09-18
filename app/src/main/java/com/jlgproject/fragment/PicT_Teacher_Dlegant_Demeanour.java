package com.jlgproject.fragment;

import android.os.Handler;
import android.view.View;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jlgproject.R;
import com.jlgproject.adapter.Pic_Teacher_Feng_Cai;
import com.jlgproject.adapter.Pic_Text_Adapter;
import com.jlgproject.base.BaseFragment;
import com.jlgproject.http.AddHeaders;
import com.jlgproject.http.BaseUrl;
import com.jlgproject.http.GetParmars;
import com.jlgproject.http.HttpMessageInfo;
import com.jlgproject.http.OkHttpUtils;
import com.jlgproject.model.Pic_Text_Bean;
import com.jlgproject.model.The_Teacher_Moldel;
import com.jlgproject.util.L;
import com.jlgproject.util.ToastUtil;
import com.jlgproject.util.UserInfoState;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;


/**
 * Created by sunbeibei on 2017/7/18.
 * 名师风采
 */

public class PicT_Teacher_Dlegant_Demeanour extends BaseFragment implements View.OnClickListener, HttpMessageInfo.IMessage, PullToRefreshBase.OnRefreshListener2 {

    private int pn = 1;
    private int ps = 5;
    private  static  final  int NOMAL=0;
    //2.是刷新的状态
    private static final int REFRESH = 2;
    //3.上啦刷新加载更多
    private static final int LOADMORE = 3;
    private int status = 1;
    private PullToRefreshListView pic_text;
    private Pic_Teacher_Feng_Cai adapter;
    private List<The_Teacher_Moldel.DataBean.ItemsBean> items;


    @Override
    public int getLoadViewId() {
        return R.layout.pic_teacher_dlegant_demanour;
    }

    @Override
    public void initDatas() {
        super.initDatas();


    }

    @Override
    public void onResume() {
        super.onResume();
        status =NOMAL;
        requestTeacher();

    }
   private void requestTeacher(){
       if (ShowDrawDialog(getActivity())){
           HttpMessageInfo<The_Teacher_Moldel>info= new HttpMessageInfo<>(BaseUrl.TEACHER_FENG_CAI,new The_Teacher_Moldel());
       info.setiMessage(this);
       AddHeaders headers = new AddHeaders();
       headers.add("Authorization", UserInfoState.getToken());
       GetParmars parmars = new GetParmars();
       parmars.add("pn",pn);
       parmars.add("ps",ps);
       OkHttpUtils.getInstance().getServiceMassage(OkHttpUtils.TYPE_GET,info,parmars,headers,1);

    }
   }

    @Override
    public View initView(View view) {

        pic_text = (PullToRefreshListView) view.findViewById(R.id.listview);
        pic_text.setOnRefreshListener(this);
        pic_text.setMode(PullToRefreshBase.Mode.BOTH);


        return view;
    }

    @Override
    public void onClick(View v) {

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
                    ToastUtil.showShort(getActivity(),data.getMessage());
                }
            }
        }


    }
    //抽取的展示数据的方法
    private void displayData(final The_Teacher_Moldel manger) {

        if (status==NOMAL) {
            items = manger.getData().getItems();
            adapter = new Pic_Teacher_Feng_Cai(getActivity(),items);
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
    public void onDestroy() {
        super.onDestroy();
        pn = 1;
        ps = 8;
    }



    @Override
    public void getErrorData(Call call, IOException e) {
        pic_text.onRefreshComplete();
        DismissDialog();
        ToastUtil.showLong(getActivity(), "服务器繁忙，请稍后再试");
    }


    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        status = REFRESH;
        pn = 1;
        requestTeacher();


    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        status = LOADMORE;
        pn = pn + 1;
        requestTeacher();


    }
}
