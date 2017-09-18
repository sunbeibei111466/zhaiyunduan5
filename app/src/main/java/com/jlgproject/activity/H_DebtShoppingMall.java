package com.jlgproject.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jlgproject.MainActivity;
import com.jlgproject.R;
import com.jlgproject.base.BaseActivity;
import com.jlgproject.http.AddHeaders;
import com.jlgproject.http.BaseUrl;
import com.jlgproject.http.GetParmars;
import com.jlgproject.http.HttpMessageInfo;
import com.jlgproject.http.OkHttpUtils;
import com.jlgproject.model.Answer_Tu_Model;
import com.jlgproject.model.shangcheng.GetUrl;
import com.jlgproject.util.ActivityCollector;
import com.jlgproject.util.ConstUtils;
import com.jlgproject.util.L;
import com.jlgproject.util.ToastUtil;
import com.jlgproject.util.User;
import com.jlgproject.util.UserInfoState;
import com.tencent.smtt.export.external.interfaces.JsPromptResult;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebViewClient;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.RequestBody;

/**
 * 债事商城
 */
public class H_DebtShoppingMall extends BaseActivity implements View.OnClickListener, HttpMessageInfo.IMessage {

    private TextView mTv_Title_shopping;
    private ImageView mIv_Title_left_shopping, iv_title_right;
    private LinearLayout mLl_ivParent_shopping;
    private com.tencent.smtt.sdk.WebView txWebView;
    private String string = "";
    private int state;

    @Override
    public int loadWindowLayout() {
        return R.layout.activity_h__debt_shopping_mall;
    }

    @Override
    public void initViews() {
        //动态设置标题
        mTv_Title_shopping = (TextView) findViewById(R.id.tv_title_name);
        mTv_Title_shopping.setText(getResources().getText(R.string.zssc));
        iv_title_right = (ImageView) findViewById(R.id.iv_title_right);//返回债云端
        iv_title_right.setVisibility(View.VISIBLE);
        iv_title_right.setImageResource(R.mipmap.back_zyd);
        iv_title_right.setOnClickListener(this);
        mIv_Title_left_shopping = (ImageView) findViewById(R.id.iv_title_left);
        mIv_Title_left_shopping.setVisibility(View.VISIBLE);
        mLl_ivParent_shopping = (LinearLayout) findViewById(R.id.ll_ivParent_title);
        mLl_ivParent_shopping.setOnClickListener(this);

        state = getIntent().getIntExtra("shopBack", 0);

        txWebView = (com.tencent.smtt.sdk.WebView) findViewById(R.id.wv_sczs);
        txWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(com.tencent.smtt.sdk.WebView webView, String s) {
                if (s.contains("&a=login&")) {
                    if (!UserInfoState.isLogin()) {
                        ActivityCollector.startA(H_DebtShoppingMall.this, Login.class, ConstUtils.PD, 5);
                        finish();
                    }
                }
                return super.shouldOverrideUrlLoading(webView, s);
            }
        });
        txWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public boolean onJsAlert(com.tencent.smtt.sdk.WebView webView, String s, String s1, JsResult jsResult) {
                if (s1 != null) {
                    //弹出对话框
                    Toast.makeText(getApplicationContext(), s1, Toast.LENGTH_LONG).show();
                }
                return true;
            }


            @Override
            public boolean onJsConfirm(com.tencent.smtt.sdk.WebView webView, String s, String s1, JsResult jsResult) {
                return super.onJsConfirm(webView, s, s1, jsResult);
            }

            @Override
            public boolean onJsPrompt(com.tencent.smtt.sdk.WebView webView, String s, String s1, String s2, JsPromptResult jsPromptResult) {
                return super.onJsPrompt(webView, s, s1, s2, jsPromptResult);
            }
        });


        com.tencent.smtt.sdk.WebSettings webSettings = txWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);//支持js
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setPluginsEnabled(true);//支持插件
        webSettings.setDomStorageEnabled(true);//开启js dom storage api功能

        if (UserInfoState.isLogin()) {//App 已登录时，去后台请求数据 给php 进行商城登录
            getShoppUrl();
            L.e("----------", "app已登录");
        } else { //未登录
            txWebView.loadUrl("http://shop.zhongjinzhaishi.com/mobile/");
            L.e("----------", "app----未登录");
        }
    }


    public void getShoppUrl() {

        if (ShowDrawDialog(this)) {
            HttpMessageInfo<GetUrl> info = new HttpMessageInfo<>(BaseUrl.SHANGC, new GetUrl());
            info.setiMessage(this);
            AddHeaders add = new AddHeaders();
            add.add("Authorization", UserInfoState.getToken());
            L.e("----token-----", UserInfoState.getToken());
            OkHttpUtils.getInstance().getServiceMassage(OkHttpUtils.TYPE_GET, info, null, add, 1);
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK://处理返回键事件
                if (txWebView.canGoBack()) {
                    txWebView.goBack();//让WebView回退到上一个网页
                    return true;
                } else {//如果WebView不能回退
                    finish();
                }
                break;
            case KeyEvent.KEYCODE_SEARCH://当
                break;
            default:
                break;
        }
        return false;
    }


    @Override
    public void onClick(View v) {
        if (v == mLl_ivParent_shopping) {
            if (txWebView.canGoBack()) {
                txWebView.goBack();//让WebView回退到上一个网页
            } else {//如果WebView不能回退
                finish();
            }
        } else if (v == iv_title_right) {
            finish();
        }
    }

    public void getLoginUrl(String url) {

        if (ShowDrawDialog(this)) {
            HttpMessageInfo<String> info = new HttpMessageInfo<>(url, new String());
            info.setiMessage(this);
            OkHttpUtils.getInstance().getServiceMassage(OkHttpUtils.TYPE_GET, info, null, null, 2);
        }

    }

    @Override
    public void getServiceData(Object o) {
        if (o instanceof GetUrl) {
            GetUrl getUrl = (GetUrl) o;
            if (getUrl.getState().equals("ok")) {
                DismissDialog();
                String url = getUrl.getData();
                L.e("----url sc----" + url);
                txWebView.loadUrl(url);
            }
        }
    }


    @Override
    public void getErrorData(Call call, IOException e) {
        DismissDialog();
        ToastUtil.showShort(this, "服务器繁忙，请稍后再试");
    }
}
