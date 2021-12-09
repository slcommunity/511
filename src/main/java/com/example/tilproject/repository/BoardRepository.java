package com.example.tilproject.repository;

import com.example.tilproject.domain.Board;
import com.example.tilproject.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    Page<Board> findAllByOrderByCreatedAtDesc(Pageable pageable);


    List<Board> findByUser(User user);

    List<Board> findByTitleContaining(String word);
}
