package com.example.tilproject.repository;

import com.example.tilproject.domain.Board;
import com.example.tilproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByUser(User user);
}
