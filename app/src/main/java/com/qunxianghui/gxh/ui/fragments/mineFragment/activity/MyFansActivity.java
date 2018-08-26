package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.mineAdapter.MyFansAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.CommonBean;
import com.qunxianghui.gxh.bean.mine.MineFansBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.widget.TitleBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MyFansActivity extends BaseActivity implements MyFansAdapter.myFansItemClickListener {
    @BindView(R.id.recycler_mine_fances)
    XRecyclerView mRecyclerMineFances;

    private List<MineFansBean.DataBean> dataList = new ArrayList<>();
    private int count = 0;
    private boolean mIsFirst = true;
    private boolean mIsRefresh = false;
    private MyFansAdapter myFansAdapter;
    private Handler handler = new Handler();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_myfans;
    }

    @Override
    protected void initViews() {
        super.initViews();
        new TitleBuilder(this).setTitleText("我的粉丝").setLeftIco(R.mipmap.common_black_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mRecyclerMineFances.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void initData() {
        super.initData();
        RequestMyFansData();
    }

    /*請求我的粉丝数据*/
    private void RequestMyFansData() {
        OkGo.<MineFansBean>post(Constant.MYFANS_URL)
                .params("limit", 10)
                .params("skip", count)
                .execute(new JsonCallback<MineFansBean>() {
                    @Override
                    public void onSuccess(Response<MineFansBean> response) {
                        parseFocusData(response.body());
                    }
                });
    }

    /*解析我的粉丝的数据*/
    private void parseFocusData(MineFansBean mineFansBean) {
        if (mIsRefresh) {
            mIsRefresh = false;
            dataList.clear();
        }
        dataList.addAll(mineFansBean.getData());
        count = dataList.size();
        if (mineFansBean.getCode() == 200) {
            if (mIsFirst) {
                mIsFirst = false;
                myFansAdapter = new MyFansAdapter(mContext, dataList);
                myFansAdapter.setMyFansItemClickListener(this);
                mRecyclerMineFances.setAdapter(myFansAdapter);

            }
            mRecyclerMineFances.refreshComplete();
            myFansAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    Intent intent = new Intent(mContext, PersonDetailActivity.class);
                    intent.putExtra("member_id", dataList.get(position - 1).getMember_id());
                    startActivity(intent);
                }
            });

            myFansAdapter.notifyItemChanged(count, mineFansBean.getData().size());
        }
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        mRecyclerMineFances.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                count = 0;
                mIsRefresh = true;
                RequestMyFansData();
            }

            @Override
            public void onLoadMore() {
                RequestMyFansData();
            }
        });
    }

    /*我的粉丝的关注*/
    @Override
    public void FansClick(final int position) {
        int be_member_id = dataList.get(position).getMember_id();
        OkGo.<CommonBean>post(Constant.ATTENTION_URL)
                .params("be_member_id", be_member_id)
                .execute(new JsonCallback<CommonBean>() {
                    @Override
                    public void onSuccess(Response<CommonBean> response) {
                        int code = response.body().code;
                        if (code == 0) {
                            asyncShowToast("关注成功");
                            dataList.get(position).setFollow_type(1);

                        } else if (code == 202) {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    asyncShowToast("取消关注成功");
                                    dataList.get(position).setFollow_type(0);
                                }
                            });
                        }
                        myFansAdapter.notifyDataSetChanged();

                    }
                });
    }
}
