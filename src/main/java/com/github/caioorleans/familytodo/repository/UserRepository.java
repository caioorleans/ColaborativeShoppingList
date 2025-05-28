package com.github.caioorleans.familytodo.repository;

import com.github.caioorleans.familytodo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}
