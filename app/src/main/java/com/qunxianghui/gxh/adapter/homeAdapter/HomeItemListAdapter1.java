package com.qunxianghui.gxh.adapter.homeAdapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.bean.home.HomeNewListBean;
import com.qunxianghui.gxh.bean.home.MoreTypeBean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/15 0015.
 */

public class HomeItemListAdapter1 extends BaseQuickAdapter<HomeNewListBean.DataBean,BaseViewHolder> {


    public HomeItemListAdapter1() {
        super(null);
        setMultiTypeDelegate(new MultiTypeDelegate<HomeNewListBean.DataBean>() {
            @Override
            protected int getItemType(HomeNewListBean.DataBean entity) {
                //根据你的实体类来判断布局类型
                return entity.getItemType();
            }
        });
        //Step.2
        getMultiTypeDelegate()
                .registerItemType(0, R.layout.item_text_text)
                .registerItemType(1, R.layout.item_right_img)
                .registerItemType(2, R.layout.item_three_img)
        ;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, HomeNewListBean.DataBean dataBean) {
        switch (dataBean.getItemType()) {
            case 0:
//               baseViewHolder.setText(R.id.tv_homelistItem_text_content,dataBean.getTitle());

                break;
            case 1:

                break;
            case 2:

                break;
        }
    }


}
