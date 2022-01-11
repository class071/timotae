package com.daily.timotae.user.repository;

import com.daily.timotae.user.domain.User;

import java.util.List;
import java.util.Map;

public interface UserRepository {

    User saveUser(User user);
    Map<Long, User> findUserAll();
    User findUserOne(Long id);
    void updateUser(Long id, User user);
    void removeUser(Long id);

    List<User> findAll();
    void clearStore();
}
