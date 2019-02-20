package cn.saintshaga.example.service;

import cn.saintshaga.example.entity.IUser;

import java.util.List;
import java.util.Map;

/**
 * Created by huang on 19-2-19.
 */
public interface UserRepository {
    IUser getUser(String userId);
    Map<String, IUser> getUsers(List<String> userId);
}
