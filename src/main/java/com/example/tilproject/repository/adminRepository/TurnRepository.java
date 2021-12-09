package com.example.tilproject.repository.adminRepository;

import com.example.tilproject.domain.Turn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurnRepository extends JpaRepository<Turn, Long> {
    Turn findByTurn(String turnName);

}
