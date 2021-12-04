package com.example.tilproject.repository.adminRepository;

import com.example.tilproject.domain.NewPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewPostRepository extends JpaRepository<NewPost, Long> {

}
