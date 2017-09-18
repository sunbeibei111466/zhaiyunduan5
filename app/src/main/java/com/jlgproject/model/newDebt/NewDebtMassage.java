package com.jlgproject.model.newDebt;

import java.io.Serializable;
import java.util.List;

/**
 * @author 王锋 on 2017/8/19.
 */

public class NewDebtMassage implements Serializable {


    /**
     * state : ok
     * message :
     * data : [{"name":"有型资产","id":"1","pId":null,"url":"http://file.zhongjinzhaishi.com/image/youxingzichan.png","sonLevelList":[{"name":"房产类","id":"2","pId":"1","url":"http://file.zhongjinzhaishi.com/image/fangchanlei.png","sonLevelList":[{"name":"住宅","id":"11","pId":"2","url":"http://file.zhongjinzhaishi.com/image/two/zhuzhai.png","sonLevelList":[]},{"name":"经济试用房","id":"12","pId":"2","url":"http://file.zhongjinzhaishi.com/image/two/jingjishiyongfang.png","sonLevelList":[]},{"name":"土地","id":"13","pId":"2","url":"http://file.zhongjinzhaishi.com/image/two/tudi.png","sonLevelList":[]}]},{"name":"家具类","id":"5","pId":"1","url":"http://file.zhongjinzhaishi.com/image/jiajulei.png","sonLevelList":[{"name":"家纺","id":"14","pId":"5","url":"http://file.zhongjinzhaishi.com/image/two/jiafang.png","sonLevelList":[]},{"name":"家居","id":"15","pId":"5","url":"http://file.zhongjinzhaishi.com/image/two/jiaju.png","sonLevelList":[]},{"name":"厨房用品","id":"16","pId":"5","url":"http://file.zhongjinzhaishi.com/image/two/chufangyongpin.png","sonLevelList":[]},{"name":"家电","id":"17","pId":"5","url":"http://file.zhongjinzhaishi.com/image/two/jiadian.png","sonLevelList":[]}]},{"name":"汽车类","id":"6","pId":"1","url":"http://file.zhongjinzhaishi.com/image/qichelei.png","sonLevelList":[{"name":"豪车","id":"3","pId":"6","url":"http://file.zhongjinzhaishi.com/image/two/haoche.png","sonLevelList":[]},{"name":"SUV","id":"8","pId":"6","url":"http://file.zhongjinzhaishi.com/image/two/suv.png","sonLevelList":[]},{"name":"家用","id":"9","pId":"6","url":"http://file.zhongjinzhaishi.com/image/two/jiayong.png","sonLevelList":[]},{"name":"跑车","id":"10","pId":"6","url":"http://file.zhongjinzhaishi.com/image/two/paoche.png","sonLevelList":[]}]},{"name":"生活用品","id":"7","pId":"1","url":"http://file.zhongjinzhaishi.com/image/shenghuoyongpin.png","sonLevelList":[{"name":"食品","id":"18","pId":"7","url":"http://file.zhongjinzhaishi.com/image/two/shipin.png","sonLevelList":[]},{"name":"服装","id":"19","pId":"7","url":"http://file.zhongjinzhaishi.com/image/two/fuzhuang.png","sonLevelList":[]},{"name":"鞋类","id":"20","pId":"7","url":"http://file.zhongjinzhaishi.com/image/two/xielei.png","sonLevelList":[]},{"name":"装饰品","id":"21","pId":"7","url":"http://file.zhongjinzhaishi.com/image/two/zhuangshipin.png","sonLevelList":[]},{"name":"箱包","id":"22","pId":"7","url":"http://file.zhongjinzhaishi.com/image/two/xiangbao.png","sonLevelList":[]}]}]},{"name":"无型资产","id":"4","pId":null,"url":"http://file.zhongjinzhaishi.com/image/wuxingzichan.png","sonLevelList":[{"name":"无形资产类","id":"28","pId":"4","url":"http://file.zhongjinzhaishi.com/image/wuxingzichanlei.png","sonLevelList":[{"name":"专利权","id":"23","pId":"28","url":"http://file.zhongjinzhaishi.com/image/two/zhuanliquan.png","sonLevelList":[]},{"name":"著作权","id":"24","pId":"28","url":"http://file.zhongjinzhaishi.com/image/two/zhuzuoquan.png","sonLevelList":[]},{"name":"特许权","id":"25","pId":"28","url":"http://file.zhongjinzhaishi.com/image/two/texuquan.png","sonLevelList":[]},{"name":"商业秘诀","id":"26","pId":"28","url":"http://file.zhongjinzhaishi.com/image/two/shangyemijue.png","sonLevelList":[]},{"name":"其他","id":"27","pId":"28","url":"http://file.zhongjinzhaishi.com/image/two/qita.png","sonLevelList":[]}]}]}]
     */

