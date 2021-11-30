package com.example.tilproject.repository.adminRepository;

import com.example.tilproject.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Page<User> findAllByTurn(String turn, Pageable pageable);

    List<User> findByNameContaining(String searchName);

    Optional<User> findByUsername(String username);

}
