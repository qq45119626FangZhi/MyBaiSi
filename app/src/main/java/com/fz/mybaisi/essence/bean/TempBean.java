package com.fz.mybaisi.essence.bean;

import java.io.Serializable;

/**
 * @FileName:com.fz.mybaisi.essence.bean.TempBean.java
 * @author：方志
 * @date: 2016-12-30 18:00
 * @QQ：459119626
 * @微信：15549433151
 * @function <item进入详情页面数据传递类>
 */

public class TempBean implements Serializable{
    private String headerUrl;//头像地址
    private String userName;//用户名
    private String time;//时间
    private String content;//内容文字
    private String videoUrl;//视频地址
    private String musicUrl;//音频地址
    private String dingNumber;//顶的数量
    private String caiNumber;//踩的数量
    private String shareNumber;//分享对的数量
    private String commentNumber;//评论的数量
    private String commentsUrl;//进入评论页面的地址
    private String imageUrl;//图片地址
    private String gifUrl;//gif地址
    private String PersonalUrl;

    public String getPersonalUrl() {
        return PersonalUrl;
    }

    public void setPersonalUrl(String personalUrl) {
        PersonalUrl = personalUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    private String thumbnailUrl;//视频图片地址

    private String Type;//定义类型



    public TempBean() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getGifUrl() {
        return gifUrl;
    }

    public void setGifUrl(String gifUrl) {
        this.gifUrl = gifUrl;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getHeaderUrl() {
        return headerUrl;
    }

    public void setHeaderUrl(String headerUrl) {
        this.headerUrl = headerUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public String getDingNumber() {
        return dingNumber;
    }

    public void setDingNumber(String dingNumber) {
        this.dingNumber = dingNumber;
    }

    public String getCaiNumber() {
        return caiNumber;
    }

    public void setCaiNumber(String caiNumber) {
        this.caiNumber = caiNumber;
    }

    public String getShareNumber() {
        return shareNumber;
    }

    public void setShareNumber(String shareNumber) {
        this.shareNumber = shareNumber;
    }

    public String getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(String commentNumber) {
        this.commentNumber = commentNumber;
    }

    public String getCommentsUrl() {
        return commentsUrl;
    }

    public void setCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
    }


    @Override
    public String toString() {
        return "TempBean{" +
                "headerUrl='" + headerUrl + '\'' +
                ", userName='" + userName + '\'' +
                ", time='" + time + '\'' +
                ", content='" + content + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", musicUrl='" + musicUrl + '\'' +
                ", dingNumber='" + dingNumber + '\'' +
                ", caiNumber='" + caiNumber + '\'' +
                ", shareNumber='" + shareNumber + '\'' +
                ", commentNumber='" + commentNumber + '\'' +
                ", commentsUrl='" + commentsUrl + '\'' +
                ", Type='" + Type + '\'' +
                '}';
    }
}
