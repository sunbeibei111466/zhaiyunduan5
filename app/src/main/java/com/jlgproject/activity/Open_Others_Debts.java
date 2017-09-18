package com.jlgproject.activity;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jlgproject.R;
import com.jlgproject.base.BaseActivity;
import com.jlgproject.http.AddHeaders;
import com.jlgproject.http.BaseUrl;
import com.jlgproject.http.GetParmars;
import com.jlgproject.http.HttpMessageInfo;
import com.jlgproject.http.OkHttpUtils;
import com.jlgproject.model.AddRess.Add;
import com.jlgproject.model.OpenOthersDebtMode;
import com.jlgproject.model.Open_Debts;
import com.jlgproject.model.Open_DebtsInfo;
import com.jlgproject.model.Yzm;
import com.jlgproject.model.eventbusMode.AddDebtBean;
import com.jlgproject.util.ActivityCollector;
import com.jlgproject.util.ConstUtils;
import com.jlgproject.util.CountdownTimer_utils;
import com.jlgproject.util.DialogUtils;
import com.jlgproject.util.JsonUtil;
import com.jlgproject.util.L;
import com.jlgproject.util.ProvinceCq;
import com.jlgproject.util.ToastUtil;
import com.jlgproject.util.UserInfoState;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.RequestBody;

/**
 * Created by sunbeibei on 2017/8/30.
 */

public class Open_Others_Debts extends BaseActivity implements View.OnClickListener, HttpMessageInfo.IMessage {

    private EditText name;
    private EditText open_organization_code;
    private EditText open_phone;

    private EditText open_mima;
    private EditText open_queren_mima;
    private EditText open_tjbm;
    private Button open_debt;
    private ImageView title_left;
    private TextView title_name;

    private Spinner mProvinceSpinner;//省
    private Spinner mCitySpinner;//市
    private Spinner mAreaSpinner;//区

    // 列表选择的省市区
    public String selectedPro = "";
    public String selectedCity = "";
    public String selectedArea = "";
    // 省份
    public String[] mProvinceDatas;
    // 城市
    public String[] mCitiesDatas;
    // 地区
    public String[] mAreaDatas;

    public ArrayAdapter<String> mProvinceAdapter;
    public ArrayAdapter<String> mCityAdapter;
    public ArrayAdapter<String> mAreaAdapter;
    // 存储省对应的所有市 name
    private Map<String, String[]> mCitiesDataMap = new HashMap<String, String[]>();
    // 存储市对应的所有区 name
    private Map<String, String[]> mAreaDataMap = new HashMap<String, String[]>();
    //省市區Id
    private String shengId = null, shiId = null, quId = null;
    private Add ssq;//地址

    @Override
    public int loadWindowLayout() {
        return R.layout.open_others_debts;
    }

    @Override
    public void initViews() {
        super.initViews();
        name = (EditText) findViewById(R.id.open_debt_name);//姓名
        open_organization_code = (EditText) findViewById(R.id.open_organization_code);//身份证号码
        open_phone = (EditText) findViewById(R.id.open_phone);//手机号
        open_mima = (EditText) findViewById(R.id.open_mima);//设置密码
        open_queren_mima = (EditText) findViewById(R.id.open_queren_mima);//确认密码
        open_tjbm = (EditText) findViewById(R.id.open_tjbm);//推荐编码
        open_debt = (Button) findViewById(R.id.open_others);//开通债行
        open_debt.setOnClickListener(this);
        mProvinceSpinner = (Spinner) findViewById(R.id.spinner_pro);//省
        mCitySpinner = (Spinner) findViewById(R.id.spinner_city);//市
        mAreaSpinner = (Spinner) findViewById(R.id.spinner_area);//区
        title_left = (ImageView) findViewById(R.id.iv_title_left);
        title_left.setOnClickListener(this);
        title_left.setVisibility(View.VISIBLE);
        title_left.setImageResource(R.mipmap.back);
        title_name = (TextView) findViewById(R.id.tv_title_name);
        title_name.setVisibility(View.VISIBLE);
        title_name.setText("开通债行");
        open_tjbm.setText(UserInfoState.getUserPhone());//推荐人编码
        ActivityCollector.startA(Open_Others_Debts.this, DialogActivity.class, "dialogIndex", 2);

        jsonCitisData();
        initSpinner();
    }

    @Override
    public void initDatas() {
        super.initDatas();

    }

    @Override
    public void onClick(View v) {
        if (v == open_debt) {
            open_debt();

        } else if (v == title_left) {
            finish();
        }
    }


