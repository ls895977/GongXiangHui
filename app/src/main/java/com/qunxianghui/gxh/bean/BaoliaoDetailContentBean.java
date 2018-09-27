package com.qunxianghui.gxh.bean;

import java.io.Serializable;

public class BaoliaoDetailContentBean implements Serializable {

    /**
     * img : http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/upload/image/20180906/7b87c9dcbd233f2a7708c90cb2c5b71d.jpeg,http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/upload/image/20180906/b2ba0b27c2d7b3b9a25186f8a5dec0be.jpeg,http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/upload/image/20180906/c909eed16df25449a86841db42372a84.jpeg
     * text : ffff
     */

    private String img;
    private String text;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
