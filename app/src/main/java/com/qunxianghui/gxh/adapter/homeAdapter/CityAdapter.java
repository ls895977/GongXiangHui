package com.qunxianghui.gxh.adapter.homeAdapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * @author 小强
 * @time 2018/5/21  15:15
 * @desc 城市适配器
 */
public class CityAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {


    public CityAdapter(List<MultiItemEntity> data) {
        super(data);
    }


    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {

    }
}
