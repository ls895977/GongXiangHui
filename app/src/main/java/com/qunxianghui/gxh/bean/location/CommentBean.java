package com.qunxianghui.gxh.bean.location;

public class CommentBean {


    /**
     * id : 1241
     * comment_id : 1213
     * pid : 0
     * uuid : 1521863845
     * data_uuid : 1521863807
     * member_id : 1000304
     * content : 今天
     * ctime : 1534579446
     * ip : 118.114.82.194
     * status : 1
     * read : 0
     * member_name : 173****3627
     * member_avatar :
     * member_reply_name : 飙分狂人
     * comment_delete : true
     */

    private int id;
    private int comment_id;
    private int pid;
    private int uuid;
    private int data_uuid;
    private int member_id;
    private String content;
    private int ctime;
    private String ip;
    private int status;
    private int read;
    private String member_name;
    private String member_avatar;
    private String member_reply_name;
    private String comment_delete;

    public int getId() {
        return id>1000?id:-1;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getUuid() {
        return uuid;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
    }

    public int getData_uuid() {
        return data_uuid;
    }

    public void setData_uuid(int data_uuid) {
        this.data_uuid = data_uuid;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCtime() {
        return ctime;
    }

    public void setCtime(int ctime) {
        this.ctime = ctime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getMember_avatar() {
        return member_avatar;
    }

    public void setMember_avatar(String member_avatar) {
        this.member_avatar = member_avatar;
    }

    public String getMember_reply_name() {
        return member_reply_name;
    }

    public void setMember_reply_name(String member_reply_name) {
        this.member_reply_name = member_reply_name;
    }

    public String getComment_delete() {
        return comment_delete;
    }

    public void setComment_delete(String comment_delete) {
        this.comment_delete = comment_delete;
    }
}
