package cn.saintshaga.example.service;

import cn.saintshaga.example.entity.User;

import java.util.List;
import java.util.Map;

/**
 * Created by huang on 19-2-19.
 */
public interface UserRepository {
    User getUser(String userId);
    Map<String, User> getUsers(List<String> userId);
}
