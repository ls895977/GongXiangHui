package com.qunxianghui.gxh.observer;

import com.qunxianghui.gxh.db.ChannelItem;

import java.util.ArrayList;
import java.util.List;

public class NewChannelEvent {

    public ArrayList<ChannelItem> mList;

    public NewChannelEvent(List<ChannelItem> list) {
        mList = new ArrayList<>();
        for (ChannelItem channelItem : list) {
            if (channelItem.viewType == ChannelItem.TYPE_MY_CHANNEL) mList.add(channelItem);
        }
    }
}
