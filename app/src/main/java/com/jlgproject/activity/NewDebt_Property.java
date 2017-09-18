package com.jlgproject.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jlgproject.MainActivity;
import com.jlgproject.R;
import com.jlgproject.adapter.newDebt.BaseAdapter_jl;
import com.jlgproject.adapter.newDebt.ShopAdapter;
import com.jlgproject.adapter.newDebt.ZongAdapter;
import com.jlgproject.base.BaseActivity;
import com.jlgproject.base.IonChlickDeleteItem;
import com.jlgproject.http.AddHeaders;
import com.jlgproject.http.BaseUrl;
import com.jlgproject.http.HttpMessageInfo;
import com.jlgproject.http.OkHttpUtils;
import com.jlgproject.model.AddDebtResponse;
import com.jlgproject.model.DebtRelation1Vo;
import com.jlgproject.model.DebtRelationVo;
import com.jlgproject.model.eventbusMode.AddDebtBean;
import com.jlgproject.model.eventbusMode.DebtBeiAn;
import com.jlgproject.model.newDebt.AssetNew;
import com.jlgproject.model.newDebt.DebtRelation3Vo;
import com.jlgproject.model.newDebt.DebtRelation4Vo;
import com.jlgproject.model.newDebt.NewDebtMassage;
import com.jlgproject.model.newDebt.StoreMode;
import com.jlgproject.util.ActivityCollector;
import com.jlgproject.util.ConstUtils;
import com.jlgproject.util.DialogUtils;
import com.jlgproject.util.JsonUtil;
import com.jlgproject.util.L;
import com.jlgproject.util.ScreenUtil;
import com.jlgproject.util.ToastUtil;
import com.jlgproject.util.UserInfoState;
import com.jlgproject.util.Utils;
import com.jlgproject.view.HomeListView;
import com.jlgproject.view.ShrinkControl.CollapseView_1;
import com.jlgproject.view.ShrinkControl.CollapseView_2;
import com.jlgproject.view.ShrinkControl.DiscreteScrollViewOptions;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.Orientation;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.RequestBody;


