package com.qunxianghui.gxh.fragments.locationFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.qunxianghui.gxh.bean.location.TestMode;
import com.qunxianghui.gxh.callback.DialogCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.LoginMsgHelper;
import com.qunxianghui.gxh.fragments.locationFragment.activity.InFormActivity;
import com.qunxianghui.gxh.fragments.locationFragment.adapter.NineGridTest2Adapter;
import com.qunxianghui.gxh.fragments.mineFragment.activity.LoginActivity;
import com.qunxianghui.gxh.fragments.mineFragment.activity.PersonDetailActivity;
import com.qunxianghui.gxh.listener.SoftKeyBoardListener;
import com.qunxianghui.gxh.utils.GsonUtil;
import com.qunxianghui.gxh.utils.GsonUtils;
import com.qunxianghui.gxh.utils.SPUtils;
import com.qunxianghui.gxh.utils.UserUtil;
import com.tencent.mm.opensdk.utils.Log;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class LocationDetailFragment extends BaseFragment implements View.OnClickListener, NineGridTest2Adapter.CircleOnClickListener {

    NineGridTest2Adapter mAdapter;
    @BindView(R.id.recyclerView_location)
    XRecyclerView recyclerView;
    @BindView(R.id.loaction_comment_edit)
    EditText comment_edit;
    @BindView(R.id.location_comment_to_send)
    TextView send_btn;
    @BindView(R.id.location_send_comment_view)
    LinearLayout commentView;
    private List<TestMode.DataBean.ListBean> localDataList = new ArrayList<>();
    private boolean mIsFirst = true;
    private int commentPosition;
    private int scrollOffsetY = 0;
    private int count = 0;
    private static LocationFragment locationFragment;

    @Override
    public int getLayoutId() {
        mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return R.layout.fragment_detail_location;
    }

    @Override
    public void initDatas() {
        RequestLocationData();
        SoftKeyBoardListener.setListener(getActivity(), new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
                Logger.i("xxx-yyy jump :" + commentPosition);
                //View item = recyclerView.getChildAt(commentPosition + 1);
                View item = recyclerView.getLayoutManager().findViewByPosition(commentPosition + 1);
                int offset = 5;
                int keyboardoffset = 80;

                int tabHeight = SPUtils.getInt(mActivity, "tabHeight", 0);
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) commentView.getLayoutParams();
                layoutParams.bottomMargin = height -tabHeight - offset;
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

                    recyclerView.scrollBy(0, (y + item.getMeasuredHeight() - (recyclerView.getMeasuredHeight()  - height) + keyboardoffset));
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

    @Override
    public void initViews(View view) {
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));


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
        localDataList.addAll(locationListBean.getData().getList());
        count = localDataList.size();
        if (locationListBean.getCode() == 0) {
            if (mIsFirst) {
                mIsFirst = false;
                mAdapter = new NineGridTest2Adapter(mActivity, localDataList);
                mAdapter.setOnClickLitener(this);
                recyclerView.setAdapter(mAdapter);
            }
            recyclerView.refreshComplete();
            mAdapter.notifyItemRangeChanged(count, locationListBean.getData().getList().size());
        }
    }

    @Override
    protected void initListeners() {

        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                // 把集合和适配器清空  重新请求数据
                localDataList.clear();
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
        return rootView;
    }
    @Override
    protected void onLoadData() {
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {

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
        List<String> imageList = localDataList.get(position).getImages();
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
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE|



                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (comment_edit.getText().toString().length() <= 0) {
                    Toast.makeText(getActivity(), "请输入评论内容", Toast.LENGTH_LONG).show();
                } else {
                    final int uuid = localDataList.get(position).getUuid();
                    if (localDataList.get(position).getComment_res().size() <= 0) {
                        localDataList.get(position).setComment_res(new ArrayList<CommentBean>());
                        //Toast.makeText(getActivity(),"username :" + dataList.get(position).getMember_name() + " position: " + position  + "origin :" + dataList.get(position).getContent() ,Toast.LENGTH_LONG).show();
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

                    //Toast.makeText(getActivity(),response.body(),Toast.LENGTH_LONG).show();
                    //Handler haner = new Handler()
                }
            });
        } else {
            OkGo.<String>post(Constant.LIKE_URL)
                    .params("data_uuid", localDataList.get(position).getUuid()).execute(new DialogCallback<String>(getActivity()) {
                @Override
                public void onSuccess(Response<String> response) {
                    TestMode.DataBean.ListBean.ClickLikeBean like = GsonUtil.parseJsonWithGson(response.body(), TestMode.DataBean.ListBean.ClickLikeBean.class);
                    //TestMode.DataBean.ListBean.ClickLikeBean like = new TestMode.DataBean.ListBean.ClickLikeBean();
                    UserUtil user = UserUtil.getInstance();
                    like.setMember_name(user.mNick);
                    if (like.getMessage().equalsIgnoreCase("点赞成功")) {
                        localDataList.get(position).getTem().add(like);
                        localDataList.get(position).setLike_info_res("true");
                        mAdapter.notifyDataSetChanged();
                        mAdapter.notifyItemChanged(position);
                        asyncShowToast("点赞成功");
                    } else if (like.getMessage().equalsIgnoreCase("取消点赞成功")) {
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
                        asyncShowToast("取消点赞成功");
                    }
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

    /*用户的回复评论*/
    @Override
    public void CommenRecall(final int position) {
        asyncShowToast("用户回复评论" + position);


    }


    /*解析删除的内容*/
    private void parseDeletePostAData(String body, int position) {
        try {
            JSONObject jsonObject = new JSONObject(body);
            int code = jsonObject.getInt("code");
            if (code == 0) {
                localDataList.remove(position);
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
