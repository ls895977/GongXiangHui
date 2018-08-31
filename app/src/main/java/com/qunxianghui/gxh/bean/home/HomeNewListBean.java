package com.qunxianghui.gxh.bean.home;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.List;

public class HomeNewListBean implements MultiItemEntity, Serializable {


    private int id;
    private int uuid;
    private int channel_id;
    private int member_id;
    private String title;
    private String picurl;
    private String linkurl;
    private String description;
    private String content;
    private int like_cnt;
    private int comment_cnt;
    private int view_cnt;
    private String ctime;
    private String ip;
    private int status;
    private String source;
    private String tags;
    private int forward_cnt;
    private int share_cnt;
    private String url;
    private List<String> images;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUuid() {
        return uuid;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
    }

    public int getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(int channel_id) {
        this.channel_id = channel_id;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getLinkurl() {
        return linkurl;
    }

    public void setLinkurl(String linkurl) {
        this.linkurl = linkurl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLike_cnt() {
        return like_cnt;
    }

    public void setLike_cnt(int like_cnt) {
        this.like_cnt = like_cnt;
    }

    public int getComment_cnt() {
        return comment_cnt;
    }

    public void setComment_cnt(int comment_cnt) {
        this.comment_cnt = comment_cnt;
    }

    public int getView_cnt() {
        return view_cnt;
    }

    public void setView_cnt(int view_cnt) {
        this.view_cnt = view_cnt;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getForward_cnt() {
        return forward_cnt;
    }

    public void setForward_cnt(int forward_cnt) {
        this.forward_cnt = forward_cnt;
    }

    public int getShare_cnt() {
        return share_cnt;
    }

    public void setShare_cnt(int share_cnt) {
        this.share_cnt = share_cnt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    @Override
    public int getItemType() {
        switch (images.size()) {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 2;
        }
        return 2;
    }

    @Override
    public String toString() {
        return "DataBean{" + "id=" + id + ", uuid=" + uuid + ", channel_id=" + channel_id + ", member_id=" + member_id + ", title='" + title + '\'' + ", picurl='" + picurl + '\'' + ", " +
                "linkurl='" + linkurl + '\'' + ", description='" + description + '\'' + ", content='" + content + '\'' + ", like_cnt=" + like_cnt + ", comment_cnt=" + comment_cnt + ", " +
                "view_cnt=" + view_cnt + ", ctime='" + ctime + '\'' + ", ip='" + ip + '\'' + ", status=" + status + ", source='" + source + '\'' + ", tags='" + tags + '\'' + ", forward_cnt=" +
                forward_cnt + ", share_cnt=" + share_cnt + ", url='" + url + '\'' + ", images=" + images + '}';
    }


}
