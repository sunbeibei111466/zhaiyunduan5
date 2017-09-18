package com.jlgproject.fragment;

import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jlgproject.R;
import com.jlgproject.activity.New_Debt_Matter_Preson;
import com.jlgproject.adapter.DebtManerAdapter;
import com.jlgproject.adapter.DebtManerPresonAdapter;
import com.jlgproject.adapter.DebtManerPresonSearchAdapter;
import com.jlgproject.adapter.DebtManerSearchAdapter;
import com.jlgproject.base.BaseFragment;
import com.jlgproject.http.AddHeaders;
import com.jlgproject.http.BaseUrl;
import com.jlgproject.http.GetParmars;
import com.jlgproject.http.HttpMessageInfo;
import com.jlgproject.http.OkHttpUtils;
import com.jlgproject.model.Debt_Preson_Manger;
import com.jlgproject.model.Debts_Manger;
import com.jlgproject.model.UserType;
import com.jlgproject.model.eventbusMode.DebtPreson;
import com.jlgproject.util.ActivityCollector;
import com.jlgproject.util.L;
import com.jlgproject.util.ToastUtil;
import com.jlgproject.util.User;
import com.jlgproject.util.UserInfoState;
import com.jlgproject.util.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;

/**
 * 债事人管理 fragment
 */
public class DebtMatterPersonFragment extends BaseFragment implements HttpMessageInfo.IMessage, View.OnClickListener, PullToRefreshBase.OnRefreshListener2 {


    private TextView title_name;
    private int pn = 1;
    private int ps = 5;
    private List<Debt_Preson_Manger.DataBean.ItemsBean> items;
    private LinearLayout line_qx;
    private LinearLayout lineList, ll_debt_p_search;
    private DebtManerPresonAdapter adapter;
    private DebtManerPresonSearchAdapter searchAdapter;
    private PullToRefreshListView listview, listview_search_p;
    // 1.设置几个状态码方便我们进行状态的判断
    private static final int NOMAIL = 0;
    //2.是刷新的状态
    private static final int REFRESH = 2;
    //3.上啦刷新加载更多
    private static final int LOADMORE = 3;
    private int status = 1;
    private ImageView title_lift_xz;
    private ImageView title_left;
    private int a = 1;
    private LinearLayout serchlie;
    private EditText naicard;
    private ImageView sousuo;
    private boolean state = false;
    private String trim;
    private boolean isSerch = false;
    private ImageView iv_p_search;


    @Override
    public int getLoadViewId() {
        return R.layout.fragment_debtmatter_person_manag;
    }


