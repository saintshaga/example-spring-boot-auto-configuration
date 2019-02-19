package cn.saintshaga.example.listener;

import cn.saintshaga.example.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

/**
 * Created by huang on 19-2-18.
 */
@Slf4j
public class CacheListener implements CacheEventListener<String, User> {

    @Override
    public void onEvent(CacheEvent<? extends String, ? extends User> cacheEvent) {
        log.info("Event: " + cacheEvent.getType()
                + " Key: " + cacheEvent.getKey()
                + " old value: " + cacheEvent.getOldValue()
                + " new value: " + cacheEvent.getNewValue());
    }
}
