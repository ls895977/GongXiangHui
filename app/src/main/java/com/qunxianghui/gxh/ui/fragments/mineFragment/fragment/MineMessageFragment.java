package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MineMessageAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.CommonResponse;
import com.qunxianghui.gxh.bean.location.CommentBean;
import com.qunxianghui.gxh.bean.mine.MineMessageCommentBean;
import com.qunxianghui.gxh.callback.DialogCallback;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/4/14 0014.
 */


public class MineMessageFragment extends BaseFragment implements MineMessageAdapter.CommontMeClickListener {
    @BindView(R.id.xrecycler_mineMessage)
    XRecyclerView xrecyclerMineMessage;

    private int count = 0;
    private boolean mIsFirst = true;
    private boolean mIsRefresh = false;
    private List<MineMessageCommentBean.DataBean> dataList = new ArrayList<>();
    private MineMessageAdapter mineMessageAdapter;
    private EditText inputResponseComment;
    private TextView btn_submit;
    private PopupWindow popupWindow;

    private void parsePaiHangData(MineMessageCommentBean mineMessageCommentBean) {
        if (mIsRefresh) {
            mIsRefresh = false;
            dataList.clear();
        }
        dataList.addAll(mineMessageCommentBean.getData());
        count = dataList.size();
        if (mineMessageCommentBean.getCode() == 0) {
            if (mIsFirst) {
                mIsFirst = false;
                mineMessageAdapter = new MineMessageAdapter(mActivity, dataList);
                xrecyclerMineMessage.setAdapter(mineMessageAdapter);
                mineMessageAdapter.setCommontMeClickListener(this);
            }
            xrecyclerMineMessage.refreshComplete();
            mineMessageAdapter.notifyDataSetChanged();
            mineMessageAdapter.notifyItemChanged(count, mineMessageCommentBean.getData().size());
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_minemessage;
    }

    @Override
    public void initViews(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setSmoothScrollbarEnabled(false);
        xrecyclerMineMessage.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void initData() {
        RequestCommonMineMessage();
    }

    @Override
    protected void initListeners() {
        xrecyclerMineMessage.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                count = 0;
                mIsRefresh = true;
                RequestCommonMineMessage();
            }

            @Override
            public void onLoadMore() {
                RequestCommonMineMessage();

            }
        });
    }

    /**
     * 请求评论我消息
     */
    private void RequestCommonMineMessage() {
        OkGo.<MineMessageCommentBean>post(Constant.DISCUSS_MINE_URL)
                .params("limit", 10)
                .params("skip", count)
                .execute(new JsonCallback<MineMessageCommentBean>() {
                    @Override
                    public void onSuccess(Response<MineMessageCommentBean> response) {
                        parsePaiHangData(response.body());
                    }
                });
    }

    /*我的消息中的回复*/
    @Override
    public void ResponseCommentListener(int position) {
        showPopupCommnet(position);
    }

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
                    RequestNewsCommon(CommonText, position);
                }
            }
        });
    }

    /*开始评论了*/
    private void RequestNewsCommon(String commonText, int position) {
        OkGo.<CommonResponse<CommentBean>>post(Constant.ISSURE_DISUSS_URL)
                .params("uuid", dataList.get(position).getData_uuid())
                .params("content", commonText)
                .execute(new DialogCallback<CommonResponse<CommentBean>>(mActivity) {
                    @Override
                    public void onSuccess(Response<CommonResponse<CommentBean>> response) {
                        if (response.body().code == 0) {
                            asyncShowToast("评论成功");
                            inputResponseComment.setVisibility(View.VISIBLE);
                            popupWindow.dismiss();
                        }
                    }
                });
    }
}
