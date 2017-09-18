package com.jlgproject.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.jlgproject.App;
import com.jlgproject.activity.ChongZhi;
import com.jlgproject.activity.PaySuccess;
import com.jlgproject.activity.ToPay;
import com.jlgproject.model.alipay.PayResult;
import com.jlgproject.model.eventbusMode.DebtBeiAn;
import com.jlgproject.model.weixin.WeiXinBean;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

/**
 * @author 王锋 on 2017/8/30.
 */

public class Pay {

    private static final int SDK_PAY_FLAG = 1;
    private static final int ZFB_CZ = 1;
    private static int state;
    public static Activity mActivity;

    private static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == SDK_PAY_FLAG) {
                PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                /**
                 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                 */
                String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                String resultStatus = payResult.getResultStatus();//返回6001 失败
                //判断resultStatus 为9000则代表支付成功
                if (TextUtils.equals(resultStatus, "9000")) {
                    // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
//                Toast.makeText(ToPay.this, payResult.getMemo(), Toast.LENGTH_SHORT).show();
                    if (state == 0) {//债事备案
                        EventBus.getDefault().postSticky(new DebtBeiAn("1"));
                    } else if (state == 1 || state == 2) {//开通债行
                        ToastUtil.showShort(App.getContext(), "开通债行成功");
                    }
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ActivityCollector.startA(mActivity, PaySuccess.class);
                            mActivity.finish();
                        }
                    }, 500);
                } else if (TextUtils.equals(resultStatus, "6001")) {
                    Toast.makeText(mActivity, payResult.getMemo(), Toast.LENGTH_SHORT).show();
                }
            } else if (msg.what == ZFB_CZ) {
                PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                /**
                 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                 */
                String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                String resultStatus = payResult.getResultStatus();//返回6001 失败
                //判断resultStatus 为9000则代表支付成功
                if (TextUtils.equals(resultStatus, "9000")) {
                    // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                    ActivityCollector.startA(mActivity, PaySuccess.class);
                    mActivity.finish();
                } else if (TextUtils.equals(resultStatus, "6001")) {
                    Toast.makeText(mActivity, payResult.getMemo(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    //微信支付
    public static void weixPay(Context context, WeiXinBean.DataBean.CallWxBean callWxBean) {

        IWXAPI api = WXAPIFactory.createWXAPI(context, null);
        // 将该app注册到微信
        api.registerApp(ConstUtils.WEIXIN_ADDID);

        new PermissionModel(11, Manifest.permission.SYSTEM_ALERT_WINDOW, "允许一个程序打开窗口使用");


        PayReq payReq = new PayReq();
        payReq.appId = callWxBean.getAppid();
        payReq.partnerId = callWxBean.getPartnerid();
        payReq.prepayId = callWxBean.getPrepayid();
        payReq.packageValue = callWxBean.getPackageX();
        payReq.nonceStr = callWxBean.getNoncestr();
        payReq.timeStamp = callWxBean.getTimestamp();
        payReq.sign = callWxBean.getSign();
        api.sendReq(payReq);
        L.e("----api.sendReq(payReq)------" + api.sendReq(payReq));

    }


    /**
     * @param activity
     * @param orderInfo
     * @param i         ==1 开行备案支付   2 充值
     */
    public static void alipayPay(final Activity activity, final String orderInfo, final int i) {
        mActivity = activity;
        handler = new Handler();
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                if (orderInfo != null) {
                    PayTask alipay = new PayTask(activity);
                    Map<String, String> result = alipay.payV2(orderInfo, true);
                    Log.i("msp", result.toString());
                    Message msg = new Message();
                    if (i == 1) {
                        msg.what = SDK_PAY_FLAG;
                    } else if (i == 2) {
                        msg.what = ZFB_CZ;
                    }
                    msg.obj = result;
                    handler.sendMessage(msg);
                } else {
                    Log.e("---orderInfo为空---", "" + orderInfo);
                }
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

}
