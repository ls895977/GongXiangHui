package com.qunxianghui.gxh.bean.mine;

import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;
import com.qunxianghui.gxh.bean.location.CommentBean;
import com.qunxianghui.gxh.bean.location.TestMode;
import com.qunxianghui.gxh.utils.GsonUtils;
import com.qunxianghui.gxh.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

public class MessageDetailBean {

    public int code;
    public String message;
    public TestMode.DataBean.ListBean data;

    public class DataBean {
        //             "model":"Posts",
//             "province_id":0,
//             "city_id":26,
//             "area_id":265,
//             "etime":1528680059,
//             "listorder":0,
//             "deled":0,
//             "comment_res":[],
//             "collect":"",
//             "click_like":"",
        public int id;
        public int uuid;
        public int member_id;
        public String title;
        public String content;
        public int like_cnt;
        public int comment_cnt;
        public String ctime;
        public String ip;
        public int status;
        public List<String> images;
        public String member_name;
        public String member_avatar;
        public String comment_num;
        public String collect;
        public String like_info_res;
        public String delete;
        public int client_id;
        public boolean isChecked;

        public List<CommentBean> comment_res;
        public Object click_like;
        public List<TestMode.DataBean.ListBean.ClickLikeBean> tem;

        public void setTem(List<TestMode.DataBean.ListBean.ClickLikeBean> tem) {
            this.tem = tem;
        }

        public List<TestMode.DataBean.ListBean.ClickLikeBean> getTem() {
            if (tem == null || tem.size() == 0) {
                tem = new ArrayList<>();
                tem.addAll(getClick_like());
            }
            return tem;
        }

        public List<TestMode.DataBean.ListBean.ClickLikeBean> getClick_like() {
            if (tem != null && tem.size() > 0) {
                return tem;
            }
            List<TestMode.DataBean.ListBean.ClickLikeBean> clickLikeBeanList = ListUtils.getNewArrayObjectList();
            if (!TextUtils.isEmpty(click_like.toString())) {
                try {
                    clickLikeBeanList = GsonUtils.jsonTypeTokenFromJson(click_like.toString(), new TypeToken<List<TestMode.DataBean.ListBean.ClickLikeBean>>() {
                    }.getType());
                } catch (Exception e) {
                }
            }
            return clickLikeBeanList;
        }
    }

}
