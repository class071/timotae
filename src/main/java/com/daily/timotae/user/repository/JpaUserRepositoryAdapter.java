package com.daily.timotae.user.repository;

import com.daily.timotae.user.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.daily.timotae.constant.UserConstant.USER_NOT_EXIST;

@Component
public class JpaUserRepositoryAdapter implements UserRepository {

    private JpaUserRepository jpaUserRepository;

    @Override
    public User saveUser(User user) {
        return jpaUserRepository.save(user);
    }

    @Override
    public List<User> findUserAll() {
        return jpaUserRepository.findAll();
    }

    @Override
    public Optional<User> findUserOne(Long id) {
        return jpaUserRepository.findById(id);
    }

    @Override
    public void updateUser(Long id, User user) {
        User newUser = findUserOne(id)
                .orElseThrow( () -> new IllegalArgumentException(USER_NOT_EXIST + id));
        newUser.update(user.getUserId(), user.getUserPassword(), user.getName(), user.getAge(), user.getEmail(), user.getRole());
    }

    @Override
    public void removeUser(Long id) {
        jpaUserRepository.deleteById(id);
    }
}
