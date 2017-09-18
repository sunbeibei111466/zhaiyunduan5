package com.jlgproject.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sunbeibei on 2017/6/21.
 */

public class Demand_Informations_New implements Serializable {


    /**
     * state : ok
     * message : 查询需求列表成功！
     * data : {"pageNum":0,"total":0,"items":[{"id":5,"name":"豪车","demandnewid":3,"url":"http://test.file.zhongjinzhaishi.com/image/two/haoche.png"},{"id":6,"name":"SUV","demandnewid":8,"url":"http://test.file.zhongjinzhaishi.com/image/two/suv.png"},{"id":3,"name":"豪车","url":"http://test.file.zhongjinzhaishi.com/image/two/haoche.png"},{"id":4,"name":"SUV","url":"http://test.file.zhongjinzhaishi.com/image/two/suv.png"},{"id":1,"name":"SUV","demandnewid":8,"url":"http://test.file.zhongjinzhaishi.com/image/two/suv.png"},{"id":2,"name":"豪车","demandnewid":3,"url":"http://test.file.zhongjinzhaishi.com/image/two/haoche.png"}]}
     */

    private String state;
    private String message;
    /**
     * pageNum : 0
     * total : 0
     * items : [{"id":5,"name":"豪车","demandnewid":3,"url":"http://test.file.zhongjinzhaishi.com/image/two/haoche.png"},{"id":6,"name":"SUV","demandnewid":8,"url":"http://test.file.zhongjinzhaishi.com/image/two/suv.png"},{"id":3,"name":"豪车","url":"http://test.file.zhongjinzhaishi.com/image/two/haoche.png"},{"id":4,"name":"SUV","url":"http://test.file.zhongjinzhaishi.com/image/two/suv.png"},{"id":1,"name":"SUV","demandnewid":8,"url":"http://test.file.zhongjinzhaishi.com/image/two/suv.png"},{"id":2,"name":"豪车","demandnewid":3,"url":"http://test.file.zhongjinzhaishi.com/image/two/haoche.png"}]
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
         * id : 5
         * name : 豪车
         * demandnewid : 3
         * url : http://test.file.zhongjinzhaishi.com/image/two/haoche.png
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
            private String name;
            private int demandnewid;
            private String url;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getDemandnewid() {
                return demandnewid;
            }

            public void setDemandnewid(int demandnewid) {
                this.demandnewid = demandnewid;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