public class NewDebt_Property extends BaseActivity implements ShopAdapter.OnClickImageViewListener,
        IonChlickDeleteItem, View.OnClickListener, HttpMessageInfo.IMessage {

    private LinearLayout mRoot, ll_ytzc;

    private LinearLayout linearLayout1;//汽车子布局
    private LinearLayout linearLayout2;//房产子布局
    private LinearLayout linearLayout3;//家具子布局
    private LinearLayout linearLayout4;//生活用品子布局
    private boolean isOne_qc = true;
    private boolean isOne_fc = true;
    private boolean isOne_jj = true;
    private boolean isOne_shyp = true;

    private List<com.jlgproject.model.newDebt.AssetNew> zong = new ArrayList<>();

    View popupView;
    HomeListView homeListView;
    ZongAdapter zongAdapter;//总和适配器
    int one = 1;

    private TextView mTv_Title_debt_zc;
    private ImageView mIv_Title_left_debt_zc, iv_title_right2_debt_zc;
    private LinearLayout mLl_ivParent_title_debt_zc;

    private Map<String, StoreMode> modeMap = new HashMap<>();
    private Map<String, String> stringMap = new HashMap<>();
    private DebtRelation1Vo debtRelation1Vo;//第一页
    private DebtRelation3Vo debtRelation3Vo;//第三页

    @Override
    public int loadWindowLayout() {
        return R.layout.activity_new_debt__property;
    }

    @Override
    public void initViews() {
        EventBus.getDefault().register(this);
        mRoot = (LinearLayout) findViewById(R.id.root);
        ll_ytzc = (LinearLayout) findViewById(R.id.ll_ytzc);
        ll_ytzc.setOnClickListener(this);
        //动态设置标题
        mTv_Title_debt_zc = (TextView) findViewById(R.id.tv_title_name);
        mTv_Title_debt_zc.setText(getResources().getText(R.string.debt_zc));
        mIv_Title_left_debt_zc = (ImageView) findViewById(R.id.iv_title_left);
        mIv_Title_left_debt_zc.setVisibility(View.VISIBLE);
        iv_title_right2_debt_zc = (ImageView) findViewById(R.id.iv_title_right);
        iv_title_right2_debt_zc.setImageResource(R.mipmap.seve_message);
        iv_title_right2_debt_zc.setVisibility(View.VISIBLE);
        iv_title_right2_debt_zc.setOnClickListener(this);
        mLl_ivParent_title_debt_zc = (LinearLayout) findViewById(R.id.ll_ivParent_title);
        mLl_ivParent_title_debt_zc.setOnClickListener(this);


        popupView = LayoutInflater.from(this).inflate(R.layout.pupop_window, null);
        homeListView = (HomeListView) popupView.findViewById(R.id.lv_zong);
        zongAdapter = new ZongAdapter(this);

        getDebtDemand();

    }

    private void getDebtDemand() {

        HttpMessageInfo<NewDebtMassage> info = new HttpMessageInfo<>(BaseUrl.ADD_DEBT_NEW, new NewDebtMassage());
        info.setiMessage(this);
        AddHeaders addHeaders = new AddHeaders();
        addHeaders.add("Authorization", UserInfoState.getToken());
        OkHttpUtils.getInstance().getServiceMassage(OkHttpUtils.TYPE_GET, info, null, addHeaders, 1);

    }


    @Override
    public void getServiceData(Object o) {

        if (o instanceof NewDebtMassage) {
            NewDebtMassage newDebtMassage = (NewDebtMassage) o;
            if (newDebtMassage != null) {
                initViewsCustomer(newDebtMassage.getData());
            }
        }else if (o instanceof AddDebtResponse) {//新增债事备案
            final AddDebtResponse zh = (AddDebtResponse) o;
            if (zh != null) {
                DismissDialog();
                if (zh.getState().equals("warn")) {
                    if (zh.getMessage().equals("您不是债事人，请添加您的证件信息")) {  //登录用户不是债事人
                        DialogUtils.showDialogZsr(this, true, zh.getMessage().toString(), new DialogUtils.OnButtonEventListener() {
                            @Override
                            public void onConfirm() {
                                //当IS_OWER 值为 1时 表示登录用户 不是债事人
                                ActivityCollector.startA(NewDebt_Property.this, New_Debt_Matter_Preson.class, ConstUtils.IS_OWER, 1);
                            }

                            @Override
                            public void onCancel() {
                            }
                        });
                    } else {
                        ToastUtil.showShort(NewDebt_Property.this, zh.getMessage());
                    }
                } else if (zh.getState().equals("ok")) {
                    if (zh.getMessage().equals("债事备案成功,未支付")) {
                        String str;
                        String money = zh.getData().getRelation().getQianshu();//支付金额用于页面显示
                        if(money.equals("0.00")){
                            str="债事备案成功";
                        }else{
                            str=zh.getMessage();
                        }

                        DialogUtils.showConfirmDialog(this,str , "确认", true, new DialogUtils.OnButtonEventListenerConfirm() {
                            @Override
                            public void onConfirm() {
                                String orderId = zh.getData().getRelation().getOrderId();
                                String money = zh.getData().getRelation().getQianshu();//支付金额用于页面显示
                                if(money.equals("0.00")){//无需支付，成功
                                    Intent intent=new Intent(NewDebt_Property.this, MainActivity.class);
                                    intent.putExtra("currMenu",1);
                                    startActivity(intent);
                                }else{  //跳转支付页
                                    AddDebtBean addDebtBean = new AddDebtBean(orderId, money, 0);
                                    EventBus.getDefault().postSticky(addDebtBean);
                                    EventBus.getDefault().postSticky(new DebtBeiAn("1"));
                                    ActivityCollector.startA(NewDebt_Property.this, ToPay.class);
                                }
                            }
                        });
                    }
                } else if (zh.getState().equals("error")) {
                    ToastUtil.showShort(this, zh.getMessage());
                }
            }

        }
    }

    @Override
    public void getErrorData(Call call, IOException e) {

    }

    private void initViewsCustomer(List<NewDebtMassage.DataBean> data) {

        for (int i = 0; i < data.size(); i++) {

            CollapseView_1 collapseView = geneCollapseView_1(data.get(i).getName(), data.get(i).getUrl(),i);

            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            linearLayout.setOrientation(LinearLayout.VERTICAL);


            List<NewDebtMassage.DataBean.SonLevelListBean> erjiList = data.get(i).getSonLevelList();

            for (int j = 0; j < erjiList.size(); j++) {

                CollapseView_2 coll_1 = geneCollapseView_2(erjiList.get(j).getName(), erjiList.get(j).getUrl());

                List<com.jlgproject.model.newDebt.AssetNew> listItemModes = new ArrayList<>();
                BaseAdapter_jl baseAdapter_jl = new BaseAdapter_jl(this);
                baseAdapter_jl.setNewDebt_property(NewDebt_Property.this);
                LinearLayout linearLayout1 = new LinearLayout(this);
                linearLayout1.setOrientation(LinearLayout.VERTICAL);
                linearLayout1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                linearLayout1.addView(huaLang3d(erjiList.get(j).getSonLevelList()));
                coll_1.setContent(linearLayout1);
                modeMap.put(erjiList.get(j).getSonLevelList().get(j).getPId(), new StoreMode(listItemModes, linearLayout1, baseAdapter_jl));
                Log.e("----第0个---二级Id-", erjiList.get(j).getId());

                linearLayout.addView(coll_1);
            }
            collapseView.setContent(linearLayout);

            mRoot.addView(collapseView);
        }


    }



    private View huaLang3d(List<com.jlgproject.model.newDebt.SonLevelListBean> listItem) {
        View view = LayoutInflater.from(this).inflate(R.layout.activity_shop, null);
        DiscreteScrollView itemPicker = (DiscreteScrollView) view.findViewById(R.id.item_picker);
        itemPicker.setOrientation(Orientation.HORIZONTAL);
        ShopAdapter shopAdapter2 = new ShopAdapter(listItem, this);
        shopAdapter2.setOnClickImageViewListener(this);//设置监听回调
        InfiniteScrollAdapter infiniteAdapter = InfiniteScrollAdapter.wrap(shopAdapter2);
        itemPicker.setAdapter(infiniteAdapter);
        itemPicker.setItemTransitionTimeMillis(DiscreteScrollViewOptions.getTransitionTime());
        itemPicker.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.5f)
                .build());

        return view;
    }

    /**
     * 一级菜单
     */
    private CollapseView_1 geneCollapseView_1(String title, String src,int i) {
        CollapseView_1 collapseView = new CollapseView_1(this);
        collapseView.setTitle(title);
        if(i==0){
            collapseView.expand();
        }
        collapseView.setBackgrungUrl(src);
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

    @Override
    public void onClick(String erjiId, String sanjiId, String snajiStr) {

        showDebtDialog(this, false, erjiId, sanjiId, snajiStr);

    }

    public final void showDebtDialog(final Context context, boolean canceled, final String erjiId, final String sanjiId, final String snajiStr) {

        final LinearLayout layout = (LinearLayout) ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.debt_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(canceled);
        dialog.show();

        final EditText leixing = (EditText) layout.findViewById(R.id.zqr_zc_leixing);
        leixing.setText(snajiStr);
        leixing.setEnabled(false);
        final EditText name = (EditText) layout.findViewById(R.id.zqr_zc_name);
        final EditText guzhi = (EditText) layout.findViewById(R.id.zqr_zc_guzhi);
        final EditText shul = (EditText) layout.findViewById(R.id.zqr_zc_shul);
        //  点击确认
        Button btCommit = (Button) layout.findViewById(R.id.queren_zwr);
        btCommit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                if (TextUtils.isEmpty(name.getText().toString().trim())) {
                    ToastUtil.showShort(context, "不能为空");
                    return;
                }
                if (TextUtils.isEmpty(guzhi.getText().toString().trim())) {
                    ToastUtil.showShort(context, "不能为空");
                    return;
                }
                if (TextUtils.isEmpty(shul.getText().toString().trim())) {
                    ToastUtil.showShort(context, "不能为空");
                    return;
                }

                String mingc = name.getText().toString().trim();
                String sanjiStr = snajiStr;
                String guz = guzhi.getText().toString().trim();
                String sl = shul.getText().toString().trim();

                addList(new com.jlgproject.model.newDebt.AssetNew(mingc, snajiStr, guz, sl, sanjiId, erjiId), erjiId);

                dialog.dismiss();
            }


        });

        Button btCancel = (Button) layout.findViewById(R.id.quxiao_zwr);
        btCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Utils.closeKeyboard(NewDebt_Property.this);
            }
        });

        int screenWidth = ScreenUtil.getScreenWidth(context);
        dialog.getWindow().setLayout((int) (screenWidth - 60), ScreenUtil.getScreenHeight(this)/2);
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);//重新显示输入盘
        dialog.getWindow().setContentView(layout);
    }

    private void addList(com.jlgproject.model.newDebt.AssetNew assetNew, String erjiId) {

        StoreMode storeMode = modeMap.get(erjiId);

        storeMode.getList().add(assetNew);

        String strErId = String.valueOf(erjiId);

        Log.e("-----二级Id------", erjiId);
        if (!stringMap.containsKey(strErId)) {
            stringMap.put(strErId, strErId);

            View view = LayoutInflater.from(this).inflate(R.layout.item_list_tj, null);
            HomeListView homeListView1 = new HomeListView(this);
            homeListView1.addHeaderView(view);

            BaseAdapter_jl baseAdapter_jl = storeMode.getAdapter_jl();
            baseAdapter_jl.setListFC(storeMode.getList());
            homeListView1.setAdapter(baseAdapter_jl);
            storeMode.getLinearLayout().addView(homeListView1);
        } else {
            BaseAdapter_jl baseAdapter_jl = storeMode.getAdapter_jl();
            baseAdapter_jl.setListFC(storeMode.getList());
            baseAdapter_jl.notifyDataSetChanged();
        }

    }


    @Override
    public void onClickDeleteItem(String erjiId, String sanjiId, String sanjiStr, int postion) {
        StoreMode storeMode = modeMap.get(erjiId);
        storeMode.getList().remove(postion);
        storeMode.getAdapter_jl().notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {

        if (view == ll_ytzc) {

            zong.clear();
            zongAdapter.notifyDataSetChanged();

            for (StoreMode s : modeMap.values()) {
                if (s != null) {
                    zong.addAll(s.getList());
                }
            }

            if (one == 1) {
                zongAdapter.setListFC(zong);
                homeListView.setAdapter(zongAdapter);
            } else {
                zongAdapter.notifyDataSetChanged();
            }

            popupView.postInvalidate();
            // TODO: 2016/5/17 创建PopupWindow对象，指定宽度和高度
            PopupWindow window = new PopupWindow(popupView, ScreenUtil.getScreenWidth(this), ScreenUtil.getScreenHeight(this) / 2);
            // TODO: 2016/5/17 设置动画
            window.setAnimationStyle(R.style.popup_window_anim);
            // TODO: 2016/5/17 设置背景颜色
            window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F8F8F8")));
            // TODO: 2016/5/17 设置可以获取焦点
            window.setFocusable(true);
            // TODO: 2016/5/17 设置可以触摸弹出框以外的区域
            window.setOutsideTouchable(true);
            // TODO：更新popupwindow的状态
            window.update();
            // TODO: 2016/5/17 以下拉的方式显示，并且可以设置显示的位置
            window.showAsDropDown(view, 0, -150);
        } else if (view == mLl_ivParent_title_debt_zc) {//上一页
            finish();
        } else if (view == iv_title_right2_debt_zc) {//保存
            zong.clear();
            for (StoreMode s : modeMap.values()) {
                if (s != null) {
                    zong.addAll(s.getList());
                }
            }
            debtBeian(zong);
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getDebePager1(DebtRelation1Vo debt1) {
        debtRelation1Vo = debt1;
        Log.e("---------",debtRelation1Vo.getNature());
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getDebePager1(DebtRelation3Vo debtV3) {
        debtRelation3Vo = debtV3;
        Log.e("---------",debtRelation3Vo.getDemendid().get(0));
    }


    private void debtBeian(List<AssetNew> zong){
        DebtRelationVo debtBeian=new DebtRelationVo();
        debtBeian.setDebtRelation1Vo(debtRelation1Vo);
        debtBeian.setDebtRelation3Vo(debtRelation3Vo);
        debtBeian.setDebtRelation4Vo(new DebtRelation4Vo(zong));

        if (ShowDrawDialog(this)) {
            String json = JsonUtil.toJson(debtBeian);
            HttpMessageInfo<AddDebtResponse> info = new HttpMessageInfo<AddDebtResponse>(BaseUrl.ADD_DEBT_NEW_SHANGCHUAN, new AddDebtResponse());
            info.setiMessage(this);
            L.e("------json--**" + json);
            info.setFormBody(RequestBody.create(ConstUtils.JSON, json));
            AddHeaders headers = new AddHeaders();
            headers.add("Authorization", UserInfoState.getToken());
            OkHttpUtils.getInstance().getServiceMassage(OkHttpUtils.TYPE_POST, info, null, headers, 1);
        }

    }


    public interface OnButtonEventListener {

        void onConfirm(LinearLayout layout);

        void onCancel();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
