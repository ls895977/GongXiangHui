package com.qunxianghui.gxh.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnDismissListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.bean.AddressBean;
import com.qunxianghui.gxh.utils.CityPickerutil;

/**
 * Created by hasee on 2018/6/16.
 */

public class LocationPickDialog extends Dialog{
    private Context context;
    private LocationPickListener locationPickListener;

    private OptionsPickerView locationPickView;

    public LocationPickDialog(@NonNull Context context, LocationPickListener locationPickListener) {
        super(context, R.style.PICK_DIALOG_THEME);
        this.context = context;
        this.locationPickListener = locationPickListener;
        init(context);
    }

    public LocationPickDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected LocationPickDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    private void init(Context context) {
        int top = getWindow().getDecorView().getTop();
        int bottom = getWindow().getDecorView().getBottom();
        getWindow().getDecorView().setPadding(0, top, 0, bottom);

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(lp);
        getWindow().setGravity(Gravity.BOTTOM);

        setCancelable(true);

        LinearLayout linearLayout = new LinearLayout(context);
        OptionsPickerBuilder locationPickerBuilder = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String location = CityPickerutil.items1.get(options1);
                if(!TextUtils.isEmpty(location) && !location.equals(CityPickerutil.items2.get(options1).get(options2))){
                    location += CityPickerutil.items2.get(options1).get(options2);
                }
                if(!TextUtils.isEmpty(location) && !location.contains(CityPickerutil.items3.get(options1).get(options2).get(options3))){
                    location += CityPickerutil.items3.get(options1).get(options2).get(options3);
                }
                locationPickListener.onSelect(location);
            }
        });
        locationPickView = locationPickerBuilder.setContentTextSize(16)//设置滚轮文字大小
                .setDividerColor(context.getResources().getColor(R.color.gray_9))//设置分割线的颜色
                .setSelectOptions(0, 0,0)//默认选中项
                .setBgColor(context.getResources().getColor(R.color.white))
                .setTitleBgColor(context.getResources().getColor(R.color.white))
                .setTitleColor(context.getResources().getColor(R.color.gray_9))
                .setCancelColor(context.getResources().getColor(R.color.gray_9))
                .setSubmitColor(context.getResources().getColor(R.color.gray_9))
                .setTextColorCenter(context.getResources().getColor(R.color.colorPrimary))
                .setCancelText("取消")
                .setSubmitText("确认")
                .isRestoreItem(true)
                .isCenterLabel(false)
                .setDecorView(linearLayout)
                .build();
        locationPickView.setPicker(CityPickerutil.items1,CityPickerutil.items2,CityPickerutil.items3);

        locationPickView.show();

        locationPickView.setOnDismissListener(new com.bigkoo.pickerview.listener.OnDismissListener() {
            @Override
            public void onDismiss(Object o) {
                locationPickListener.onSelect("");
                dismiss();
            }
        });

        setContentView(linearLayout);
    }

    public void showPickView(){
        if(locationPickView != null){
            locationPickView.show();
        }
    }

    public interface LocationPickListener{
        void onSelect(String info);
    }
}
