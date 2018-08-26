package com.qunxianghui.gxh.guide.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.ui.activity.MainActivity;
import com.qunxianghui.gxh.utils.SPUtils;

public class ThirdPageFragment extends Fragment implements View.OnClickListener {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.guide_fragment_third_page, container, false);
        inflate.findViewById(R.id.guide_third_page_bt_enter_home).setOnClickListener(this);
        return inflate;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.guide_third_page_bt_enter_home:
                enterHome();
                break;
        }
    }

    private void enterHome() {
        SPUtils.putBoolean("isFirstUse", false);
        startActivity(new Intent(getContext(), MainActivity.class));
        getActivity().finish();
    }
}
