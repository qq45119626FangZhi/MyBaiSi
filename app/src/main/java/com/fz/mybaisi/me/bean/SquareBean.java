package com.fz.mybaisi.me.bean;

import java.util.List;

/**
 * @FileName:com.fz.mybaisi.me.bean.SquareBean.java
 * @author：方志
 * @date: 2017-01-03 14:19
 * @QQ：459119626
 * @微信：15549433151
 * @function <我的页面中间网格数据>
 */

public class SquareBean {


    /**
     * info : {"count":18,"np":null}
     * tag_list : [{"theme_id":3096,"theme_name":"百思红人"},{"theme_id":9,"theme_name":"自拍"}]
     * square_list : [{"ipad":"","iphone":"","name":"审帖","url":"mod://BDJ_To_Check","android":"","id":28,"icon":"http://img.spriteapp.cn/ugc/2015/05/20/150532_5078.png"},{"ipad":"1.0|","iphone":"3.0|","name":"内容贡献榜","url":"http://m.budejie.com/user/credit.html?from=singlemessage&amp;isappinstalled=0","android":"4.0|","id":151,"icon":"http://mstatic.spriteapp.cn//ugc/2016/07/06/p0VaKL.jpg"},{"ipad":"3.8|1.0","iphone":"3.9|","name":"排行榜","url":"mod://BDJ_To_RankingList","android":"6.2.4|","id":46,"icon":"http://img.spriteapp.cn/ugc/2015/12/11/094131_3938.png"},{"ipad":"","iphone":"","name":"我的收藏","url":"mod://BDJ_To_Mine@dest=2","android":"3.8.0|","id":48,"icon":"http://img.spriteapp.cn/ugc/2015/05/20/150609_9589.png"},{"ipad":"","iphone":"3.3|","name":"随机穿越","url":"mod://BDJ_To_Cate@cate=3#type=0","android":"5.0|","id":55,"icon":"http://img.spriteapp.cn/ugc/2014/12/23/182756_5328.png"},{"ipad":"","iphone":"10.0|","name":"败家姐","url":"http://m.maila88.com/mailaIndex?mailaAppKey=8nZcoCTTD9vhf2tCqzyNbY&amp;mailaOs=android","android":"6.6.2|","id":156,"icon":"http://mstatic.spriteapp.cn//ugc/2016/12/24/rKNcte.jpg"},{"ipad":"1.0|","iphone":"3.0|","name":"百思帮派","url":"http://d.api.budejie.com/gang/enter/","android":"4.0|","id":154,"icon":"http://mstatic.spriteapp.cn//ugc/2016/10/15/xaN_97.jpg"},{"ipad":"1.0|3.8.2","iphone":"|4.1","name":"游戏大厅","url":"http://www.curlend.com/?d=20160513","android":"4.0|","id":87,"icon":"http://img.spriteapp.cn/ugc/2016/02/15/150052_5490.png"},{"ipad":"10.0|","iphone":"4.2|","name":"搜索","url":"mod://App_To_SearchUser","android":"6.4.7|","id":141,"icon":"http://mstatic.spriteapp.cn//ugc/2016/04/18/Oj6QPj.jpg"},{"ipad":"1.0|3.8.2","iphone":"6.0|","name":"美女直播","url":"http://m.2339.com/?union=bsbdj_h5","android":"4.0|","id":116,"icon":"http://img.spriteapp.cn/ugc/2016/01/14/112726_8635.png"},{"ipad":"10.0|","iphone":"3.0|4.1","name":"彩铃","url":"http://m.diyring.cc/friend/48bbbb4128a41b27","android":"4.0|","id":82,"icon":"http://img.spriteapp.cn/ugc/2015/07/06/193140_3393.png"},{"ipad":"1.0|3.8.2","iphone":"3.0|4.1.1","name":"美女写真","url":"http://tu.budejie.com/","android":"4.0|","id":113,"icon":"http://img.spriteapp.cn/ugc/2016/02/22/144250_9910.png"},{"ipad":"6.0|","iphone":"3.0|4.1","name":"超人塔防","url":"http://h5.duantian.cn/open/index.html?spid=5&amp;device=budejie#100090","android":"4.0|","id":102,"icon":"http://mstatic.spriteapp.cn//ugc/2016/10/25/P6OUVr.jpg"},{"ipad":"6.0|","iphone":"3.0|4.1","name":"传世H5","url":"http://h5.mobo168.com/open/index.html?spid=5&amp;device=budejie#100004","android":"2.0|","id":85,"icon":"http://img.spriteapp.cn/ugc/2015/12/23/092659_3690.png"},{"ipad":"100.0|","iphone":"10.0|","name":"下载视频","url":"mod://App_To_MyVideo","android":"5.9.9|","id":83,"icon":"http://img.spriteapp.cn/ugc/2015/07/30/172608_7159.png"},{"ipad":"1.0|","iphone":"4.0|","name":"头条新闻","url":"http://m.9wuli.com/sdkweb/?appId=dfb3405cd7e43be149e1c6679e4e189d&amp;appSecret=35925174bc62732bbbc82f1a1a571ede&amp;navstyle=a","android":"4.0|","id":153,"icon":"http://mstatic.spriteapp.cn//ugc/2016/08/26/vF9w8T.jpg"},{"ipad":"6.0|","iphone":"3.0|4.1","name":"爬塔三国","url":"http://h5.mobo168.com/open/index.html?spid=5&amp;device=budejie#100026","android":"4.0|","id":130,"icon":"http://mstatic.spriteapp.cn//ugc/2016/06/13/16opSP.jpg"},{"ipad":"","iphone":"","name":"更多","url":"","android":"","id":0,"icon":"http://img.spriteapp.cn/ugc/2014/05/06/141211_3901.png"}]
     */

    private InfoBean info;
    private List<TagListBean> tag_list;
    private List<SquareListBean> square_list;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public List<TagListBean> getTag_list() {
        return tag_list;
    }

    public void setTag_list(List<TagListBean> tag_list) {
        this.tag_list = tag_list;
    }

    public List<SquareListBean> getSquare_list() {
        return square_list;
    }

    public void setSquare_list(List<SquareListBean> square_list) {
        this.square_list = square_list;
    }

    public static class InfoBean {
        /**
         * count : 18
         * np : null
         */

        private int count;
        private Object np;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public Object getNp() {
            return np;
        }

        public void setNp(Object np) {
            this.np = np;
        }
    }

    public static class TagListBean {
        /**
         * theme_id : 3096
         * theme_name : 百思红人
         */

        private int theme_id;
        private String theme_name;

        public int getTheme_id() {
            return theme_id;
        }

        public void setTheme_id(int theme_id) {
            this.theme_id = theme_id;
        }

        public String getTheme_name() {
            return theme_name;
        }

        public void setTheme_name(String theme_name) {
            this.theme_name = theme_name;
        }
    }

    public static class SquareListBean {
        /**
         * ipad :
         * iphone :
         * name : 审帖
         * url : mod://BDJ_To_Check
         * android :
         * id : 28
         * icon : http://img.spriteapp.cn/ugc/2015/05/20/150532_5078.png
         */

        private String ipad;
        private String iphone;
        private String name;
        private String url;
        private String android;
        private int id;
        private String icon;

        public String getIpad() {
            return ipad;
        }

        public void setIpad(String ipad) {
            this.ipad = ipad;
        }

        public String getIphone() {
            return iphone;
        }

        public void setIphone(String iphone) {
            this.iphone = iphone;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAndroid() {
            return android;
        }

        public void setAndroid(String android) {
            this.android = android;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }
}
