package com.qunxianghui.gxh.interfaces;

import com.qunxianghui.gxh.bean.Status;

import java.util.List;

public interface RequestCallBack {
    void success(List<Status> data);

    void fail(Exception e);
}
