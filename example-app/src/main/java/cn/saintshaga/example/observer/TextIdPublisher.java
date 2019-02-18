package cn.saintshaga.example.observer;

import java.beans.PropertyChangeSupport;

/**
 * Created by huang on 19-1-16.
 */
public class TextIdPublisher {

    private PropertyChangeSupport support;

    public TextIdPublisher() {
        support = new PropertyChangeSupport(this);
    }

    public void publish(Object event) {
    }
}