    private String state;
    private String message;
    /**
     * name : 有型资产
     * id : 1
     * pId : null
     * url : http://file.zhongjinzhaishi.com/image/youxingzichan.png
     * sonLevelList : [{"name":"房产类","id":"2","pId":"1","url":"http://file.zhongjinzhaishi.com/image/fangchanlei.png","sonLevelList":[{"name":"住宅","id":"11","pId":"2","url":"http://file.zhongjinzhaishi.com/image/two/zhuzhai.png","sonLevelList":[]},{"name":"经济试用房","id":"12","pId":"2","url":"http://file.zhongjinzhaishi.com/image/two/jingjishiyongfang.png","sonLevelList":[]},{"name":"土地","id":"13","pId":"2","url":"http://file.zhongjinzhaishi.com/image/two/tudi.png","sonLevelList":[]}]},{"name":"家具类","id":"5","pId":"1","url":"http://file.zhongjinzhaishi.com/image/jiajulei.png","sonLevelList":[{"name":"家纺","id":"14","pId":"5","url":"http://file.zhongjinzhaishi.com/image/two/jiafang.png","sonLevelList":[]},{"name":"家居","id":"15","pId":"5","url":"http://file.zhongjinzhaishi.com/image/two/jiaju.png","sonLevelList":[]},{"name":"厨房用品","id":"16","pId":"5","url":"http://file.zhongjinzhaishi.com/image/two/chufangyongpin.png","sonLevelList":[]},{"name":"家电","id":"17","pId":"5","url":"http://file.zhongjinzhaishi.com/image/two/jiadian.png","sonLevelList":[]}]},{"name":"汽车类","id":"6","pId":"1","url":"http://file.zhongjinzhaishi.com/image/qichelei.png","sonLevelList":[{"name":"豪车","id":"3","pId":"6","url":"http://file.zhongjinzhaishi.com/image/two/haoche.png","sonLevelList":[]},{"name":"SUV","id":"8","pId":"6","url":"http://file.zhongjinzhaishi.com/image/two/suv.png","sonLevelList":[]},{"name":"家用","id":"9","pId":"6","url":"http://file.zhongjinzhaishi.com/image/two/jiayong.png","sonLevelList":[]},{"name":"跑车","id":"10","pId":"6","url":"http://file.zhongjinzhaishi.com/image/two/paoche.png","sonLevelList":[]}]},{"name":"生活用品","id":"7","pId":"1","url":"http://file.zhongjinzhaishi.com/image/shenghuoyongpin.png","sonLevelList":[{"name":"食品","id":"18","pId":"7","url":"http://file.zhongjinzhaishi.com/image/two/shipin.png","sonLevelList":[]},{"name":"服装","id":"19","pId":"7","url":"http://file.zhongjinzhaishi.com/image/two/fuzhuang.png","sonLevelList":[]},{"name":"鞋类","id":"20","pId":"7","url":"http://file.zhongjinzhaishi.com/image/two/xielei.png","sonLevelList":[]},{"name":"装饰品","id":"21","pId":"7","url":"http://file.zhongjinzhaishi.com/image/two/zhuangshipin.png","sonLevelList":[]},{"name":"箱包","id":"22","pId":"7","url":"http://file.zhongjinzhaishi.com/image/two/xiangbao.png","sonLevelList":[]}]}]
     */

    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String name;
        private String id;
        private Object pId;
        private String url;
        /**
         * name : 房产类
         * id : 2
         * pId : 1
         * url : http://file.zhongjinzhaishi.com/image/fangchanlei.png
         * sonLevelList : [{"name":"住宅","id":"11","pId":"2","url":"http://file.zhongjinzhaishi.com/image/two/zhuzhai.png","sonLevelList":[]},{"name":"经济试用房","id":"12","pId":"2","url":"http://file.zhongjinzhaishi.com/image/two/jingjishiyongfang.png","sonLevelList":[]},{"name":"土地","id":"13","pId":"2","url":"http://file.zhongjinzhaishi.com/image/two/tudi.png","sonLevelList":[]}]
         */

        private List<SonLevelListBean> sonLevelList;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Object getPId() {
            return pId;
        }

        public void setPId(Object pId) {
            this.pId = pId;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<SonLevelListBean> getSonLevelList() {
            return sonLevelList;
        }

        public void setSonLevelList(List<SonLevelListBean> sonLevelList) {
            this.sonLevelList = sonLevelList;
        }

        public static class SonLevelListBean {
            private String name;
            private String id;
            private String pId;
            private String url;
            /**
             * name : 住宅
             * id : 11
             * pId : 2
             * url : http://file.zhongjinzhaishi.com/image/two/zhuzhai.png
             * sonLevelList : []
             */

            private List<com.jlgproject.model.newDebt.SonLevelListBean> sonLevelList;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPId() {
                return pId;
            }

            public void setPId(String pId) {
                this.pId = pId;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public List<com.jlgproject.model.newDebt.SonLevelListBean> getSonLevelList() {
                return sonLevelList;
            }

            public void setSonLevelList(List<com.jlgproject.model.newDebt.SonLevelListBean> sonLevelList) {
                this.sonLevelList = sonLevelList;
            }


        }
    }
}
