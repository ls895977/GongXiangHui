package com.qunxianghui.gxh.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 作者：李标
 * 日期  2017/11/6 15:39
 * 邮箱：Lb_android@163.com
 * 模块：ListArray 集合的工具类
 * 描述: 集合的工具类
 */
public class ListUtils {
    private static List<String> linkedList = null,arrayList=null;
    private static List<Integer> linkedListInteger = null;

    private ListUtils() {}
     /***
     * 创建一个List集合对象
     * @return new 对象
     */
    public static  <T> List<T> getNewArrayObjectList() {
        return  new ArrayList<T>();
    }
      /***
     * 创建一个List集合对象
     * @return new 对象
     */
    public static <T> List<T> getNewLinkedObjectList() {
        return  new LinkedList<T>();
    }
    /***
     * 得到一个List集合对象
     * @return new 对象
     */
    public static List<String> getLinkedListString() {
        if (linkedList == null) {
            linkedList = getNewLinkedObjectList();
        } else {
            linkedList.clear();
        }
        return linkedList;
    }
    /***
     * 得到一个List集合对象
     * @return new 对象
     */
    public static List<String> getArrayListString() {
        if (arrayList == null) {
            arrayList = getNewArrayObjectList();
        } else {
            arrayList.clear();
        }
        return arrayList;
    }
    /***
     * 得到一个List集合对象
     * @return new 对象
     */
    public static List<Integer> getArrayListInteger() {
        if (linkedListInteger == null) {
            linkedListInteger = getNewArrayObjectList();
        } else {
            linkedListInteger.clear();
        }
        return linkedListInteger;
    }
}
