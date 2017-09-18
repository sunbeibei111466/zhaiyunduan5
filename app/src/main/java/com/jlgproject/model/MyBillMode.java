package com.jlgproject.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author 王锋 on 2017/9/14.
 */

public class MyBillMode implements Serializable {


    /**
     * state : ok
     * message : 账单信息查询成功
     * data : {"pageNum":0,"total":25,"items":[{"mypackvo":[{"amount":"-0.01","time":1505204299000,"remark":"APP余额债事备案","mouthTime":"09/2017","dayTime":"09-12","image":"http://file.zhongjinzhaishi.com/mypack/qita.png","property":"余额","dayYearTime":"周二"},{"amount":"+1","time":1505108850000,"remark":"APP充值债币","mouthTime":"09/2017","dayTime":"09-11","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"债币","dayYearTime":"周一"},{"amount":"+1","time":1505108829000,"remark":"APP充值债币","mouthTime":"09/2017","dayTime":"09-11","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"债币","dayYearTime":"周一"},{"amount":"+1","time":1505108803000,"remark":"APP充值债币","mouthTime":"09/2017","dayTime":"09-11","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"债币","dayYearTime":"周一"},{"amount":"+100","time":1504892141000,"remark":"APP充值债币","mouthTime":"09/2017","dayTime":"09-09","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"债币","dayYearTime":"周六"},{"amount":"+0.02","time":1504889785000,"remark":"APP微信充值余额","mouthTime":"09/2017","dayTime":"09-09","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"余额","dayYearTime":"周六"},{"amount":"+2","time":1504889761000,"remark":"APP充值债币","mouthTime":"09/2017","dayTime":"09-09","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"债币","dayYearTime":"周六"},{"amount":"+1","time":1504889732000,"remark":"APP充值债币","mouthTime":"09/2017","dayTime":"09-09","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"债币","dayYearTime":"周六"},{"amount":"+0.01","time":1504888090000,"remark":"APP支付宝充值余额","mouthTime":"09/2017","dayTime":"09-09","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"余额","dayYearTime":"周六"},{"amount":"+1","time":1504888064000,"remark":"APP充值债币","mouthTime":"09/2017","dayTime":"09-09","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"债币","dayYearTime":"周六"},{"amount":"+1","time":1504887158000,"remark":"APP充值债币","mouthTime":"09/2017","dayTime":"09-09","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"债币","dayYearTime":"周六"},{"amount":"+0.01","time":1504887130000,"remark":"APP微信充值余额","mouthTime":"09/2017","dayTime":"09-09","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"余额","dayYearTime":"周六"},{"amount":"+1","time":1504887104000,"remark":"APP充值债币","mouthTime":"09/2017","dayTime":"09-09","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"债币","dayYearTime":"周六"},{"amount":"+1","time":1504886991000,"remark":"APP充值债币","mouthTime":"09/2017","dayTime":"09-09","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"债币","dayYearTime":"周六"},{"amount":"+1","time":1504885676000,"remark":"APP充值债币","mouthTime":"09/2017","dayTime":"09-08","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"债币","dayYearTime":"周五"},{"amount":"+1","time":1504884846000,"remark":"APP充值债币","mouthTime":"09/2017","dayTime":"09-08","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"债币","dayYearTime":"周五"},{"amount":"+1","time":1504884435000,"remark":"APP充值债币","mouthTime":"09/2017","dayTime":"09-08","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"债币","dayYearTime":"周五"},{"amount":"+0.01","time":1504854911000,"remark":"商城充值","mouthTime":"09/2017","dayTime":"09-08","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"余额","dayYearTime":"周五"},{"amount":"+0.01","time":1504842769000,"remark":"商城充值","mouthTime":"09/2017","dayTime":"09-08","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"余额","dayYearTime":"周五"},{"amount":"+1","time":1504802363000,"remark":"APP充值债币","mouthTime":"09/2017","dayTime":"09-08","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"债币","dayYearTime":"周五"},{"amount":"+0.01","time":1504802320000,"remark":"APP支付宝充值","mouthTime":"09/2017","dayTime":"09-08","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"余额","dayYearTime":"周五"},{"amount":"+1","time":1504799993000,"remark":"APP充值债币","mouthTime":"09/2017","dayTime":"09-07","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"债币","dayYearTime":"周四"},{"amount":"+0.01","time":1504799961000,"remark":"APP支付宝充值","mouthTime":"09/2017","dayTime":"09-07","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"余额","dayYearTime":"周四"},{"amount":"-0.01","time":1504779900000,"remark":"支付订单 2017090718250060404","mouthTime":"09/2017","dayTime":"09-07","image":"http://file.zhongjinzhaishi.com/mypack/xiangshangjiafufei.png","property":"余额","dayYearTime":"周四"},{"amount":"-0.01","time":1504773176000,"remark":"支付订单 2017090716325696243","mouthTime":"09/2017","dayTime":"09-07","image":"http://file.zhongjinzhaishi.com/mypack/xiangshangjiafufei.png","property":"余额","dayYearTime":"周四"}],"mouthtime":"09/2017"}]}
     */

