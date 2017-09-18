package com.jlgproject.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sunbeibei on 2017/6/9.
 */

public class Debts_Manger implements Serializable {


    /**
     * state : ok
     * message :
     * data : {"pageNum":1,"total":31,"items":[{"id":14722,"createTime":"2017-08-10 ","from":"阿尔法小分队","to":"阿尔法公司","amout":100000,"orderId":"2c92f4efb7584acfb09ad79b05028b30","payStatus":1,"isSolution":0,"otherPerson":null,"qianshu":"0.00","recommend":"13683154836","recommendName":"张磊","hangzhang":"云债行行长"},{"id":14695,"createTime":"2017-08-04 ","from":"123456","to":"测试","amout":123321,"orderId":"b6dd45b2be014f5ea09a34f6a4668338","payStatus":0,"isSolution":0,"otherPerson":null,"qianshu":"0.00","recommend":"13683154836","recommendName":"张磊","hangzhang":"云债行行长"},{"id":14694,"createTime":"2017-08-04 ","from":"123456","to":"测试","amout":1221,"orderId":"199c241528db436689e32cc3909a6a76","payStatus":0,"isSolution":0,"otherPerson":null,"qianshu":"0.00","recommend":"13683154836","recommendName":"张磊","hangzhang":"云债行行长"},{"id":14723,"createTime":"2017-08-10 ","from":"阿尔法公司","to":"阿尔法小分队","amout":66,"orderId":"9abd24e2d3cd42adad585450d56b1da3","payStatus":1,"isSolution":0,"otherPerson":null,"qianshu":"0.00","recommend":"13552949045","recommendName":"王锋","hangzhang":"债行行长"},{"id":14721,"createTime":"2017-08-09 ","from":"zhaokai","to":"chaplain","amout":875847,"orderId":"b9ae6f2be05944ebbaaea7140211de54","payStatus":1,"isSolution":0,"otherPerson":null,"qianshu":"0.00","recommend":"13552949045","recommendName":"王锋","hangzhang":"债行行长"},{"id":14720,"createTime":"2017-08-09 ","from":"zhaokai","to":"chaplain","amout":3678455,"orderId":"a33dd7124ff6413a81246aad89099ce9","payStatus":1,"isSolution":0,"otherPerson":null,"qianshu":"0.00","recommend":"13552949045","recommendName":"王锋","hangzhang":"债行行长"},{"id":14719,"createTime":"2017-08-09 ","from":"chaplain","to":"zhaokai","amout":5000032,"orderId":"d0c058f8333844a1855498a0102ac187","payStatus":1,"isSolution":0,"otherPerson":null,"qianshu":"0.00","recommend":"13552949045","recommendName":"王锋","hangzhang":"债行行长"},{"id":14718,"createTime":"2017-08-09 ","from":"zhaokai","to":"chaplain","amout":12121332,"orderId":"ce73735d06c34fefa6e309979c9e8219","payStatus":1,"isSolution":0,"otherPerson":null,"qianshu":"0.00","recommend":"13552949045","recommendName":"王锋","hangzhang":"债行行长"}]}
     */

