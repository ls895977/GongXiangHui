package com.qunxianghui.gxh.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.GuidViewPagerAdapter;
import com.qunxianghui.gxh.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2018/7/22.
 */

public class GuidSlideActivity extends BaseActivity {


    @BindView(R.id.viewpager_guidslide)
    ViewPager viewpagerGuidslide;
    private List<View> viewList = new ArrayList<>();//ViewPager数据源
    private GuidViewPagerAdapter guidViewPagerAdapter;
    private int count = 0; //页面展示的数据，无实际作用

    @Override
    protected int getLayoutId() {
        return R.layout.activity_slideguid;
    }

    @Override
    protected void initViews() {
        guidViewPagerAdapter = new GuidViewPagerAdapter(viewList);
        viewpagerGuidslide.setAdapter(guidViewPagerAdapter);

    }

    @Override
    protected void initDatas() {

    }
    /**
     * 添加选项菜单
     * 为了不影响ViewPager每个页面的一致性，这里使用选项菜单来操作添加和删除页面的点击事件
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1, 1, 1, "添加页面");
        menu.add(1, 2, 1, "删除页面");
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 为选项菜单设置点击事件
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                //添加页面
                String text = "页面" + count;
                count++;
                addPage(text);
                break;
            case 2:
                delPage();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void delPage() {
        int position = viewpagerGuidslide.getCurrentItem();
        viewList.remove(position);
        guidViewPagerAdapter.notifyDataSetChanged();

    }
    /**
     *该方法封装了添加页面的代码逻辑实现，参数text为要展示的数据
     */
    private void addPage(String text) {
        LayoutInflater inflater = LayoutInflater.from(this);//获取LayoutInflater的实例
        View view = inflater.inflate(R.layout.guidslide_item, null);//调用LayoutInflater实例的inflate()方法来加载页面的布局
        TextView textView = (TextView) view.findViewById(R.id.text_view);//获取该View对象的TextView实例
        textView.setText(text);//展示数据
        viewList.add(view);
        guidViewPagerAdapter.notifyDataSetChanged();

   }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
