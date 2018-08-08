package com.qunxianghui.gxh.ui.fragments.homeFragment.activity;

import android.view.View;
import android.widget.AdapterView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.homeAdapter.DragAdapter;
import com.qunxianghui.gxh.adapter.homeAdapter.OtherAdapter;
import com.qunxianghui.gxh.bean.home.HomeVideoChannelBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.db.ChannelItem;
import com.qunxianghui.gxh.utils.GsonUtils;
import com.qunxianghui.gxh.widget.DragGrid;
import com.qunxianghui.gxh.widget.OtherGridView;
import com.qunxianghui.gxh.widget.TitleBuilder;

import java.util.ArrayList;

/**
 * 对tab进行添加删除排序操作
 */
public class HomeVideoChannelActivity extends GestureDetectorActivity implements AdapterView.OnItemClickListener {

    public static final String VIDEO_USER_CHANNEL = "user_channel";//订阅列表
    private HomeVideoChannelBean.DataBean mDatas;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_homevideo_channel;
    }

    /**
     * 用户栏目
     */
    private DragGrid userGridView; // GridView
    DragAdapter userAdapter; // 适配器
    ArrayList<ChannelItem> userChannelList = new ArrayList<>();


    /**
     * 其它栏目
     */
    private OtherGridView otherGridView; // GridView
    OtherAdapter otherAdapter; // 适配器
    ArrayList<ChannelItem> otherChannelList = new ArrayList<>(); // 数据源

    @Override
    protected void initViews() {
        userGridView = findViewById(R.id.userGridView);
        otherGridView = findViewById(R.id.otherGridView);
    }

    @Override
    protected void initData() {
        new TitleBuilder(HomeVideoChannelActivity.this).setLeftIco(R.mipmap.common_black_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userAdapter.isListChanged()) {
                    setResult(HomeVideoActivity.VIDEO_CHANNELRESULT);
                    finish();
                } else {
                    finish();
                }
            }
        }).setTitleText("频道管理");
        ArrayList<ChannelItem> userChannelListData = (ArrayList<ChannelItem>) getIntent().getSerializableExtra(VIDEO_USER_CHANNEL);
        if (userChannelListData != null) {
            userChannelList = userChannelListData;
            userAdapter = new DragAdapter(this, userChannelListData);
            userGridView.setAdapter(userAdapter);
        }
        //获取全部频道
        OkGo.<String>post(Constant.EDIT_VIDEO_TAB_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                getAllVideoData(response.body());
            }

        });
        otherGridView.setOnItemClickListener(this);
        userGridView.setOnItemClickListener(this);
    }

    private void getAllVideoData(String body) {
        HomeVideoChannelBean.DataBean.OthersBean othersBean = GsonUtils.jsonFromJson(body, HomeVideoChannelBean.DataBean.OthersBean.class);
        if (othersBean != null) {


        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
