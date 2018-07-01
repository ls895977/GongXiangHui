package com.qunxianghui.gxh.fragments.mineFragment.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.AdListAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.mine.AdListBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.fragments.mineFragment.activity.AdvertisActivity;
import com.qunxianghui.gxh.utils.GsonUtil;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by user on 2018/6/10.
 */

@SuppressLint("ValidFragment")
public class AdverTiseCommenFragment extends BaseFragment implements AdListAdapter.AdListener {
    private final int mAdType;
    @BindView(R.id.xrecycler_addver_commen)
    XRecyclerView xrecyclerAddverCommen;
    Unbinder unbinder;

    private AdListAdapter adListAdapter = new AdListAdapter();
    private AdListBean adListBean;
    private int jumpPosition;
    private String positionStr;

    @SuppressLint("ValidFragment")
    public AdverTiseCommenFragment(int index) {
        this.mAdType = index;
    }

    @Override
    protected void onLoadData() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_addvertise_common;
    }

    @Override
    public void initDatas() {
        OkGo.<String>post(Constant.GET_AD_LIST)
                .params("ad_type", mAdType)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body());
                            int code = jsonObject.getInt("code");
                            if (code == 0 && jsonObject.getJSONArray("data") != null) {
                                adListBean = GsonUtil.parseJsonWithGson(response.body(), AdListBean.class);
                                adListAdapter.setDatas(adListBean.data);
                                adListAdapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public void initViews(View view) {
        xrecyclerAddverCommen.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        Intent intent = getActivity().getIntent();
        positionStr = intent.getStringExtra("position");
        adListAdapter.setType(mAdType).setAdOnClickListen(this);
        xrecyclerAddverCommen.setAdapter(adListAdapter);
    }

    @Override
    protected void initListeners() {
        xrecyclerAddverCommen.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                xrecyclerAddverCommen.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                xrecyclerAddverCommen.refreshComplete();
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onEditClick(int position) {
        jumpPosition = position;
        Intent intent = new Intent(getActivity(), AdvertisActivity.class);
        intent.putExtra("isComingFromColum", true);
        String url = adListAdapter.mList.get(position).images;
        intent.putExtra("imageUrl", url);
        intent.putExtra("ad_id", adListAdapter.mList.get(position).id);
        switch (mAdType) {
            case 1:
            case 3:
            case 6:
                intent.putExtra("link", adListAdapter.mList.get(position).link);
                break;
            case 2:
                intent.putExtra("name", adListAdapter.mList.get(position).settings.name);
                intent.putExtra("mobile", adListAdapter.mList.get(position).settings.mobile);
                intent.putExtra("address", adListAdapter.mList.get(position).settings.address);
            case 4:
                intent.putExtra("twoname", adListAdapter.mList.get(position).settings.name);
                intent.putExtra("intro", adListAdapter.mList.get(position).settings.intro);
                break;
            case 5:
                intent.putExtra("nick", adListAdapter.mList.get(position).settings.nick);
                intent.putExtra("qq", adListAdapter.mList.get(position).settings.qq);
                intent.putExtra("intro", adListAdapter.mList.get(position).settings.intro);
                break;

        }
        startActivityForResult(intent, 200);
    }

    @Override
    public void onDeleteClick(int p) {
        final int position = p;
        OkGo.<String>post(Constant.DELETE_AD)
                .params("id", adListAdapter.mList.get(p).id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body());
                            int code = jsonObject.getInt("code");
                            if (code == 0) {
                                Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_LONG).show();
                                adListAdapter.mList.remove(position);
                                adListAdapter.notifyDataSetChanged();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        Log.v("xx-yy-error", response.toString());
                    }
                });
    }

    @Override
    public void onAddCarousel(int p, boolean ischecked) {
        Toast.makeText(getActivity(), "add", Toast.LENGTH_LONG).show();
        OkGo.<String>post(Constant.ADD_SILDE)
                .params("id", adListAdapter.mList.get(p).id)
                .params("is_slide", ischecked ? 1 : 0)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Toast.makeText(getActivity(), response.body(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onUsed(int p) {
        final int position = p;
        OkGo.<String>post(Constant.USED_AD)
                .params("id", adListAdapter.mList.get(p).id)
                .params("position", position)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body());
                            int code = jsonObject.getInt("code");
                            if (code == 0) {
                                Intent intent = new Intent();
                                intent.putExtra("type", 1);
                                intent.putExtra("url", adListBean.data.get(position).images);
                                intent.putExtra("position", positionStr);
                                intent.putExtra("title", adListBean.data.get(position).link);
                                mActivity.setResult(Activity.RESULT_OK, intent);
                                mActivity.finish();
                            }
                        } catch (Exception e) {
                        }
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -2) {
            switch (mAdType) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
            }
            String url = data.getStringExtra("imageUrl");
            String link = data.getStringExtra("link");
            adListAdapter.mList.get(jumpPosition).images = url;
            adListAdapter.mList.get(jumpPosition).link = link;
            adListAdapter.notifyDataSetChanged();
        }
    }
}
