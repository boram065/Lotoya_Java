package com.example.Lotoya_Java.service;

import com.example.Lotoya_Java.entity.Player;
import com.example.Lotoya_Java.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    public List<Player> getAllPlayers(){
        return playerRepository.findAll();
    }

    public Player getPlayerByID(Long playerId){
        return playerRepository.findById(playerId).orElse(null);
    }

    public void savePlayer(Player player) {
        playerRepository.save(player);
    }

}
