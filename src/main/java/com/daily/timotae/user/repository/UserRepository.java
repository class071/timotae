package com.daily.timotae.user.repository;

import com.daily.timotae.user.domain.User;

public interface UserRepository {

    User saveUser(User user);

    User findUserOne(Long id);

    void updateUser(Long id, User user);

    void removeUser(Long id);

    void clearStore();
}
