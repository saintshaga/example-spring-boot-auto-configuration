package cn.saintshaga.example.observer;

import com.google.common.eventbus.EventBus;

import java.util.Observable;

/**
 * Created by huang on 19-1-16.
 */
public class TextManager extends Observable {

    public String getTextId(String textId) {
        EventBus eventBus = new EventBus();
        eventBus.post(textId);
        return null;
    }
}
