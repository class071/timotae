package com.daily.timotae.user.repository;

import com.daily.timotae.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User saveUser(User user);

    List<User> findUserAll();

    Optional<User> findUserOne(Long id);

    void updateUser(Long id, User user);

    void removeUser(Long id);
}
