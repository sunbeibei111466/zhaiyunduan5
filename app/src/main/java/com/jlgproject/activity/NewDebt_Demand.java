package com.jlgproject.activity;

import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jlgproject.R;
import com.jlgproject.adapter.newDebt.ImageAdapter;
import com.jlgproject.adapter.newDebt.ImageAdapter2;
import com.jlgproject.adapter.newDebt.ImageAdapter3;
import com.jlgproject.adapter.newDebt.ImageAdapter4;
import com.jlgproject.adapter.newDebt.ImageAdapter5;
import com.jlgproject.base.BaseActivity;
import com.jlgproject.http.AddHeaders;
import com.jlgproject.http.BaseUrl;
import com.jlgproject.http.HttpMessageInfo;
import com.jlgproject.http.OkHttpUtils;
import com.jlgproject.model.newDebt.DebtRelation3Vo;
import com.jlgproject.model.newDebt.NewDebtMassage;
import com.jlgproject.model.newDebt.OneLevel;
import com.jlgproject.model.newDebt.Xu;
import com.jlgproject.util.ActivityCollector;
import com.jlgproject.util.ToastUtil;
import com.jlgproject.util.UserInfoState;
import com.jlgproject.view.HorizontialListView;
import com.jlgproject.view.ShrinkControl.CollapseView_1;
import com.jlgproject.view.ShrinkControl.CollapseView_2;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

public class NewDebt_Demand extends BaseActivity implements View.OnClickListener, HttpMessageInfo.IMessage {


    private LinearLayout mRoot;

    private HashMap<Integer, Integer> map2;
    private HashMap<Integer, Integer> map3;
    private HashMap<Integer, Integer> map4;
    private HashMap<Integer, Integer> map5;

    private List<Integer> listQiChe1;
    private List<Integer> listFangC2;
    private List<Integer> listJiaJu3;
    private List<Integer> listSHYP4;
    private List<String> listZong;

    private List<String> image;
    private List<Xu> xuList1;
    private List<Xu> xuList2, xuList3, xuList4;
    private ImageAdapter adapter1;
    private ImageAdapter2 adapter2;
    private ImageAdapter3 adapter3;
    private ImageAdapter4 adapter4;
    private ImageAdapter5 adapter5;

    private TextView mTv_Title_debt_xq;
    private ImageView mIv_Title_left_debt_xq, iv_title_right2_debt_xq;
    private LinearLayout mLl_ivParent_title_debt_xq;

    private Map<String, ImageAdapter> imageAdapterMap;

    @Override
    public int loadWindowLayout() {
        return R.layout.new_debt_demand;
    }

    @Override
    public void getServiceData(Object o) {

        if (o instanceof NewDebtMassage) {
            NewDebtMassage newDebtMassage = (NewDebtMassage) o;
            if (newDebtMassage != null) {
                DismissDialog();
                initViewsCustomer(newDebtMassage.getData());
            }
        }
    }

    @Override
    public void getErrorData(Call call, IOException e) {
        DismissDialog();
        ToastUtil.showShort(this, "服务器繁忙，请稍再试");
    }

    @Override
    public void initViews() {

        getDebtDemand();

        //动态设置标题
        mTv_Title_debt_xq = (TextView) findViewById(R.id.tv_title_name);
        mTv_Title_debt_xq.setText(getResources().getText(R.string.debt_xq));
        mIv_Title_left_debt_xq = (ImageView) findViewById(R.id.iv_title_left);
        mIv_Title_left_debt_xq.setVisibility(View.VISIBLE);
        iv_title_right2_debt_xq = (ImageView) findViewById(R.id.iv_title_right);
        iv_title_right2_debt_xq.setImageResource(R.mipmap.next_yq);
        iv_title_right2_debt_xq.setVisibility(View.VISIBLE);
        iv_title_right2_debt_xq.setOnClickListener(this);
        mLl_ivParent_title_debt_xq = (LinearLayout) findViewById(R.id.ll_ivParent_title);
        mLl_ivParent_title_debt_xq.setOnClickListener(this);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        listZong = new ArrayList<>();

        imageAdapterMap = new HashMap<>();

        mRoot = (LinearLayout) findViewById(R.id.root);


    }


