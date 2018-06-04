package com.qunxianghui.gxh.bean.mine;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qunxianghui.gxh.utils.GsonUtils;
import com.qunxianghui.gxh.utils.ListUtils;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MineIssurePostBean implements Serializable {

    /**
     * code : 0
     * message :
     * data : {"list":[{"id":952,"uuid":1521860368,"member_id":1000175,"title":"","content":"脑袋瓦特啦","like_cnt":0,"comment_cnt":0,"ctime":"2天前","ip":"113.215.161.106","status":0,"images":[],"member_name":"臭居居的归来","member_avatar":"http://api.qunxianghui.com.cn/upload/images/20180524/f746158014f1c6361431e3347316d42a.png","comment_res":[],"comment_num":"","collect":"true","click_like":"","like_info_res":"","delete":"true","client_id":1000175},{"id":907,"uuid":1521860141,"member_id":1000175,"title":"","content":"多么亲戚的领导，生日快乐哦","like_cnt":0,"comment_cnt":0,"ctime":"3天前","ip":"113.215.161.106","status":0,"images":["http://api.qunxianghui.com.cn/upload/images/20180601/2fab07a5c74f1c0cc4430bc0a5fbe55d.jpg"],"member_name":"臭居居的归来","member_avatar":"http://api.qunxianghui.com.cn/upload/images/20180524/f746158014f1c6361431e3347316d42a.png","comment_res":[],"comment_num":"","collect":"","click_like":"","like_info_res":"","delete":"true","client_id":1000175},{"id":874,"uuid":1521860067,"member_id":1000175,"title":"","content":"知道为什么这么帅么","like_cnt":1,"comment_cnt":0,"ctime":"3天前","ip":"113.215.161.106","status":0,"images":["http://api.qunxianghui.com.cn/upload/images/20180531/ec423f7a2645566f6c52f8374f27f3ec.png"],"member_name":"臭居居的归来","member_avatar":"http://api.qunxianghui.com.cn/upload/images/20180524/f746158014f1c6361431e3347316d42a.png","comment_res":[],"comment_num":"","collect":"","click_like":[{"id":1225,"data_uuid":1521860067,"member_id":1000052,"unlike":0,"member_name":"彼岸花"}],"like_info_res":"","delete":"true","client_id":1000175},{"id":783,"uuid":1521859491,"member_id":1000175,"title":"","content":"开心一刻","like_cnt":1,"comment_cnt":0,"ctime":"6天前","ip":"183.157.81.215","status":0,"images":["http://api.qunxianghui.com.cn/upload/images/20180529/e7d30e9df165c35f99a34a578d1a6049.jpg"],"member_name":"臭居居的归来","member_avatar":"http://api.qunxianghui.com.cn/upload/images/20180524/f746158014f1c6361431e3347316d42a.png","comment_res":[],"comment_num":"","collect":"","click_like":[{"id":1163,"data_uuid":1521859491,"member_id":1000035,"unlike":0,"member_name":"逍遥"}],"like_info_res":"","delete":"true","client_id":1000175}]}
     */

    private int code;
    private String message;
    private DataBean data;

    public static MineIssurePostBean objectFromData(String str) {

        return new Gson().fromJson(str, MineIssurePostBean.class);
    }

    public static List<MineIssurePostBean> arrayMineIssurePostBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<MineIssurePostBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ListBean> list;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }

        public static List<DataBean> arrayDataBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<DataBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 952
             * uuid : 1521860368
             * member_id : 1000175
             * title :
             * content : 脑袋瓦特啦
             * like_cnt : 0
             * comment_cnt : 0
             * ctime : 2天前
             * ip : 113.215.161.106
             * status : 0
             * images : []
             * member_name : 臭居居的归来
             * member_avatar : http://api.qunxianghui.com.cn/upload/images/20180524/f746158014f1c6361431e3347316d42a.png
             * comment_res : []
             * comment_num :
             * collect : true
             * click_like :
             * like_info_res :
             * delete : true
             * client_id : 1000175
             */

            private int id;
            private int uuid;
            private int member_id;
            private String title;
            private String content;
            private int like_cnt;
            private int comment_cnt;
            private String ctime;
            private String ip;
            private int status;
            private String member_name;
            private String member_avatar;
            private String comment_num;
            private String collect;
            private Object click_like;
            private String like_info_res;
            private String delete;
            private int client_id;
            private List<?> images;
            private List<?> comment_res;

            public List<ClickLikeBean> getClick_like() {
                List<ClickLikeBean> clickLikeBeanList = ListUtils.getNewArrayObjectList();
                if (!TextUtils.isEmpty(click_like.toString())) {
                    clickLikeBeanList = GsonUtils.jsonTypeTokenFromJson(click_like.toString(), new TypeToken<List<ClickLikeBean>>() {
                    }.getType());
                }
                return clickLikeBeanList;
            }



            public void setClick_like(Object click_like) {
                this.click_like = click_like;
            }

            public static ListBean objectFromData(String str) {

                return new Gson().fromJson(str, ListBean.class);
            }

            public static List<ListBean> arrayListBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<ListBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUuid() {
                return uuid;
            }

            public void setUuid(int uuid) {
                this.uuid = uuid;
            }

            public int getMember_id() {
                return member_id;
            }

            public void setMember_id(int member_id) {
                this.member_id = member_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getLike_cnt() {
                return like_cnt;
            }

            public void setLike_cnt(int like_cnt) {
                this.like_cnt = like_cnt;
            }

            public int getComment_cnt() {
                return comment_cnt;
            }

            public void setComment_cnt(int comment_cnt) {
                this.comment_cnt = comment_cnt;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
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

            public String getComment_num() {
                return comment_num;
            }

            public void setComment_num(String comment_num) {
                this.comment_num = comment_num;
            }

            public String getCollect() {
                return collect;
            }

            public void setCollect(String collect) {
                this.collect = collect;
            }

//            public String getClick_like() {
//                return click_like;
//            }
//
//            public void setClick_like(String click_like) {
//
//                this.click_like = click_like;
//            }

            public String getLike_info_res() {
                return like_info_res;
            }

            public void setLike_info_res(String like_info_res) {
                this.like_info_res = like_info_res;
            }

            public String getDelete() {
                return delete;
            }

            public void setDelete(String delete) {
                this.delete = delete;
            }

            public int getClient_id() {
                return client_id;
            }

            public void setClient_id(int client_id) {
                this.client_id = client_id;
            }

            public List<?> getImages() {
                return images;
            }

            public void setImages(List<?> images) {
                this.images = images;
            }

            public List<?> getComment_res() {
                return comment_res;
            }

            public void setComment_res(List<?> comment_res) {
                this.comment_res = comment_res;
            }

            public static class ClickLikeBean {


                /**
                 * id : 1225
                 * data_uuid : 1521860067
                 * member_id : 1000052
                 * unlike : 0
                 * member_name : 彼岸花
                 */

                private int id;
                private int data_uuid;
                private int member_id;
                private int unlike;
                private String member_name;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
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

                public int getUnlike() {
                    return unlike;
                }

                public void setUnlike(int unlike) {
                    this.unlike = unlike;
                }

                public String getMember_name() {
                    return member_name;
                }

                public void setMember_name(String member_name) {
                    this.member_name = member_name;
                }
            }
        }
    }
}
