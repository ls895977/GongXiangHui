package com.qunxianghui.gxh.db;

public class Model {

    private String title;
    private String content;
    private String imgUrl;

    private String contentfrom;
    private int count;
    private String longtime;

    public String getContentfrom() {
        return contentfrom;
    }

    public void setContentfrom(String contentfrom) {
        this.contentfrom = contentfrom;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getLongtime() {
        return longtime;
    }

    public void setLongtime(String longtime) {
        this.longtime = longtime;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
