package com.jlgproject.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author 王锋 on 2017/8/9.
 */

public class AnswerMode implements Serializable {


    /**
     * state : ok
     * message :
     * data : {"pageNum":1,"total":0,"items":[{"id":1,"createTime":"2017-08-09 11:32","subtitle":"2"},{"id":2,"createTime":"2017-08-09 11:32","subtitle":"2"}]}
     */

    private String state;
    private String message;
    /**
     * pageNum : 1
     * total : 0
     * items : [{"id":1,"createTime":"2017-08-09 11:32","subtitle":"2"},{"id":2,"createTime":"2017-08-09 11:32","subtitle":"2"}]
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
        private int pageNum;
        private int total;
        /**
         * id : 1
         * createTime : 2017-08-09 11:32
         * subtitle : 2
         */

        private List<ItemsBean> items;

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean implements Serializable{
            private int id;
            private String createTime;
            private String subtitle;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getSubtitle() {
                return subtitle;
            }

            public void setSubtitle(String subtitle) {
                this.subtitle = subtitle;
            }
        }
    }
}
