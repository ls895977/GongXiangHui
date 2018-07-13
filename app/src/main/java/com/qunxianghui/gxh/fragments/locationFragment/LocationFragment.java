package com.qunxianghui.gxh.fragments.locationFragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.activity.PhotoBrowserActivity;
import com.qunxianghui.gxh.activity.PublishActivity;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.location.CommentBean;
import com.qunxianghui.gxh.bean.location.MyCollectBean;
import com.qunxianghui.gxh.bean.location.TestMode;
import com.qunxianghui.gxh.callback.DialogCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.LoginMsgHelper;
import com.qunxianghui.gxh.fragments.locationFragment.adapter.NineGridTest2Adapter;
import com.qunxianghui.gxh.fragments.mineFragment.activity.LoginActivity;
import com.qunxianghui.gxh.fragments.mineFragment.activity.PersonDetailActivity;
import com.qunxianghui.gxh.listener.SoftKeyBoardListener;
import com.qunxianghui.gxh.utils.GsonUtil;
import com.qunxianghui.gxh.utils.GsonUtils;
import com.qunxianghui.gxh.utils.UserUtil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class LocationFragment extends BaseFragment implements View.OnClickListener, NineGridTest2Adapter.CircleOnClickListener {
    private static LocationFragment locationFragment;
    @BindView(R.id.tv_location_mine_fabu)
    TextView tvLocationMineFabu;
    LinearLayout commentView;
    EditText comment_edit;
    TextView send_btn;
    XRecyclerView recyclerView;
    Unbinder unbinder;
    NineGridTest2Adapter mAdapter;
    private RelativeLayout topNav;
    private int count = 0;
    private List<TestMode.DataBean.ListBean> dataList = new ArrayList<TestMode.DataBean.ListBean>();
    private boolean mIsFirst = true;
    private int commentPosition;
    private int scrollOffsetY = 0;

    @Override
    public int getLayoutId() {
        mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return R.layout.fragment_location;
    }

    @Override
    public void initDatas() {
        RequestLocationData();
    }

    @Override
    public void initViews(View view) {
        commentView = view.findViewById(R.id.location_send_comment_view);
        comment_edit = view.findViewById(R.id.loaction_comment_edit);
        send_btn = view.findViewById(R.id.location_comment_to_send);
        recyclerView = view.findViewById(R.id.recyclerView_location);
        topNav = view.findViewById(R.id.loaction_top_nav);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        SoftKeyBoardListener.setListener(getActivity(), new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
                Logger.i("xxx-yyy jump :" + commentPosition);
                //View item = recyclerView.getChildAt(commentPosition + 1);
                View item = recyclerView.getLayoutManager().findViewByPosition(commentPosition + 1);
                int offset = 5;
                int keyboardoffset = 80;
                int tabHeight = getArguments().getInt("tabHeight");
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) commentView.getLayoutParams();
                layoutParams.bottomMargin = height - tabHeight - offset;
                commentView.setLayoutParams(layoutParams);
                Logger.i("xxx-yyy scrollOffsetY " + scrollOffsetY);
                if (item != null) {
                    int[] location = new int[2];
                    item.getLocationOnScreen(location);
                    int x = location[0];
                    int y = location[1];
                    Logger.v("xxx-yyy item " + item);
                    Logger.v("xxx-yyy item height :", item.getMeasuredHeight());
                    Logger.v("xxx-yyy y :" + y);
                    Logger.v("xxx-yyy nav top :" + topNav.getMeasuredHeight());
                    Logger.v("xxx-yyy scroll :" + (y + item.getMeasuredHeight() - topNav.getMeasuredHeight()));
                    recyclerView.scrollBy(0, (y + item.getMeasuredHeight() - topNav.getMeasuredHeight()) - (recyclerView.getMeasuredHeight() + tabHeight - height) + keyboardoffset);
                } else {
                    Logger.i("xxx-yyy" + " item is null");
                }
            }

            public int px2dip(Context context, float pxValue) {
                final float scale = context.getResources().getDisplayMetrics().density;
                return (int) (pxValue / scale + 0.5f);
            }

            @Override
            public void keyBoardHide(int height) {
                commentView.setVisibility(View.INVISIBLE);
//                Toast.makeText(getActivity(), "键盘隐藏 高度" + height, Toast.LENGTH_SHORT).show();
//                ViewGroup.LayoutParams layout = mRootView.getLayoutParams();
//                layout.height = layout.height + height;
//                mRootView.setLayoutParams(layout);
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //super.onScrolled(recyclerView, dx, dy);
                //Logger.v("xxx-yyy" + "x :" + dx + "y :" + dy);
                scrollOffsetY = scrollOffsetY + dy;
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case 0:
                        System.out.println("recyclerview已经停止滚动");

                        break;
                    case 1:
                        //System.out.println("recyclerview正在被拖拽");
                        //System.out.println("value " + getActivity().getWindow().getAttributes().softInputMode);
                        if (getActivity().getWindow().getAttributes().softInputMode == WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
                                || getActivity().getWindow().getAttributes().softInputMode == WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    hideSoftKeyboard(comment_edit, getActivity());
                                }
                            }, 10);
                        }
                        break;
                    case 2:
                        //System.out.println("recyclerview正在依靠惯性滚动");
                        break;
                }
            }
        });
    }

    private void RequestLocationData() {
        OkGo.<String>get(Constant.LOCATION_NEWS_LIST_URL)
                .params("limit", 10)
                .params("skip", count)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        parseLocationData(response.body());
                    }
                });
    }

    private void parseLocationData(String body) {
        Logger.i("Location：" + body.toString());
        final TestMode locationListBean = GsonUtils.jsonFromJson(body, TestMode.class);
        dataList.addAll(locationListBean.getData().getList());
        count = dataList.size();
        if (locationListBean.getCode() == 0) {
            if (mIsFirst) {
                mIsFirst = false;
                mAdapter = new NineGridTest2Adapter(mActivity, dataList);
                mAdapter.setOnClickLitener(this);
                recyclerView.setAdapter(mAdapter);
            }
            recyclerView.refreshComplete();
            mAdapter.notifyItemRangeChanged(count, locationListBean.getData().getList().size());

        }
    }

    @Override
    protected void initListeners() {
        tvLocationMineFabu.setOnClickListener(this);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                // 把集合和适配器清空  重新请求数据
                dataList.clear();
                count = 0;
                RequestLocationData();
            }

            @Override
            public void onLoadMore() {
                RequestLocationData();

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

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.tv_location_mine_fabu:
                toActivity(PublishActivity.class);
                break;
            case R.id.tv_alertbottom_up_pic:
                toActivity(PublishActivity.class);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

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

    /**
     * 评论的点击
     *
     * @param position
     * @param content
     */
    @Override
    public void onCommentClick(final int position, String content) {
        if (!LoginMsgHelper.isLogin(getContext())) {
            toActivity(LoginActivity.class);
            mActivity.finish();
            return;
        }
        commentPosition = position;
        commentView.setVisibility(View.VISIBLE);
        comment_edit.setFocusable(true);
        comment_edit.setFocusableInTouchMode(true);
        comment_edit.requestFocus();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (comment_edit.getText().toString().length() <= 0) {
                    Toast.makeText(getActivity(), "请输入评论内容", Toast.LENGTH_LONG).show();
                } else {
                    final int uuid = dataList.get(position).getUuid();
                    if (dataList.get(position).getComment_res().size() <= 0) {
                        dataList.get(position).setComment_res(new ArrayList<CommentBean>());
                        //Toast.makeText(getActivity(),"username :" + dataList.get(position).getMember_name() + " position: " + position  + "origin :" + dataList.get(position).getContent() ,Toast.LENGTH_LONG).show();
                    }
                    List<CommentBean> commentBeanList = dataList.get(position).getComment_res();
                    CommentBean comment = new CommentBean();
                    UserUtil user = UserUtil.getInstance();
                    comment.setContent(comment_edit.getText().toString());
                    comment.setUuid(user.id);
                    comment.setMember_name(user.mNick);
                    commentBeanList.add(comment);
                    mAdapter.notifyDataSetChanged();
                    mAdapter.notifyItemChanged(position);
                    OkGo.<String>post(Constant.ISSURE_DISUSS_URL)
                            .params("uuid", uuid)
                            .params("content", comment_edit.getText().toString())
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    CommentBean comment = GsonUtils.jsonFromJson(response.body(), CommentBean.class);
                                    if (comment.getCode() == 0) {
                                        comment_edit.setText("");
                                        hideSoftKeyboard(comment_edit, getActivity());
                                    }
                                }
                            });


                }
            }
        });
    }

    public static void hideSoftKeyboard(EditText editText, Context context) {
        if (editText != null && context != null) {
            InputMethodManager imm = (InputMethodManager) context
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }

    void requestCommentList(int uuid) {
        OkGo.<String>post(Constant.COMMENT_LIST)
                .params("uuid", uuid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e(TAG, "onSuccess: -----------------------" + response.body());
                    }
                });
    }

    //接口回调之 点赞
    @Override
    public void onLaunClick(final int position) {
        if (!LoginMsgHelper.isLogin(getContext())) {
            toActivity(LoginActivity.class);
            mActivity.finish();
            return;
        }
        if (dataList.get(position).getClick_like() != null && dataList.get(position).getClick_like().toString().length() == 0) {
            if (dataList.get(position).getClick_like().size() <= 0) {
                dataList.get(position).setClick_like(new ArrayList<TestMode.DataBean.ListBean.ClickLikeBean>());
            }
            TestMode.DataBean.ListBean.ClickLikeBean like = new TestMode.DataBean.ListBean.ClickLikeBean();
            UserUtil user = UserUtil.getInstance();
            like.setMember_name(user.mNick);
            List<TestMode.DataBean.ListBean.ClickLikeBean> likeBeanList = dataList.get(position).getClick_like();
            likeBeanList.add(like);
            mAdapter.notifyDataSetChanged();
            mAdapter.notifyItemChanged(position);
            OkGo.<String>post(Constant.LIKE_URL)
                    .params("data_uuid", dataList.get(position).getUuid()).execute(new DialogCallback<String>(getActivity()) {
                @Override
                public void onSuccess(Response<String> response) {
                    TestMode.DataBean.ListBean.ClickLikeBean like = GsonUtil.parseJsonWithGson(response.body(), TestMode.DataBean.ListBean.ClickLikeBean.class);

                    //Toast.makeText(getActivity(),response.body(),Toast.LENGTH_LONG).show();
                    //Handler haner = new Handler()
                }
            });
        } else {
            OkGo.<String>post(Constant.LIKE_URL)
                    .params("data_uuid", dataList.get(position).getUuid()).execute(new DialogCallback<String>(getActivity()) {
                @Override
                public void onSuccess(Response<String> response) {
                    TestMode.DataBean.ListBean.ClickLikeBean like = GsonUtil.parseJsonWithGson(response.body(), TestMode.DataBean.ListBean.ClickLikeBean.class);
                    //TestMode.DataBean.ListBean.ClickLikeBean like = new TestMode.DataBean.ListBean.ClickLikeBean();
                    UserUtil user = UserUtil.getInstance();
                    like.setMember_name(user.mNick);
                    if (like.getMessage().equalsIgnoreCase("点赞成功")) {
                        dataList.get(position).getTem().add(like);
                        dataList.get(position).setLike_info_res("true");
                        mAdapter.notifyDataSetChanged();
                        mAdapter.notifyItemChanged(position);

                        asyncShowToast("点赞成功");
                    } else if (like.getMessage().equalsIgnoreCase("取消点赞成功")) {
                        List<TestMode.DataBean.ListBean.ClickLikeBean> list = dataList.get(position).getTem();
                        for (int i = 0; i < dataList.get(position).getTem().size(); i++) {
                            TestMode.DataBean.ListBean.ClickLikeBean tem = dataList.get(position).getTem().get(i);
                            if (tem.getMember_name().equalsIgnoreCase(user.mNick)) {
                                if (dataList.get(position).getClick_like().size() == 1 && dataList.get(position).getTem().size() == 1) {
                                    dataList.get(position).setClick_like("");
                                }
                                dataList.get(position).getTem().remove(tem);
                                break;
                            }
                        }
                        dataList.get(position).setLike_info_res("");
                        mAdapter.notifyDataSetChanged();
                        mAdapter.notifyItemChanged(position);
                        asyncShowToast("取消点赞成功");
                    }
                }
            });
        }
    }

    @Override
    public void onCollectionClick(final int position) {
        if (!LoginMsgHelper.isLogin(getContext())) {
            toActivity(LoginActivity.class);
            mActivity.finish();
            return;
        }
        OkGo.<String>post(Constant.ADD_COLLECT_URL)
                .params("data_uuid", dataList.get(position).getUuid()).execute(new DialogCallback<String>(getActivity()) {
            @Override
            public void onSuccess(Response<String> response) {
                MyCollectBean myCollectBean = GsonUtil.parseJsonWithGson(response.body(), MyCollectBean.class);
                if (myCollectBean.getCode() == 0) {
                    Toast.makeText(getActivity(), "收藏成功", Toast.LENGTH_SHORT).show();
                    dataList.get(position).setCollect("true");
                } else if (myCollectBean.getCode() == 202) {
                    Toast.makeText(getActivity(), "取消收藏成功", Toast.LENGTH_SHORT).show();
                    dataList.get(position).setCollect("");
                }
                mAdapter.notifyDataSetChanged();
                mAdapter.notifyItemChanged(position);
            }
        });
    }

    /**
     * 头像点击跳转用户详情页
     *
     * @param position
     */
    @Override
    public void headImageClick(int position) {
        Intent intent = new Intent(mActivity, PersonDetailActivity.class);
        intent.putExtra("member_id", dataList.get(position).getMember_id());
        startActivity(intent);
    }

    /**
     * @param position
     */
    @Override
    public void deletePost(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("删除提示");
        builder.setMessage("您确定要删除该条记录么?");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                OkGo.<String>post(Constant.DELETE_POST_URL)
                        .params("uuid", dataList.get(position).getUuid())
                        .execute(new DialogCallback<String>(getActivity()) {
                            @Override
                            public void onSuccess(Response<String> response) {
                                parseDeletePostAData(response.body(), position);
                            }
                        });
            }
        });
        builder.setNeutralButton("取消", null);

        builder.show();

    }

    /*解析删除的内容*/
    private void parseDeletePostAData(String body, int position) {
        try {
            JSONObject jsonObject = new JSONObject(body);
            int code = jsonObject.getInt("code");
            if (code == 0) {
                dataList.remove(position);
                mAdapter.notifyDataSetChanged();
                asyncShowToast("删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static LocationFragment getInstance() {
        if (locationFragment == null) {
            locationFragment = new LocationFragment();
        }
        return locationFragment;
    }
}

