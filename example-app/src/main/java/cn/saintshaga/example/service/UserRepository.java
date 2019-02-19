package cn.saintshaga.example.service;

import cn.saintshaga.example.entity.User;

/**
 * Created by huang on 19-2-19.
 */
public interface UserRepository {
    User getUser(String userId);
//    List<User> getUser(List<String> userId);
}
