package com.example.Lotoya_Java.repository;

import com.example.Lotoya_Java.entity.Player;
import com.example.Lotoya_Java.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    @Query("SELECT w FROM Wishlist w WHERE w.user.id = :userId AND w.player.id = :playerId")
    Wishlist findPlayersByUserId(@Param("userId") Long userId, @Param("playerId") Long playerId);

    @Modifying
    @Query("DELETE FROM Wishlist w WHERE w.user.id = :userId AND w.player.id = :playerId")
    void deletePlayerFromWishlist(@Param("userId") Long userId, @Param("playerId") Long playerId);

    List<Wishlist> findByUserId(Long userId);
}


