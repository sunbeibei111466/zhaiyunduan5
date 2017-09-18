package com.jlgproject.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sunbeibei on 2017/8/11.
 */

public class The_Teacher_Moldel implements  Serializable {

    /**
     * state : ok
     * message : 查询名师风采信息成功
     * data : {"pageNum":0,"total":0,"items":[{"content":"名师1名师1名师1","subTitle":"名师3名师3名师3名师3","title":"名师3","img":"http://test.file.zhongjinzhaishi.com/app/teacher/8055c17f32ee405380cbd39e93b550b3.jpg"},{"content":"名师1名师1名师1","subTitle":"名师2名师2名师2名师2","title":"名师2","img":"http://test.file.zhongjinzhaishi.com/app/teacher/6805287b873a4de29b138da96b5eadba.jpg"},{"content":"名师1名师1名师1","subTitle":"名师1名师1名师1","title":"名师1","img":"http://test.file.zhongjinzhaishi.com/app/teacher/21f64c26b62f47e9b39629595cbd6048.jpg"}]}
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

    public static class DataBean {
        /**
         * pageNum : 0
         * total : 0
         * items : [{"content":"名师1名师1名师1","subTitle":"名师3名师3名师3名师3","title":"名师3","img":"http://test.file.zhongjinzhaishi.com/app/teacher/8055c17f32ee405380cbd39e93b550b3.jpg"},{"content":"名师1名师1名师1","subTitle":"名师2名师2名师2名师2","title":"名师2","img":"http://test.file.zhongjinzhaishi.com/app/teacher/6805287b873a4de29b138da96b5eadba.jpg"},{"content":"名师1名师1名师1","subTitle":"名师1名师1名师1","title":"名师1","img":"http://test.file.zhongjinzhaishi.com/app/teacher/21f64c26b62f47e9b39629595cbd6048.jpg"}]
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
             * content : 名师1名师1名师1
             * subTitle : 名师3名师3名师3名师3
             * title : 名师3
             * img : http://test.file.zhongjinzhaishi.com/app/teacher/8055c17f32ee405380cbd39e93b550b3.jpg
             */

            private String content;
            private String subTitle;
            private String title;
            private String img;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getSubTitle() {
                return subTitle;
            }

            public void setSubTitle(String subTitle) {
                this.subTitle = subTitle;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }
    }
}
