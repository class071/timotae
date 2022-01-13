package com.daily.timotae.user.controller;

import com.daily.timotae.user.domain.User;
import com.daily.timotae.user.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @PostMapping
    public User joinUser(@RequestBody User user) {
        return userService.joinUser(user);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody User user) {
        userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void removeUser(@PathVariable Long id) {
        userService.removeUser(id);
    }
}
