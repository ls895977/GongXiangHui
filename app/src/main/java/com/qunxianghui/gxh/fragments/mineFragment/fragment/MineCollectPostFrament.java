package com.qunxianghui.gxh.fragments.mineFragment.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.activity.PhotoBrowserActivity;
import com.qunxianghui.gxh.adapter.mineAdapter.MineCollectPostAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.mine.MineCollectPostBean;
import com.qunxianghui.gxh.bean.mine.MyCollectPostBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MineCollectPostFrament extends BaseFragment implements MineCollectPostAdapter.MycollectPostListener {
    @BindView(R.id.recycler_mycollect_post)
    RecyclerView recyclerMycollectPost;
    Unbinder unbinder;
    private List<MineCollectPostBean.DataBean> dataList;

    @Override
    public int getLayoutId() {
        return R.layout.mine_collect_post;
    }

    @Override
    public void initDatas() {
        OkGo.<String>post(Constant.GET_COLLECT_POST_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                parseCollectPostDaTA(response.body());
            }
        });

    }


    private void parseCollectPostDaTA(String body) {
        Logger.d("我收藏帖子的内容+++" + body.toString());
        final MineCollectPostBean myCollectPostBean = GsonUtils.jsonFromJson(body, MineCollectPostBean.class);
        if (myCollectPostBean.getCode() == 0) {
            dataList = myCollectPostBean.getData();

            final MineCollectPostAdapter mineCollectPostAdapter = new MineCollectPostAdapter(mActivity, dataList);
            recyclerMycollectPost.setAdapter(mineCollectPostAdapter);
            mineCollectPostAdapter.setMycollectPostListener(this);
            asyncShowToast("请求成功");
        }
    }

    @Override
    public void initViews(View view) {
        recyclerMycollectPost.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
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


    /**
     * 取消收藏
     *
     * @param position
     */

    @Override
    public void cancelCollect(int position) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("删除提示");
        builder.setMessage("您确定要删除该条消息吗?");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CancelCollectPostData();

            }
        });
        builder.setNegativeButton("取消", null);
        builder.show();
    }


    /**
     * 图片点击
     *
     * @param position
     * @param picpostion
     */
    @Override
    public void onPicClick(int position, int picpostion) {
        List<String> imageList = dataList.get(position).getImages();
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String data : imageList) {
            arrayList.add(data);
        }
        Intent intent = new Intent(getActivity(), PhotoBrowserActivity.class);
        intent.putStringArrayListExtra("url", arrayList);
        intent.putExtra("position", picpostion);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.activity_pop_in, R.anim.pop_out);

    }

    private void CancelCollectPostData() {

        Toast.makeText(mActivity, "取消成功", Toast.LENGTH_SHORT).show();
    }
}
