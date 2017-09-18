package com.jlgproject.model;

import java.util.List;

/**
 * @author 王锋 on 2017/9/14.
 */

public class ActivityPrefectrueMode {


    /**
     * state : ok
     * message : 获取活动专区成功！
     * data : {"pageNum":1,"total":5,"items":[{"id":12,"title":"活动4","url":"http://file.zhongjinzhaishi.com/app/college/6ccc39e31b85462384a93fdda1e9331d.html","img":"http://file.zhongjinzhaishi.com/app/college/6df798a075d448cfabffb21a52c74e6e.jpg","updateTime":"2017-09-13 18:05:51","content":"<p><img src=\"http://file.zhongjinzhaishi.com/app/college/6df798a075d448cfabffb21a52c74e6e.jpg\" style=\"width: 422px;\">活动4<br><\/p>","brief":"","author":"","top":"2"},{"id":11,"title":"活动3","url":"http://file.zhongjinzhaishi.com/app/college/0d5f546f0f224edcb19fb850002e52f2.html","img":"http://file.zhongjinzhaishi.com/app/college/6785ff8d4d4e43f8831e3a7ad301c40d.jpg","updateTime":"2017-09-13 18:05:22","content":"<p><img src=\"http://file.zhongjinzhaishi.com/app/college/6785ff8d4d4e43f8831e3a7ad301c40d.jpg\" style=\"width: 422px;\">活动3<br><\/p>","brief":"","author":"","top":"2"},{"id":10,"title":"活动3","url":"http://file.zhongjinzhaishi.com/app/college/16985490b3d1451d9c23aa9e456fcd25.html","img":"http://file.zhongjinzhaishi.com/app/college/1eef8b710a3949a3ab8909e59fec8bef.jpg","updateTime":"2017-09-13 18:04:53","content":"<p><img src=\"http://file.zhongjinzhaishi.com/app/college/1eef8b710a3949a3ab8909e59fec8bef.jpg\" style=\"width: 422px;\"><b>活动3<\/b><br><\/p>","brief":"","author":"","top":"2"},{"id":9,"title":"活动2","url":"http://file.zhongjinzhaishi.com/app/college/8d1173c2be6248f8a5a4e6e7f79c8a51.html","img":"http://file.zhongjinzhaishi.com/app/college/00b590a1c19b4d118e63a590e98cccfc.jpg","updateTime":"2017-09-13 18:04:19","content":"<p><img src=\"http://file.zhongjinzhaishi.com/app/college/00b590a1c19b4d118e63a590e98cccfc.jpg\" style=\"width: 422px;\">活动2<br><\/p>","brief":"","author":"","top":"2"},{"id":8,"title":"活动1","url":"http://file.zhongjinzhaishi.com/app/college/28f65c66faea486c9a57b6fc2248be24.html","img":"http://file.zhongjinzhaishi.com/app/college/5b9e842ede5b4178a021e905e8dc7ef2.jpg","updateTime":"2017-09-13 18:03:45","content":"<p><img src=\"http://file.zhongjinzhaishi.com/app/college/5b9e842ede5b4178a021e905e8dc7ef2.jpg\" style=\"width: 422px;\">活动1<br><\/p>","brief":"","author":"","top":"1"}]}
     */

    private String state;
    private String message;
    /**
     * pageNum : 1
     * total : 5
     * items : [{"id":12,"title":"活动4","url":"http://file.zhongjinzhaishi.com/app/college/6ccc39e31b85462384a93fdda1e9331d.html","img":"http://file.zhongjinzhaishi.com/app/college/6df798a075d448cfabffb21a52c74e6e.jpg","updateTime":"2017-09-13 18:05:51","content":"<p><img src=\"http://file.zhongjinzhaishi.com/app/college/6df798a075d448cfabffb21a52c74e6e.jpg\" style=\"width: 422px;\">活动4<br><\/p>","brief":"","author":"","top":"2"},{"id":11,"title":"活动3","url":"http://file.zhongjinzhaishi.com/app/college/0d5f546f0f224edcb19fb850002e52f2.html","img":"http://file.zhongjinzhaishi.com/app/college/6785ff8d4d4e43f8831e3a7ad301c40d.jpg","updateTime":"2017-09-13 18:05:22","content":"<p><img src=\"http://file.zhongjinzhaishi.com/app/college/6785ff8d4d4e43f8831e3a7ad301c40d.jpg\" style=\"width: 422px;\">活动3<br><\/p>","brief":"","author":"","top":"2"},{"id":10,"title":"活动3","url":"http://file.zhongjinzhaishi.com/app/college/16985490b3d1451d9c23aa9e456fcd25.html","img":"http://file.zhongjinzhaishi.com/app/college/1eef8b710a3949a3ab8909e59fec8bef.jpg","updateTime":"2017-09-13 18:04:53","content":"<p><img src=\"http://file.zhongjinzhaishi.com/app/college/1eef8b710a3949a3ab8909e59fec8bef.jpg\" style=\"width: 422px;\"><b>活动3<\/b><br><\/p>","brief":"","author":"","top":"2"},{"id":9,"title":"活动2","url":"http://file.zhongjinzhaishi.com/app/college/8d1173c2be6248f8a5a4e6e7f79c8a51.html","img":"http://file.zhongjinzhaishi.com/app/college/00b590a1c19b4d118e63a590e98cccfc.jpg","updateTime":"2017-09-13 18:04:19","content":"<p><img src=\"http://file.zhongjinzhaishi.com/app/college/00b590a1c19b4d118e63a590e98cccfc.jpg\" style=\"width: 422px;\">活动2<br><\/p>","brief":"","author":"","top":"2"},{"id":8,"title":"活动1","url":"http://file.zhongjinzhaishi.com/app/college/28f65c66faea486c9a57b6fc2248be24.html","img":"http://file.zhongjinzhaishi.com/app/college/5b9e842ede5b4178a021e905e8dc7ef2.jpg","updateTime":"2017-09-13 18:03:45","content":"<p><img src=\"http://file.zhongjinzhaishi.com/app/college/5b9e842ede5b4178a021e905e8dc7ef2.jpg\" style=\"width: 422px;\">活动1<br><\/p>","brief":"","author":"","top":"1"}]
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
         * id : 12
         * title : 活动4
         * url : http://file.zhongjinzhaishi.com/app/college/6ccc39e31b85462384a93fdda1e9331d.html
         * img : http://file.zhongjinzhaishi.com/app/college/6df798a075d448cfabffb21a52c74e6e.jpg
         * updateTime : 2017-09-13 18:05:51
         * content : <p><img src="http://file.zhongjinzhaishi.com/app/college/6df798a075d448cfabffb21a52c74e6e.jpg" style="width: 422px;">活动4<br></p>
         * brief :
         * author :
         * top : 2
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
