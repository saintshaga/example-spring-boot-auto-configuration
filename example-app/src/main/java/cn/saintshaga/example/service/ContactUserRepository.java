package cn.saintshaga.example.service;

import cn.saintshaga.example.entity.User;
import org.springframework.stereotype.Service;

import javax.cache.annotation.CacheResult;

/**
 * Created by huang on 19-2-19.
 */
@Service
public class ContactUserRepository implements UserRepository {

    @CacheResult(cacheName = "contactUser")
    @Override
    public User getUser(String userId) {
        return User.builder()
                .name(userId + "name")
                .userId(userId)
                .build();
    }
}
