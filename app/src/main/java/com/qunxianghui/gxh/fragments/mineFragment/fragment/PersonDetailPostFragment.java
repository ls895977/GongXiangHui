package com.qunxianghui.gxh.fragments.mineFragment.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MineIssurePostAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.location.TestMode;
import com.qunxianghui.gxh.bean.mine.MineIssurePostBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.fragments.locationFragment.adapter.NineGridTest2Adapter;
import com.qunxianghui.gxh.fragments.mineFragment.activity.PersonDetailActivity;
import com.qunxianghui.gxh.utils.GsonUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PersonDetailPostFragment extends BaseFragment {
    @BindView(R.id.xrecycler_persondetail_post)
    XRecyclerView xrecyclerPersondetailPost;
    Unbinder unbinder;
    private List<TestMode.DataBean.ListBean> postList;

    @Override
    protected void onLoadData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_persondetail_post;
    }

    @Override
    public void initDatas() {
        final PersonDetailActivity personDetailActivity = (PersonDetailActivity) getActivity();


        /**
         * 获取帖子列表
         */
        OkGo.<String>post(Constant.LOCATION_NEWS_LIST_URL)
                .params("user_id",personDetailActivity.member_id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Logger.e("用户发布的帖子+++++" + response.body().toString());

                        parsePersonDetailPostData(response.body());


                    }
                });
    }

    private void parsePersonDetailPostData(String body) {
        final TestMode testMode = GsonUtils.jsonFromJson(body, TestMode.class);

        if (testMode.getCode()==0){
            postList = testMode.getData().getList();

            final NineGridTest2Adapter persondetailPostAdapter = new NineGridTest2Adapter(mActivity, postList);
            xrecyclerPersondetailPost.setAdapter(persondetailPostAdapter);


        }


    }

    @Override
    public void initViews(View view) {
        xrecyclerPersondetailPost.setLayoutManager(new LinearLayoutManager(mActivity,LinearLayoutManager.VERTICAL,false));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
