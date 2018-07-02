package com.qunxianghui.gxh.fragments.homeFragment.activity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.activity.NewsDetailActivity;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.homeAdapter.PersonDetailVideoAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.home.HomeVideoListBean;
import com.qunxianghui.gxh.config.Code;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.fragments.locationFragment.activity.VideoListActivity;
import com.qunxianghui.gxh.fragments.mineFragment.activity.PersonDetailActivity;
import com.qunxianghui.gxh.utils.GsonUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/24 0024.
 */

public class HomeVideoActivity extends BaseActivity implements View.OnClickListener, PersonDetailVideoAdapter.VideoListClickListener {

    @BindView(R.id.xrecycler_homevideo_list)
    XRecyclerView xrecyclerHomevideoList;
    @BindView(R.id.iv_home_video_back)
    ImageView ivHomeVideoBack;
    @BindView(R.id.tv_home_video_issue)
    TextView tvHomeVideoIssue;

    private PersonDetailVideoAdapter personDetailVideoAdapter;

    private int count = 0;
    private boolean mIsFirst = true;
    private List<HomeVideoListBean.DataBean.ListBean> videoDataList = new ArrayList<>();
    private Dialog dialog;
    private View alertView;
    private TextView tv_shoot_video;
    private TextView tv_location_video;
    private TextView tv_video_cancle;
    private Intent intent;
    private String filePath;
    private Dialog quickUpDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_video;
    }

    @Override
    protected void initViews() {
        xrecyclerHomevideoList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void initDatas() {

        RequestHomeVideoList();
    }

    private void RequestHomeVideoList() {
        OkGo.<String>post(Constant.HOME_VIDEO_LIST_URL)
                .params("limit", 10)
                .params("skip", count)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        parsePersonDetailVideoData(response.body());

                    }
                });
    }

    /**
     * 解析首页视频列表
     *
     * @param body
     */
    private void parsePersonDetailVideoData(String body) {

        final HomeVideoListBean homeVideoListBean = GsonUtils.jsonFromJson(body, HomeVideoListBean.class);
        videoDataList.addAll(homeVideoListBean.getData().getList());
        count = videoDataList.size();
        if (homeVideoListBean.getCode() == 0) {
            if (mIsFirst) {
                mIsFirst = false;
                personDetailVideoAdapter = new PersonDetailVideoAdapter(mContext, videoDataList);
                personDetailVideoAdapter.setVideoListClickListener(this);

                xrecyclerHomevideoList.setAdapter(personDetailVideoAdapter);
            }
            xrecyclerHomevideoList.refreshComplete();

            personDetailVideoAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    Intent intent = new Intent(mContext, NewsDetailActivity.class);
                    intent.putExtra("url", videoDataList.get(position - 1).getUrl());
                    startActivity(intent);
                }
            });

            personDetailVideoAdapter.notifyItemRangeChanged(count, homeVideoListBean.getData().getList().size());
        }
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        ivHomeVideoBack.setOnClickListener(this);
        tvHomeVideoIssue.setOnClickListener(this);

        xrecyclerHomevideoList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                videoDataList.clear();
                count = 0;
                RequestHomeVideoList();
            }

            @Override
            public void onLoadMore() {
                RequestHomeVideoList();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_home_video_back:
                finish();
                break;
            case R.id.tv_home_video_issue:
                showBottomAliert();
                break;
            case R.id.tv_shoot_video:
                //创建一个请求视频的意图
                intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);// 设置视频的质量，值为0-1，
                intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 20);// 设置视频的录制长度，s为单位
                intent.putExtra(MediaStore.EXTRA_SIZE_LIMIT, 20 * 1024 * 1024L);// 设置视频文件大小，字节为单位
                startActivityForResult(intent, Code.VIDEO_RECORD_REQUEST);// 设置请求码，在onActivityResult()方法中接收结果
                dialog.dismiss();
                break;
            case R.id.tv_location_video:
                intent = new Intent(HomeVideoActivity.this, VideoListActivity.class);
                startActivityForResult(intent, Code.LOCAL_VIDEO_REQUEST);
                dialog.dismiss();
                break;
            case R.id.tv_video_cancle:
                dialog.dismiss();
                break;
            case R.id.tv_quickly_up_video:
                /**
                 *   这里可以添加上传视频的方法
                 *
                 */
                asyncShowToast("模拟文件上传成功");
                quickUpDialog.dismiss();
                break;
            case R.id.bt_quickly_up_video_cancel:
                quickUpDialog.dismiss();
                break;
        }
    }
    /**
     * 底部弹起
     */
    private void showBottomAliert() {
        dialog = new Dialog(HomeVideoActivity.this, R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        alertView = LayoutInflater.from(mContext).inflate(R.layout.bottom_alertvido_dialog, null);
        //初始化控件
        tv_shoot_video = alertView.findViewById(R.id.tv_shoot_video);
        tv_location_video = alertView.findViewById(R.id.tv_location_video);
        tv_video_cancle = alertView.findViewById(R.id.tv_video_cancle);
        tv_shoot_video.setOnClickListener(this);
        tv_location_video.setOnClickListener(this);
        tv_video_cancle.setOnClickListener(this);

        //将布局设置给dialog
        dialog.setContentView(alertView);
        //获取当前activity所在的窗体
        final Window dialogWindow = dialog.getWindow();
        //设置dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        final WindowManager.LayoutParams lp = dialogWindow.getAttributes();

        final WindowManager windowManager = getWindowManager();
        final Display display = windowManager.getDefaultDisplay();
        lp.width = (int) display.getWidth();  //设置宽度
        lp.y = 5;  //设置dialog距离底部的距离
        //将属性设置给窗体
        dialogWindow.setAttributes(lp);
        dialog.show();
    }
    /**
     * 视频列表的关注
     *
     * @param position
     */
    @Override
    public void attentionClick(int position) {
        OkGo.<String>post(Constant.ATTENTION_URL).params("be_member_id", videoDataList.get(position).getMember_id())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body());
                            final int code = jsonObject.getInt("code");
                            if (code == 0) {
                                asyncShowToast("关注成功");
                                personDetailVideoAdapter.videoAttention.setText("已关注");

                            } else if (code == 202) {
                                asyncShowToast("取消关注成功");
                                personDetailVideoAdapter.videoAttention.setText("关注");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Logger.e("视频关注" + response.body().toString());
                    }
                });

        Logger.d("视频汇的关注position" + position);
    }

    //    视频头像点击
    @Override
    public void videoHeadImageClick(int position) {
        Intent intent = new Intent(mContext, PersonDetailActivity.class);
        intent.putExtra("member_id", videoDataList.get(position).getMember_id());
        startActivity(intent);
    }

    private void showUploadVideoDialog() {
        if (quickUpDialog == null) {
            quickUpDialog = new Dialog(mContext, R.style.ActionSheetDialogStyle);
            //填充对话框的布局
            View upVideoDialogView = LayoutInflater.from(mContext).inflate(R.layout.bottom_video_up_quickly_alertdialog, null);
            //初始化控件
            upVideoDialogView.findViewById(R.id.tv_quickly_up_video).setOnClickListener(this);
            upVideoDialogView.findViewById(R.id.bt_quickly_up_video_cancel).setOnClickListener(this);
            //将布局设置给dialog
            quickUpDialog.setContentView(upVideoDialogView);
            //获取当前activity所在的窗体
            final Window dialogWindow = quickUpDialog.getWindow();
            //设置dialog从窗体底部弹出
            dialogWindow.setGravity(Gravity.BOTTOM);
            //获得窗体的属性
            final WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            final WindowManager windowManager = getWindowManager();
            final Display display = windowManager.getDefaultDisplay();
            lp.width = (int) display.getWidth();  //设置宽度
            lp.y = 20;  //设置dialog距离底部的距离
            //将属性设置给窗体
            dialogWindow.setAttributes(lp);
        }
        quickUpDialog.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case Code.VIDEO_RECORD_REQUEST:
                if (data != null) {
                    Uri uri = data.getData();
                    if (uri == null) {
                        return;
                    } else {
                        Cursor cursor = getContentResolver().query(uri, new String[]{MediaStore.MediaColumns.DATA}, null, null, null);
                        if (cursor != null && cursor.moveToFirst()) {
                            filePath = cursor.getString(0);
                            showUploadVideoDialog();

                        }
                    }
                }
                break;

            case Code.LOCAL_VIDEO_REQUEST:
                if (resultCode == Code.LOCAL_VIDEO_RESULT && data != null) {
                    filePath = data.getStringExtra("path");
                    showUploadVideoDialog();

                    break;
                }


        }
    }
}
