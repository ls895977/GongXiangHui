package com.qunxianghui.gxh.ui.fragments.homeFragment.activity;

import android.view.View;
import android.widget.ListView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.homeAdapter.LifeStyleAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.widget.TitleBuilder;

import java.util.Vector;

/**
 * Created by Administrator on 2018/3/20 0020.
 */

public class LiftStyleActivity extends BaseActivity {
    private ListView listView;
    private Vector<String> stringVector = new Vector<String>();
    private LifeStyleAdapter adapter;
    @Override
    protected int getLayoutId() {

        return R.layout.activity_life_style;
    }

    @Override
    protected void initViews() {
        listView = (ListView) findViewById(R.id.list);
        adapter = new LifeStyleAdapter(this, this.getLayoutInflater(), stringVector);
        stringVector.add("0");
        stringVector.add("1");
        stringVector.add("2");
        stringVector.add("3");
        stringVector.add("4");
        stringVector.add("5");
        listView.setAdapter(adapter);
    }

    @Override
    protected void initDatas() {
         new TitleBuilder(this).setTitleText("生活圈").setLeftIco(R.mipmap.icon_back).setLeftIcoListening(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 finish();
             }
         });
    }
}
