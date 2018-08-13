package com.qunxianghui.gxh.db;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * tab的对应可序化队列属性
 */
public class ChannelItem implements Serializable {

    private static final long serialVersionUID = -6465237897027410019L;
    /**
     * 栏目对应ID
     */
    public Integer id;
    /**
     * 栏目对应name
     */
    @SerializedName("cate_name")
    public String name;
    /**
     * 栏目在整体中的排序顺序  rank
     */
    public Integer orderId;
    /**
     * 栏目是否选中
     */
    public Integer selected;

    /**
     * id : 1
     * pid : 0
     * cate_name : 搞笑
     * listorder : 0
     * status : 1
     * ctime : 0
     */
//    public int id;
    public int pid;
//    public String cate_name;
    public int listorder;
    public int status;
    public int ctime;

    public ChannelItem() {
    }

    public ChannelItem(int id, String name, int orderId, int selected) {
        this.id = Integer.valueOf(id);
        this.name = name;
        this.orderId = Integer.valueOf(orderId);
        this.selected = Integer.valueOf(selected);
    }

    public int getId() {
        return this.id.intValue();
    }

    public String getName() {
        return this.name;
    }

    public int getOrderId() {
        return this.orderId.intValue();
    }

    public Integer getSelected() {
        return this.selected;
    }

    public void setId(int paramInt) {
        this.id = Integer.valueOf(paramInt);
    }

    public void setName(String paramString) {
        this.name = paramString;
    }

    public void setOrderId(int paramInt) {
        this.orderId = Integer.valueOf(paramInt);
    }

    public void setSelected(Integer paramInteger) {
        this.selected = paramInteger;
    }

    @Override
    public String toString() {
        return "ChannelItem{" + "id=" + id + ", name='" + name + '\'' + ", orderId=" + orderId + ", selected=" + selected + '}';
    }
}
