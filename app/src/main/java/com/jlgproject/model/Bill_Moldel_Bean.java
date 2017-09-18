package com.jlgproject.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sunbeibei on 2017/8/23.
 */

public class Bill_Moldel_Bean implements Serializable {


    /**
     * state : ok
     * message : 账单信息查询成功
     * data : {"pageNum":0,"total":2,"items":[{"mypackvo":[{"amount":"+43","time":1498120243000,"remark":"余额充值","mouthTime":"06/2017","dayTime":"06-22","image":"http://file.zhongjinzhaishi.com/app/mypack/null","dayYearTime":"周四"},{"amount":"+435","time":1498033942000,"remark":"余额充值","mouthTime":"06/2017","dayTime":"06-21","image":"http://file.zhongjinzhaishi.com/app/mypack/null","dayYearTime":"周三"}],"mouthtime":"06/2017"}]}
     */

    private String state;
    private String message;
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
        /**
         * pageNum : 0
         * total : 2
         * items : [{"mypackvo":[{"amount":"+43","time":1498120243000,"remark":"余额充值","mouthTime":"06/2017","dayTime":"06-22","image":"http://file.zhongjinzhaishi.com/app/mypack/null","dayYearTime":"周四"},{"amount":"+435","time":1498033942000,"remark":"余额充值","mouthTime":"06/2017","dayTime":"06-21","image":"http://file.zhongjinzhaishi.com/app/mypack/null","dayYearTime":"周三"}],"mouthtime":"06/2017"}]
         */

        private int pageNum;
        private int total;
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
            /**
             * mypackvo : [{"amount":"+43","time":1498120243000,"remark":"余额充值","mouthTime":"06/2017","dayTime":"06-22","image":"http://file.zhongjinzhaishi.com/app/mypack/null","dayYearTime":"周四"},{"amount":"+435","time":1498033942000,"remark":"余额充值","mouthTime":"06/2017","dayTime":"06-21","image":"http://file.zhongjinzhaishi.com/app/mypack/null","dayYearTime":"周三"}]
             * mouthtime : 06/2017
             */

            private String mouthtime;
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

            public static class MypackvoBean implements Serializable{
                /**
                 * amount : +43
                 * time : 1498120243000
                 * remark : 余额充值
                 * mouthTime : 06/2017
                 * dayTime : 06-22
                 * image : http://file.zhongjinzhaishi.com/app/mypack/null
                 * dayYearTime : 周四
                 */

                private String amount;
                private long time;
                private String remark;
                private String mouthTime;
                private String dayTime;
                private String image;
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
