package com.jlgproject.activity;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.jlgproject.R;
import com.jlgproject.base.BaseActivity;
import com.jlgproject.http.AddHeaders;
import com.jlgproject.http.BaseUrl;
import com.jlgproject.http.HttpMessageInfo;
import com.jlgproject.http.OkHttpUtils;
import com.jlgproject.model.OrderInfo;
import com.jlgproject.model.alipay.PayResult;
import com.jlgproject.model.eventbusMode.DebtBeiAn;
import com.jlgproject.model.weixin.WeiXinBean;
import com.jlgproject.util.ActivityCollector;
import com.jlgproject.util.ConstUtils;
import com.jlgproject.util.L;
import com.jlgproject.util.Pay;
import com.jlgproject.util.ToastUtil;
import com.jlgproject.util.UserInfoState;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * Created by sunbeibei on 2017/8/22.
 */

public class ChongZhi extends BaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener,HttpMessageInfo.IMessage {

    private RadioGroup radioGroup;
    private EditText chongzhi;
    private EditText bei_zhu;
    private Button ch_btn;
    private ImageView title_left;
    private TextView title_name;
    private int STARTE = 0;
    private IWXAPI api;
    private static final int ZFB_CZ = 1;
    private String type;

    @Override
    public int loadWindowLayout() {
        return R.layout.chong_zhi;
    }

    @Override
    public void initViews() {
        super.initViews();
        radioGroup = (RadioGroup) findViewById(R.id.group);
        radioGroup.setOnCheckedChangeListener(this);
        chongzhi = (EditText) findViewById(R.id.ch_zhi_money);
        bei_zhu = (EditText) findViewById(R.id.bei_zhu);
        ch_btn = (Button) findViewById(R.id.chongzhi_btn);
        ch_btn.setOnClickListener(this);
        title_left = (ImageView) findViewById(R.id.iv_title_left);
        title_left.setVisibility(View.VISIBLE);
        title_left.setOnClickListener(this);
        title_name = (TextView) findViewById(R.id.tv_title_name);
        title_name.setVisibility(View.VISIBLE);
        title_name.setText("充值");

        api = WXAPIFactory.createWXAPI(this, null);
        // 将该app注册到微信
        api.registerApp(ConstUtils.WEIXIN_ADDID);

        type = getIntent().getStringExtra("type");
        L.e("-----充值类型-----", type);

    }

    @Override
    public void onClick(View v) {
        if (v == ch_btn) {//充值


            String beizhu = "";
            if (TextUtils.isEmpty(chongzhi.getText().toString().trim())) {
                ToastUtil.showShort(this, "请输入充值金额");
                return;
            } else {
                String qianShu = chongzhi.getText().toString().trim();
                Double dou = Double.parseDouble(qianShu);
                if (dou == 0.00 || qianShu.charAt(0)==0) {
                    ToastUtil.showShort(this, "充值金额不能为0");
                    return;
                }

                if(type.equals("3")){//债币充值
                    if(qianShu.contains(".")){
                        ToastUtil.showShort(this, "充值金额不能有小数");
                        return;
                    }
                }
            }

            if (!TextUtils.isEmpty(bei_zhu.getText().toString().trim())) {
                beizhu = bei_zhu.getText().toString().trim();
            }

            String qian = chongzhi.getText().toString().trim();

            if (STARTE == 1) {//支付宝
                zfbGetOrderInfo(qian, beizhu);
            } else if (STARTE == 2) {//微信
                initWeiXinOrderInfo(qian, beizhu);
            } else {
                ToastUtil.showShort(this, "请选择充值方式");
            }


        } else if (v == title_left) {//返回
            finish();
        }

    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.zfb://支付宝支付
                STARTE = 1;
                break;
            case R.id.wb://微信支付
                STARTE = 2;
                break;
        }
    }


    //支付宝获取OrderInfo
    private void zfbGetOrderInfo(String qian, String beizhu) {
        if (ShowDrawDialog(this)) {
            HttpMessageInfo<OrderInfo> info = new HttpMessageInfo<>(BaseUrl.ZFB_ORDERINFO_GET, new OrderInfo());
            info.setiMessage(this);
            RequestBody requestBody = new FormBody.Builder()
                    .add("remark", beizhu)
                    .add("amount", qian)
                    .add("type", type)
                    .build();
            info.setFormBody(requestBody);
            AddHeaders headers = new AddHeaders();
            headers.add("Authorization", UserInfoState.getToken());
            OkHttpUtils.getInstance().getServiceMassage(OkHttpUtils.TYPE_POST, info, null, headers, 1);
        }
    }

    /**
     * 微信获取OrderInfo
     */
    public void initWeiXinOrderInfo(String qian, String beizhu) {

        if (ShowDrawDialog(this)) {
            HttpMessageInfo<WeiXinBean> info = new HttpMessageInfo<>(BaseUrl.WEIXIN_ORDERINFO_GET, new WeiXinBean());
            info.setiMessage(this);
            RequestBody requestBody = new FormBody.Builder()
                    .add("remark", beizhu)
                    .add("amount", qian)
                    .add("type", type)
                    .build();
            info.setFormBody(requestBody);
            AddHeaders headers = new AddHeaders();
            headers.add("Authorization", UserInfoState.getToken());
            OkHttpUtils.getInstance().getServiceMassage(OkHttpUtils.TYPE_POST, info, null, headers, 1);
        }
    }


    @Override
    public void getServiceData(Object o) {

        if (o instanceof OrderInfo) {
            OrderInfo orderInfo = (OrderInfo) o;
            if (orderInfo != null) {
                DismissDialog();
                if (orderInfo.getCode().equals("ok")) {
                    Pay.alipayPay(this,orderInfo.getData(), 2);
                }
            }
        } else if (o instanceof WeiXinBean) {
            WeiXinBean weixin = (WeiXinBean) o;
            if (weixin != null) {
                DismissDialog();
                if (weixin.getState().equals("ok")) {
                    WeiXinBean.DataBean.CallWxBean callWxBean = weixin.getData().getCallWx();
                    Pay.weixPay(this, callWxBean);
                }
            }
        }
    }

    @Override
    public void getErrorData(Call call, IOException e) {
        DismissDialog();
        ToastUtil.showShort(this, "服务器繁忙，请稍后再试");
    }






}
