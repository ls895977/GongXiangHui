package com.qunxianghui.gxh.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author: lujialei
 * @date: 2018/9/25
 * @describe:
 */

@Entity
public class NewsEntity {
    @Id
    private long id;
    private String url;
    private int isRead;//0未读 1已读
    @Generated(hash = 1952550505)
    public NewsEntity(long id, String url, int isRead) {
        this.id = id;
        this.url = url;
        this.isRead = isRead;
    }
    @Generated(hash = 2121778047)
    public NewsEntity() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public int getIsRead() {
        return this.isRead;
    }
    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }
}
