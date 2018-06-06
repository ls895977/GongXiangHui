package com.qunxianghui.gxh.fragments.mineFragment.fragment;

import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.mineAdapter.MineMessageAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.generalize.EmployeePaiHangBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/4/14 0014.
 */


@SuppressLint("ValidFragment")
public class MineMessageFragment extends BaseFragment {
    private String type = "";

    @SuppressLint("ValidFragment")
    public MineMessageFragment(String type) {
        this.type = type;
    }

    private List<String> mDatas = new ArrayList<>();
    @BindView(R.id.xrecycler_mineMessage)
    XRecyclerView xrecyclerMineMessage;


    private String url = Constant.GENERALIZE_PAIHANG_URL + "?type=";


    @Override
    protected void onLoadData() {
        OkGo.<String>post(url).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {

                parsePaiHangData(response.body());


            }
        });

    }

    private void parsePaiHangData(String body) {
        final EmployeePaiHangBean employeePaiHangBean = GsonUtils.jsonFromJson(body, EmployeePaiHangBean.class);
        if (employeePaiHangBean.getCode() == 0) {
            final List<EmployeePaiHangBean.DataBean> dataList = employeePaiHangBean.getData();
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_minemessage;
    }

    @Override
    public void initDatas() {

        for (int i = 0; i < 30; i++) {
            mDatas.add(i, i + 1 + " ");
        }
        final MineMessageAdapter mineMessageAdapter = new MineMessageAdapter(mActivity, mDatas);
        mineMessageAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(mActivity, "点击了:" + position, Toast.LENGTH_SHORT).show();
            }
        });

        xrecyclerMineMessage.setAdapter(mineMessageAdapter);


    }

    @Override
    public void initViews(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setSmoothScrollbarEnabled(false);
        xrecyclerMineMessage.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void initListeners() {
        xrecyclerMineMessage.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                xrecyclerMineMessage.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                xrecyclerMineMessage.refreshComplete();
            }
        });
    }


}