    private String state;
    private String message;
    /**
     * pageNum : 0
     * total : 25
     * items : [{"mypackvo":[{"amount":"-0.01","time":1505204299000,"remark":"APP余额债事备案","mouthTime":"09/2017","dayTime":"09-12","image":"http://file.zhongjinzhaishi.com/mypack/qita.png","property":"余额","dayYearTime":"周二"},{"amount":"+1","time":1505108850000,"remark":"APP充值债币","mouthTime":"09/2017","dayTime":"09-11","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"债币","dayYearTime":"周一"},{"amount":"+1","time":1505108829000,"remark":"APP充值债币","mouthTime":"09/2017","dayTime":"09-11","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"债币","dayYearTime":"周一"},{"amount":"+1","time":1505108803000,"remark":"APP充值债币","mouthTime":"09/2017","dayTime":"09-11","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"债币","dayYearTime":"周一"},{"amount":"+100","time":1504892141000,"remark":"APP充值债币","mouthTime":"09/2017","dayTime":"09-09","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"债币","dayYearTime":"周六"},{"amount":"+0.02","time":1504889785000,"remark":"APP微信充值余额","mouthTime":"09/2017","dayTime":"09-09","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"余额","dayYearTime":"周六"},{"amount":"+2","time":1504889761000,"remark":"APP充值债币","mouthTime":"09/2017","dayTime":"09-09","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"债币","dayYearTime":"周六"},{"amount":"+1","time":1504889732000,"remark":"APP充值债币","mouthTime":"09/2017","dayTime":"09-09","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"债币","dayYearTime":"周六"},{"amount":"+0.01","time":1504888090000,"remark":"APP支付宝充值余额","mouthTime":"09/2017","dayTime":"09-09","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"余额","dayYearTime":"周六"},{"amount":"+1","time":1504888064000,"remark":"APP充值债币","mouthTime":"09/2017","dayTime":"09-09","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"债币","dayYearTime":"周六"},{"amount":"+1","time":1504887158000,"remark":"APP充值债币","mouthTime":"09/2017","dayTime":"09-09","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"债币","dayYearTime":"周六"},{"amount":"+0.01","time":1504887130000,"remark":"APP微信充值余额","mouthTime":"09/2017","dayTime":"09-09","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"余额","dayYearTime":"周六"},{"amount":"+1","time":1504887104000,"remark":"APP充值债币","mouthTime":"09/2017","dayTime":"09-09","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"债币","dayYearTime":"周六"},{"amount":"+1","time":1504886991000,"remark":"APP充值债币","mouthTime":"09/2017","dayTime":"09-09","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"债币","dayYearTime":"周六"},{"amount":"+1","time":1504885676000,"remark":"APP充值债币","mouthTime":"09/2017","dayTime":"09-08","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"债币","dayYearTime":"周五"},{"amount":"+1","time":1504884846000,"remark":"APP充值债币","mouthTime":"09/2017","dayTime":"09-08","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"债币","dayYearTime":"周五"},{"amount":"+1","time":1504884435000,"remark":"APP充值债币","mouthTime":"09/2017","dayTime":"09-08","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"债币","dayYearTime":"周五"},{"amount":"+0.01","time":1504854911000,"remark":"商城充值","mouthTime":"09/2017","dayTime":"09-08","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"余额","dayYearTime":"周五"},{"amount":"+0.01","time":1504842769000,"remark":"商城充值","mouthTime":"09/2017","dayTime":"09-08","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"余额","dayYearTime":"周五"},{"amount":"+1","time":1504802363000,"remark":"APP充值债币","mouthTime":"09/2017","dayTime":"09-08","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"债币","dayYearTime":"周五"},{"amount":"+0.01","time":1504802320000,"remark":"APP支付宝充值","mouthTime":"09/2017","dayTime":"09-08","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"余额","dayYearTime":"周五"},{"amount":"+1","time":1504799993000,"remark":"APP充值债币","mouthTime":"09/2017","dayTime":"09-07","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"债币","dayYearTime":"周四"},{"amount":"+0.01","time":1504799961000,"remark":"APP支付宝充值","mouthTime":"09/2017","dayTime":"09-07","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"余额","dayYearTime":"周四"},{"amount":"-0.01","time":1504779900000,"remark":"支付订单 2017090718250060404","mouthTime":"09/2017","dayTime":"09-07","image":"http://file.zhongjinzhaishi.com/mypack/xiangshangjiafufei.png","property":"余额","dayYearTime":"周四"},{"amount":"-0.01","time":1504773176000,"remark":"支付订单 2017090716325696243","mouthTime":"09/2017","dayTime":"09-07","image":"http://file.zhongjinzhaishi.com/mypack/xiangshangjiafufei.png","property":"余额","dayYearTime":"周四"}],"mouthtime":"09/2017"}]
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
         * mypackvo : [{"amount":"-0.01","time":1505204299000,"remark":"APP余额债事备案","mouthTime":"09/2017","dayTime":"09-12","image":"http://file.zhongjinzhaishi.com/mypack/qita.png","property":"余额","dayYearTime":"周二"},{"amount":"+1","time":1505108850000,"remark":"APP充值债币","mouthTime":"09/2017","dayTime":"09-11","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"债币","dayYearTime":"周一"},{"amount":"+1","time":1505108829000,"remark":"APP充值债币","mouthTime":"09/2017","dayTime":"09-11","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"债币","dayYearTime":"周一"},{"amount":"+1","time":1505108803000,"remark":"APP充值债币","mouthTime":"09/2017","dayTime":"09-11","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"债币","dayYearTime":"周一"},{"amount":"+100","time":1504892141000,"remark":"APP充值债币","mouthTime":"09/2017","dayTime":"09-09","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"债币","dayYearTime":"周六"},{"amount":"+0.02","time":1504889785000,"remark":"APP微信充值余额","mouthTime":"09/2017","dayTime":"09-09","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"余额","dayYearTime":"周六"},{"amount":"+2","time":1504889761000,"remark":"APP充值债币","mouthTime":"09/2017","dayTime":"09-09","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"债币","dayYearTime":"周六"},{"amount":"+1","time":1504889732000,"remark":"APP充值债币","mouthTime":"09/2017","dayTime":"09-09","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"债币","dayYearTime":"周六"},{"amount":"+0.01","time":1504888090000,"remark":"APP支付宝充值余额","mouthTime":"09/2017","dayTime":"09-09","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"余额","dayYearTime":"周六"},{"amount":"+1","time":1504888064000,"remark":"APP充值债币","mouthTime":"09/2017","dayTime":"09-09","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"债币","dayYearTime":"周六"},{"amount":"+1","time":1504887158000,"remark":"APP充值债币","mouthTime":"09/2017","dayTime":"09-09","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"债币","dayYearTime":"周六"},{"amount":"+0.01","time":1504887130000,"remark":"APP微信充值余额","mouthTime":"09/2017","dayTime":"09-09","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"余额","dayYearTime":"周六"},{"amount":"+1","time":1504887104000,"remark":"APP充值债币","mouthTime":"09/2017","dayTime":"09-09","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"债币","dayYearTime":"周六"},{"amount":"+1","time":1504886991000,"remark":"APP充值债币","mouthTime":"09/2017","dayTime":"09-09","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"债币","dayYearTime":"周六"},{"amount":"+1","time":1504885676000,"remark":"APP充值债币","mouthTime":"09/2017","dayTime":"09-08","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"债币","dayYearTime":"周五"},{"amount":"+1","time":1504884846000,"remark":"APP充值债币","mouthTime":"09/2017","dayTime":"09-08","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"债币","dayYearTime":"周五"},{"amount":"+1","time":1504884435000,"remark":"APP充值债币","mouthTime":"09/2017","dayTime":"09-08","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"债币","dayYearTime":"周五"},{"amount":"+0.01","time":1504854911000,"remark":"商城充值","mouthTime":"09/2017","dayTime":"09-08","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"余额","dayYearTime":"周五"},{"amount":"+0.01","time":1504842769000,"remark":"商城充值","mouthTime":"09/2017","dayTime":"09-08","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"余额","dayYearTime":"周五"},{"amount":"+1","time":1504802363000,"remark":"APP充值债币","mouthTime":"09/2017","dayTime":"09-08","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"债币","dayYearTime":"周五"},{"amount":"+0.01","time":1504802320000,"remark":"APP支付宝充值","mouthTime":"09/2017","dayTime":"09-08","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"余额","dayYearTime":"周五"},{"amount":"+1","time":1504799993000,"remark":"APP充值债币","mouthTime":"09/2017","dayTime":"09-07","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"债币","dayYearTime":"周四"},{"amount":"+0.01","time":1504799961000,"remark":"APP支付宝充值","mouthTime":"09/2017","dayTime":"09-07","image":"http://file.zhongjinzhaishi.com/mypack/chongzhizhaibi.png","property":"余额","dayYearTime":"周四"},{"amount":"-0.01","time":1504779900000,"remark":"支付订单 2017090718250060404","mouthTime":"09/2017","dayTime":"09-07","image":"http://file.zhongjinzhaishi.com/mypack/xiangshangjiafufei.png","property":"余额","dayYearTime":"周四"},{"amount":"-0.01","time":1504773176000,"remark":"支付订单 2017090716325696243","mouthTime":"09/2017","dayTime":"09-07","image":"http://file.zhongjinzhaishi.com/mypack/xiangshangjiafufei.png","property":"余额","dayYearTime":"周四"}]
         * mouthtime : 09/2017
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
            private String mouthtime;
            /**
             * amount : -0.01
             * time : 1505204299000
             * remark : APP余额债事备案
             * mouthTime : 09/2017
             * dayTime : 09-12
             * image : http://file.zhongjinzhaishi.com/mypack/qita.png
             * property : 余额
             * dayYearTime : 周二
             */

