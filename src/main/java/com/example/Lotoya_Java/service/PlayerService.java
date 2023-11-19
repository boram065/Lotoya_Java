package com.example.Lotoya_Java.service;

import com.example.Lotoya_Java.dto.PlayerFilterRequest;
import com.example.Lotoya_Java.dto.PlayerViewResponse;
import com.example.Lotoya_Java.entity.Player;
import com.example.Lotoya_Java.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;

    public List<Player> getAllPlayers(){
        return playerRepository.findAll();
    }

    public Optional<Player> getPlayer(Long playerId){
        return playerRepository.findById(playerId);
    }

    public Long getPlayerId(Player player) {
        return player.getId();
    }

    public List<PlayerViewResponse> getFilteredPlayers(PlayerFilterRequest filterRequest) {
        String club = filterRequest.getClub();
        String position = filterRequest.getPosition();

        List<Player> filteredPlayers;

        if (club != null && position != null) {
            filteredPlayers = playerRepository.findByClubAndPosition(club, position);
        } else if (club != null) {
            filteredPlayers = playerRepository.findByClub(club);
        } else if (position != null) {
            filteredPlayers = playerRepository.findByPosition(position);
        } else {
            filteredPlayers = playerRepository.findAll();
        }
        return convertToPlayerViewResponseList(filteredPlayers);
    }


    private List<PlayerViewResponse> convertToPlayerViewResponseList(List<Player> players) {
        List<PlayerViewResponse> playerViewResponses = new ArrayList<>();
        for (Player player : players) {
            playerViewResponses.add(new PlayerViewResponse(player));
        }
        return playerViewResponses;
    }
}
