package com.qunxianghui.gxh.adapter.mineAdapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.mine.MineIssueVideoBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GlideApp;

import java.util.List;

public class MineIssueVideoAdapter extends BaseRecycleViewAdapter<MineIssueVideoBean.DataBean> {
    private Handler handler = new Handler();
    private int uuid;

    public MineIssueVideoAdapter(Context context, List<MineIssueVideoBean.DataBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, MineIssueVideoBean.DataBean dataBean) {
        uuid = dataBean.getUuid();
        final ImageView headImage = holder.getView(R.id.iv_item_issue_video_head);
        holder.setText(R.id.tv_item_issue_video_title, dataBean.getTitle());
        holder.setText(R.id.tv_item_issue_vido_time, dataBean.getNewctime());
        GlideApp.with(mContext).load(dataBean.getPicurl()).centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(headImage);

/**
 * 删除视频
 *
 */
        final TextView deleteVideo = holder.getView(R.id.tv_item_issue_video_delete);

        deleteVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("删除提示");
                builder.setMessage("您确定要删除该条消息吗?");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DeleteVideo();

                    }
                });
                builder.setNegativeButton("取消", null);
                builder.show();


            }
        });
    }

    private void DeleteVideo() {
        OkGo.<String>post(Constant.DELETE_MYISSUE_URL)
                .params("uuid", uuid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(final Response<String> response) {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(mContext, "删除视频成功", Toast.LENGTH_SHORT).show();
                                Logger.d("删除视频信息 ++++" + response.body().toString());
                            }
                        }, 500);

                    }
                });

    }

    @Override
    protected int getItemView() {
        return R.layout.item_mine_issue_video;
    }
}
