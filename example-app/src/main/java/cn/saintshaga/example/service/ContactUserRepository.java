package cn.saintshaga.example.service;

import cn.saintshaga.example.entity.IUser;
import cn.saintshaga.example.entity.User;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PreDestroy;
import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.annotation.CacheResult;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ContactUserRepository implements UserRepository {

    static final String CONTACT_USER_CACHE_NAME = "contactUser";

    @Autowired
    private CacheManager cacheManager;

    @CacheResult(cacheName = CONTACT_USER_CACHE_NAME)
    @Override
    public IUser getUser(@NonNull String userId) {
        return User.builder()
                .name(userId + "name")
                .userId(userId)
                .build();
    }

    @Override
    public Map<String, IUser> getUsers(List<String> userIds) {
        if(CollectionUtils.isEmpty(userIds)) {
            return Collections.emptyMap();
        }
        final Cache<String, IUser> cache = cacheManager.getCache(CONTACT_USER_CACHE_NAME);
        List<String> uncachedUserIds = userIds.stream().filter(Objects::nonNull)
                .filter(userId -> !cache.containsKey(userId))
                .collect(Collectors.toList());
        Map<String, IUser> uncachedUsers = getUsersNative(uncachedUserIds).stream().collect(Collectors.toMap(User::getUserId, Function.identity()));
        cache.putAll(uncachedUsers);
        return cache.getAll(userIds.stream().filter(Objects::nonNull).collect(Collectors.toSet()));
    }

    private List<User> getUsersNative(List<String> userIds) {
        if(CollectionUtils.isEmpty(userIds)) {
            return Collections.emptyList();
        }
        return userIds.stream().map(userId ->
                User.builder()
                        .name(userId+"name")
                        .userId(userId).build())
                .collect(Collectors.toList());
    }

    @PreDestroy
    public void closeCacheManager() {
        cacheManager.close();
    }
}
