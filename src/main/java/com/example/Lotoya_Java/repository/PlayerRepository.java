package com.example.Lotoya_Java.repository;

import com.example.Lotoya_Java.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findById(Long playerId);
    List<Player> findByClubAndPosition(String club, String position);
    List<Player> findByClub(String club);
    List<Player> findByPosition(String position);
}
