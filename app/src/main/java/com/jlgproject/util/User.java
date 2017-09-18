package com.jlgproject.util;

import android.app.Activity;
import android.content.Context;

import com.jlgproject.MainActivity;
import com.jlgproject.activity.New_Debt_Matter;
import com.jlgproject.activity.New_Debt_Matter_Preson;

/**
 * @author 王锋 on 2017/8/2.
 */

public class User {

    /**
     * 债事备案
     */
    public static boolean openDebtFiling(Activity activity, int i) {
        switch (i) {
            case ConstUtils.USER_1://总行
            case ConstUtils.USER_2://省
            case ConstUtils.USER_3://市
            case ConstUtils.USER_5://拓展行
            case ConstUtils.USER_7://商学院
                ToastUtil.showShort(activity, "您没有权限进行债事备案");
                return false;
            case ConstUtils.USER_4://债行（服务行）
            case ConstUtils.USER_6://云债行
            case ConstUtils.USER_8:
                //可以进
                ActivityCollector.startA(activity, New_Debt_Matter.class);
                return true;
        }
        return false;
    }

    /**
     * 开通债行
     */
    public static boolean openDebtRew(Activity activity, int i) {
        switch (i) {
            case ConstUtils.USER_1://总行
            case ConstUtils.USER_2://省
            case ConstUtils.USER_3://市
            case ConstUtils.USER_5://拓展行
            case ConstUtils.USER_7://商学院
            case ConstUtils.USER_4://债行（服务行）
            case ConstUtils.USER_6://云债行
            case ConstUtils.USER_8:
                return true;
        }
        return false;
    }

    /**
     * 添加债事人
     */
    public static boolean openAddDebtPerson(Activity activity, int i) {
        switch (i) {
            case ConstUtils.USER_1://总行
            case ConstUtils.USER_2://省
            case ConstUtils.USER_3://市
            case ConstUtils.USER_5://拓展行
            case ConstUtils.USER_7://商学院
                ToastUtil.showShort(activity, "您没有权限进行添加债事人");
                return false;
            case ConstUtils.USER_4://债行（服务行）
            case ConstUtils.USER_6://云债行
            case ConstUtils.USER_8:
                ActivityCollector.startA(activity, New_Debt_Matter_Preson.class);
                return true;
        }
        return false;
    }

    /**
     * 债事管理
     *
     * @param activity
     * @param i
     * @return true 表示可以显示
     */
    public static boolean debtMatter(Activity activity, int i) {
        switch (i) {
            case ConstUtils.USER_1://总行
            case ConstUtils.USER_2://省
            case ConstUtils.USER_3://市
            case ConstUtils.USER_5://拓展行
            case ConstUtils.USER_4://债行（服务行）
            case ConstUtils.USER_6://云债行
            case ConstUtils.USER_8:
                return true;
            case ConstUtils.USER_7://商学院
                return false;
        }
        return false;
    }

    /**
     * 债事人管理
     *
     * @param activity
     * @param i
     * @return true 表示可以显示
     */
    public static boolean debtMatterPreson(Activity activity, int i) {
        switch (i) {
            case ConstUtils.USER_1://总行
            case ConstUtils.USER_2://省
            case ConstUtils.USER_3://市
            case ConstUtils.USER_5://拓展行
            case ConstUtils.USER_4://债行（服务行）
            case ConstUtils.USER_6://云债行
            case ConstUtils.USER_8:
                return true;
            case ConstUtils.USER_7://商学院
                return false;
        }
        return false;
    }

    /**
     * 债事管理搜索
     *
     * @param activity
     * @param i
     * @return true 表示显示且可以搜索
     */
    public static boolean debtMatterSearch(Activity activity, int i) {
        switch (i) {
            case ConstUtils.USER_1://总行
            case ConstUtils.USER_2://省
            case ConstUtils.USER_3://市
            case ConstUtils.USER_5://拓展行
            case ConstUtils.USER_4://债行（服务行）
            case ConstUtils.USER_6://云债行
            case ConstUtils.USER_8:
                return true;
            case ConstUtils.USER_7://商学院
                return false;
        }
        return false;
    }

    /**
     * 债事管理搜索
     *
     * @param activity
     * @param i
     * @return true 表示显示且进入详情
     */
    public static boolean myUserType(Activity activity, int i) {
        switch (i) {
            case ConstUtils.USER_1://总行
            case ConstUtils.USER_2://省
            case ConstUtils.USER_3://市
            case ConstUtils.USER_5://拓展行
            case ConstUtils.USER_4://债行（服务行）
            case ConstUtils.USER_6://云债行
            case ConstUtils.USER_8:
                return true;
            case ConstUtils.USER_7://商学院
                return false;
        }
        return false;
    }





}
