package com.example.tilproject.repository.adminRepository;

import com.example.tilproject.domain.NewPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewPostRepository extends JpaRepository<NewPost, Long> {
    List<NewPost> findBytitleContainingOrderByIdxDesc(String txt);

    List<NewPost> findBysummaryContainingOrderByIdxDesc(String txt);

    List<NewPost> findAllByOrderByIdxDesc();
}
