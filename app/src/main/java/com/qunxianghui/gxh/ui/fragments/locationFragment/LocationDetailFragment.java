package com.qunxianghui.gxh.ui.fragments.locationFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
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
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.CommonBean;
import com.qunxianghui.gxh.bean.location.CommentBean;
import com.qunxianghui.gxh.bean.location.TestMode;
import com.qunxianghui.gxh.callback.DialogCallback;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.LoginMsgHelper;
import com.qunxianghui.gxh.listener.SoftKeyBoardListener;
import com.qunxianghui.gxh.ui.activity.PhotoBrowserActivity;
import com.qunxianghui.gxh.ui.activity.PublishActivity;
import com.qunxianghui.gxh.ui.fragments.locationFragment.activity.InFormActivity;
import com.qunxianghui.gxh.ui.fragments.locationFragment.adapter.NineGridTest2Adapter;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.LoginActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.PersonDetailActivity;
import com.qunxianghui.gxh.utils.GsonUtil;
import com.qunxianghui.gxh.utils.GsonUtils;
import com.qunxianghui.gxh.utils.UserUtil;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class LocationDetailFragment extends BaseFragment implements View.OnClickListener, NineGridTest2Adapter.CircleOnClickListener {

    @BindView(R.id.recyclerView_location)
    XRecyclerView recyclerView;
    @BindView(R.id.loaction_comment_edit)
    EditText comment_edit;
    @BindView(R.id.location_comment_to_send)
    TextView send_btn;
    @BindView(R.id.location_send_comment_view)
    LinearLayout commentView;

    private List<TestMode.DataBean.ListBean> localDataList = new ArrayList<>();
    private int commentPosition;
    private int scrollOffsetY = 0;
    private int count = 0;
    NineGridTest2Adapter mAdapter;
    private int mCateId;

    @Override
    public int getLayoutId() {
        mActivity.getWindow()
                .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
                        | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        return R.layout.fragment_detail_location;
    }

    @Override
    public void initViews(View view) {
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        mAdapter = new NineGridTest2Adapter(mActivity, localDataList);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initData() {

        mCateId = getArguments().getInt("channel_id");
        RequestLocalServiceData();

    }

    private void RequestLocalServiceData() {
        OkGo.<String>get(Constant.LOCATION_NEWS_LIST_URL)
                .params("cate_id", mCateId)
                .params("limit", 10)
                .params("skip", count)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        TestMode locationListBean = GsonUtils.jsonFromJson(response.body(), TestMode.class);
                        localDataList.addAll(locationListBean.getData().getList());
                        count = localDataList.size();
                        if (locationListBean.getCode() == 0) {
                            recyclerView.refreshComplete();
                            mAdapter.notifyItemRangeChanged(count, locationListBean.getData().getList().size());
                        }
                    }
                });

    }

    @Override
    protected void initListeners() {
        mAdapter.setOnClickListener(this);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                localDataList.clear();
                count = 0;
                initData();
            }

            @Override
            public void onLoadMore() {
                initData();
            }
        });
        SoftKeyBoardListener.setListener(getActivity(), new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
                Logger.i("xxx-yyy jump :" + commentPosition);
                View item = recyclerView.getLayoutManager().findViewByPosition(commentPosition);

                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) commentView.getLayoutParams();
                int bottomMargin = height - getBottomKeyboardHeight() - 30;
                layoutParams.bottomMargin = bottomMargin;

                commentView.setLayoutParams(layoutParams);
                if (item != null) {
                    int[] location = new int[2];
                    item.getLocationOnScreen(location);
                    int x = location[0];
                    int y = location[1];
                    Logger.v("xxx-yyy item height :", item.getMeasuredHeight());
                    Logger.v("xxx-yyy y :" + y);
                    recyclerView.smoothScrollBy(0, (bottomMargin + commentView.getHeight() - item.getBottom()));//计算item滚动多少
                } else {
                    Logger.i("xxx-yyy" + " item is null");
                }
            }

            @Override
            public void keyBoardHide(int height) {
                commentView.setVisibility(View.INVISIBLE);
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
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
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_alertbottom_up_pic:
                toActivity(PublishActivity.class);
                break;
        }
    }

    @Override
    public void onPicClick(int position, int picpostion) {
        List<String> imageList = localDataList.get(position).getImages();
        ArrayList<String> arrayList = new ArrayList<>(imageList);
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
        Log.v("xxx-yyy", position + "");
        if (!LoginMsgHelper.isLogin()) {
            toActivity(LoginActivity.class);
            return;
        }
        commentPosition = position;
        commentView.setVisibility(View.VISIBLE);
        comment_edit.setFocusable(true);
        comment_edit.setFocusableInTouchMode(true);
        comment_edit.requestFocus();
        onFocusChange(true);
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (comment_edit.getText().toString().length() <= 0) {
                    Toast.makeText(getActivity(), "请输入评论内容", Toast.LENGTH_LONG).show();
                } else {
                    final int uuid = localDataList.get(position).getUuid();
                    if (localDataList.get(position).getComment_res().size() <= 0) {
                        localDataList.get(position).setComment_res(new ArrayList<CommentBean>());
                    }
                    List<CommentBean> commentBeanList = localDataList.get(position).getComment_res();
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
                            .params("content", comment.getContent())
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    CommentBean comment = GsonUtils.jsonFromJson(response.body(), CommentBean.class);
                                    if (comment.getCode() == 0) {
                                        comment_edit.setText("");
                                        hideSoftKeyboard(comment_edit, getActivity());
                                    } else {
                                        asyncShowToast(response.message());
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
                    .getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }

    //接口回调之 点赞
    @Override
    public void onPraiseClick(final int position) {
        if (!LoginMsgHelper.isLogin()) {
            toActivity(LoginActivity.class);
            mActivity.finish();
            return;
        }
        if (localDataList.get(position).getClick_like() != null && localDataList.get(position).getClick_like().toString().length() == 0) {
            if (localDataList.get(position).getClick_like().size() <= 0) {
                localDataList.get(position).setClick_like(new ArrayList<TestMode.DataBean.ListBean.ClickLikeBean>());
            }
            TestMode.DataBean.ListBean.ClickLikeBean like = new TestMode.DataBean.ListBean.ClickLikeBean();
            UserUtil user = UserUtil.getInstance();
            like.setMember_name(user.mNick);
            List<TestMode.DataBean.ListBean.ClickLikeBean> likeBeanList = localDataList.get(position).getClick_like();
            likeBeanList.add(like);
            mAdapter.notifyDataSetChanged();
            mAdapter.notifyItemChanged(position);
            OkGo.<String>post(Constant.LIKE_URL)
                    .params("data_uuid", localDataList.get(position).getUuid()).execute(new DialogCallback<String>(getActivity()) {
                @Override
                public void onSuccess(Response<String> response) {
                    TestMode.DataBean.ListBean.ClickLikeBean like = GsonUtil.parseJsonWithGson(response.body(), TestMode.DataBean.ListBean.ClickLikeBean.class);
                }

                @Override
                public void onError(Response<String> response) {
                    super.onError(response);
                    asyncShowToast("登陆账号异常");
                }
            });
        } else {
            OkGo.<CommonBean>post(Constant.LIKE_URL)
                    .params("data_uuid", localDataList.get(position).getUuid())
                    .execute(new JsonCallback<CommonBean>() {
                        @Override
                        public void onSuccess(Response<CommonBean> response) {
                            UserUtil user = UserUtil.getInstance();
                            TestMode.DataBean.ListBean.ClickLikeBean clickLikeBean = new TestMode.DataBean.ListBean.ClickLikeBean();
                            clickLikeBean.setMember_name(user.mNick);
//                            like.setMember_name(user.mNick);
                            if ("点赞成功".equals(response.body().msg)) {
                                localDataList.get(position).getTem().add(clickLikeBean);
                                localDataList.get(position).setLike_info_res("true");
                                mAdapter.notifyDataSetChanged();
                                mAdapter.notifyItemChanged(position);
                                asyncShowToast("点赞成功");
                            } else if ("取消点赞成功".equals(response.body().msg)) {
                                List<TestMode.DataBean.ListBean.ClickLikeBean> list = localDataList.get(position).getTem();
                                for (int i = 0; i < localDataList.get(position).getTem().size(); i++) {
                                    TestMode.DataBean.ListBean.ClickLikeBean tem = localDataList.get(position).getTem().get(i);
                                    if (tem.getMember_name().equalsIgnoreCase(user.mNick)) {
                                        if (localDataList.get(position).getClick_like().size() == 1 && localDataList.get(position).getTem().size() == 1) {
                                            localDataList.get(position).setClick_like("");
                                        }
                                        localDataList.get(position).getTem().remove(tem);
                                        break;
                                    }
                                }
                                localDataList.get(position).setLike_info_res("");
                                mAdapter.notifyDataSetChanged();
                                mAdapter.notifyItemChanged(position);
                                asyncShowToast("取消点赞");
                            }
                        }

                        @Override
                        public void onError(Response<CommonBean> response) {
                            super.onError(response);
                            asyncShowToast("登陆账号异常");
                        }
                    });
        }
    }

    @Override
    public void onCollectionClick(final int position) {
        toActivity(InFormActivity.class);
    }

    /**
     * 头像点击跳转用户详情页
     *
     * @param position
     */
    @Override
    public void headImageClick(int position) {
        Intent intent = new Intent(mActivity, PersonDetailActivity.class);
        intent.putExtra("member_id", localDataList.get(position).getMember_id());
        startActivity(intent);
    }

    @Override
    public void commentRecall(final int position, final CommentBean commentBean) {
        commentView.setVisibility(View.VISIBLE);
        comment_edit.setFocusable(true);
        comment_edit.setFocusableInTouchMode(true);
        comment_edit.requestFocus();
        onFocusChange(true);
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(comment_edit.getText().toString())) {
                    OkGo.<String>post(Constant.REPAY_COMMENT_URL).params("comment_id", commentBean.getComment_id())
                            .params("content", comment_edit.getText().toString().trim())
                            .params("uuid", commentBean.getUuid())
                            .params("pid", commentBean.getPid())
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    CommentBean comment = GsonUtils.jsonFromJson(response.body(), CommentBean.class);
                                    if (comment.getCode() == 0) {
                                        comment_edit.setText("");
                                        hideSoftKeyboard(comment_edit, getActivity());
                                    } else {
                                        asyncShowToast(response.message());
                                    }
                                }

                                @Override
                                public void onError(Response<String> response) {
                                    super.onError(response);
                                    asyncShowToast(response.message());
                                }
                            });
                } else {
                    asyncShowToast("请输入评论内容");
                }
            }
        });
    }

    /**
     * 显示或隐藏输入法
     */
    private void onFocusChange(boolean hasFocus) {
        final boolean isFocus = hasFocus;
        (new Handler()).postDelayed(new Runnable() {
            public void run() {
                InputMethodManager imm = (InputMethodManager)
                        comment_edit.getContext().getSystemService(INPUT_METHOD_SERVICE);
                if (isFocus) {
                    //显示输入法
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                } else {
                    //隐藏输入法
                    imm.hideSoftInputFromWindow(comment_edit.getWindowToken(), 0);
                }
            }
        }, 100);
    }

    public int getBottomKeyboardHeight() {
        int screenHeight = getAccurateScreenDpi()[1];
        DisplayMetrics dm = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return screenHeight - dm.heightPixels;
    }

    /**
     * 获取精确的屏幕大小
     */
    public int[] getAccurateScreenDpi() {
        int[] screenWH = new int[2];
        Display display = mActivity.getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        try {
            Class<?> c = Class.forName("android.view.Display");
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            screenWH[0] = dm.widthPixels;
            screenWH[1] = dm.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return screenWH;
    }

}
