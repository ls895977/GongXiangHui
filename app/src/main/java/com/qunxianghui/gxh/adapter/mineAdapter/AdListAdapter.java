package com.qunxianghui.gxh.adapter.mineAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.MyApplication;
import com.qunxianghui.gxh.bean.mine.AdListBean;
import com.qunxianghui.gxh.fragments.mineFragment.activity.PersonDataActivity;
import com.qunxianghui.gxh.item.BaseRecyclerAdapter;
import com.qunxianghui.gxh.item.ViewHolder;
import com.qunxianghui.gxh.utils.GlideApp;

/**
 * Created by user on 2018/6/11.
 */

public class AdListAdapter extends BaseRecyclerAdapter<AdListBean.DataBean> {


    @Override
    public int getLayoutId(int position) {
        return R.layout.item_addadver_bigimag;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, AdListBean.DataBean dataBean, int position) {
        GlideApp.with(MyApplication.getMyApplicaiton()).load(dataBean.getImages()).
                placeholder(R.mipmap.user_moren).
                error(R.mipmap.user_moren).
                into(holder.getImageView(R.id.item_adlist_1));
        CheckBox cb_addbigimg_addlunbo = holder.getView(R.id.cb_addbigimg_addlunbo);
        cb_addbigimg_addlunbo.setChecked(dataBean.getIs_slide()==1);
    }
}
