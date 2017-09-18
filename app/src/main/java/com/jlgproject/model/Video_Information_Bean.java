package com.jlgproject.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sunbeibei on 2017/9/14.
 */

public class Video_Information_Bean implements Serializable{


    /**
     * state : ok
     * message : 获取视频详情成功！
     * data : {"courseDetail":{"id":10,"title":"课程视频1","brief":"课程视频1","author":"课程视频1","url":"http://file.zhongjinzhaishi.com/app/college/855e569f1c224d3983a838218a39b9ef.mp4","img":"http://file.zhongjinzhaishi.com/app/college/43e0d501d7f84a6db7dee134ea6da00c.jpg","updateTime":"2017-09-13"},"recommendDetail":[{"id":8,"title":"视频课程1","url":"http://file.zhongjinzhaishi.com/app/college/11ee07347b274d53b4cd3d4f426c5e11.mp4","img":"http://file.zhongjinzhaishi.com/app/college/3f21f38cf43943fc8137398196ae7b32.jpg","updateTime":"2017-09-13","content":"","brief":"视频课程1","author":"视频课程1","top":""},{"id":9,"title":"视频课程2","url":"http://file.zhongjinzhaishi.com/app/college/7d87be47bb9f430fb3b80a58550e4bc0.mp4","img":"http://file.zhongjinzhaishi.com/app/college/7c49a2c9bf3d4282b478c8fb0de1d4bc.jpg","updateTime":"2017-09-13","content":"","brief":"视频课程2","author":"视频课程2","top":""},{"id":10,"title":"课程视频1","url":"http://file.zhongjinzhaishi.com/app/college/855e569f1c224d3983a838218a39b9ef.mp4","img":"http://file.zhongjinzhaishi.com/app/college/43e0d501d7f84a6db7dee134ea6da00c.jpg","updateTime":"2017-09-13","content":"","brief":"课程视频1","author":"课程视频1","top":""}]}
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
         * courseDetail : {"id":10,"title":"课程视频1","brief":"课程视频1","author":"课程视频1","url":"http://file.zhongjinzhaishi.com/app/college/855e569f1c224d3983a838218a39b9ef.mp4","img":"http://file.zhongjinzhaishi.com/app/college/43e0d501d7f84a6db7dee134ea6da00c.jpg","updateTime":"2017-09-13"}
         * recommendDetail : [{"id":8,"title":"视频课程1","url":"http://file.zhongjinzhaishi.com/app/college/11ee07347b274d53b4cd3d4f426c5e11.mp4","img":"http://file.zhongjinzhaishi.com/app/college/3f21f38cf43943fc8137398196ae7b32.jpg","updateTime":"2017-09-13","content":"","brief":"视频课程1","author":"视频课程1","top":""},{"id":9,"title":"视频课程2","url":"http://file.zhongjinzhaishi.com/app/college/7d87be47bb9f430fb3b80a58550e4bc0.mp4","img":"http://file.zhongjinzhaishi.com/app/college/7c49a2c9bf3d4282b478c8fb0de1d4bc.jpg","updateTime":"2017-09-13","content":"","brief":"视频课程2","author":"视频课程2","top":""},{"id":10,"title":"课程视频1","url":"http://file.zhongjinzhaishi.com/app/college/855e569f1c224d3983a838218a39b9ef.mp4","img":"http://file.zhongjinzhaishi.com/app/college/43e0d501d7f84a6db7dee134ea6da00c.jpg","updateTime":"2017-09-13","content":"","brief":"课程视频1","author":"课程视频1","top":""}]
         */

        private CourseDetailBean courseDetail;
        private List<RecommendDetailBean> recommendDetail;

        public CourseDetailBean getCourseDetail() {
            return courseDetail;
        }

        public void setCourseDetail(CourseDetailBean courseDetail) {
            this.courseDetail = courseDetail;
        }

        public List<RecommendDetailBean> getRecommendDetail() {
            return recommendDetail;
        }

        public void setRecommendDetail(List<RecommendDetailBean> recommendDetail) {
            this.recommendDetail = recommendDetail;
        }

        public static class CourseDetailBean {
            /**
             * id : 10
             * title : 课程视频1
             * brief : 课程视频1
             * author : 课程视频1
             * url : http://file.zhongjinzhaishi.com/app/college/855e569f1c224d3983a838218a39b9ef.mp4
             * img : http://file.zhongjinzhaishi.com/app/college/43e0d501d7f84a6db7dee134ea6da00c.jpg
             * updateTime : 2017-09-13
             */

            private int id;
            private String title;
            private String brief;
            private String author;
            private String url;
            private String img;
            private String updateTime;

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
        }

        public static class RecommendDetailBean {
            /**
             * id : 8
             * title : 视频课程1
             * url : http://file.zhongjinzhaishi.com/app/college/11ee07347b274d53b4cd3d4f426c5e11.mp4
             * img : http://file.zhongjinzhaishi.com/app/college/3f21f38cf43943fc8137398196ae7b32.jpg
             * updateTime : 2017-09-13
             * content :
             * brief : 视频课程1
             * author : 视频课程1
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
