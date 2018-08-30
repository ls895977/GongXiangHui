package com.qunxianghui.gxh.bean.location;

import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;
import com.qunxianghui.gxh.utils.GsonUtils;
import com.qunxianghui.gxh.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * 作者：李标
 * 日期  2018/5/28 09:54
 * 邮箱：Lb_android@163.com
 * 模块：
 * 描述:
 */

public class TestMode {


    /**
     * code : 0
     * message :
     * data : {"list":[{"id":742,"uuid":1521859296,"member_id":1000099,"title":"","content":"自制烧仙草第一期，下一次改善配方比，味道会更顺滑","like_cnt":1,"comment_cnt":0,"ctime":"2小时前","ip":"112.17.240.6","status":0,"images":["http://api.qunxianghui.com.cn/upload/images/20180528/2625ea1a5e207616736f8f244cd675c9.jpg"],"member_name":"玄黄子","member_avatar":"http://api.qunxianghui.com.cn/upload/images/20180508/72c9c177b015cc362f8e01642fafbde6.png","comment_res":[],"comment_num":"","collect":"","click_like":[{"id":1011,"data_uuid":1521859296,"member_id":1000099,"unlike":0,"member_name":"玄黄子"}],"like_info_res":"","delete":"","client_id":0},{"id":741,"uuid":1521859295,"member_id":1000033,"title":"","content":"","like_cnt":1,"comment_cnt":0,"ctime":"2小时前","ip":"211.138.116.7","status":0,"images":["http://api.qunxianghui.com.cn/upload/images/20180528/d7feab505e6a6bb49b987a9162e662fc.jpg"],"member_name":"中国好吃货","member_avatar":"http://api.qunxianghui.com.cn/upload/images/20180519/de960748b264dfe49d7c0586721c27f7.png","comment_res":[],"comment_num":"","collect":"","click_like":[{"id":1012,"data_uuid":1521859295,"member_id":1000099,"unlike":0,"member_name":"玄黄子"}],"like_info_res":"","delete":"","client_id":0},{"id":740,"uuid":1521859293,"member_id":1000162,"title":"","content":"落叶随风将要去远方，\n我离开故乡\n是为了茁壮成树","like_cnt":1,"comment_cnt":0,"ctime":"2小时前","ip":"113.215.161.106","status":0,"images":["http://api.qunxianghui.com.cn/upload/images/20180528/40a5f5a748ac092d3984f375803c3604.jpg"],"member_name":"乌云漏光是希望","member_avatar":"http://api.qunxianghui.com.cn/upload/images/20180521/6ff529836bfef8fad2020ded0eb55edf.png","comment_res":[],"comment_num":"","collect":"","click_like":[{"id":1013,"data_uuid":1521859293,"member_id":1000099,"unlike":0,"member_name":"玄黄子"}],"like_info_res":"","delete":"","client_id":0},{"id":739,"uuid":1521859292,"member_id":1000037,"title":"","content":"19年后的你","like_cnt":1,"comment_cnt":0,"ctime":"2小时前","ip":"183.157.81.215","status":0,"images":["http://api.qunxianghui.com.cn/upload/images/20180528/007d802a9165451eb5a1313eedb06d6a.jpg"],"member_name":"犟犟","member_avatar":"","comment_res":[],"comment_num":"","collect":"","click_like":[{"id":1014,"data_uuid":1521859292,"member_id":1000099,"unlike":0,"member_name":"玄黄子"}],"like_info_res":"","delete":"","client_id":0}]}
     */

    private int code;
    private String message;
    private DataBean data;

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

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 742
             * uuid : 1521859296
             * member_id : 1000099
             * title :
             * content : 自制烧仙草第一期，下一次改善配方比，味道会更顺滑
             * like_cnt : 1
             * comment_cnt : 0
             * ctime : 2小时前
             * ip : 112.17.240.6
             * status : 0
             * images : ["http://api.qunxianghui.com.cn/upload/images/20180528/2625ea1a5e207616736f8f244cd675c9.jpg"]
             * member_name : 玄黄子
             * member_avatar : http://api.qunxianghui.com.cn/upload/images/20180508/72c9c177b015cc362f8e01642fafbde6.png
             * comment_res : []
             * comment_num :
             * collect :
             * click_like : [{"id":1011,"data_uuid":1521859296,"member_id":1000099,"unlike":0,"member_name":"玄黄子"}]
             * like_info_res :
             * delete :
             * client_id : 0
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
            private String like_info_res;
            private String delete;
            private int client_id;
            private List<String> images;
            private boolean isChecked;

            public boolean isChecked() {
                return isChecked;
            }

            public void setChecked(boolean checked) {
                isChecked = checked;
            }

            private List<CommentBean> comment_res;

            public List<ClickLikeBean> getClick_like() {
                if (tem !=null && tem.size()>0){
                    return tem;
                }
                List<ClickLikeBean>  clickLikeBeanList= ListUtils.getNewArrayObjectList();
                    if(!TextUtils.isEmpty(click_like.toString())){
                    clickLikeBeanList= GsonUtils.jsonTypeTokenFromJson(click_like.toString(),new TypeToken<List<ClickLikeBean>>(){}.getType());
                }
                return clickLikeBeanList;
            }

            public void setClick_like(Object click_like) {
                this.click_like = click_like;
            }

            private Object click_like;
//            private List<ClickLikeBean> click_like;
            private List<ClickLikeBean> tem;

            public void setTem(List<ClickLikeBean> tem) {
                this.tem = tem;
            }

            public List<ClickLikeBean> getTem() {
                if (tem==null || tem.size()==0) {
                    tem = new ArrayList<>();
                    tem.addAll(getClick_like());
                }
                return tem;
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

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }

            public List<CommentBean> getComment_res() {
                return comment_res;
            }

            public void setComment_res(List<CommentBean> comment_res) {
                this.comment_res = comment_res;
            }

//            public List<ClickLikeBean> getClick_like() {
//                return click_like;
//            }
//
//            public void setClick_like(List<ClickLikeBean> click_like) {
//                this.click_like = click_like;
//            }

            public static class ClickLikeBean {
                /**
                 * id : 1011
                 * data_uuid : 1521859296
                 * member_id : 1000099
                 * unlike : 0
                 * member_name : 玄黄子
                 */

                private int code;
                private int id;
                private int data_uuid;
                private int member_id;
                private int unlike;
                private String member_name;
                private String message;

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

                public int getCode() {
                    return code;
                }

                public void setCode(int code) {
                    this.code = code;
                }

                public void setMember_name(String member_name) {
                    this.member_name = member_name;
                }

                public String getMessage() {
                    return message;
                }

                public void setMessage(String message) {
                    this.message = message;
                }
            }
        }
    }
}
