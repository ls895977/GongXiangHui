package com.qunxianghui.gxh.widget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.bean.location.ActionItem;
import com.qunxianghui.gxh.bean.location.TestMode;
import com.qunxianghui.gxh.utils.DensityUtil;

import java.util.ArrayList;

/**
 * 朋友圈点赞评论的popupwindow
 *
 * @author wei.yi
 */
public class SnsPopupWindow extends PopupWindow implements OnClickListener {

    private TextView digBtn;
    private TextView commentBtn;
    private TextView colletBtn;
    private String collect;
    private String click_like;
    private Context mContext;
    // 实例化一个矩形
    private Rect mRect = new Rect();
    // 坐标的位置（x、y）
    private final int[] mLocation = new int[2];
    // 弹窗子类项选中时的监听
    private OnItemClickListener mItemClickListener;
    // 定义弹窗子类项列表
    private ArrayList<ActionItem> mActionItems = new ArrayList<ActionItem>();

    public void setmItemClickListener(OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public void setCollect(String collect) {

        this.collect = collect;
    }

    public void setClick_like(String click_like) {
        this.click_like = click_like;
    }

    public ArrayList<ActionItem> getmActionItems() {
        return mActionItems;
    }

    public void setmActionItems(ArrayList<ActionItem> mActionItems) {
        this.mActionItems = mActionItems;
    }

    public SnsPopupWindow(Context context, String collect, String like) {
        this.collect = collect;
        this.click_like = like;
        this.mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.social_sns_popupwindow, null);
        digBtn = (TextView) view.findViewById(R.id.digBtn);
        commentBtn = (TextView) view.findViewById(R.id.commentBtn);
        colletBtn = (TextView) view.findViewById(R.id.colletBtn);
        digBtn.setOnClickListener(this);
        commentBtn.setOnClickListener(this);
        colletBtn.setOnClickListener(this);

        this.setContentView(view);
        this.setWidth(DensityUtil.dip2px(context, 200));
        this.setHeight(DensityUtil.dip2px(context, 30));
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        this.setAnimationStyle(R.style.social_pop_anim);

        initItemData();
    }

    public SnsPopupWindow(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.social_sns_popupwindow, null);
        digBtn = (TextView) view.findViewById(R.id.digBtn);
        commentBtn = (TextView) view.findViewById(R.id.commentBtn);
        colletBtn = (TextView) view.findViewById(R.id.colletBtn);
        digBtn.setOnClickListener(this);
        commentBtn.setOnClickListener(this);
        colletBtn.setOnClickListener(this);

        this.setContentView(view);
        this.setWidth(DensityUtil.dip2px(context, 200));
        this.setHeight(DensityUtil.dip2px(context, 30));
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        this.setAnimationStyle(R.style.social_pop_anim);

        //initItemData();
    }

    public void initItemData() {

        if (click_like == null || click_like.length() == 0) {
            addAction(new ActionItem("点赞"));
            digBtn.setText("点赞");
        } else {
            digBtn.setText("取消赞");
            addAction(new ActionItem("取消赞"));
        }
        if (collect == null || collect.length() == 0) {
            addAction(new ActionItem("举报"));
            //colletBtn.setText("取消");
            colletBtn.setText("举报");
        }
        addAction(new ActionItem("评论"));
        //addAction(new ActionItem("取消收藏"));
    }

    public void showPopupWindow(View parent, TestMode.DataBean.ListBean listBean, Context context) {
        parent.getLocationOnScreen(mLocation);
        // 设置矩形的大小
        mRect.set(mLocation[0], mLocation[1], mLocation[0] + parent.getWidth(), mLocation[1] + parent.getHeight());
        //Log.v("xxxx-yyyy",mRect.toString());

        // digBtn.setText(mActionItems.get(0).mTitle);
        //判断当前状态
        Drawable like = parent.getResources().getDrawable(R.mipmap.icon_local_good_normal);
        like.setBounds(0, 0, like.getIntrinsicWidth(), like.getMinimumHeight());
        Drawable collection = parent.getResources().getDrawable(R.mipmap.icon_local_report);
        collection.setBounds(0, 0, collection.getIntrinsicWidth(), collection.getIntrinsicHeight());
        if (listBean.getCollect().equals("true") && listBean.getLike_info_res().equalsIgnoreCase("true")) {
            colletBtn.setCompoundDrawables(null, null, null, null);
            digBtn.setCompoundDrawables(null, null, null, null);
            this.setWidth(DensityUtil.dip2px(context, 250));
        } else if (listBean.getCollect().equals("true")) {
            colletBtn.setCompoundDrawables(null, null, null, null);
            digBtn.setCompoundDrawables(like, null, null, null);
            this.setWidth(DensityUtil.dip2px(context, 230));
        } else if (listBean.getLike_info_res().equalsIgnoreCase("true")) {

            colletBtn.setCompoundDrawables(collection, null, null, null);
            digBtn.setCompoundDrawables(null, null, null, null);
            this.setWidth(DensityUtil.dip2px(context, 220));
        } else {

            this.setWidth(DensityUtil.dip2px(context, 200));

            digBtn.setCompoundDrawables(like, null, null, null);
            colletBtn.setCompoundDrawables(collection, null, null, null);
        }
        if (!this.isShowing()) {
            showAtLocation(parent, Gravity.NO_GRAVITY, mLocation[0] - this.getWidth()
                    , mLocation[1] - ((this.getHeight() - parent.getHeight()) / 2));
        } else {
            dismiss();
        }
    }

    @Override
    public void onClick(View view) {
        dismiss();
        switch (view.getId()) {
            case R.id.digBtn:
                mItemClickListener.onItemClick(mActionItems.get(0), 0);
                break;
            case R.id.colletBtn:
                mItemClickListener.onItemClick(mActionItems.get(1), 1);
                break;
            case R.id.commentBtn:
                mItemClickListener.onItemClick(mActionItems.get(2), 2);
                break;

            default:
                break;
        }
    }

    /**
     * 添加子类项
     */
    public void addAction(ActionItem action) {
        if (action != null) {
            mActionItems.add(action);
        }
    }

    /**
     * 功能描述：弹窗子类项按钮监听事件
     */
    public static interface OnItemClickListener {
        public void onItemClick(ActionItem item, int position);
    }
}
