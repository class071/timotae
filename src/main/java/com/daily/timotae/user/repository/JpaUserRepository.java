package com.daily.timotae.user.repository;

import com.daily.timotae.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserId(String userId);
}
