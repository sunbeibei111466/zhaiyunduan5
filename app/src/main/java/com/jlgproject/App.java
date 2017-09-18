package com.jlgproject;

import android.app.Application;
import android.content.Context;

import com.jlgproject.view.ShrinkControl.DiscreteScrollViewOptions;
import com.tencent.smtt.sdk.QbSdk;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 王锋 on 2017/4/24.
 */

public class App extends Application {

    private static Context mContext;
    public static List<?> images = new ArrayList<>();
    private static App instance;
    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();

        //友盟
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
        //使用集成测试环境
//        MobclickAgent.setDebugMode(true);
        MobclickAgent. startWithConfigure(new MobclickAgent.UMAnalyticsConfig(this,"5962ffb65312dd802a000796", "TengXu_YYB", MobclickAgent.EScenarioType.E_UM_NORMAL));

        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(),null);

        instance = this;
        DiscreteScrollViewOptions.init(this);
    }

    public static Context getContext() {
        return mContext;
    }
}
