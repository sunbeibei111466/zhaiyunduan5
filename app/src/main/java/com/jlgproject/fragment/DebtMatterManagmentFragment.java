package com.jlgproject.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jlgproject.R;
import com.jlgproject.activity.DebtManagementSearch;
import com.jlgproject.activity.Login;
import com.jlgproject.activity.New_Debt_Matter;
import com.jlgproject.adapter.DebtFragmentAdapter;
import com.jlgproject.base.BaseFragment;
import com.jlgproject.http.AddHeaders;
import com.jlgproject.http.BaseUrl;
import com.jlgproject.http.HttpMessageInfo;
import com.jlgproject.http.OkHttpUtils;
import com.jlgproject.model.Login_zud;
import com.jlgproject.model.UserType;
import com.jlgproject.model.eventbusMode.CurrentItem;
import com.jlgproject.util.ActivityCollector;
import com.jlgproject.util.ConstUtils;
import com.jlgproject.util.L;
import com.jlgproject.util.NetUtils;
import com.jlgproject.util.SharedUtil;
import com.jlgproject.util.ToastUtil;
import com.jlgproject.util.User;
import com.jlgproject.util.UserInfoState;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 债事管理 fragment
 * 嵌套fragment
 */
public class DebtMatterManagmentFragment extends BaseFragment implements HttpMessageInfo.IMessage, View.OnClickListener, ViewPager.OnPageChangeListener {


    private TextView titile;
    private ImageView image_right;
    private int index = 0;
    private DebtFragmentAdapter debtFragmentAdapter;
    // 碎片集合
    private List<Fragment> fragments;
    // 按钮数组
    private RelativeLayout[] arrBtn = new RelativeLayout[2];
    // 标签文字数组
    private TextView[] arrTxt = new TextView[2];
    // 标签下划线(Indicator)
    private RelativeLayout[] arrLine = new RelativeLayout[2];
    // 滑动页容器
    private ViewPager viewPager;
    // 选中的标签颜色
    int color_selected = R.color.red;
    // 未选中的标签颜色
    int color_unselected = R.color.black;
    private ImageView title_left;
    private int userType1;
    private LinearLayout id_ly_bottombar, ll_zsgl;
    private boolean isSetUserType=false;


    @Override
    public int getLoadViewId() {
        return R.layout.fragment_debtmatter_manag;
    }

    @Override
    public void initDatas() {
        super.initDatas();
    }

    @Override
    public View initView(View view) {
        EventBus.getDefault().register(this);
        titile = (TextView) view.findViewById(R.id.tv_title_name);
        titile.setText("债事管理");
        image_right = (ImageView) view.findViewById(R.id.iv_title_right);
        image_right.setVisibility(View.VISIBLE);
        image_right.setImageResource(R.drawable.add_person3);
        image_right.setOnClickListener(this);
        title_left = (ImageView) view.findViewById(R.id.iv_title_left);
        title_left.setImageResource(R.mipmap.search_bar);
        title_left.setOnClickListener(this);
        id_ly_bottombar = (LinearLayout) view.findViewById(R.id.id_ly_bottombar);
        ll_zsgl = (LinearLayout) view.findViewById(R.id.ll_zsgl);//权限不足
        getUserType();

        if(UserInfoState.getUserType() ==1){
            if (User.debtMatter(getActivity(), UserInfoState.getHangType())) {
                id_ly_bottombar.setVisibility(View.VISIBLE);
                ll_zsgl.setVisibility(View.GONE);
            } else{
                id_ly_bottombar.setVisibility(View.GONE);
                ll_zsgl.setVisibility(View.VISIBLE);
            }
        }else{
            id_ly_bottombar.setVisibility(View.VISIBLE);
            ll_zsgl.setVisibility(View.GONE);
        }

        initViewPager();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        L.e("---------走走走走---------");
        getUserType();
        isSetUserType=true;
    }

    private void initViewPager() {
        initStyle();
        initViews(view);
        // 准备碎片
        fragments = new ArrayList<Fragment>();
        fragments.add(new DebtMatterMangChildFragment1());
        fragments.add(new DebtMatterMangChildFragment2());
        // 实例化适配器
        debtFragmentAdapter = new DebtFragmentAdapter(getFragmentManager(), fragments);
        viewPager.setAdapter(debtFragmentAdapter);// 关联适配器
        initListener();
        viewPager.setCurrentItem(0);
        setColor(0);
        if (index == 1) {
            viewPager.setCurrentItem(1);
        }
    }

    // 改变颜色
    private void initStyle() {
        color_selected = R.color.red;
    }

    private void initViews(View view) {
        // 初始化下划线(逐帧动画)
        String packageName = getContext().getPackageName();//获取当前包名
        for (int i = 0; i < 2; i++) {
            //从图片名称反射资源ID  R.id.line1
            int id = this.getResources().getIdentifier("lineWf" + (i + 1), "id", packageName);
            arrLine[i] = (RelativeLayout) view.findViewById(id);
            int id2 = this.getResources().getIdentifier("btnWf" + (i + 1), "id", packageName);
            arrBtn[i] = (RelativeLayout) view.findViewById(id2);
            int id3 = this.getResources().getIdentifier("txtWf" + (i + 1), "id", packageName);
            arrTxt[i] = (TextView) view.findViewById(id3);
            // 设置标签名
            arrTxt[i].setText(ConstUtils.Tab_Name[i]);
        }
        // 获取ViewPager对象
        viewPager = (ViewPager) view.findViewById(R.id.vp_zsba);
    }

