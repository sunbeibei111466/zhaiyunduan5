package com.jlgproject.model;

import java.io.Serializable;

/**
 * Created by sunbeibei on 2017/9/12.
 */

public class Yue_Pay_Bean implements Serializable {

    /**
     * state : ok
     * message : 账户余额支付成功
     * data : 500.00
     */

    private String state;
    private String message;
    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