            private List<MypackvoBean> mypackvo;

            public String getMouthtime() {
                return mouthtime;
            }

            public void setMouthtime(String mouthtime) {
                this.mouthtime = mouthtime;
            }

            public List<MypackvoBean> getMypackvo() {
                return mypackvo;
            }

            public void setMypackvo(List<MypackvoBean> mypackvo) {
                this.mypackvo = mypackvo;
            }

            public static class MypackvoBean {
                private String amount;
                private long time;
                private String remark;
                private String mouthTime;
                private String dayTime;
                private String image;
                private String property;
                private String dayYearTime;

                public String getAmount() {
                    return amount;
                }

                public void setAmount(String amount) {
                    this.amount = amount;
                }

                public long getTime() {
                    return time;
                }

                public void setTime(long time) {
                    this.time = time;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public String getMouthTime() {
                    return mouthTime;
                }

                public void setMouthTime(String mouthTime) {
                    this.mouthTime = mouthTime;
                }

                public String getDayTime() {
                    return dayTime;
                }

                public void setDayTime(String dayTime) {
                    this.dayTime = dayTime;
                }

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                public String getProperty() {
                    return property;
                }

                public void setProperty(String property) {
                    this.property = property;
                }

                public String getDayYearTime() {
                    return dayYearTime;
                }

                public void setDayYearTime(String dayYearTime) {
                    this.dayYearTime = dayYearTime;
                }
            }
        }
    }
}
