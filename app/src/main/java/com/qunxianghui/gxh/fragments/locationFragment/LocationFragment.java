package com.qunxianghui.gxh.fragments.locationFragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.activity.PhotoBrowserActivity;
import com.qunxianghui.gxh.activity.PublishActivity;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.base.MyApplication;
import com.qunxianghui.gxh.bean.LzyResponse;
import com.qunxianghui.gxh.bean.SigninBean;
import com.qunxianghui.gxh.bean.location.CommentBean;
import com.qunxianghui.gxh.bean.location.MyCollectBean;
import com.qunxianghui.gxh.bean.location.TestMode;
import com.qunxianghui.gxh.callback.DialogCallback;
import com.qunxianghui.gxh.config.Code;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.fragments.homeFragment.activity.BaoLiaoActivity;
import com.qunxianghui.gxh.fragments.locationFragment.activity.VideoListActivity;
import com.qunxianghui.gxh.fragments.locationFragment.adapter.NineGridTest2Adapter;
import com.qunxianghui.gxh.fragments.locationFragment.model.NineGridTestModel;
import com.qunxianghui.gxh.fragments.mineFragment.activity.LoginActivity;
import com.qunxianghui.gxh.listener.SoftKeyBoardListener;
import com.qunxianghui.gxh.utils.GlideApp;
import com.qunxianghui.gxh.utils.GsonUtil;
import com.qunxianghui.gxh.utils.GsonUtils;
import com.qunxianghui.gxh.utils.SPUtils;
import com.qunxianghui.gxh.utils.UserUtil;
import com.qunxianghui.gxh.widget.Bind;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class LocationFragment extends BaseFragment implements View.OnClickListener ,NineGridTest2Adapter.CircleOnClickListener {
    @BindView(R.id.tv_location_mine_fabu)
    TextView tvLocationMineFabu;

//    @BindView(R.id.photo_view)
//    PhotoView photoView;

    //@Bind(R.id.location_send_comment_view)
    LinearLayout commentView;
    //@Bind(R.id.loaction_comment_edit)
    EditText comment_edit;
    //@Bind(R.id.location_comment_to_send)
    TextView send_btn;

    XRecyclerView recyclerView;
    Unbinder unbinder;

    RecyclerView.LayoutManager mLayoutManager;
    NineGridTest2Adapter mAdapter;


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
    private int count = 0;
    private int page = 1;
    private List<TestMode.DataBean.ListBean> dataList=new ArrayList<TestMode.DataBean.ListBean>();
    private int currentPosition;
    private RelativeLayout mRootView;

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


    }

    /**
     * 初始化控件
     */
    @Override
    public void initViews(View view) {
        commentView = view.findViewById(R.id.location_send_comment_view);
        comment_edit = view.findViewById(R.id.loaction_comment_edit);
        send_btn = view.findViewById(R.id.location_comment_to_send);
        recyclerView = view.findViewById(R.id.recyclerView_location);
        mLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        mRootView = view.findViewById(R.id.loactionn_fragment_relative_layout);
        SoftKeyBoardListener.setListener(getActivity(), new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {


//                Toast.makeText(getActivity(), "键盘显示 高度" + height, Toast.LENGTH_SHORT).show();
//                ViewGroup.LayoutParams layout = mRootView.getLayoutParams();
//                layout.height = layout.height - height;
//                mRootView.setLayoutParams(layout);
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
//        mRootView = view.findViewById(R.id.loactionn_fragment_relative_layout);
//
//        DisplayMetrics metrics = new DisplayMetrics();
//        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        final int screenHeight = metrics.heightPixels;
//
//        mRootView.getViewTreeObserver().addOnGlobalLayoutListener(
//                new ViewTreeObserver.OnGlobalLayoutListener() {
//                    @Override
//                    public void onGlobalLayout() { //当界面大小变化时，系统就会调用该方法
//                        Rect r = new Rect(); //该对象代表一个矩形（rectangle）
//                        mRootView.getWindowVisibleDisplayFrame(r); //将当前界面的尺寸传给Rect矩形
//                        int deltaHeight = screenHeight - r.bottom;  //弹起键盘时的变化高度，在该场景下其实就是键盘高度。
//
//                    }
//                });



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

            mAdapter = new NineGridTest2Adapter(mActivity, dataList);
            mAdapter.setOnClickLitener(this);
            recyclerView.setAdapter(mAdapter);

            /*
            for (int i = 0; i < dataList.size(); i++) {

                TestMode.DataBean.ListBean listBean = dataList.get(i);
                if (listBean.getClick_like().size() > 0) {
                    mAdapter = new NineGridTest2Adapter(mActivity, dataList);
                    mAdapter.setOnClickLitener(this);
                    recyclerView.setAdapter(mAdapter);

                }
            }*/
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
                // 把集合和适配器清空  重新请求数据
                dataList.clear();
                count = 0;
                RequestLocationData();

                recyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        RequestLocationData();
                    }
                }, 1000);
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
    protected void onLoadData() {
        RequestLocationData();

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
                toActivity(PublishActivity.class);
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


    @Override
    public void onPicClick(int position, int picpostion) {

        List<String> imageList = dataList.get(position).getImages();
        ArrayList<String> arrayList = new ArrayList<String>();
        for(String data : imageList)    {
            arrayList.add(data);
        }

        Intent intent = new Intent(getActivity(), PhotoBrowserActivity.class);
        intent.putStringArrayListExtra("url",arrayList);
        intent.putExtra("position",picpostion);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.activity_pop_in, R.anim.pop_out);
        /*
        Intent broadcast = new Intent("android.intent.action.HIDE_TAB");
        broadcast.putExtra("hide",true);
        getActivity().sendBroadcast(broadcast);
        List<String> imageList = dataList.get(position).getImages();
        String url = imageList.get(picpostion);
        GlideApp.with(getContext()).load(url)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(photoView);
        photoView.setVisibility(View.VISIBLE);
        //LocalBroadcastManager.getInstance(getContext()).sendBroadcast(broadcast);

        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setVisibility(View.INVISIBLE);
                Intent broadcast = new Intent("android.intent.action.HIDE_TAB");
                broadcast.putExtra("hide",false);
                getActivity().sendBroadcast(broadcast);
            }
        });*/
    }

    @Override
    public void onCommentClick(final int position, String content) {

        commentView.setVisibility(View.VISIBLE);
        comment_edit.setFocusable(true);
        comment_edit.setFocusableInTouchMode(true);
        comment_edit.requestFocus();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(comment_edit.getText().toString().length()<=0){
                    Toast.makeText(getActivity(),"请输入评论内容",Toast.LENGTH_LONG).show();
                    return;

                }else {
                    final int uuid=dataList.get(position).getUuid();
                    if (dataList.get(position).getComment_res().size()<=0) {
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

                    OkGo.<LzyResponse<CommentBean>>post(Constant.ISSURE_DISUSS_URL)
                            .params("uuid", uuid)
                            .params("content", comment_edit.getText().toString())
                            .execute(new DialogCallback<LzyResponse<CommentBean>>(getActivity()) {
                                @Override
                                public void onSuccess(Response<LzyResponse<CommentBean>> response) {
                                    if (response.body().code.equals("0")){
                                        //requestCommentList(uuid);
                                        Toast.makeText(getActivity(),response.toString(),Toast.LENGTH_LONG).show();
                                        comment_edit.setText("");
                                        hideSoftKeyboard(comment_edit,getActivity());

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

    void requestCommentList(int uuid){
        OkGo.<String>post(Constant.COMMENT_LIST)
                .params("uuid", uuid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e(TAG, "onSuccess: -----------------------"+response.body() );
                    }
                });
    }

    @Override
    public void onLaunClick(int position) {

        if (dataList.get(position).getClick_like() != null && dataList.get(position).getClick_like().toString().length() ==0 ){
            OkGo.<String>post(Constant.LIKE_URL)
                    .params("data_uuid", dataList.get(position).getUuid()).execute(new DialogCallback<String>(getActivity()) {
                @Override
                public void onSuccess(Response<String> response) {
                    TestMode.DataBean.ListBean.ClickLikeBean like = GsonUtil.parseJsonWithGson(response.body(), TestMode.DataBean.ListBean.ClickLikeBean.class);
                    Toast.makeText(getActivity(),response.body(),Toast.LENGTH_LONG).show();
                }
            });
        }else {
            OkGo.<String>post(Constant.UNLIKE_URL)
                    .params("data_uuid", dataList.get(position).getUuid()).execute(new DialogCallback<String>(getActivity()) {
                @Override
                public void onSuccess(Response<String> response) {
                    TestMode.DataBean.ListBean.ClickLikeBean like = GsonUtil.parseJsonWithGson(response.body(), TestMode.DataBean.ListBean.ClickLikeBean.class);
                    Toast.makeText(getActivity(),response.body(),Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    @Override
    public void onCollectionClick(int position) {
        OkGo.<String>post(Constant.ADD_COLLECT_URL)
                .params("data_uuid", dataList.get(position).getUuid()).execute(new DialogCallback<String>(getActivity()) {
            @Override
            public void onSuccess(Response<String> response) {
                MyCollectBean myCollectBean = GsonUtil.parseJsonWithGson(response.body(), MyCollectBean.class);
                if (myCollectBean.getCode() == 0) {
                    Toast.makeText(getActivity(), "收藏成功", Toast.LENGTH_SHORT).show();
                }else if (myCollectBean.getCode() == 202) {
                    Toast.makeText(getActivity(), "取消收藏成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
//        OkGo.<String>post(Constant.ADD_COLLECT_URL)
//                .params("data_uuid", dataList.get(position).getUuid()).execute(new StringCallback() {
//            @Override
//            public void onSuccess(Response<String> response) {
//                MyCollectBean myCollectBean = GsonUtil.parseJsonWithGson(response.body(), MyCollectBean.class);
//                if (myCollectBean.getCode() == 0) {
//                    Toast.makeText(getActivity(), "收藏成功", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }
}

