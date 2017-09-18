package com.jlgproject.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jlgproject.R;
import com.jlgproject.activity.Debt_Matter_Management_Preson_Details;
import com.jlgproject.activity.Details_Asserts_Information;
import com.jlgproject.adapter.Asset_Information_Adapter;
import com.jlgproject.base.BaseFragment;
import com.jlgproject.http.AddHeaders;
import com.jlgproject.http.BaseUrl;
import com.jlgproject.http.GetParmars;
import com.jlgproject.http.HttpMessageInfo;
import com.jlgproject.http.OkHttpUtils;
import com.jlgproject.model.Asserts_Infromations;
import com.jlgproject.model.Asserts_Infromations_new;
import com.jlgproject.model.AssetNew;
import com.jlgproject.model.eventbusMode.AssetEvent;
import com.jlgproject.model.newDebt.ListItemMode;
import com.jlgproject.util.ConstUtils;
import com.jlgproject.util.JsonUtil;
import com.jlgproject.util.L;
import com.jlgproject.util.ScreenUtil;
import com.jlgproject.util.ToastUtil;
import com.jlgproject.util.UserInfoState;
import com.jlgproject.util.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.RequestBody;

/**
 * Created by sunbeibei on 2017/5/6.
 * 资产信息
 */

public class Asset_Information extends BaseFragment implements HttpMessageInfo.IMessage, PullToRefreshBase.OnRefreshListener2, Asset_Information_Adapter.OnClickUpdata {


    private PullToRefreshListView listView;

    private int pn = 1;
    private int ps = 8;
    // 1.设置几个状态码方便我们进行状态的判断
    private static final int NORMAL = 1;
    //2.是刷新的状态
    private static final int REFRESH = 2;
    //3.上啦刷新加载更多
    private static final int LOADMORE = 3;
    private int status = 1;
    private List<Asserts_Infromations_new.DataBean.ItemsBean> items;
    private Asset_Information_Adapter asset_info_adapter;
    private String name;
    private Long grId;
    private String grType;
    private long qyId;
    private String qyType;
    private int shua;


    @Override
    public int getLoadViewId() {
        return R.layout.asset_information;
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
        int pp = 1;
        ++pp;
        L.e("--------资产信息---执行-----" + pp);
        requestAssertInformation();

    }


    @Override
    public void initDatas() {
        grId = Debt_Matter_Management_Preson_Details.id;
        name = Debt_Matter_Management_Preson_Details.name;
        grType = Debt_Matter_Management_Preson_Details.tyep;

    }


    private void requestAssertInformation() {
        if (ShowDrawDialog(getActivity())) {
            GetParmars parmars = new GetParmars();
            parmars.add("debtid", grId);
            parmars.add("pn", pn);
            parmars.add("ps", ps);
            AddHeaders headers = new AddHeaders();
            headers.add("Authorization", UserInfoState.getToken());
            HttpMessageInfo<Asserts_Infromations_new> info = new HttpMessageInfo<>(BaseUrl.ASSERT_INFORMATION_NEW, new Asserts_Infromations_new());
            info.setiMessage(this);
            OkHttpUtils.getInstance().getServiceMassage(OkHttpUtils.TYPE_GET, info, parmars, headers, 1);
        }
    }

    @Override
    public void getServiceData(Object o) {
        if (o instanceof Asserts_Infromations_new && o != null) {
            Asserts_Infromations_new infromations = (Asserts_Infromations_new) o;
            DismissDialog();
            if (infromations != null) {
                listView.onRefreshComplete();
                if (infromations.getState().equals("ok")) {
                    displayData(infromations);
                } else {
                    ToastUtil.showShort(getActivity(), infromations.getMessage());
                }
            }
        }
    }

    @Override
    public void getErrorData(Call call, IOException e) {
        DismissDialog();
        listView.onRefreshComplete();
        ToastUtil.showShort(getActivity(), "服务器繁忙，请稍后再试");
    }


