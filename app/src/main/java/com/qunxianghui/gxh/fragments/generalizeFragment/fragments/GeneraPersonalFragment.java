package com.qunxianghui.gxh.fragments.generalizeFragment.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.activity.NewsDetailActivity;
import com.qunxianghui.gxh.adapter.homeAdapter.HomeItemListAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.home.HomeNewListBean;
import com.qunxianghui.gxh.bean.home.MoreTypeBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtil;
import com.qunxianghui.gxh.utils.GsonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/4/3 0003.
 */

public class GeneraPersonalFragment extends BaseFragment {
    private List<MoreTypeBean> mDatas;
    private int[] icons = {R.mipmap.ic_test_0, R.mipmap.ic_test_1, R.mipmap.ic_test_2, R.mipmap.ic_test_3,R.mipmap.ic_test_0, R.mipmap.ic_test_1, R.mipmap.ic_test_2, R.mipmap.ic_test_3};

    @BindView(R.id.xrecycler_genera_personal_list)
    XRecyclerView xrecyclerGeneraPersonalList;
    Unbinder unbinder;
    private List<HomeNewListBean> data;

    @Override
    public int getLayoutId() {
        return R.layout.genera_personl;
    }

    @Override
    public void initDatas() {
        mDatas = new ArrayList<>();
        //随机数用来标记item界面的类型
        Random random = new Random();
        for (int i=0;i<icons.length;i++){
            MoreTypeBean more = new MoreTypeBean();
            more.pic=icons[i];
            more.type=random.nextInt(3);
            mDatas.add(more);

            //首页新闻数据
//            OkGo.<String>get(Constant.HOME_NEWS_LIST_URL)
//                    .execute(new StringCallback() {
//                        @Override
//                        public void onSuccess(Response<String> response) {
//                            parseData(response.body());
//                        }
//                    });
        }

        xrecyclerGeneraPersonalList.setLayoutManager(new LinearLayoutManager(mActivity));
        HomeItemListAdapter adapter = new HomeItemListAdapter(mDatas);
        adapter.setOnItemClickListener(new HomeItemListAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                toActivity(NewsDetailActivity.class);
            }

            @Override
            public void onLongClick(int position) {
                asyncShowToast("处理长按的操作");

            }
        });
        xrecyclerGeneraPersonalList.setAdapter(adapter);
    }



    private void parseData(String body) {

//        final HomeNewListBean homeNewListBean = GsonUtils.jsonFromJson(body, HomeNewListBean.class);
//        data = homeNewListBean.getData();

    }

    @Override
    public void initViews(View view) {

    }

    @Override
    protected void initListeners() {
        xrecyclerGeneraPersonalList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                xrecyclerGeneraPersonalList.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                xrecyclerGeneraPersonalList.refreshComplete();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void onLoadData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
