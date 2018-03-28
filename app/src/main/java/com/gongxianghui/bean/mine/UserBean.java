package com.gongxianghui.bean.mine;

/**
 * Created by Administrator on 2018/3/28 0028.
 */

public class UserBean {
    private int id;
    private String username;
    private String password;

    public UserBean( String username, String password) {

        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserBean[id="+id+",username="+username+",password="+password+"]";
    }
}
