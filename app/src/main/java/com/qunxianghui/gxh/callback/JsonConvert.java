/*
 * Copyright 2016 jeasonlzy(廖子尧)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.qunxianghui.gxh.callback;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.stream.JsonReader;
import com.lzy.okgo.convert.Converter;
import com.qunxianghui.gxh.bean.CommonBean;
import com.qunxianghui.gxh.bean.CommonResponse;
import com.qunxianghui.gxh.bean.SimpleResponse;
import com.qunxianghui.gxh.config.LoginMsgHelper;
import com.qunxianghui.gxh.observer.TokenLoseEvent;
import com.qunxianghui.gxh.utils.Convert;
import com.qunxianghui.gxh.utils.GsonUtil;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;

public class JsonConvert<T> implements Converter<T> {

    private Type type;
    private Class<T> clazz;
    public static boolean sIsShow = true;

    public JsonConvert() {
    }

    public JsonConvert(Type type) {
        this.type = type;
    }

    public JsonConvert(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * 该方法是子线程处理，不能做ui相关的工作
     * 主要作用是解析网络返回的 response 对象，生成onSuccess回调中需要的数据对象
     * 这里的解析工作不同的业务逻辑基本都不一样,所以需要自己实现,以下给出的时模板代码,实际使用根据需要修改
     */
    @Override
    public T convertResponse(Response response) throws Throwable {

        // 重要的事情说三遍，不同的业务，这里的代码逻辑都不一样，如果你不修改，那么基本不可用
        // 重要的事情说三遍，不同的业务，这里的代码逻辑都不一样，如果你不修改，那么基本不可用
        // 重要的事情说三遍，不同的业务，这里的代码逻辑都不一样，如果你不修改，那么基本不可用

        if (type == null) {
            if (clazz == null) {
                // 如果没有通过构造函数传进来，就自动解析父类泛型的真实类型（有局限性，继承后就无法解析到）
                Type genType = getClass().getGenericSuperclass();
                type = ((ParameterizedType) genType).getActualTypeArguments()[0];
            } else {
                return parseClass(response, clazz);
            }
        }

        if (type instanceof ParameterizedType) {
            return parseParameterizedType(response, (ParameterizedType) type);
        } else if (type instanceof Class) {
            return parseClass(response, (Class<?>) type);
        } else {
            return parseType(response, type);
        }
    }

    private T parseClass(Response response, Class<?> rawType) throws Exception {
        if (rawType == null) return null;
        ResponseBody body = response.body();
        if (body == null) return null;
        String string = body.string();
        isFailure(string);
        if (rawType == String.class) {
            //noinspection unchecked
            return (T) string;
        } else if (rawType == JSONObject.class) {
            //noinspection unchecked
            return (T) new JSONObject(string);
        } else if (rawType == JSONArray.class) {
            //noinspection unchecked
            return (T) new JSONArray(string);
        } else {
            T t = Convert.fromJson(string, rawType);
            response.close();
            return t;
        }
    }

    private T parseType(Response response, Type type) throws Exception {
        if (type == null) return null;
        ResponseBody body = response.body();
        if (body == null) return null;
        JsonReader jsonReader = new JsonReader(body.charStream());

        // 泛型格式如下： new JsonCallback<任意JavaBean>(this)
        T t = Convert.fromJson(jsonReader, type);
        response.close();
        return t;
    }

    private T parseParameterizedType(Response response, ParameterizedType type) throws Exception {
        if (type == null) return null;
        ResponseBody body = response.body();
        if (body == null) return null;
        JsonReader jsonReader = new JsonReader(body.charStream());
        Type rawType = type.getRawType();                     // 泛型的实际类型
        Type typeArgument = type.getActualTypeArguments()[0]; // 泛型的参数
        if (rawType != CommonResponse.class) {
            // 泛型格式如下： new JsonCallback<外层BaseBean<内层JavaBean>>(this)
            T t = Convert.fromJson(jsonReader, type);
            response.close();
            return t;
        } else {
            if (typeArgument == Void.class) {
                // 泛型格式如下： new JsonCallback<LzyResponse<Void>>(this)
                SimpleResponse simpleResponse = Convert.fromJson(jsonReader, SimpleResponse.class);
                response.close();
                //noinspection unchecked
                return (T) simpleResponse.toLzyResponse();
            } else {
                // 泛型格式如下： new JsonCallback<LzyResponse<内层JavaBean>>(this)
                CommonResponse commonResponse = Convert.fromJson(jsonReader, type);
                response.close();
                int code = commonResponse.code;
                //这里的0是以下意思
                //一般来说服务器会和客户端约定一个数表示成功，其余的表示失败，这里根据实际情况修改

                if (LoginMsgHelper.isLogin() && (code == 300 || code == 1000) && sIsShow) {
                    sIsShow = false;
                    EventBus.getDefault().postSticky(new TokenLoseEvent(commonResponse.message));
                }
                if (code == 0) {
                    return (T) commonResponse;
                } else {
                    //直接将服务端的错误信息抛出，onError中可以获取
                    Log.e("okgo", "错误代码： " + code);
                    return (T) commonResponse;
//                    throw new IllegalStateException("错误代码：" + code + "，错误信息：" + lzyResponse.msg);
                }
            }
        }
    }

    private void isFailure(String str) {
        if (LoginMsgHelper.isLogin() && !TextUtils.isEmpty(str) && (str.contains("\"code\":1000") || str.contains("\"code\":300")) && sIsShow) {
            CommonBean commonBean = GsonUtil.parseJsonWithGson(str, CommonBean.class);
            sIsShow = false;
            EventBus.getDefault().postSticky(new TokenLoseEvent(commonBean.message));
        }
    }
}
