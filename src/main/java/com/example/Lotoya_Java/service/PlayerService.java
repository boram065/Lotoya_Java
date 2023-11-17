package com.example.Lotoya_Java.service;

import com.example.Lotoya_Java.entity.Player;
import com.example.Lotoya_Java.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;

//    public Player createPlayer(Player player) {
//        return playerRepository.save(player);
//    }

//    public Player savePlayer(Player player) {
//        return playerRepository.save(player);
//    }

    public List<Player> getAllPlayers(){
        return playerRepository.findAll();
    }

    public Optional<Player> getPlayer(Long playerId){
        return playerRepository.findById(playerId);
    }
}
