package com.sparta.instagram_clone_sv.repository;

import com.sparta.instagram_clone_sv.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByRealname(String realname);
}