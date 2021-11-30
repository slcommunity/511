package com.example.tilproject.repository;

import com.example.tilproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    @Transactional
    Optional<User> findByUsername(String username);

    @Transactional
    List<User> findByNameContaining(String searchName);
}
