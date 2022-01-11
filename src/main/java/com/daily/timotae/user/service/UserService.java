package com.daily.timotae.user.service;

import com.daily.timotae.user.domain.User;
import com.daily.timotae.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User joinUser(User user) {
        return userRepository.saveUser(user);
    }

    public void updateUser(Long id, User user) {
        userRepository.updateUser(id, user);
    }

    public void removeUser(Long id) {
        userRepository.removeUser(id);
    }

    public User findUserById(Long id) {
        return userRepository.findUserOne(id);
    }

    public List<User> viewUserAll() {
        return userRepository.findAll();
    }
}
