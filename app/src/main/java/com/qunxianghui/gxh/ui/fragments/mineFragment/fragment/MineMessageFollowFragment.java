package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MineMessageFollewAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.LzyResponse;
import com.qunxianghui.gxh.bean.location.CommentBean;
import com.qunxianghui.gxh.bean.mine.MineMessageFollowBean;
import com.qunxianghui.gxh.callback.DialogCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MineMessageFollowFragment extends BaseFragment implements MineMessageFollewAdapter.MineMessageResponseListener {
    @BindView(R.id.xrecycler_mineFollowMessage)
    XRecyclerView xrecyclerMineFollowMessage;
    Unbinder unbinder;
    private int count = 0;
    private boolean mIsRefresh = false;
    private boolean mIsFirst = true;
    private List<MineMessageFollowBean.DataBean> dataList = new ArrayList<>();
    private MineMessageFollewAdapter mineMessageFollewAdapter;
    private EditText inputResponseComment;
    private TextView btn_submit;
    private PopupWindow popupWindow;

    @Override
    protected void onLoadData() {
        RequestMyMessageFollow();

    }
    /**
     * 请求我的跟帖
     */
    private void RequestMyMessageFollow() {
        OkGo.<String>post(Constant.DISCUSS_MINE_FOLLOW_URL)
                .params("limit", 10)
                .params("skip", count)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        parsePaiHangData(response.body());
                    }
                });
    }
    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine_message_follow;
    }

    @Override
    public void initDatas() {
    }
    private void parsePaiHangData(String body) {
        final MineMessageFollowBean mineMessageFollowBean = GsonUtils.jsonFromJson(body, MineMessageFollowBean.class);
        if (mIsRefresh) {
            mIsRefresh = false;
            dataList.clear();

        }

        dataList.addAll(mineMessageFollowBean.getData());
        count = dataList.size();
        if (mineMessageFollowBean.getCode() == 0) {
            if (mIsFirst) {
                mIsFirst = false;
                mineMessageFollewAdapter = new MineMessageFollewAdapter(mActivity, dataList);
                xrecyclerMineFollowMessage.setAdapter(mineMessageFollewAdapter);
                mineMessageFollewAdapter.setMineMessageResponseListener(this);
            }

            xrecyclerMineFollowMessage.refreshComplete();
            mineMessageFollewAdapter.notifyDataSetChanged();

            mineMessageFollewAdapter.notifyItemChanged(count, mineMessageFollowBean.getData().size());
        }
    }

    @Override
    public void initViews(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setSmoothScrollbarEnabled(false);
        xrecyclerMineFollowMessage.setLayoutManager(linearLayoutManager);
    }
    @Override
    protected void initListeners() {
        super.initListeners();
        xrecyclerMineFollowMessage.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                count = 0;
                mIsRefresh = true;
                RequestMyMessageFollow();
            }
            @Override
            public void onLoadMore() {
                RequestMyMessageFollow();
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

    /*接口回调之点击了回复*/
    @Override
    public void ResponseClick(int position) {
        showPopupCommnet(position);
    }

    /*点击回复弹出框*/
    @SuppressLint("WrongConstant")
    private void showPopupCommnet(final int position) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.comment_popupwindow, null);
        inputResponseComment = view.findViewById(R.id.et_discuss);
        btn_submit = view.findViewById(R.id.tv_confirm);
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, false);
        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    popupWindow.dismiss();
                }
                return false;
            }
        });
        popupWindow.setFocusable(true);
        //设置点击窗口外边窗口消失
        popupWindow.setOutsideTouchable(true);
        //设置弹出窗体需要软键盘
        popupWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        // 再设置模式，和Activity的一样，覆盖，调整大小。
        popupWindow
                .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        ColorDrawable cd = new ColorDrawable(0x000000);
        popupWindow.setBackgroundDrawable(cd);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        //    WindowManager.LayoutParams params = getWindow().getAttributes();
//    params.alpha = 0.4f;
//    getWindow().setAttributes(params);
        // 设置popWindow的显示和消失动画
//    popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
        popupWindow.update();
        //        popupInputMethodWindow();

        /**
         * 提交评论
         */
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String CommonText = inputResponseComment.getText().toString().trim();
                if (TextUtils.isEmpty(CommonText)) {

                    asyncShowToast("请输入评论内容");
                } else {
                    RequestNewsCommon(CommonText,position);
                }
            }
        });
    }
    private void RequestNewsCommon(String commonText,int position) {
        OkGo.<LzyResponse<CommentBean>>post(Constant.ISSURE_DISUSS_URL)
                .params("uuid", dataList.get(position).getData_uuid())
                .params("content", commonText)
                .execute(new DialogCallback<LzyResponse<CommentBean>>(mActivity) {
                    @Override
                    public void onSuccess(Response<LzyResponse<CommentBean>> response) {
                        if (response.body().code==0) {
                            asyncShowToast("评论成功");
                            inputResponseComment.setVisibility(View.VISIBLE);
                            popupWindow.dismiss();
                        }
                    }
                });
    }
}
