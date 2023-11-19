package com.example.Lotoya_Java.service;

import com.example.Lotoya_Java.entity.Player;
import com.example.Lotoya_Java.entity.User;
import com.example.Lotoya_Java.entity.Wishlist;
import com.example.Lotoya_Java.repository.PlayerRepository;
import com.example.Lotoya_Java.repository.UserRepository;
import com.example.Lotoya_Java.repository.WishlistRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final UserRepository userRepository;
    private final PlayerRepository playerRepository;
    private final WishlistRepository wishlistRepository;

    public void addToWishlist(Long userId, Long playerId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new EntityNotFoundException("Player not found"));

        Wishlist wishlist = new Wishlist(user, player);
        wishlistRepository.save(wishlist);
    }


    public void removeFromWishlist(Long userId, Long playerId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new EntityNotFoundException("Player not found"));

        Wishlist wishlist = wishlistRepository.findPlayersByUserId(userId, playerId);
        if (wishlist != null) {
            wishlistRepository.delete(wishlist);
        } else {
            throw new EntityNotFoundException("Wishlist not found for user and player");
        }
    }

    public List<Wishlist> getWishlistByUserId(Long userId) {
        return wishlistRepository.findByUserId(userId);
    }
}


