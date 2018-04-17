package com.gongxianghui.bean.home;

/**
 * Created by Administrator on 2018/4/16 0016.
 */

public class BianMinBean {
    private int pic;
    private String text;

    public BianMinBean(int pic, String text) {
        this.pic = pic;
        this.text = text;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
