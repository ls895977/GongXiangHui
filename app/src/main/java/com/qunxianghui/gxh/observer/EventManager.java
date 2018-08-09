package com.qunxianghui.gxh.observer;


import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * 作者：Administrator on 2018/8/9 14:30
 *
 * 事件处理中心
 */
public class EventManager extends Observable {

    public static final String EDIT="EDIT";
    public static final String COMPLETE="COMPLETE";

    private List<Observer> observers;

    private static EventManager eventManager;

    public void publishMessage(Object message) {
        setChanged();
        notifyObservers(message);
    }

    public EventManager(){
        observers=new ArrayList<>();
    }

    public static EventManager getInstance(){

        synchronized (EventManager.class){
            if(eventManager==null){
                eventManager=new EventManager();
            }
        }

        return eventManager;
    }
}
