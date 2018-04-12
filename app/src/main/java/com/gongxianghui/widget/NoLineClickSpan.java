package com.gongxianghui.widget;

import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

/**
 * Created by Administrator on 2018/4/12 0012.
 */

public class NoLineClickSpan extends ClickableSpan {
    String color;

    public NoLineClickSpan(String color) {
        this.color = color;
    }

    @Override
    public void onClick(View widget) {

    }

    @Override
    public void updateDrawState(TextPaint ds) {
        //设置字条的颜色
        ds.setColor(Color.parseColor(color));
        ds.setUnderlineText(false); //去掉下划线
    }
}
