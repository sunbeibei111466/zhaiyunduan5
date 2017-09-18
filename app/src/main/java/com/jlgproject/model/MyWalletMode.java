package com.jlgproject.model;

import java.io.Serializable;

/**
 * @author 王锋 on 2017/8/24.
 */

public class MyWalletMode implements Serializable {


    /**
     * state : ok
     * message : 获取账户余额成功
     * data : {"user_money":"0.00","pay_points":"0"}
     */

    private String state;
    private String message;
    /**
     * user_money : 0.00
     * pay_points : 0
     */

    private DataBean data;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String user_money;
        private String pay_points;

        public String getUser_money() {
            return user_money;
        }

        public void setUser_money(String user_money) {
            this.user_money = user_money;
        }

        public String getPay_points() {
            return pay_points;
        }

        public void setPay_points(String pay_points) {
            this.pay_points = pay_points;
        }
    }
}