    //抽取的展示数据的方法
    private void displayData(final Asserts_Infromations_new info) {
        int total = info.getData().getTotal();
//        if (total == 0) {
//            ToastUtil.showShort(getActivity(), "暂无资产信息");
//        } else {

        if (status == NORMAL) {
            items = info.getData().getItems();
            if (items != null) {
                asset_info_adapter = new Asset_Information_Adapter(getActivity());
                asset_info_adapter.setItems(items);
                asset_info_adapter.setOnClickUpdata(this);
                listView.setAdapter(asset_info_adapter);
            } else {
                ToastUtil.showShort(getActivity(), "暂无资产信息");
            }
        } else {

            if (status == REFRESH) {
                items.clear();
                items = info.getData().getItems();
                asset_info_adapter.setItems(items);
                listView.setAdapter(asset_info_adapter);
            } else if (status == LOADMORE) {
                int size = items.size();
                if (size < 8) {
                    asset_info_adapter.setItems(items);
                    listView.setAdapter(asset_info_adapter);
                    asset_info_adapter.notifyDataSetChanged();
                } else {
                    List<Asserts_Infromations_new.DataBean.ItemsBean> items2 = info.getData().getItems();
                    items.addAll(items2);
                    asset_info_adapter.setItems(items);
                    listView.setAdapter(asset_info_adapter);
                    asset_info_adapter.notifyDataSetChanged();
                }
            }
        }
    }




    @Override
    public void clickUpdata(final AssetNew assetNew) {


        final LinearLayout layout = (LinearLayout) ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.debt_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);
        dialog.show();

        final EditText leixing = (EditText) layout.findViewById(R.id.zqr_zc_leixing);
        final EditText name = (EditText) layout.findViewById(R.id.zqr_zc_name);
        final EditText guzhi = (EditText) layout.findViewById(R.id.zqr_zc_guzhi);
        final EditText shul = (EditText) layout.findViewById(R.id.zqr_zc_shul);

        leixing.setText(assetNew.getModel());
        leixing.setEnabled(false);
        name.setText(assetNew.getName());
        name.setEnabled(false);
        guzhi.setText(assetNew.getTotalAmout());
        shul.setText(assetNew.getAssetNum());

        //  点击确认
        Button btCommit = (Button) layout.findViewById(R.id.queren_zwr);
        btCommit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(name.getText().toString().trim())) {
                    ToastUtil.showShort(getActivity(), "不能为空");
                    return;
                }
                if (TextUtils.isEmpty(guzhi.getText().toString().trim())) {
                    ToastUtil.showShort(getActivity(), "不能为空");
                    return;
                }
                if (TextUtils.isEmpty(shul.getText().toString().trim())) {
                    ToastUtil.showShort(getActivity(), "不能为空");
                    return;
                }

                String mingc = name.getText().toString().trim();
                String leixin = leixing.getText().toString().trim();
                String guz = guzhi.getText().toString().trim();
                String sl = shul.getText().toString().trim();

                AssetNew assetNew1=new AssetNew();
                assetNew1.setId(assetNew.getId());
                assetNew1.setName(mingc);
                assetNew1.setModel(leixin);
                assetNew1.setTotalAmout(guz);
                assetNew1.setAssetNum(sl);
                requestUpdate(assetNew1);
                dialog.dismiss();
            }


        });

        Button btCancel = (Button) layout.findViewById(R.id.quxiao_zwr);
        btCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                dialog.dismiss();
//                Utils.closeKeyboard(getActivity());
                InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
            }
        });

        int screenWidth = ScreenUtil.getScreenWidth(getActivity());
        dialog.getWindow().setLayout((int) (screenWidth - 60), WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);//重新显示输入盘
        dialog.getWindow().setContentView(layout);
    }



    //编辑item
    private void requestUpdate(AssetNew assetNew) {

        String str = JsonUtil.toJson(assetNew);
        HttpMessageInfo<String> info = new HttpMessageInfo<>(BaseUrl.ASSERT_UPDATA_NEW, new String());
        info.setiMessage(this);
        info.setFormBody(RequestBody.create(ConstUtils.JSON, str));
        AddHeaders addHeaders=new AddHeaders();
        addHeaders.add("Authorization",UserInfoState.getToken());
        OkHttpUtils.getInstance().getServiceMassage(OkHttpUtils.TYPE_POST, info, null, addHeaders, 1);

    }


    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        status = REFRESH;
        pn = 1;
        requestAssertInformation();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        status = LOADMORE;
        pn = pn + 1;
        requestAssertInformation();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        pn = 1;
        ps = 8;
        shua = 0;
    }
}

