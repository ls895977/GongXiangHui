package com.qunxianghui.gxh.adapter.mineAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.lzy.okgo.callback.StringCallback;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.MyApplication;
import com.qunxianghui.gxh.bean.mine.AdListBean;
import com.qunxianghui.gxh.fragments.mineFragment.activity.PersonDataActivity;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.AdverTiseCommenFragment;
import com.qunxianghui.gxh.item.BaseRecyclerAdapter;
import com.qunxianghui.gxh.item.ViewHolder;
import com.qunxianghui.gxh.utils.GlideApp;
import com.qunxianghui.gxh.widget.Bind;

import org.w3c.dom.Text;

/**
 * Created by user on 2018/6/11.
 */

public class AdListAdapter extends BaseRecyclerAdapter<AdListBean.DataBean> {

//    @Bind(R.id.tv_addbigimg_edit)
//    TextView edit;
//    @Bind(R.id.tv_addbigimg_delete)
//    TextView delete;

    private AdListener listener;

    public void setAdOnClickListen(AdListener listener){
        this.listener = listener;
    }

    @Override
    public int getLayoutId(int position) {
        return R.layout.item_addadver_bigimag;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, AdListBean.DataBean dataBean, final int position) {
        GlideApp.with(MyApplication.getMyApplicaiton()).load(dataBean.getImages()).
                placeholder(R.mipmap.user_moren).
                error(R.mipmap.user_moren).
                into(holder.getImageView(R.id.item_adlist_1));
        CheckBox cb_addbigimg_addlunbo = holder.getView(R.id.cb_addbigimg_addlunbo);
        cb_addbigimg_addlunbo.setChecked(dataBean.getIs_slide()==1);

        holder.getTextView(R.id.tv_addbigimg_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.onEditClick(position);

            }
        });

        holder.getTextView(R.id.tv_addbigimg_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDeleteClick(position);
            }
        });

        holder.getBoxView(R.id.cb_addbigimg_addlunbo).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                listener.onAddCarousel(position,isChecked);
            }
        });

        holder.getTextView(R.id.tv_addbigimg_to_used).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onUsed(position);
            }
        });
    }

//    public class ViewHolder extends RecyclerView.ViewHolder {
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//        }
//    }

    public interface AdListener {
        void onEditClick(int p);

        void onDeleteClick(int p);

        void onAddCarousel(int p, boolean ischecked);

        void onUsed(int p);
    }
}
