package com.qunxianghui.gxh.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Admin on 2017/11/7.
 */

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private SpaceSize space;

    public SpacesItemDecoration(SpaceSize space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        outRect.right = space.getRight();
        outRect.left = space.getLeft();
        outRect.top = space.getTop();
        outRect.bottom = space.getBottom();
    }
}
