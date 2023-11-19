package com.example.Lotoya_Java.repository;

import com.example.Lotoya_Java.entity.MyPlayer;
import com.example.Lotoya_Java.entity.Player;
import com.example.Lotoya_Java.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyPlayerRepository extends JpaRepository<MyPlayer, Long> {

    List<MyPlayer> findByUserId(Long userId);

    @Query("SELECT p FROM Player p INNER JOIN MyPlayer mp ON p.id = mp.player.id WHERE mp.user.id = :userId")
    List<Player> findPlayersByUserId(@Param("userId") Long userId);

    boolean existsByUserAndPlayer(User user, Player player);

    List<MyPlayer> findByUser(User user) ;
}
