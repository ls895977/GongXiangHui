package com.gongxianghui.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/4/12 0012.
 */

public class Person implements Serializable {
    /**
     * 序列化的版本号
     */
    private  static final long serialVersionUID=1L;
    private int _id;
    private String name;
    private int age;
    private String sex;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
