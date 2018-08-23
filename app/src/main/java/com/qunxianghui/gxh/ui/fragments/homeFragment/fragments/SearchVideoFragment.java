package com.qunxianghui.gxh.ui.fragments.homeFragment.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.homeAdapter.HomeVideoSearchAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.home.HomeVideoSearchBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.SpConstant;
import com.qunxianghui.gxh.ui.activity.NewsDetailActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.AddTiePianAdvertActivity;
import com.qunxianghui.gxh.utils.GsonUtil;
import com.qunxianghui.gxh.utils.SPUtils;

import java.util.List;

import butterknife.BindView;

/**
 * @author 小强
 * @time 2018/5/28  18:07
 * @desc 搜索的页面
 */
public class SearchVideoFragment extends BaseFragment implements HomeVideoSearchAdapter.VideoSearchListClickListener{
    public static final String DATA = "data";
    @BindView(R.id.recyclerview_video)
    RecyclerView mRecyclerview;
    private HomeVideoSearchBean mBean;
    private List<HomeVideoSearchBean.DataBean> mSearchVideodata;

    /**
     * 子类实现此抽象方法返回View进行展示
     */
    @Override
    public int getLayoutId() {
        return R.layout.fragment_video_search;
    }

    /**
     * 子类在此方法中实现数据的初始化
     */
    @Override
    public void initData() {
        String keyWords = getArguments().getString(DATA);
        if (!TextUtils.isEmpty(keyWords)) {
            goNextWorks(keyWords);
        }
    }

    /**
     * ==================请求网络=====================
     */
    private void goNextWorks(String trim) {
        OkGo.<String>get(Constant.SEARCH_GET_VIDEO_LIST).
                params("keywords", trim).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        parseData(response.body());
                    }
                });
    }

    //设置数据
    private void parseData(String body) {
        mBean = GsonUtil.parseJsonWithGson(body, HomeVideoSearchBean.class);
        mSearchVideodata = mBean.getData();
        HomeVideoSearchAdapter adapter = new HomeVideoSearchAdapter(mActivity, mSearchVideodata);
        adapter.setVideoSearchListClickListener(this);
        mRecyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                String url = mBean.getData().get(position).getVideo_url();
                int uuid = mBean.getData().get(position).getUuid();
                Intent intent = new Intent(mActivity, NewsDetailActivity.class);
                intent.putExtra("url", Constant.VIDEO_DETAIL_URL);
                intent.putExtra("uuid", uuid);
                intent.putExtra("token", SPUtils.getString(SpConstant.ACCESS_TOKEN, ""));
                startActivity(intent);
            }
        });

    }

    /**
     * ==================初始化fragment=====================
     */
    public static SearchVideoFragment newInstance(String data) {
        SearchVideoFragment fragment = new SearchVideoFragment();
        Bundle bundle = new Bundle();
        bundle.putString(SearchVideoFragment.DATA, data);
        fragment.setArguments(bundle);
        return fragment;
    }


    /*搜索的贴片的点击事件 */
    @Override
    public void PasterClick(int position) {
        Intent intent = new Intent(mActivity, AddTiePianAdvertActivity.class);
        StringBuilder sb = new StringBuilder(Constant.VIDEO_DETAIL_URL);
        sb.append("?token=").append(SPUtils.getString(SpConstant.ACCESS_TOKEN, "")).append("&uuid=").append(mSearchVideodata.get(position).getUuid());
        intent.putExtra("url", sb.toString());
        startActivity(intent);
    }
}
