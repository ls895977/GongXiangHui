package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MyIssueLocalServiceAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.mine.MineIssueLocalServiceBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.observer.EventManager;
import com.qunxianghui.gxh.utils.ToastUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;

/**
 * 本地服务
 */
public class MyIssueLocalServiceFragment extends BaseFragment implements Observer {

    @BindView(R.id.xrecycler_mylocal_servive)
    XRecyclerView mRv;
    @BindView(R.id.bt_myissue_localservice_delete)
    Button btnDelete;
    private int mSkip = 0;
    private List<MineIssueLocalServiceBean.DataBean> mList = new ArrayList<>();
    private MyIssueLocalServiceAdapter mAdapter;
    private String data_id="";
    @Override
    public int getLayoutId() {
        return R.layout.fragment_myissue_localservice;
    }

    @Override
    public void initViews(View view) {
        EventManager.getInstance().addObserver(this);
        mRv.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        mAdapter = new MyIssueLocalServiceAdapter(getContext(), mList);
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
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    deleteLocalServiceData();
            }
        });
    }
    private void RequestDeleteData() {
        OkGo.<String>post(Constant.CANCEL_COLLECT_URL)
                .params("id", data_id)
                .params("type","2")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body());
                            int code = jsonObject.getInt("code");
                            if (code == 200) {
                                ToastUtils.showLong("删除成功");
                                ArrayList<MineIssueLocalServiceBean.DataBean> selectList = new ArrayList<MineIssueLocalServiceBean.DataBean>();
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
    /*多条删除*/
    private void deleteLocalServiceData() {

        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).isChecked() == true) {
                //这边获取选中的数据id
                if (data_id.equals("")) {
                    //这边获取选中的数据id
                    data_id = data_id + mList.get(i).getId();
                } else {
                    data_id = data_id + "," + mList.get(i).getId();
                }
                RequestDeleteData();

            }
        }
    }

    @Override
    public void initData() {
        OkGo.<MineIssueLocalServiceBean>post(Constant.MYISSURE_LOCAL_SERVICE_URL)
                .params("limit", 10)
                .params("skip", mSkip)
                .execute(new JsonCallback<MineIssueLocalServiceBean>() {
                    @Override
                    public void onSuccess(Response<MineIssueLocalServiceBean> response) {
                        parseData(response.body());
                    }
                });
    }

    private void parseData(MineIssueLocalServiceBean data) {
        if (data.getCode() == 200) {
            if (mSkip == 200) {
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


    @Override
    public void update(Observable observable, Object o) {
        if (o instanceof String && "localser".equals(o)) {
            mAdapter.isShow = true;
            mAdapter.notifyDataSetChanged();
            btnDelete.setVisibility(View.VISIBLE);
        }
        if (o instanceof String && "localser_c".equals(o)) {
            mAdapter.isShow = false;
            mAdapter.notifyDataSetChanged();
            btnDelete.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventManager.getInstance().deleteObserver(this);
    }

}
