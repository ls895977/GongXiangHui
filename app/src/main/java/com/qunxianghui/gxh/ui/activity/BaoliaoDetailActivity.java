package com.qunxianghui.gxh.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.BaoliaoDetailAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.BaoliaoDetailContentBean;
import com.qunxianghui.gxh.bean.mine.BaoliaoBean;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/26 0026.
 */
public class BaoliaoDetailActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.iv_myissue_back)
    ImageView ivMyissueBack;
    @BindView(R.id.tv_myissue_cancel)
    TextView tvMyissueCancel;
    @BindView(R.id.tv_edit)
    TextView tvEdit;
    @BindView(R.id.tv_baoliao_title)
    TextView tvBaoliaoTitle;
    @BindView(R.id.tv_baoliao_time)
    TextView tvBaoliaoTime;
    @BindView(R.id.recyclerView)
    XRecyclerView recyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_baoliao_detail;
    }

    @Override
    protected void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        BaoliaoBean.DataBean dataBean = (BaoliaoBean.DataBean) getIntent().getSerializableExtra("baoliao");
        Object content = dataBean.content;
        try {
            //List<BaoLiaoBean.DataBean.Content> contentList = JSONObject.parseArray(dataBean.content, BaoLiaoBean.DataBean.Content.class);
            if (content != null) {
                String contentStr = content.toString();
                if (contentStr.indexOf("\"img\"")==-1){
                    if (contentStr.indexOf("img=")!=-1){
                        contentStr = contentStr.replaceAll("img=","\"img\":");
                        if (contentStr.indexOf("http")!=-1){
                            contentStr = contentStr.replaceAll("http","\"http");
                        }
                        if (contentStr.indexOf("jpeg")!=-1){
                            contentStr = contentStr.replaceAll("jpeg","jpeg\"");
                        }
                        if (contentStr.indexOf("gif")!=-1){
                            contentStr = contentStr.replaceAll("gif","gif\"");
                        }

                    }
                }
                Log.e("TAG_爆料詳情", "contentStr=" + contentStr);
                List<BaoliaoDetailContentBean> baoliaoDetailContentBeen = JSON.parseArray(contentStr, BaoliaoDetailContentBean.class);
                BaoliaoDetailAdapter mAdapter = new BaoliaoDetailAdapter(this, baoliaoDetailContentBeen);
                recyclerView.setAdapter(mAdapter);
            }
        } catch (Exception e) {
//            if (content != null) {
//                BaoliaoDetailAdapter mAdapter = new BaoliaoDetailAdapter(this, baoliaoDetailContentBeen);
//                recyclerView.setAdapter(mAdapter);
//            }
        }
        recyclerView.setLoadingMoreEnabled(false);
        recyclerView.setPullRefreshEnabled(false);

        if (!TextUtils.isEmpty(dataBean.getCtime())) {
            tvBaoliaoTime.setText(dataBean.getCtime());
        }
        if (!TextUtils.isEmpty(dataBean.getTitle())) {
            tvBaoliaoTitle.setText(dataBean.getTitle());
        }
    }

    @Override
    protected void initListeners() {
        ivMyissueBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_myissue_back:
                finish();
                break;
        }
    }
}
