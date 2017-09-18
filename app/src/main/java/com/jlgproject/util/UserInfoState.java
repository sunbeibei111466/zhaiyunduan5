package com.jlgproject.util;

import com.jlgproject.App;
import com.jlgproject.model.Login_zud;

/**
 * @author 王锋 on 2017/7/21.
 */

public class UserInfoState {

    /**
     * 判断用户登录状态
     *
     * @return
     */
    public static boolean isLogin() {
        Login_zud login_zud = (Login_zud) SharedUtil.getSharedUtil().getObject(App.getContext(), ConstUtils.IOGIN_INFO, null);
        if (login_zud != null) {
            return true;
        }
        return false;
    }

    /**返回用户Token
     *
     * @return 用户token
     */
    public static String getToken() {
        String token = null;
        if (isLogin()) {
            Login_zud login_zud = (Login_zud) SharedUtil.getSharedUtil().getObject(App.getContext(), ConstUtils.IOGIN_INFO, null);
            token = login_zud.getData().getToken();
        }
        return token;
    }


    /**给用户身份状态值重新赋值
     *
     * @param newUserType
     */
    public static void setUserType(int newUserType) {
        int user_Type = SharedUtil.getSharedUtil().getInt(App.getContext(), ConstUtils.USER_TYPE);
        if (user_Type != 0) {
            SharedUtil.getSharedUtil().remove(App.getContext(), ConstUtils.USER_TYPE);
            SharedUtil.getSharedUtil().putInt(App.getContext(), ConstUtils.USER_TYPE,newUserType);
            L.e("-----------删除并存储成功--------------");
        }else{
            SharedUtil.getSharedUtil().putInt(App.getContext(), ConstUtils.USER_TYPE,newUserType);
            L.e("-----------存储成功--------------");
        }
    }

    /**
     * 返回用户身份状态值
     * @return
     */
    public static int getUserType(){
        int user_Type = SharedUtil.getSharedUtil().getInt(App.getContext(), ConstUtils.USER_TYPE);
        if(user_Type==0){
            return 0;
        }
        return user_Type;
    }

    public static void setUserPhone(String phone) {
        String user_Type = SharedUtil.getSharedUtil().getString(App.getContext(), ConstUtils.USER_PHONE);
        if (user_Type==null) {
            SharedUtil.getSharedUtil().remove(App.getContext(), ConstUtils.USER_PHONE);
            SharedUtil.getSharedUtil().putString(App.getContext(), ConstUtils.USER_PHONE,phone);
            L.e("-----------删除并存储成功--------------");
        }else{
            SharedUtil.getSharedUtil().putString(App.getContext(), ConstUtils.USER_PHONE,phone);
            L.e("-----------存储成功--------------");
        }
    }

    /**
     * 返回用户手机号
     * @return
     */
    public static String getUserPhone(){
        String phone = SharedUtil.getSharedUtil().getString(App.getContext(), ConstUtils.USER_PHONE);
        if(phone==null){
            return null;
        }
        return phone;
    }



    /**给用户设置hangtype
     *
     * @param newUserType
     */
    public static void setHangType(int newUserType) {
        int user_Type = SharedUtil.getSharedUtil().getInt(App.getContext(), ConstUtils.HANG_TYPE);
        if (user_Type != 0) {
            SharedUtil.getSharedUtil().remove(App.getContext(), ConstUtils.HANG_TYPE);
            SharedUtil.getSharedUtil().putInt(App.getContext(), ConstUtils.HANG_TYPE,newUserType);
            L.e("-----------删除并存储成功--------------");
        }else{
            SharedUtil.getSharedUtil().putInt(App.getContext(), ConstUtils.HANG_TYPE,newUserType);
            L.e("-----------存储成功--------------");
        }
    }

    /**
     * 返回用户hangtype
     * @return
     */
    public static int getHangType(){
        int hangType = SharedUtil.getSharedUtil().getInt(App.getContext(), ConstUtils.HANG_TYPE);
        if(hangType==0){
            return 0;
        }
        return hangType;
    }

    public static boolean getUser_type(){
        int index=UserInfoState.getUserType();
        if(index==2 || index ==3){
            return true;
        }
        return false;
    }




}
