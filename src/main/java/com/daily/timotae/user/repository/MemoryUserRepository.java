package com.daily.timotae.user.repository;

import com.daily.timotae.user.domain.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MemoryUserRepository implements UserRepository {

    private static Map<Long, User> store = new HashMap<>();
    private static long seq = 0L;

    @Override
    public User saveUser(User user) {
        seq++;
        store.put(seq, user);
        return user;
    }

    @Override
    public User findUserOne(Long id) {
        return store.get(id);
    }

    @Override
    public void updateUser(Long id, User user) {
        store.replace(id, user);
    }

    @Override
    public void removeUser(Long id) {
        store.remove(id);
    }

    @Override
    public void clearStore() {
        store.clear();
    }
}