    private void open_debt() {

        if (TextUtils.isEmpty(name.getText().toString().trim())) {
            ToastUtil.showShort(this, "请输入开通债行人的姓名");
            return;
        }
        if (TextUtils.isEmpty(open_organization_code.getText().toString().trim())) {
            ToastUtil.showShort(this, "请输入开通债行人的身份证号");
            return;
        }

        if (TextUtils.isEmpty(open_phone.getText().toString().trim())) {
            ToastUtil.showShort(this, "请输入开通债行人的身份证号");
            return;
        }

        if (TextUtils.isEmpty(open_mima.getText().toString().trim())) {
            ToastUtil.showShort(this, "请设置密码");
            return;
        }
        if (TextUtils.isEmpty(open_queren_mima.getText().toString().trim())) {
            ToastUtil.showShort(this, "请再次输入密码");
            return;
        }

        String nameOthers=name.getText().toString().trim();
        String sfz_code=open_organization_code.getText().toString().trim();
        String phone=open_phone.getText().toString().trim();
        String mima=open_mima.getText().toString().trim();
        String querenMima=open_queren_mima.getText().toString().trim();
        String sheng=shengId;
        String shi=shiId;
        String qu=quId;

        OpenOthersDebtMode openOthersDebtMode=new OpenOthersDebtMode();
        openOthersDebtMode.setRealname(nameOthers);
        openOthersDebtMode.setIdNumber(sfz_code);
        openOthersDebtMode.setPassword(mima);
        openOthersDebtMode.setMobile(phone);
        openOthersDebtMode.setRepassword(querenMima);
        openOthersDebtMode.setRecommendCode(open_tjbm.getText().toString());
        openOthersDebtMode.setProvinceId(sheng);
        openOthersDebtMode.setCityId(shi);
        openOthersDebtMode.setCountyId(qu);
        requstOpenOthersDebts(openOthersDebtMode);

    }

    //给被人开通债行
    private void requstOpenOthersDebts(OpenOthersDebtMode openOthersDebtMode) {
        if (ShowDrawDialog(this)) {
            String string = JsonUtil.toJson(openOthersDebtMode);
            L.e("----开通债行---" + string);
            RequestBody body = RequestBody.create(ConstUtils.JSON, string);
            HttpMessageInfo<Open_Debts> info = new HttpMessageInfo<>(BaseUrl.OPEN_OTHERS_DEBTS, new Open_Debts());
            info.setiMessage(this);
            info.setFormBody(body);
            AddHeaders header = new AddHeaders();
            header.add("Authorization", UserInfoState.getToken());
            OkHttpUtils.getInstance().getServiceMassage(OkHttpUtils.TYPE_POST, info, null, header, 1);
        }
    }


    private void jsonCitisData() {

        Gson gson = new Gson();
        ssq = gson.fromJson(ProvinceCq.InitData(), Add.class);
        int shengSize = ssq.getData().size();//省个数
        mProvinceDatas = new String[shengSize];

        String mProvinceStr;
        for (int i = 0; i < shengSize; i++) {

            mProvinceStr = ssq.getData().get(i).getValue();
            mProvinceDatas[i] = mProvinceStr;
            int shiSize = ssq.getData().get(i).getChild().size();//市个数
            mCitiesDatas = new String[shiSize];

            String mCityStr;
            for (int j = 0; j < shiSize; j++) {
                mCityStr = ssq.getData().get(i).getChild().get(j).getValue();
                mCitiesDatas[j] = mCityStr;
                int quSize = ssq.getData().get(i).getChild().get(j).getChild().size();
                mAreaDatas = new String[quSize];
                for (int k = 0; k < quSize; k++) {
                    mAreaDatas[k] = ssq.getData().get(i).getChild().get(j).getChild().get(k).getValue();
                }
                // 存储市对应的所有第三级区域
                mAreaDataMap.put(mCityStr, mAreaDatas);
            }
            // 存储省份对应的所有市
            mCitiesDataMap.put(mProvinceStr, mCitiesDatas);
        }
    }