    @Override
    public View initView(View view) {
        EventBus.getDefault().register(this);
        naicard = (EditText) view.findViewById(R.id.et_naicard);

        title_name = (TextView) view.findViewById(R.id.tv_title_name);
        title_name.setText("债事人管理");
        serchlie = (LinearLayout) view.findViewById(R.id.serch_line);
        title_lift_xz = (ImageView) view.findViewById(R.id.iv_title_right);
        title_lift_xz.setImageResource(R.drawable.add_debt_person3);
        title_lift_xz.setVisibility(View.VISIBLE);
        title_lift_xz.setOnClickListener(this);
        title_left = (ImageView) view.findViewById(R.id.iv_title_left);
        title_left.setVisibility(View.VISIBLE);
        title_left.setImageResource(R.mipmap.search_bar);
        title_left.setOnClickListener(this);

        line_qx = (LinearLayout) view.findViewById(R.id.linerlayout);//默认权限页面
        lineList = (LinearLayout) view.findViewById(R.id.line2);//列表
        ll_debt_p_search = (LinearLayout) view.findViewById(R.id.ll_debt_p_search);//搜索

        listview = (PullToRefreshListView) view.findViewById(R.id.listview);//列表
        listview.setOnRefreshListener(this);
        listview.setMode(PullToRefreshBase.Mode.BOTH);

        listview_search_p = (PullToRefreshListView) view.findViewById(R.id.listview_search_p);//搜索
        listview_search_p.setOnRefreshListener(this);
        listview_search_p.setMode(PullToRefreshBase.Mode.BOTH);

        sousuo = (ImageView) view.findViewById(R.id.serch_fdj);//搜索图标
        sousuo.setOnClickListener(this);
        iv_p_search = (ImageView) view.findViewById(R.id.iv_p_search);//取消
        iv_p_search.setOnClickListener(this);

        getUserType();
//        int type = UserInfoState.getUserType();
//        if (type != 0) {
//            judgeState();
//        }

        return view;
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            getUserType();
            judgeState();
        }
    }

    public void getUserType() {

        if (UserInfoState.isLogin()) {
            if (ShowDrawDialog(getActivity())) {
                L.e("----请求用户type----");
                HttpMessageInfo<UserType> info = new HttpMessageInfo<>(BaseUrl.USER_TYPE, new UserType());
                info.setiMessage(this);
                AddHeaders headers = new AddHeaders();
                headers.add("Authorization", UserInfoState.getToken());
                OkHttpUtils.getInstance().getServiceMassage(OkHttpUtils.TYPE_GET, info, null, headers, 1);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v == title_left) {//搜索框
            switch (a) {
                case 1:
                    ll_debt_p_search.setVisibility(View.VISIBLE);
                    lineList.setVisibility(View.GONE);
                    a = 2;
                    break;
                case 2:
                    ll_debt_p_search.setVisibility(View.GONE);
                    lineList.setVisibility(View.VISIBLE);
                    a = 1;
                    break;
            }
        } else if (v == title_lift_xz) {//新增债事人
            if (UserInfoState.isLogin()) {
                if (User.openAddDebtPerson(getActivity(), UserInfoState.getHangType()) || UserInfoState.getUser_type()) {
                    ActivityCollector.startA(getActivity(), New_Debt_Matter_Preson.class);
                }
            }

        } else if (v == sousuo) {
            isSerch = true;//进行搜索
            lineList.setVisibility(View.GONE);
            ll_debt_p_search.setVisibility(View.VISIBLE);
            status = NOMAIL;
            trim = naicard.getText().toString().trim();
            if (!TextUtils.isEmpty(trim)) {
                Utils.closeKeyboard(getActivity());
                requstDebtMangerSearch();
            } else {
                ToastUtil.showShort(getActivity(), "请输入您要搜索的内容");
            }

        } else if (v == iv_p_search) {//搜索取消
            Utils.closeKeyboard(getActivity());
            lineList.setVisibility(View.VISIBLE);
            ll_debt_p_search.setVisibility(View.GONE);
            naicard.setText("");
            isSerch = false;
            status = NOMAIL;
            pn=1;
            requstDebtMangerList();

        }
    }


    //搜索
    private void requstDebtMangerSearch() {
        if (UserInfoState.isLogin()) {
            if (ShowDrawDialog(getActivity())) {
                HttpMessageInfo<Debt_Preson_Manger> info = new HttpMessageInfo<>(BaseUrl.DEBT_PRESON_MANGER, new Debt_Preson_Manger());
                info.setiMessage(this);
                GetParmars parmars = new GetParmars();
                parmars.add("condition", trim);
                parmars.add("pn", pn);
                parmars.add("ps", ps);
                AddHeaders headers = new AddHeaders();
                headers.add("Authorization", UserInfoState.getToken());
                OkHttpUtils.getInstance().getServiceMassage(OkHttpUtils.TYPE_GET, info, parmars, headers, 1);
            }
        }
    }

    //列表
    private void requstDebtMangerList() {
        if (UserInfoState.isLogin()) {
            if (ShowDrawDialog(getActivity())) {
                HttpMessageInfo<Debt_Preson_Manger> info = new HttpMessageInfo<>(BaseUrl.DEBT_PRESON_MANGER, new Debt_Preson_Manger());
                info.setiMessage(this);
                GetParmars parmars = new GetParmars();
                parmars.add("pn", pn);
                parmars.add("ps", ps);
                AddHeaders headers = new AddHeaders();
                headers.add("Authorization", UserInfoState.getToken());
                OkHttpUtils.getInstance().getServiceMassage(OkHttpUtils.TYPE_GET, info, parmars, headers, 1);
            }
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getDebtP(DebtPreson debtPreson) {
        int person = debtPreson.getDebtpVul();
        if (person == 1) {
            getUserType();
            status = NOMAIL;
            requstDebtMangerList();
        }
    }

    //抽取的展示数据的方法
    private void displayData(final Debt_Preson_Manger manger) {


        if (isSerch) {
            if (status == NOMAIL) {

                items = manger.getData().getItems();
                searchAdapter = new DebtManerPresonSearchAdapter(getActivity(), items);
                listview_search_p.setAdapter(searchAdapter);
                searchAdapter.notifyDataSetChanged();
            } else if (status == REFRESH) {
                L.e("--------刷新——————");
                items.clear();
                items = manger.getData().getItems();
                searchAdapter.setItems(items);
                searchAdapter.notifyDataSetChanged();
            } else if (status == LOADMORE) {

                List<Debt_Preson_Manger.DataBean.ItemsBean> items2 = manger.getData().getItems();
                if(items2.size()!=0){
                    items.addAll(items2);
                    searchAdapter.setItems(items);
                    searchAdapter.notifyDataSetChanged();
                }else{
                    ToastUtil.showShort(getActivity(),"没有更多数据了");
                }
            }

        } else {
            if (status == NOMAIL) {

                items = manger.getData().getItems();
                adapter = new DebtManerPresonAdapter(getActivity(), items);
                listview.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            } else if (status == REFRESH) {
                L.e("--------刷新——————");
                items.clear();
                items = manger.getData().getItems();
                adapter.setItems(items);
                adapter.notifyDataSetChanged();
            } else if (status == LOADMORE) {
                L.e("--------加载——————");
                List<Debt_Preson_Manger.DataBean.ItemsBean> items2 = manger.getData().getItems();
                if(items2.size()!=0){
                    items.addAll(items2);
                    adapter.setItems(items);
                    adapter.notifyDataSetChanged();
                }else{
                    ToastUtil.showShort(getActivity(),"没有更多数据了");
                }
            }
        }

    }


    private void judgeState() {

        if (UserInfoState.getUserType() == 1) {
            L.e("---------11111----------UserInfoState.getUserType()----" + UserInfoState.getUserType());
            if (User.debtMatterPreson(getActivity(), UserInfoState.getHangType())) {
                L.e("-------等于---111----有权限-------");
                line_qx.setVisibility(View.GONE);//权限页
                lineList.setVisibility(View.VISIBLE);
                title_left.setVisibility(View.VISIBLE);
                status = NOMAIL;
                requstDebtMangerList();
            } else {

                line_qx.setVisibility(View.VISIBLE);
                lineList.setVisibility(View.GONE);
                title_left.setVisibility(View.GONE);
            }
        } else {
            L.e("-------不等于---111-----");
            lineList.setVisibility(View.GONE);
            line_qx.setVisibility(View.VISIBLE);
            title_left.setVisibility(View.GONE);
        }
    }


    @Override
    public void getServiceData(Object o) {
        if (o instanceof UserType) { //根据type动态设置权限
            UserType userType = (UserType) o;
            if (userType != null) {
                DismissDialog();
                UserInfoState.setUserType(userType.getData().getUserType());
                UserInfoState.setUserPhone(userType.getData().getPhoneNumber());
                judgeState();
            } else {
                getUserType();
            }
        } else if (o instanceof Debt_Preson_Manger) {
            Debt_Preson_Manger debt_preson_manger = (Debt_Preson_Manger) o;
            if (debt_preson_manger != null) {
                DismissDialog();
                listview.onRefreshComplete();
                listview_search_p.onRefreshComplete();
                if (debt_preson_manger.getState().equals("ok")) {
                    displayData(debt_preson_manger);
                } else {
                    ToastUtil.show(getActivity(), debt_preson_manger.getMessage(), Toast.LENGTH_SHORT);
                }
            }
        }
    }

    @Override
    public void getErrorData(Call call, IOException e) {
        listview.onRefreshComplete();
        listview_search_p.onRefreshComplete();
        DismissDialog();
        ToastUtil.showShort(getActivity(), "服务器繁忙,请稍后再试");
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        pn = 1;
        status = REFRESH;
        if (isSerch) {
            requstDebtMangerSearch();
        } else {
            requstDebtMangerList();
        }

    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        pn = pn + 1;
        status = LOADMORE;
        if (isSerch) {
            requstDebtMangerSearch();
        } else {
            requstDebtMangerList();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        pn = 1;
        ps = 5;
    }
}
