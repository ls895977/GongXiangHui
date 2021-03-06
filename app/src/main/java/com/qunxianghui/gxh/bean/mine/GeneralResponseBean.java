package com.qunxianghui.gxh.bean.mine;

import java.io.Serializable;
import java.util.List;

/**
 * 通用返回信息的模板
 * <p>
 * Created by zlt
 *
 */

public class GeneralResponseBean implements Serializable {

    private static final long serialVersionUID = 7633287747986518406L;

    /**
     * code : 100
     * message : 请传入正确的手机号
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
