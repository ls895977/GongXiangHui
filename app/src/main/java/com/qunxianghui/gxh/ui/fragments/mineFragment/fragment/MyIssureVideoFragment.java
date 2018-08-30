package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.mineAdapter.MineIssueVideoAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.CommonBean;
import com.qunxianghui.gxh.bean.mine.MineIssueVideoBean;
import com.qunxianghui.gxh.bean.mine.MyCollectVideoDetailBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.SpConstant;
import com.qunxianghui.gxh.observer.EventManager;
import com.qunxianghui.gxh.ui.activity.NewsDetailActivity;
import com.qunxianghui.gxh.utils.SPUtils;
import com.qunxianghui.gxh.utils.ToastUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;

/**
 * 视频
 */
public class MyIssureVideoFragment extends BaseFragment implements MineIssueVideoAdapter.MyIssueVideoClikListener,Observer {

    @BindView(R.id.recycler_mine_issue_video)
    XRecyclerView mRv;
    @BindView(R.id.bt_myissue_video_delete)
    Button btnDelete;
    private int mSkip = 0;
    private List<MineIssueVideoBean.DataBean> mList = new ArrayList<>();
    private MineIssueVideoAdapter mAdapter;
    private String data_id="";
    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine_issue_video;
    }

    @Override
    public void initViews(View view) {
        EventManager.getInstance().addObserver(this);
        mRv.setLayoutManager(new GridLayoutManager(mActivity, 2, GridLayoutManager.VERTICAL, false));
        mAdapter = new MineIssueVideoAdapter(getContext(), mList);
        mRv.setAdapter(mAdapter);
        mRv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mSkip = 0;
                initData();
            }

            @Override
            public void onLoadMore() {
                mSkip += 10;
                initData();
            }
        });
        mAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                if(!mAdapter.isShow){
                    int uuid = mList.get(position - 1).getUuid();
                    SkipMyIssueVideoDetail(uuid, position);
                }

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ;
                for (int i = 0; i < mList.size(); i++) {

                    if (mList.get(i).isChecked() == true) {
                        //这边获取选中的数据id
                        if (data_id.equals("")) {
                            data_id = data_id + mList.get(i).getInfo().getUuid();
                        } else {
                            data_id = data_id + "," + mList.get(i).getInfo().getUuid();
                        }
                    }
                }

                RequestDeleteData();
            }

        });
    }

    @Override
    public void initData() {
        OkGo.<MineIssueVideoBean>post(Constant.GET_ISSURE_VIDEO_URL)
                .params("limit", 10)
                .params("skip", mSkip)
                .execute(new JsonCallback<MineIssueVideoBean>() {
                    @Override
                    public void onSuccess(Response<MineIssueVideoBean> response) {
                        parseData(response.body());
                    }
                });
    }

    private void parseData(MineIssueVideoBean data) {
        if (data.getCode() == 0) {
            if (mSkip == 0) {
                mList.clear();
                mRv.setLoadingMoreEnabled(true);
            }
            if (data.getData().size() < 10) {
                mRv.setLoadingMoreEnabled(false);
            }
            mList.addAll(data.getData());
            mRv.refreshComplete();
        } else {
            mRv.setLoadingMoreEnabled(false);
        }
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 跳转我的发布的视频详情页
     *
     * @param uuid
     */
    private void SkipMyIssueVideoDetail(int uuid, final int position) {
        OkGo.<MyCollectVideoDetailBean>post(Constant.GET_NEWS_CONTENT_DETAIL_URL)
                .params("id", uuid)
                .execute(new JsonCallback<MyCollectVideoDetailBean>() {
                    @Override
                    public void onSuccess(Response<MyCollectVideoDetailBean> response) {
                        MyCollectVideoDetailBean myCollectVideoDetailBean = response.body();
                        int code = myCollectVideoDetailBean.getCode();
                        if (code == 0) {
                            String url = myCollectVideoDetailBean.getData().getRand_data().get(position).getUrl();
                            int uuid = myCollectVideoDetailBean.getData().getDetail().getUuid();
                            Intent intent = new Intent(mActivity, NewsDetailActivity.class);
                            intent.putExtra("url", Constant.VIDEO_DETAIL_URL);
                            intent.putExtra("uuid", uuid);
                            intent.putExtra("token", SPUtils.getString(SpConstant.ACCESS_TOKEN, ""));
                            startActivity(intent);
                        }
                    }
                });
    }

    private void RequestDeleteData() {
        Log.d(TAG,"data_id = " + data_id);
        OkGo.<String>post(Constant.CANCEL_ISSUE_URL)
                .params("uuid", data_id)
               // .params("id","")
                //.params("type","")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body());
                            int code = jsonObject.getInt("code");
                            if (code == 200) {
                                ToastUtils.showLong("删除成功");
                                ArrayList<MineIssueVideoBean.DataBean> selectList = new ArrayList<MineIssueVideoBean.DataBean>();
                                for (int j = 0; j <mList.size() ; j++) {
                                    if (mList.get(j).isChecked() == true) {
                                        selectList.add(mList.get(j));
                                        //dataList.remove(j);
                                    }
                                }
                                for(int k=0; k < selectList.size(); k++){
                                    mList.remove(selectList.get(k));
                                }
                                mAdapter.isShow = false;
                                mAdapter.notifyDataSetChanged();
                                btnDelete.setVisibility(View.GONE);
                                EventManager.getInstance().publishMessage("init");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


    /*接口回调  删除视频的操作*/
    @Override
    public void deleVideoItem(final int position) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("删除提示");
        builder.setMessage("您确定要删除该条消息吗?");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DeleteVideo(position);
            }

        });
        mAdapter.notifyDataSetChanged();
        builder.setNegativeButton("取消", null);
        builder.show();
    }

    @Override
    public void update(Observable observable, Object o) {
        if (o instanceof String && "issue_video".equals(o)) {
            mAdapter.isShow = true;
            mAdapter.notifyDataSetChanged();
            btnDelete.setVisibility(View.VISIBLE);
        }
        if (o instanceof String && "issue_video_c".equals(o)) {
            mAdapter.isShow = false;
            mAdapter.notifyDataSetChanged();
            btnDelete.setVisibility(View.GONE);
        }
    }



    /*请求接口删除*/
    private void DeleteVideo(final int position) {
        OkGo.<CommonBean>post(Constant.DELETE_MYISSUE_URL)
                .params("uuid", mList.get(position).getUuid())
                .execute(new JsonCallback<CommonBean>() {
                    @Override
                    public void onSuccess(Response<CommonBean> response) {
                        int code = response.body().code;
                        if (code == 0) {
                            asyncShowToast("删除成功");
                            mList.remove(position);
                            mAdapter.notifyDataSetChanged();
                        } else {
                            asyncShowToast("删除失败");
                        }
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventManager.getInstance().deleteObserver(this);
    }

}
