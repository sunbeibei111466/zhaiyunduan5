package com.jlgproject.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sunbeibei on 2017/7/21.
 */

public class Video_List_Bean implements Serializable {


    /**
     * state : ok
     * message : 获取视频课程列表成功！
     * data : {"pageNum":1,"total":5,"items":[{"id":14,"title":"课程视频5","url":"http://file.zhongjinzhaishi.com/app/college/55b1791c2b654a75b810681f01befd5a.mp4","img":"http://file.zhongjinzhaishi.com/app/college/d74daa07005c4bbcac5dab0538c08bff.jpg","updateTime":"2017-09-13 18:15:51","content":"","brief":"课程视频5","author":"课程视频5","top":""},{"id":13,"title":"课程视频4","url":"http://file.zhongjinzhaishi.com/app/college/9a9ab7817a7d424ea4ab04987f209584.mp4","img":"http://file.zhongjinzhaishi.com/app/college/777a311e67254a0cb9bee98939b2473d.jpg","updateTime":"2017-09-13 18:15:22","content":"","brief":"课程视频4","author":"课程视频4","top":"2"},{"id":12,"title":"课程视频3","url":"http://file.zhongjinzhaishi.com/app/college/66c5aa85c08a44129e40efcabc3bf145.mp4","img":"http://file.zhongjinzhaishi.com/app/college/b5f464b2de9a4d4584c3ea4b94b2e3a3.jpg","updateTime":"2017-09-13 18:14:17","content":"","brief":"课程视频3","author":"课程视频3","top":"2"},{"id":11,"title":"课程视频2","url":"http://file.zhongjinzhaishi.com/app/college/23e53e78ef1247b88785d065e281eead.mp4","img":"http://file.zhongjinzhaishi.com/app/college/8bd628b32d6f465c89722e929d98785a.jpg","updateTime":"2017-09-13 18:13:22","content":"","brief":"课程视频2","author":"课程视频2","top":"2"},{"id":10,"title":"课程视频1","url":"http://file.zhongjinzhaishi.com/app/college/855e569f1c224d3983a838218a39b9ef.mp4","img":"http://file.zhongjinzhaishi.com/app/college/43e0d501d7f84a6db7dee134ea6da00c.jpg","updateTime":"2017-09-13 18:12:21","content":"","brief":"课程视频1","author":"课程视频1","top":"1"}]}
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
         * pageNum : 1
         * total : 5
         * items : [{"id":14,"title":"课程视频5","url":"http://file.zhongjinzhaishi.com/app/college/55b1791c2b654a75b810681f01befd5a.mp4","img":"http://file.zhongjinzhaishi.com/app/college/d74daa07005c4bbcac5dab0538c08bff.jpg","updateTime":"2017-09-13 18:15:51","content":"","brief":"课程视频5","author":"课程视频5","top":""},{"id":13,"title":"课程视频4","url":"http://file.zhongjinzhaishi.com/app/college/9a9ab7817a7d424ea4ab04987f209584.mp4","img":"http://file.zhongjinzhaishi.com/app/college/777a311e67254a0cb9bee98939b2473d.jpg","updateTime":"2017-09-13 18:15:22","content":"","brief":"课程视频4","author":"课程视频4","top":"2"},{"id":12,"title":"课程视频3","url":"http://file.zhongjinzhaishi.com/app/college/66c5aa85c08a44129e40efcabc3bf145.mp4","img":"http://file.zhongjinzhaishi.com/app/college/b5f464b2de9a4d4584c3ea4b94b2e3a3.jpg","updateTime":"2017-09-13 18:14:17","content":"","brief":"课程视频3","author":"课程视频3","top":"2"},{"id":11,"title":"课程视频2","url":"http://file.zhongjinzhaishi.com/app/college/23e53e78ef1247b88785d065e281eead.mp4","img":"http://file.zhongjinzhaishi.com/app/college/8bd628b32d6f465c89722e929d98785a.jpg","updateTime":"2017-09-13 18:13:22","content":"","brief":"课程视频2","author":"课程视频2","top":"2"},{"id":10,"title":"课程视频1","url":"http://file.zhongjinzhaishi.com/app/college/855e569f1c224d3983a838218a39b9ef.mp4","img":"http://file.zhongjinzhaishi.com/app/college/43e0d501d7f84a6db7dee134ea6da00c.jpg","updateTime":"2017-09-13 18:12:21","content":"","brief":"课程视频1","author":"课程视频1","top":"1"}]
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
             * id : 14
             * title : 课程视频5
             * url : http://file.zhongjinzhaishi.com/app/college/55b1791c2b654a75b810681f01befd5a.mp4
             * img : http://file.zhongjinzhaishi.com/app/college/d74daa07005c4bbcac5dab0538c08bff.jpg
             * updateTime : 2017-09-13 18:15:51
             * content :
             * brief : 课程视频5
             * author : 课程视频5
             * top :
             */

            private int id;
            private String title;
            private String url;
            private String img;
            private String updateTime;
            private String content;
            private String brief;
            private String author;
            private String top;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getBrief() {
                return brief;
            }

            public void setBrief(String brief) {
                this.brief = brief;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public String getTop() {
                return top;
            }

            public void setTop(String top) {
                this.top = top;
            }
        }
    }
}