    private void initViewsCustomer(List<NewDebtMassage.DataBean> data) {

        for (int i = 0; i < data.size(); i++) {
            CollapseView_1 collapseView = geneCollapseView_1(data.get(i).getName(),data.get(i).getUrl(),i);

            ScrollView scrollView = new ScrollView(this);
            scrollView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            linearLayout.setOrientation(LinearLayout.VERTICAL);

            List<NewDebtMassage.DataBean.SonLevelListBean> erjiList = data.get(i).getSonLevelList();
            if (i == 0) {   //有形资产

                for (int j = 0; j < erjiList.size(); j++) {

                    CollapseView_2 coll_1 = geneCollapseView_2(erjiList.get(j).getName(), erjiList.get(j).getUrl());

                    if (j == 0) {

                        View view = LayoutInflater.from(this).inflate(R.layout.listview, null);
                        view.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                        HorizontialListView listView = (HorizontialListView) view.findViewById(R.id.listview_debt_new);
                        adapter1 = new ImageAdapter(this, erjiList.get(j).getSonLevelList());
                        listView.setAdapter(adapter1);
                        coll_1.setContent(view);
                        linearLayout.addView(coll_1);
                    } else if (j == 1) {

                        View view = LayoutInflater.from(this).inflate(R.layout.listview, null);
                        view.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                        HorizontialListView listView = (HorizontialListView) view.findViewById(R.id.listview_debt_new);
                        adapter2 = new ImageAdapter2(this, erjiList.get(j).getSonLevelList());
                        listView.setAdapter(adapter2);
                        coll_1.setContent(view);
                        linearLayout.addView(coll_1);


                    } else if (j == 2) {
                        View view = LayoutInflater.from(this).inflate(R.layout.listview, null);
                        view.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                        HorizontialListView listView = (HorizontialListView) view.findViewById(R.id.listview_debt_new);
                        adapter3 = new ImageAdapter3(this, erjiList.get(j).getSonLevelList());
                        listView.setAdapter(adapter3);
                        coll_1.setContent(view);
                        linearLayout.addView(coll_1);


                    } else if (j == 3) {

                        View view = LayoutInflater.from(this).inflate(R.layout.listview, null);
                        view.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                        HorizontialListView listView = (HorizontialListView) view.findViewById(R.id.listview_debt_new);
                        adapter4 = new ImageAdapter4(this, erjiList.get(j).getSonLevelList());
                        listView.setAdapter(adapter4);
                        coll_1.setContent(view);
                        linearLayout.addView(coll_1);

                    }
                }
                scrollView.addView(linearLayout);
                collapseView.setContent(scrollView);

            } else if (i == 1) {   //无形资产

                for (int j = 0; j < erjiList.size(); j++) {

                    CollapseView_2 coll_2 = geneCollapseView_2(erjiList.get(j).getName(), erjiList.get(j).getUrl());
                    View view = LayoutInflater.from(this).inflate(R.layout.listview, null);
                    view.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                    HorizontialListView listView = (HorizontialListView) view.findViewById(R.id.listview_debt_new);
                    adapter5 = new ImageAdapter5(this, erjiList.get(j).getSonLevelList());
                    listView.setAdapter(adapter5);

                    coll_2.setContent(view);
                    linearLayout.addView(coll_2);
                }
                collapseView.setContent(linearLayout);
            }

            mRoot.addView(collapseView);
        }
    }

    private void getDebtDemand() {

        if (ShowDrawDialog(this)) {
            HttpMessageInfo<NewDebtMassage> info = new HttpMessageInfo<>(BaseUrl.ADD_DEBT_NEW, new NewDebtMassage());
            info.setiMessage(this);
            AddHeaders addHeaders = new AddHeaders();
            addHeaders.add("Authorization", UserInfoState.getToken());
            OkHttpUtils.getInstance().getServiceMassage(OkHttpUtils.TYPE_GET, info, null, addHeaders, 1);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == iv_title_right2_debt_xq) {
            next();
        } else if (v == mLl_ivParent_title_debt_xq) {
            finish();
        }
    }


    private void next() {
        listZong.clear();

        if (adapter1.getList() != null && adapter1.getList().size() != 0) {
            listZong.addAll(adapter1.getList());
        }

        if (adapter2.getList() != null && adapter2.getList().size() != 0) {
            listZong.addAll(adapter2.getList());
        }
        if (adapter3.getList() != null && adapter3.getList().size() != 0) {
            listZong.addAll(adapter3.getList());
        }
        if (adapter4.getList() != null && adapter4.getList().size() != 0) {
            listZong.addAll(adapter4.getList());
        }

        if (adapter5.getList() != null && adapter5.getList().size() != 0) {
            listZong.addAll(adapter5.getList());
        }

        DebtRelation3Vo debtV3 = new DebtRelation3Vo(listZong);
        EventBus.getDefault().postSticky(debtV3);
        ;
        Log.e("----总----", +listZong.size() + "");
        for (int i = 0; i < listZong.size(); i++) {
            Log.e("--每一条-------", listZong.get(i) + "");
        }
        ActivityCollector.startA(NewDebt_Demand.this, NewDebt_Property.class);
    }


    /**
     * 一级菜单
     *
     * @param title 标题
     * @return
     */
    private CollapseView_1 geneCollapseView_1(String title,String url,int i) {
        CollapseView_1 collapseView = new CollapseView_1(this);
        collapseView.setTitle(title);
        if(i==0){
            collapseView.expand();
        }
        collapseView.setBackgrungUrl(url);
        return collapseView;
    }

    /**
     * 二级菜单
     *
     * @param title 标题
     * @param src   图片
     * @return
     */
    private CollapseView_2 geneCollapseView_2(String title, String src) {
        CollapseView_2 collapseView = new CollapseView_2(this);
        collapseView.setTitle(title);
        collapseView.setImage(src);
        return collapseView;
    }


}
