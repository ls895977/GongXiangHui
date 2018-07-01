package com.qunxianghui.gxh.fragments.mineFragment.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.location.MyCollectBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by Administrator on 2018/4/2 0002.
 */

public class Fragment2 extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.et_addadv_name_fragment2)
    EditText etAddadvNameFragment2;
    @BindView(R.id.et_addadv_phonenumber_fragment2)
    EditText etAddadvPhonenumberFragment2;
    @BindView(R.id.et_addadv_address_fragment2)
    EditText etAddadvAddressFragment2;
    Unbinder unbinder;

    private boolean isComingFromColum = false;
    private int index;
    private int ad_id;

    @Override
    public int getLayoutId() {
        mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return R.layout.fragment_advertise2;
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void initViews(View view) {
//        final Intent intent = getActivity().getIntent();
//        isComingFromColum = intent.getBooleanExtra("isComingFromColum",false);
//        if (isComingFromColum==true){
//            index = intent.getIntExtra("index", 0);
//            if (index==1){
//                String url = intent.getStringExtra("imgUrl");
//                String link = intent.getStringExtra("link");
//                ad_id = intent.getIntExtra("ad_id", 0);
//            }
//        }

    }

    @Override
    protected void initListeners() {

    }


    @Override
    protected void onLoadData() {

    }


    @Override
    public void onClick(View v) {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    /**
     * 保存广告2
     */
    @Override
    public void commitData() {
        super.commitData();
        OkGo.<String>post(Constant.CHECK_ADD)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        MyCollectBean check = GsonUtil.parseJsonWithGson(response.body(), MyCollectBean.class);
                        if (check.getCode() == 0) {
                            commitAdverCardData();
                        }
                    }
                });
    }

    /**
     * 真正的fragment2 名片广告提交
     */
    private void commitAdverCardData() {
        final String mAddadverNameFragment2 = etAddadvNameFragment2.getText().toString().trim();
        final String mAddAdverPhoneNumberFragment2 = etAddadvPhonenumberFragment2.getText().toString().trim();
        final String mAddAdverAddressFragment2 = etAddadvAddressFragment2.getText().toString().trim();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
