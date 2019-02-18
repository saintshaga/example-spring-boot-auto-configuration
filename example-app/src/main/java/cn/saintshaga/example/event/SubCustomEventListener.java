package cn.saintshaga.example.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * Created by huang on 19-1-18.
 */
@Component
@Slf4j
public class SubCustomEventListener implements ApplicationListener<SubCustomEvent>, Ordered {
    @Override
    public void onApplicationEvent(SubCustomEvent event) {
        log.info("I have receive message {} from {}", event.getMessage(), event.getSource().getClass());
    }

    @Override
    public int getOrder() {
        return 4;
    }
}
