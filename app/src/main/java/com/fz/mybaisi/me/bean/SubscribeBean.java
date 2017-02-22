package com.fz.mybaisi.me.bean;

import java.util.List;

/**
 * @FileName:com.fz.mybaisi.me.bean.SubscribeBean.java
 * @author：方志
 * @date: 2017-01-03 19:12
 * @QQ：459119626
 * @微信：15549433151
 * @function <推荐订阅的数据>
 */

public class SubscribeBean {

    private List<RecTagsBean> rec_tags;
    private List<?> sub_tags;

    public List<RecTagsBean> getRec_tags() {
        return rec_tags;
    }

    public void setRec_tags(List<RecTagsBean> rec_tags) {
        this.rec_tags = rec_tags;
    }

    public List<?> getSub_tags() {
        return sub_tags;
    }

    public void setSub_tags(List<?> sub_tags) {
        this.sub_tags = sub_tags;
    }

    public static class RecTagsBean {
        /**
         * theme_name : 不得姐体验官
         * image_detail : http://img.spriteapp.cn/ugc/2016/12/17/151502_76805.png
         * post_num : 477
         * image_list : http://img.spriteapp.cn/ugc/2016/12/17/151502_68205.png
         * is_sub : 0
         * sub_number : 3081
         * theme_id : 42835
         */

        private String theme_name;
        private String image_detail;
        private int post_num;
        private String image_list;
        private int is_sub;
        private int sub_number;
        private int theme_id;

        public String getTheme_name() {
            return theme_name;
        }

        public void setTheme_name(String theme_name) {
            this.theme_name = theme_name;
        }

        public String getImage_detail() {
            return image_detail;
        }

        public void setImage_detail(String image_detail) {
            this.image_detail = image_detail;
        }

        public int getPost_num() {
            return post_num;
        }

        public void setPost_num(int post_num) {
            this.post_num = post_num;
        }

        public String getImage_list() {
            return image_list;
        }

        public void setImage_list(String image_list) {
            this.image_list = image_list;
        }

        public int getIs_sub() {
            return is_sub;
        }

        public void setIs_sub(int is_sub) {
            this.is_sub = is_sub;
        }

        public int getSub_number() {
            return sub_number;
        }

        public void setSub_number(int sub_number) {
            this.sub_number = sub_number;
        }

        public int getTheme_id() {
            return theme_id;
        }

        public void setTheme_id(int theme_id) {
            this.theme_id = theme_id;
        }
    }
}
