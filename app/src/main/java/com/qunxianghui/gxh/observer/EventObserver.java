package com.qunxianghui.gxh.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 作者：Administrator on 2018/8/9 16:17
 */
public abstract class EventObserver implements Observer{
    @Override
    public void update(Observable o, Object arg) {
        update(arg);
    }

     public abstract void update(Object object);
}
