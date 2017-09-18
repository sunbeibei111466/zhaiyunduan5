package com.jlgproject.model;

import java.io.Serializable;

/**
 * @author 王锋 on 2017/7/17.
 */

public class VersionBean implements Serializable{


    /**
     * state : ok
     * message :
     * data : {"id":1,"versionNum":"2.0.2","isForce":2,"updateItems":"1、新增开通债行功能。2、新增支付功能。3、优化我的页面。4、整体优化APP性能。","type":"android","downUrl":"http://api.zhongjin.com","versionCode":7,"content":"中金债事"}
     */

    private String state;
    private String message;
    /**
     * id : 1
     * versionNum : 2.0.2
     * isForce : 2
     * updateItems : 1、新增开通债行功能。2、新增支付功能。3、优化我的页面。4、整体优化APP性能。
     * type : android
     * downUrl : http://api.zhongjin.com
     * versionCode : 7
     * content : 中金债事
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

    public static class DataBean implements Serializable{
        private int id;
        private String versionNum;
        private int isForce;
        private String updateItems;
        private String type;
        private String downUrl;
        private int versionCode;
        private String content;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getVersionNum() {
            return versionNum;
        }

        public void setVersionNum(String versionNum) {
            this.versionNum = versionNum;
        }

        public int getIsForce() {
            return isForce;
        }

        public void setIsForce(int isForce) {
            this.isForce = isForce;
        }

        public String getUpdateItems() {
            return updateItems;
        }

        public void setUpdateItems(String updateItems) {
            this.updateItems = updateItems;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDownUrl() {
            return downUrl;
        }

        public void setDownUrl(String downUrl) {
            this.downUrl = downUrl;
        }

        public int getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(int versionCode) {
            this.versionCode = versionCode;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
