package com.qunxianghui.gxh.interfaces;

public interface OnChannelListener {
    void onItemMove(int starPos, int endPos);

    void onMoveToMyChannel(int starPos, int endPos);

    void onMoveToOtherChannel(int starPos, int endPos);

    void onFinish(String selectedChannelName);
}
