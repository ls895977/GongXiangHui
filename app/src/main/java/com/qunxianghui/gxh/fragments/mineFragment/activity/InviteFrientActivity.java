package com.qunxianghui.gxh.fragments.mineFragment.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.TestBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/26 0026.
 */

public class InviteFrientActivity extends BaseActivity {


    @BindView(R.id.btn_show)
    Button btnShow;
    @BindView(R.id.tv_show)
    TextView tvShow;
    private ArrayList<TestBean> jsonDataList;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_invite_friend;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        jsonDataList = new ArrayList<TestBean>();
        //从Assets中获取json数据字符串
        String jsonData = getStringFromAssert(this, "parseDataJson.json");
        Log.e(TAG, "jsonData=" + jsonData);

        //解析json字符串
        try {
            JSONArray jsonDataArray = new JSONArray(jsonData);
            if (jsonDataArray.length() > 0) {
                for (int i = 0; i < jsonDataArray.length(); i++) {
                    final JSONObject jsonDataItemObj = jsonDataArray.getJSONObject(i);
                    TestBean testBean = new TestBean();
                    testBean.setCode(jsonDataItemObj.getInt("code"));
                    testBean.setData(jsonDataItemObj.get("data"));
                    jsonDataList.add(testBean);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void initListeners() {
        super.initListeners();
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String showResult = "";
                //循环获取json数据
                for (int i = 0; i < jsonDataList.size(); i++) {
                    final TestBean testBean = jsonDataList.get(i);

                    int code = testBean.getCode();
                    Object data = testBean.getData();
                    String dataType = "Object";
                    if (data instanceof Integer) {
                        data = (Integer) data;
                        dataType = "Integer";
                    } else if (data instanceof String) {
                        data = (String) data;
                        dataType = "String";
                    } else if (data instanceof JSONObject) {
                        data = (JSONObject) data;
                        dataType = "JSONObject";
                    }
                    showResult += "code=" + code + ";data=" + data + "【dataType=" + dataType + "】\n";


                }
                tvShow.setText(showResult);
            }
        });
    }

    private String getStringFromAssert(Context mContext, String assetsFilePath) {

        String content = ""; // 结果字符串
        try {
            final InputStream is = mContext.getResources().getAssets().open(assetsFilePath);
            int ch = 0;
            final ByteArrayOutputStream out = new ByteArrayOutputStream();
            while ((ch = is.read()) != -1) {
                out.write(ch);
            }
            byte[] buff = out.toByteArray();// 以 byte 数组的形式返回此输出流的当前内容
            out.close(); // 关闭流
            is.close(); // 关闭流
            content = new String(buff, "UTF-8"); // 设置字符串编码
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(mContext, "对不起，没有找到指定文件！", Toast.LENGTH_SHORT)
                    .show();
        }


        return content;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}



