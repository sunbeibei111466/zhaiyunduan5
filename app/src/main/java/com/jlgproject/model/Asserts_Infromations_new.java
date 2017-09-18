package com.jlgproject.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sunbeibei on 2017/6/20.
 */

public class Asserts_Infromations_new implements Serializable {


    /**
     * state : ok
     * message : 查询资产列表成功！
     * data : {"pageNum":1,"total":0,"items":[{"id":14,"debtRelationid":null,"createUserId":null,"zid":null,"name":"别克","model":"45p","createTime":null,"totalAmout":649000,"idasset":null,"assetNum":1},{"id":15,"debtRelationid":null,"createUserId":null,"zid":null,"name":"别克","model":"6p8","createTime":null,"totalAmout":649000,"idasset":null,"assetNum":1}]}
     */

    private String state;
    private String message;
    /**
     * pageNum : 1
     * total : 0
     * items : [{"id":14,"debtRelationid":null,"createUserId":null,"zid":null,"name":"别克","model":"45p","createTime":null,"totalAmout":649000,"idasset":null,"assetNum":1},{"id":15,"debtRelationid":null,"createUserId":null,"zid":null,"name":"别克","model":"6p8","createTime":null,"totalAmout":649000,"idasset":null,"assetNum":1}]
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
         * id : 14
         * debtRelationid : null
         * createUserId : null
         * zid : null
         * name : 别克
         * model : 45p
         * createTime : null
         * totalAmout : 649000
         * idasset : null
         * assetNum : 1
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

        public static class ItemsBean implements Serializable {
            private Long id;
            private Object debtRelationid;
            private Object createUserId;
            private Object zid;
            private String name;
            private String model;
            private Object createTime;
            private String totalAmout;
            private Object idasset;
            private String assetNum;


            public Long getId() {
                return id;
            }

            public void setId(Long id) {
                this.id = id;
            }

            public Object getDebtRelationid() {
                return debtRelationid;
            }

            public void setDebtRelationid(Object debtRelationid) {
                this.debtRelationid = debtRelationid;
            }

            public Object getCreateUserId() {
                return createUserId;
            }

            public void setCreateUserId(Object createUserId) {
                this.createUserId = createUserId;
            }

            public Object getZid() {
                return zid;
            }

            public void setZid(Object zid) {
                this.zid = zid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getModel() {
                return model;
            }

            public void setModel(String model) {
                this.model = model;
            }

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }

            public String getTotalAmout() {
                return totalAmout;
            }

            public void setTotalAmout(String totalAmout) {
                this.totalAmout = totalAmout;
            }

            public Object getIdasset() {
                return idasset;
            }

            public void setIdasset(Object idasset) {
                this.idasset = idasset;
            }

            public String getAssetNum() {
                return assetNum;
            }

            public void setAssetNum(String assetNum) {
                this.assetNum = assetNum;
            }
        }


    }
}
