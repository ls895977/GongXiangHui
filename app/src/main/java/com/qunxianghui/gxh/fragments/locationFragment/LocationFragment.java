package com.qunxianghui.gxh.fragments.locationFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.location.LocationListBean;
import com.qunxianghui.gxh.config.Code;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.fragments.homeFragment.activity.BaoLiaoActivity;
import com.qunxianghui.gxh.fragments.locationFragment.activity.VideoListActivity;
import com.qunxianghui.gxh.fragments.locationFragment.adapter.NineGridTest2Adapter;
import com.qunxianghui.gxh.fragments.locationFragment.model.NineGridTestModel;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class LocationFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.tv_location_mine_fabu)
    TextView tvLocationMineFabu;

    XRecyclerView recyclerView;
    Unbinder unbinder;

    RecyclerView.LayoutManager mLayoutManager;
    NineGridTest2Adapter mAdapter;
    List<NineGridTestModel> mList = new ArrayList<>();
    String[] mUrls = new String[]{
            "http://d.hiphotos.baidu.com/image/h%3D200/sign=201258cbcd80653864eaa313a7dca115/ca1349540923dd54e54f7aedd609b3de9c824873.jpg",
            "http://img3.fengniao.com/forum/attachpics/537/165/21472986.jpg",
            "http://d.hiphotos.baidu.com/image/h%3D200/sign=ea218b2c5566d01661199928a729d498/a08b87d6277f9e2fd4f215e91830e924b999f308.jpg",
            "http://img4.imgtn.bdimg.com/it/u=3445377427,2645691367&fm=21&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=2644422079,4250545639&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=1444023808,3753293381&fm=21&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=882039601,2636712663&fm=21&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=4119861953,350096499&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=2437456944,1135705439&fm=21&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=3251359643,4211266111&fm=21&gp=0.jpg",
            "http://img4.duitang.com/uploads/item/201506/11/20150611000809_yFe5Z.jpeg",
            "http://img5.imgtn.bdimg.com/it/u=1717647885,4193212272&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=2024625579,507531332&fm=21&gp=0.jpg" };


    private AlertDialog.Builder builder;
    private View view;
    private Dialog picVideo_dialog;
    private TextView tv_alertbottom_up_pic;
    private TextView tv_alertbottom_up_video;
    private TextView tv_alertbottom_video_cancel;
    private TextView tv_fubu_recode_video;
    private TextView tv_fabu_location_vodeo;
    private Button bt_fabu_video_cancel;
    private LinearLayout ll_fabu_first_list;
    private LinearLayout ll_fabu_second_list;
    private String filPaths;
    private Dialog upDialog;
    private Dialog quickUpDialog;
    private TextView tv_quickly_up_video;
    private Button bt_quickly_up_video_cancel;
    private View upVideoDialogView;
    private List<LocationListBean.DataBean.ListBean> dataList;
    private int page=1;

    @Override
    public int getLayoutId() {
       mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return R.layout.fragment_location;
    }

    /**
     * 子类在此方法中实现数据的初始化
     */
    @Override
    public void initDatas() {

        NineGridTestModel model4 = new NineGridTestModel();
        for (int i = 0; i <mUrls.length; i++) {
            model4.urlList.add(mUrls[i]);
        }
        model4.isShowAll = false;
        mList.add(model4);

        NineGridTestModel model5 = new NineGridTestModel();
        for (int i = 0; i < mUrls.length; i++) {
            model5.urlList.add(mUrls[i]);
        }
        model5.isShowAll = true;//显示全部图片
        mList.add(model5);

        NineGridTestModel model6 = new NineGridTestModel();
        for (int i = 0; i < 9; i++) {
            model6.urlList.add(mUrls[i]);
        }
        mList.add(model6);

        NineGridTestModel model7 = new NineGridTestModel();
        for (int i = 3; i < 7; i++) {
            model7.urlList.add(mUrls[i]);
        }
        mList.add(model7);

        NineGridTestModel model8 = new NineGridTestModel();
        for (int i = 3; i < 6; i++) {
            model8.urlList.add(mUrls[i]);
        }
        mList.add(model8);

    }

    /**
     * 初始化控件
     */
    @Override
    public void initViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerView_location);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        RequestLocationData();


    }

    private void RequestLocationData() {
        OkGo.<String>get(Constant.LOCATION_NEWS_LIST_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                parseLocationData(response.body());
            }
        });
    }

    private void parseLocationData(String body) {

        Logger.i("Location"+body.toString());
        final LocationListBean locationListBean = LocationListBean.objectFromData(body);

        if (locationListBean.getCode() == 0) {
            dataList = locationListBean.getData().getList();
            mAdapter = new NineGridTest2Adapter(getActivity(), dataList);
            mAdapter.setList(mList);
            recyclerView.setAdapter(mAdapter);

        }

    }

    /**
     * 子类可以复写此方法初始化事件
     */
    @Override
    protected void initListeners() {
        tvLocationMineFabu.setOnClickListener(this);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
              mAdapter=null; // 把集合和适配器清空  重新请求数据

                RequestLocationData();

                recyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                mAdapter.notifyDataSetChanged();
                recyclerView.refreshComplete();
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
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.tv_location_mine_fabu:
                showBottomAliert();
                break;
            case R.id.tv_alertbottom_up_pic:
                toActivity(BaoLiaoActivity.class);
                picVideo_dialog.dismiss();
                break;
            case R.id.tv_alertbottom_up_video:
                ll_fabu_first_list.setVisibility(View.GONE);
                ll_fabu_second_list.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_fubu_recode_video:  //发布录制视频
                intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE); //创建一个请求视频的意图
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);  //设置视频的质量，值为0-1
                intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 20); //设置视频的录制长度，s为单位
                intent.putExtra(MediaStore.EXTRA_SIZE_LIMIT, 20 * 1024 * 1024); //设置视频的文件大小
                startActivityForResult(intent, Code.VIDEO_RECORD_REQUEST);
                picVideo_dialog.dismiss();
                break;
            case R.id.tv_fabu_location_vodeo:  //发布本地视频
                intent = new Intent(mActivity, VideoListActivity.class);
                startActivityForResult(intent, Code.LOCAL_VIDEO_REQUEST);
                picVideo_dialog.dismiss();
                break;
            case R.id.bt_fabu_video_cancel:   //第二栏的取消
                picVideo_dialog.dismiss();
                break;
            case R.id.tv_alertbottom_video_cancel:   //第一栏的取消
                picVideo_dialog.dismiss();
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

    private void showBottomAliert() {
        picVideo_dialog = new Dialog(mActivity, R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        view = LayoutInflater.from(mActivity).inflate(R.layout.bottom_video_alertdialog, null);
        //初始化控件
        ll_fabu_first_list = view.findViewById(R.id.ll_fabu_first_list);
        ll_fabu_second_list = view.findViewById(R.id.ll_fabu_second_list);
        tv_alertbottom_up_pic = view.findViewById(R.id.tv_alertbottom_up_pic);   //上传图片
        tv_alertbottom_up_video = view.findViewById(R.id.tv_alertbottom_up_video);  //上传视频
        tv_alertbottom_video_cancel = view.findViewById(R.id.tv_alertbottom_video_cancel);  //取消
        tv_fubu_recode_video = view.findViewById(R.id.tv_fubu_recode_video);   //录制视频
        tv_fabu_location_vodeo = view.findViewById(R.id.tv_fabu_location_vodeo); //本地视频
        bt_fabu_video_cancel = view.findViewById(R.id.bt_fabu_video_cancel); //取消


        tv_alertbottom_up_pic.setOnClickListener(this);
        tv_alertbottom_up_video.setOnClickListener(this);
        tv_alertbottom_video_cancel.setOnClickListener(this);
        tv_fubu_recode_video.setOnClickListener(this);
        tv_fabu_location_vodeo.setOnClickListener(this);
        bt_fabu_video_cancel.setOnClickListener(this);

        //将布局设置给dialog
        picVideo_dialog.setContentView(view);
        //获取当前activity所在的窗体
        final Window dialogWindow = picVideo_dialog.getWindow();
        //设置dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        final WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        final WindowManager windowManager = mActivity.getWindowManager();
        final Display display = windowManager.getDefaultDisplay();
        lp.width = (int) display.getWidth();  //设置宽度
        lp.y = 20;  //设置dialog距离底部的距离
        //将属性设置给窗体
        dialogWindow.setAttributes(lp);
        picVideo_dialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case Code.VIDEO_RECORD_REQUEST:
                if (data != null) {
                    final Uri uri = data.getData();
                    if (uri == null) {
                        return;
                    } else {
                        final Cursor cursor = mActivity.getContentResolver().query(uri, new String[]{MediaStore.MediaColumns.DATA}, null, null, null);

                        if (cursor != null && cursor.moveToFirst()) {
                            filPaths = cursor.getString(0);
                            showUploadVideoDialog();
                        }

                    }
                }
                break;
            case Code.LOCAL_VIDEO_REQUEST:
                if (resultCode == Code.LOCAL_VIDEO_RESULT && data != null) {
                    filPaths = data.getStringExtra("path");
                    showUploadVideoDialog();
                }
                break;
        }
    }

    private void showUploadVideoDialog() {
        quickUpDialog = new Dialog(mActivity, R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        upVideoDialogView = LayoutInflater.from(mActivity).inflate(R.layout.bottom_video_up_quickly_alertdialog, null);
        //初始化控件
        tv_quickly_up_video = upVideoDialogView.findViewById(R.id.tv_quickly_up_video);
        bt_quickly_up_video_cancel = upVideoDialogView.findViewById(R.id.bt_quickly_up_video_cancel);
        tv_quickly_up_video.setOnClickListener(this);
        bt_fabu_video_cancel.setOnClickListener(this);
        bt_quickly_up_video_cancel.setOnClickListener(this);

        //将布局设置给dialog
        quickUpDialog.setContentView(upVideoDialogView);
        //获取当前activity所在的窗体
        final Window dialogWindow = quickUpDialog.getWindow();
        //设置dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        final WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        final WindowManager windowManager = mActivity.getWindowManager();
        final Display display = windowManager.getDefaultDisplay();
        lp.width = (int) display.getWidth();  //设置宽度
        lp.y = 20;  //设置dialog距离底部的距离
        //将属性设置给窗体
        dialogWindow.setAttributes(lp);
        quickUpDialog.show();
    }


}

