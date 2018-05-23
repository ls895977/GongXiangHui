package com.qunxianghui.gxh.bean.location;

import java.io.Serializable;
import java.util.List;

public class MyCollectBean implements Serializable {

    /**
     * code : 0
     * message : 收藏成功
     * data : []
     */

    private int code;
    private String message;
    private List<?> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
