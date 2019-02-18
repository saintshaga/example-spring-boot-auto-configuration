package cn.saintshaga.example.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * Created by huang on 18-11-15.
 */
public class CustomEvent extends ApplicationEvent {
    @Getter
    private String message;

    public CustomEvent(Object source, String message) {
        super(source);
        this.message = message;
    }
}
