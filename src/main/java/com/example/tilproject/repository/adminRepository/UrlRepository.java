package com.example.tilproject.repository.adminRepository;

import com.example.tilproject.domain.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, Long> {
    List<Url> findAllByUrlTurn(String perPage);

    Optional<Url> findByUrlAndUrlNameAndUrlTurn(String url, String urlName, String urlTurn);
}