    private void initListener() {
        // 添加按钮的监听
        for (int i = 0; i < arrBtn.length; i++) {
            arrBtn[i].setOnClickListener(this);
        }
        // 添加滑动页的监听
        viewPager.addOnPageChangeListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnWf1:
                viewPager.setCurrentItem(0);// 第一页
                break;
            case R.id.btnWf2:
                viewPager.setCurrentItem(1);// 第二页
                break;
            case R.id.iv_title_left://搜索
                ActivityCollector.startA(getActivity(), DebtManagementSearch.class);
                break;
            case R.id.iv_title_right:
                if (UserInfoState.isLogin()) {
                    if (User.openDebtFiling(getActivity(), UserInfoState.getHangType()) || UserInfoState.getUser_type()) {
                        ActivityCollector.startA(getActivity(), New_Debt_Matter.class, "back", 1);
                    }
                }
                break;
            default:
                break;
        }
    }


    /**
     * 1.将所有的背景统一颜色
     * 2.将当前选中的背景设置特殊颜色
     *
     * @param index
     */
    public void setColor(int index) {
        // "所有人"都回复最初的状态
        for (int i = 0; i < arrBtn.length; i++) {
            arrLine[i].setBackgroundColor(getResources().getColor(R.color.xian));
            arrTxt[i].setTextColor(getResources().getColor(color_unselected));
        }
        arrLine[index].setBackgroundColor(getResources().getColor(color_selected));// 特殊
        arrTxt[index].setTextColor(getResources().getColor(color_selected));

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getCurrentItem(CurrentItem currentItem) {
        index = currentItem.getIndex();
        L.e("-----fragment--index----" + index);
    }

    //获取用户身份状态
    private void getUserType() {
        if(UserInfoState.isLogin()){
            if (ShowDrawDialog(getActivity())) {
                HttpMessageInfo<UserType> info = new HttpMessageInfo<>(BaseUrl.USER_TYPE, new UserType());
                info.setiMessage(this);
                AddHeaders headers = new AddHeaders();
                headers.add("Authorization", UserInfoState.getToken());
                OkHttpUtils.getInstance().getServiceMassage(OkHttpUtils.TYPE_GET, info, null, headers, 1);
            }
        }
//        else{
//            L.e("----------请求 USERtYPE-------去登陆-------");
//            ActivityCollector.startA(getActivity(), Login.class, ConstUtils.PD, 1);
//        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {// 在最前端显示 相当于调用了onResume();
            getUserType();


            if(UserInfoState.getUserType() ==1){
                if (User.debtMatter(getActivity(), UserInfoState.getHangType())) {
                    id_ly_bottombar.setVisibility(View.VISIBLE);
                    ll_zsgl.setVisibility(View.GONE);
                    if (index == 1) {
                        viewPager.setCurrentItem(1);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                }else{
                    id_ly_bottombar.setVisibility(View.GONE);
                    ll_zsgl.setVisibility(View.VISIBLE);
                }
            }else{
                id_ly_bottombar.setVisibility(View.VISIBLE);
                ll_zsgl.setVisibility(View.GONE);
                if (index == 1) {
                    viewPager.setCurrentItem(1);
                } else {
                    viewPager.setCurrentItem(0);
                }
            }
        }
    }


    /*判断是否显示搜寻图标*/
    private void isShowSerBar() {
        if (userType1 == 1 && User.debtMatterSearch(getActivity(), UserInfoState.getHangType())) {
            title_left.setVisibility(View.VISIBLE);
        } else {
            title_left.setVisibility(View.GONE);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void getServiceData(Object o) {
        if (o instanceof UserType) {
            UserType userType = (UserType) o;
            if (userType != null) {
                DismissDialog();
                if (userType.getState().equals("ok")) {
                    userType1 = userType.getData().getUserType();
                    UserInfoState.setUserType(userType.getData().getUserType());
                    UserInfoState.setHangType(userType.getData().getHangtype());
                    isShowSerBar();
                }
                if(isSetUserType){
                    if(UserInfoState.getUserType() ==1){
                        if (User.debtMatter(getActivity(), UserInfoState.getHangType())) {
                            id_ly_bottombar.setVisibility(View.VISIBLE);
                            ll_zsgl.setVisibility(View.GONE);
                        }else{
                            id_ly_bottombar.setVisibility(View.GONE);
                            ll_zsgl.setVisibility(View.VISIBLE);
                        }
                    }else{
                        id_ly_bottombar.setVisibility(View.VISIBLE);
                        ll_zsgl.setVisibility(View.GONE);
                    }
                    isSetUserType=false;
                }
            }
        }
    }

    @Override
    public void getErrorData(Call call, IOException e) {
        DismissDialog();
        ToastUtil.showShort(getActivity(), "服务器繁忙,请稍后再试");
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        // 滑动过程中...(写动画)
    }

    @Override
    public void onPageSelected(int position) {
        // 页面的选中(当前的页面已经显示了90%)
        setColor(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        // 滑动的状态改变
    }


}
