package com.example.Lotoya_Java.service;

import com.example.Lotoya_Java.entity.MyPlayer;
import com.example.Lotoya_Java.entity.Player;
import com.example.Lotoya_Java.entity.User;
import com.example.Lotoya_Java.repository.MyPlayerRepository;
import com.example.Lotoya_Java.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MyPlayerService {
    private final MyPlayerRepository myPlayerRepository;

    public List<MyPlayer> getAllPlayersByUser(User user){
        return myPlayerRepository.findByUser(user);
    }

    public Optional<MyPlayer> getPlayer(Long playerId){
        return myPlayerRepository.findById(playerId);
    }

    public boolean isPlayerBought(User user, Player player) {
        return myPlayerRepository.existsByUserAndPlayer(user, player);
    }
}
