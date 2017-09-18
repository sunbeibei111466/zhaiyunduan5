package com.jlgproject.model.shangcheng;

import java.io.Serializable;

/**
 * @author 王锋 on 2017/8/16.
 */

public class GetUrl implements Serializable{


    /**
     * state : ok
     * message : 请求成功
     * data : http://shop.zhongjinzhaishi.com/mobile/index.php?m=default&c=index&a=index&u=51
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
