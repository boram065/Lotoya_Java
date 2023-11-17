package com.example.Lotoya_Java.repository;

import com.example.Lotoya_Java.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
//    Optional<Player> findById(Long playerId);
}
