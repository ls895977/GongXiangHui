package com.qunxianghui.gxh.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.home.WelcomeAdvertBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtils;
import com.qunxianghui.gxh.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 欢迎界面 倒计时
 * Created by Administrator on 2018/3/26 0026.
 */

public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.iv_welcomeadver)
    ImageView mIvWelcomeadver;
    @BindView(R.id.textView)
    TextView mTextView;
    @BindView(R.id.ll_welcome_skip)
    LinearLayout mLlWelcomeSkip;
    @BindView(R.id.vp_guide_items)
    ViewPager vpGuideItems;
    @BindView(R.id.btn_start)
    ImageButton btnStart;
    private Animation animation;
    private int count = 5;
    //向导图片
    private int[] imgs = new int[]{R.mipmap.icon_guid_one, R.mipmap.icon_guid_two, R.mipmap.icon_guid_three};
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                mTextView.setText(String.valueOf(getCount()));
                handler.sendEmptyMessageDelayed(0, 1000);
                animation.reset();
                mTextView.startAnimation(animation);
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initViews() {
        animation = AnimationUtils.loadAnimation(this, R.anim.animation_text);
        //textView.startAnimation(animation);
        handler.sendEmptyMessageDelayed(0, 1000);
    }

    private int getCount() {
        count--;
        if (count == 0) {
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else if (count == 3) {
            mLlWelcomeSkip.setVisibility(View.VISIBLE);
        }
        return count;
    }


    /**
     * 请求网络广告
     */
    private void requestWelcomeAdvert() {
        OkGo.<String>get(Constant.WELCOM_ADVER_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                WelcomeAdvertBean welcomeAdvertBean = GsonUtils.jsonFromJson(response.body(), WelcomeAdvertBean.class);
                if (welcomeAdvertBean.getCode() == 0) {
                    WelcomeAdvertBean.DataBean data = welcomeAdvertBean.getData();
                    String image = data.getImage();
                    Glide.with(mContext)
                            .load(image)
                            .apply(new RequestOptions().placeholder(R.mipmap.icon_starpage)
                                    .error(R.mipmap.icon_starpage)
                                    .centerCrop())
                            .into(mIvWelcomeadver);
                }
            }
        });
    }

    @Override
    protected void initData() {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                requestWelcomeAdvert();
            }
        }, 1000);



        SPUtils.saveBoolean(Constant.KEY_HAS_GUIDE, true);
        List<View> views = new ArrayList<>();
        for (int img : imgs) {
            final ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(img);
            views.add(imageView);
        }
        initGuideItems(views);
    }

    private void initGuideItems(List<View> views) {
        final GuidePagerAdapter adapter = new GuidePagerAdapter(views);
        vpGuideItems.setAdapter(adapter);

    }

    @Override
    protected void initListeners() {
        super.initListeners();
        mLlWelcomeSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacksAndMessages(null);
                toActivity(MainActivity.class);
                finish();
            }
        });

        /*滑动检测*/
        vpGuideItems.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == imgs.length - 1) {
                    btnStart.setVisibility(View.VISIBLE);
                    btnStart.setClickable(true);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /*适配器*/
    private class GuidePagerAdapter extends PagerAdapter {

        private List<View> views;

        public GuidePagerAdapter(List<View> views) {

            this.views = views;
        }

        @Override
        public int getCount() {

            return views != null ? views.size() : 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            final View child = views.get(position);
            container.addView(child);
            return child;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
