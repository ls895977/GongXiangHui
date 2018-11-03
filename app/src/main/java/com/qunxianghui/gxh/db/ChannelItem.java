package com.qunxianghui.gxh.db;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * tab的对应可序化队列属性
 */
public class ChannelItem implements Serializable, MultiItemEntity {

    private static final long serialVersionUID = -6465237897027410019L;

    public static int TYPE_MY = 1;
    public static int TYPE_OTHER = 2;
    public static int TYPE_MY_CHANNEL = 3;
    public static int TYPE_OTHER_CHANNEL = 4;

    public int viewType = 3;

    public int id;
    @SerializedName("cate_name")
    public String channelName;

    /**
     * 0 未选中 1 选中
     */
    public Boolean isChannelSelect = false;


    /**
     * 栏目对应ID
     */
//    public Integer id;
    /**
     * 栏目对应name
     */
//    @SerializedName("cate_name")
//    public String name;
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
        this.channelName = name;
        this.orderId = Integer.valueOf(orderId);
        this.selected = Integer.valueOf(selected);
    }


    @Override
    public String toString() {
        return "ChannelItem{" + "id=" + id + ", name='" + channelName + '\'' + ", orderId=" + orderId + ", selected=" + selected + '}';
    }

    @Override
    public int getItemType() {
        return viewType;
    }
}
