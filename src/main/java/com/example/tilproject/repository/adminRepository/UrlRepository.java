package com.example.tilproject.repository.adminRepository;

import com.example.tilproject.domain.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UrlRepository extends JpaRepository<Url, Long> {
    List<Url> findAllByUrlTurn(String perPage);
}