    private void initSpinner() {

        //设置未下拉时的布局
        mProvinceAdapter = new ArrayAdapter<String>(this, R.layout.my_spinner_item, mProvinceDatas);
        //设置下拉时的布局
        mProvinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mProvinceSpinner.setAdapter(mProvinceAdapter);

        // 省份选择
        mProvinceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedPro = "";
                selectedPro = (String) parent.getSelectedItem();
                // 根据省份更新城市区域信息
                shengId = updateCity(selectedPro);
                Log.d("--H_OpenDebt-", "mProvinceSpinner has excuted !" + "selectedPro is " + selectedPro);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // 市选择
        mCitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCity = "";


                selectedCity = (String) parent.getSelectedItem();
                shiId = updateArea(selectedPro, selectedCity);
                Log.d("----H_OpenDebt--", "mCitySpinner has excuted !" + "selectedCity is " + selectedCity);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // 区选择
        mAreaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedArea = "";
                selectedArea = (String) parent.getSelectedItem();
                quId = getQuId(selectedPro, selectedCity, selectedArea);
                Log.e("--区--id--", selectedArea + "------" + quId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * 根据省份更新城市数据    返回省id
     */
    private String updateCity(String pro) {
        String shengId = "";
        String[] cities = null;
        List<Add.DataBean.ChildBean> shiNameList;
        for (int i = 0; i < ssq.getData().size(); i++) {
            if (pro.equals(ssq.getData().get(i).getValue())) {
                shiNameList = ssq.getData().get(i).getChild();
                shengId = ssq.getData().get(i).getId();
                Log.e("-----", "---选择---省 Id---- " + shengId);
                cities = new String[shiNameList.size()];
                for (int j = 0; j < shiNameList.size(); j++) {
                    cities[j] = shiNameList.get(j).getValue();
                }
            }
        }

        // 存在区
        mCityAdapter = new ArrayAdapter<String>(this, R.layout.my_spinner_item, cities);
        mCityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCitySpinner.setAdapter(mCityAdapter);
        mCityAdapter.notifyDataSetChanged();
        mCitySpinner.setSelection(0);
        return shengId;
    }

    /**
     * 根据市 选择对应的区  返回市Id
     */
    private String updateArea(String pro, String city) {

        String[] areas = null;
        String shiId = "";

        //市 集合
        List<Add.DataBean.ChildBean> shiNameList = null;
        //区 集合
        List<com.jlgproject.model.AddRess.ChildBean> quNameList = null;

        for (int i = 0; i < ssq.getData().size(); i++) {//循环省

            //找到指定省
            if (pro.equals(ssq.getData().get(i).getValue())) {
                //拿到指定省下的市
                shiNameList = ssq.getData().get(i).getChild();
                //循环市
                for (int j = 0; j < shiNameList.size(); j++) {

                    //找到指定市
                    if (city.equals(shiNameList.get(j).getValue())) {
                        shiId = shiNameList.get(j).getId();
                        quNameList = shiNameList.get(j).getChild();
                        if (quNameList != null && quNameList.size() != 0) {
                            areas = new String[quNameList.size()];
                            for (int k = 0; k < quNameList.size(); k++) {
                                areas[k] = quNameList.get(k).getValue();
                            }
                        } else {
                            areas = null;
                        }
                    }
                }
            }
        }
        // 存在区
        if (areas != null) {
            // 存在区
            mAreaSpinner.setVisibility(View.VISIBLE);
            mAreaAdapter = new ArrayAdapter<String>(this, R.layout.my_spinner_item, areas);
            mAreaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mAreaSpinner.setAdapter(mAreaAdapter);
            mAreaAdapter.notifyDataSetChanged();
            mAreaSpinner.setSelection(0);
        } else {
            L.e("-------隱藏-------");
            mAreaSpinner.setVisibility(View.GONE);
            quId = "";
        }
        return shiId;
    }

    /**
     * 获取区Id
     *
     * @param pro
     * @param city
     * @param area
     * @return
     */
    private String getQuId(String pro, String city, String area) {

        String shiId = null;
        String quId = "";
        //市 集合
        List<Add.DataBean.ChildBean> shiNameList = null;
        //区 集合
        List<com.jlgproject.model.AddRess.ChildBean> quNameList = null;
        for (int i = 0; i < ssq.getData().size(); i++) {//循环省

            //找到指定省
            if (pro.equals(ssq.getData().get(i).getValue())) {
                //拿到指定省下的市
                shiNameList = ssq.getData().get(i).getChild();
                //循环市
                for (int j = 0; j < shiNameList.size(); j++) {
                    //找到指定市
                    if (city.equals(shiNameList.get(j).getValue())) {

                        shiId = shiNameList.get(j).getId();
                        quNameList = shiNameList.get(j).getChild();
                        for (int k = 0; k < quNameList.size(); k++) {
                            if (area.equals(quNameList.get(k).getValue())) {
                                quId = quNameList.get(k).getId();
                                Log.e("---", "---选择---区---- " + quNameList.get(k).getValue());
                                Log.e("---", "---选择---区--Id-- " + quNameList.get(k).getId());
                            }
                        }
                    }
                }
            }
        }
        return quId;
    }


    @Override
    public void getServiceData(Object o) {
        if (o instanceof Open_Debts) {  //开通债行
            final Open_Debts open_debts = (Open_Debts) o;
            if (open_debts != null) {
                DismissDialog();
                if (open_debts.getState().equals("ok")) {
                    if (open_debts.getMessage().equals("已录入信息，请去支付") || open_debts.getMessage().equals("已录入过信息，未支付，请支付")) { //开通债行
                        DialogUtils.showConfirmDialog(this, open_debts.getMessage(), "确认", true, new DialogUtils.OnButtonEventListenerConfirm() {
                            @Override
                            public void onConfirm() {
                                String orderId = open_debts.getData().getOpenOrderId().toString();
                                String money = open_debts.getData().getPayAmount().toString();
                                AddDebtBean addDebtBean = new AddDebtBean(orderId, money,2);
                                EventBus.getDefault().postSticky(addDebtBean);
                                ActivityCollector.startA(Open_Others_Debts.this, ToPay.class);
                            }
                        });
                    }else{
                        ToastUtil.showShort(this,open_debts.getMessage());
                    }
                } else  {//添加失败
                    ToastUtil.showShort(this, open_debts.getMessage());
                }
            }
        }
    }

    @Override
    public void getErrorData(Call call, IOException e) {
        DismissDialog();
        ToastUtil.showShort(this, "服务器繁忙请稍后再试");
    }
}
