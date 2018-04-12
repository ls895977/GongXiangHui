package com.gongxianghui.bean.mine;

import java.io.Serializable;

/**
 * 通用返回信息的模板
 * <p>
 * Created by zlt
 *
 */

public class GeneralResponseBean implements Serializable {

    private static final long serialVersionUID = 7633287747986518406L;
    /**
     * errno : 0
     * data : 1
     * message : 请求成功
     */

    private int errno;
    private String data;
    private String message;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
