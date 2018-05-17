package com.qunxianghui.gxh.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2018/3/20 0020.
 */

public class GloriousRecyclerView extends RecyclerView {
    private View mHeaderView;
    private View mHeaderViewViewPager;
    private View mFooterView;
    private View mEmptyView;
        private AutoLoadMoreListener mLoadMoreListener;
    private boolean mIsLoadMoreEnabled;
    private boolean mIsLoadingMore;
    private GloriousAdapter mGloriousAdapter;

    private void init(){
        this.addOnScrollListener(mOnScrollListener);
    }


    private OnScrollListener mOnScrollListener = new OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

            super.onScrolled(recyclerView, dx, dy);
            if (mIsLoadMoreEnabled && !mIsLoadingMore && dy > 0) {
                if (findLastVisibleItemPosition() == mGloriousAdapter.getItemCount() - 1) {
                    mIsLoadingMore = true;
                    mLoadMoreListener.onLoadMore();
                }
            }


        }
    };

    private int findLastVisibleItemPosition() {
        int position;
        if (getLayoutManager() instanceof LinearLayoutManager) {
            position = ((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
        } else if (getLayoutManager() instanceof GridLayoutManager) {
            position = ((GridLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
        } else if (getLayoutManager() instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) getLayoutManager();
            int[] lastPositions = layoutManager.findLastVisibleItemPositions(new int[layoutManager.getSpanCount()]);
            position = findMaxPosition(lastPositions);
        } else {
            position = getLayoutManager().getItemCount() - 1;
        }
        return position;
    }


    public GloriousRecyclerView(Context context) {
        super(context);
    }

    public GloriousRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GloriousRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public interface AutoLoadMoreListener {
        void onLoadMore();
    }


    public void addHeaderView(View view) {
        mHeaderView = view;
        mGloriousAdapter.notifyItemInserted(0);
    }

    public void addHeaderView2(View view) {
        mHeaderViewViewPager = view;
        mGloriousAdapter.notifyItemInserted(1);
    }

    public void addFooterView(View view) {
        mFooterView = view;
        mGloriousAdapter.notifyItemInserted(mGloriousAdapter.getItemCount() - 1);
    }

    public void setEmptyView(View view) {
        mEmptyView = view;
        mGloriousAdapter.notifyDataSetChanged();
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (adapter != null) {
            mGloriousAdapter = new GloriousAdapter(adapter);
        }
        super.setAdapter(mGloriousAdapter);
    }


    /**
     * Find StaggeredGridLayoutManager the last visible position
     */

    private int findMaxPosition(int[] positions) {
        int maxPosition = 0;
        for (int position : positions) {
            maxPosition = Math.max(maxPosition, position);
        }
        return maxPosition;
    }

    private class GloriousAdapter extends RecyclerView.Adapter<ViewHolder> {
        private Adapter mOriginalAdapter;
        private int ITEM_TYPE_NORMAL = 0;
        private int ITEM_TYPE_HEADER = 1;
        private int ITEM_TYPE_HEADER2 = 2;
        private int ITEM_TYPE_FOOTER = 3;
        private int ITEM_TYPE_EMPTY = 4;

               //聪明的人会发现我们这里用了一个装饰模式
        public GloriousAdapter(Adapter originalAdapter) {
            mOriginalAdapter = originalAdapter;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == ITEM_TYPE_HEADER) {
                return new GloriousViewHolder(mHeaderView);
            } else if (viewType == ITEM_TYPE_HEADER2) {
                return new GloriousViewHolder(mHeaderViewViewPager);
            } else if (viewType == ITEM_TYPE_EMPTY) {
                return new GloriousViewHolder(mEmptyView);
            } else if (viewType == ITEM_TYPE_FOOTER) {
                return new GloriousViewHolder(mFooterView);
            } else {
                return mOriginalAdapter.onCreateViewHolder(parent, viewType);
            }

        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            int type = getItemViewType(position);
            if (type == ITEM_TYPE_HEADER ||type == ITEM_TYPE_HEADER2|| type == ITEM_TYPE_FOOTER || type == ITEM_TYPE_EMPTY) {
                return;
            }
            int realPosition = getRealItemPosition(position);
            mOriginalAdapter.onBindViewHolder(holder, realPosition);
        }

        @Override
        public int getItemCount() {
            int itemCount = mOriginalAdapter.getItemCount();
            //加上其他各种View
            if (null != mEmptyView && itemCount == 0) itemCount++;
            if (null != mHeaderView) itemCount++;
            if (null != mHeaderViewViewPager) itemCount++;
            if (null != mFooterView) itemCount++;
            return itemCount;

        }

        @Override
        public int getItemViewType(int position) {
            if (null != mHeaderView && position == 0) return ITEM_TYPE_HEADER;
            if (null != mHeaderViewViewPager && position == 1) return ITEM_TYPE_HEADER2;
            if (null != mFooterView && position == getItemCount() - 1) return ITEM_TYPE_FOOTER;
            if (null != mEmptyView && mOriginalAdapter.getItemCount() == 0) return ITEM_TYPE_EMPTY;
            return ITEM_TYPE_NORMAL;
        }

        private int getRealItemPosition(int position) {
            if (null != mHeaderView) {
                return position - 1;
            }
            return position;
        }

        /**
         * ViewHolder 是一个抽象类
         */
        class GloriousViewHolder extends ViewHolder {
            GloriousViewHolder(View itemView) {
                super(itemView);
            }
        }
    }

}