    private String state;
    private String message;
    /**
     * pageNum : 1
     * total : 31
     * items : [{"id":14722,"createTime":"2017-08-10 ","from":"阿尔法小分队","to":"阿尔法公司","amout":100000,"orderId":"2c92f4efb7584acfb09ad79b05028b30","payStatus":1,"isSolution":0,"otherPerson":null,"qianshu":"0.00","recommend":"13683154836","recommendName":"张磊","hangzhang":"云债行行长"},{"id":14695,"createTime":"2017-08-04 ","from":"123456","to":"测试","amout":123321,"orderId":"b6dd45b2be014f5ea09a34f6a4668338","payStatus":0,"isSolution":0,"otherPerson":null,"qianshu":"0.00","recommend":"13683154836","recommendName":"张磊","hangzhang":"云债行行长"},{"id":14694,"createTime":"2017-08-04 ","from":"123456","to":"测试","amout":1221,"orderId":"199c241528db436689e32cc3909a6a76","payStatus":0,"isSolution":0,"otherPerson":null,"qianshu":"0.00","recommend":"13683154836","recommendName":"张磊","hangzhang":"云债行行长"},{"id":14723,"createTime":"2017-08-10 ","from":"阿尔法公司","to":"阿尔法小分队","amout":66,"orderId":"9abd24e2d3cd42adad585450d56b1da3","payStatus":1,"isSolution":0,"otherPerson":null,"qianshu":"0.00","recommend":"13552949045","recommendName":"王锋","hangzhang":"债行行长"},{"id":14721,"createTime":"2017-08-09 ","from":"zhaokai","to":"chaplain","amout":875847,"orderId":"b9ae6f2be05944ebbaaea7140211de54","payStatus":1,"isSolution":0,"otherPerson":null,"qianshu":"0.00","recommend":"13552949045","recommendName":"王锋","hangzhang":"债行行长"},{"id":14720,"createTime":"2017-08-09 ","from":"zhaokai","to":"chaplain","amout":3678455,"orderId":"a33dd7124ff6413a81246aad89099ce9","payStatus":1,"isSolution":0,"otherPerson":null,"qianshu":"0.00","recommend":"13552949045","recommendName":"王锋","hangzhang":"债行行长"},{"id":14719,"createTime":"2017-08-09 ","from":"chaplain","to":"zhaokai","amout":5000032,"orderId":"d0c058f8333844a1855498a0102ac187","payStatus":1,"isSolution":0,"otherPerson":null,"qianshu":"0.00","recommend":"13552949045","recommendName":"王锋","hangzhang":"债行行长"},{"id":14718,"createTime":"2017-08-09 ","from":"zhaokai","to":"chaplain","amout":12121332,"orderId":"ce73735d06c34fefa6e309979c9e8219","payStatus":1,"isSolution":0,"otherPerson":null,"qianshu":"0.00","recommend":"13552949045","recommendName":"王锋","hangzhang":"债行行长"}]
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
        private int pageNum;
        private int total;
        /**
         * id : 14722
         * createTime : 2017-08-10
         * from : 阿尔法小分队
         * to : 阿尔法公司
         * amout : 100000
         * orderId : 2c92f4efb7584acfb09ad79b05028b30
         * payStatus : 1
         * isSolution : 0
         * otherPerson : null
         * qianshu : 0.00
         * recommend : 13683154836
         * recommendName : 张磊
         * hangzhang : 云债行行长
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

        public static class ItemsBean {
            private Long id;
            private String createTime;
            private String from;
            private String to;
            private Long amout;
            private String orderId;
            private int payStatus;
            private int isSolution;
            private Object otherPerson;
            private String qianshu;
            private String recommend;
            private String recommendName;
            private String hangzhang;

            public Long getId() {
                return id;
            }

            public void setId(Long id) {
                this.id = id;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getFrom() {
                return from;
            }

            public void setFrom(String from) {
                this.from = from;
            }

            public String getTo() {
                return to;
            }

            public void setTo(String to) {
                this.to = to;
            }

            public Long getAmout() {
                return amout;
            }

            public void setAmout(Long amout) {
                this.amout = amout;
            }

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public int getPayStatus() {
                return payStatus;
            }

            public void setPayStatus(int payStatus) {
                this.payStatus = payStatus;
            }

            public int getIsSolution() {
                return isSolution;
            }

            public void setIsSolution(int isSolution) {
                this.isSolution = isSolution;
            }

            public Object getOtherPerson() {
                return otherPerson;
            }

            public void setOtherPerson(Object otherPerson) {
                this.otherPerson = otherPerson;
            }

            public String getQianshu() {
                return qianshu;
            }

            public void setQianshu(String qianshu) {
                this.qianshu = qianshu;
            }

            public String getRecommend() {
                return recommend;
            }

            public void setRecommend(String recommend) {
                this.recommend = recommend;
            }

            public String getRecommendName() {
                return recommendName;
            }

            public void setRecommendName(String recommendName) {
                this.recommendName = recommendName;
            }

            public String getHangzhang() {
                return hangzhang;
            }

            public void setHangzhang(String hangzhang) {
                this.hangzhang = hangzhang;
            }
        }
    }
}
