package com.qunxianghui.gxh.bean.mine;

import java.io.Serializable;
import java.util.List;

public class RegistBean implements Serializable {

    /**
     * code : 101
     * message : 验证码错误
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
