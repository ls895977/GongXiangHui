package com.qunxianghui.gxh.utils;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;

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
        OkGo.<String>post(Constant.CATCH_USERDATA_URL).
                execute(new JsonCallback<String>() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (HttpStatusUtil.getStatus(response.body())) {
//                            Logger.d("onSuccess-->:" + response.body().toString());
                            parseUserData(response.body());
                            return;
                        }
                        //toActivity(LoginActivity.class);
//                        Logger.d("onSuccess-->:" + response.body().toString());
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
