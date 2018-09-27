package com.qunxianghui.gxh.utils;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.SpConstant;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class UserUtil {
    private static UserUtil instance = null;

    private UserUtil() {
        getUserData();
    }

    public String mNick;
    public String mAvatar;
    public int id;

    public static UserUtil getInstance() {
        if (instance == null) {
            synchronized (UserUtil.class) {
                if (instance == null) {
                    instance = new UserUtil();
                }
            }
        }
        return instance;
    }

    private void getUserData() {
        OkGo.<String>post(Constant.CATCH_USERDATA_URL)
                .headers("X-accesstoken", SPUtils.getString(SpConstant.ACCESS_TOKEN, ""))
                .headers("X-appkey", String.valueOf(100))
                .execute(new JsonCallback<String>() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (HttpStatusUtil.getStatus(response.body())) {
                            parseUserData(response.body());

                            Logger.e("测试数据" + response.body().toString());


                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });


    }

    private void parseUserData(String body) {
        try {
            JSONObject jsonObject = new JSONObject(body);
            JSONObject data = jsonObject.getJSONObject("data");
            mNick = data.getString("nick");
            mAvatar = data.getString("avatar");
            id = data.getInt("id");
//            mMobile = data.getString("mobile");
//            mAddress = data.getString("address");
//            mSex = data.getInt("sex");
//
//            like_cnt = data.getInt("like_cnt");
//            posts_cnt = data.getInt("posts_cnt");
//            comment_cnt = data.getInt("comment_cnt");
//
//            mLevelName = data.getJSONObject("level_info").getString("name");

            Object companyInfo = new JSONTokener(data.getString("company_info")).nextValue();
            if (companyInfo instanceof JSONArray) {
                Logger.d("fillUserData-->数组:");
            } else if (companyInfo instanceof Object) {
                Logger.d("fillUserData-->对象:");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
