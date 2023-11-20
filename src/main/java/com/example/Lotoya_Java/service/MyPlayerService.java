package com.example.Lotoya_Java.service;

import com.example.Lotoya_Java.dto.PlayerFilterRequest;
import com.example.Lotoya_Java.dto.PlayerViewResponse;
import com.example.Lotoya_Java.entity.MyPlayer;
import com.example.Lotoya_Java.entity.Player;
import com.example.Lotoya_Java.entity.User;
import com.example.Lotoya_Java.repository.MyPlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

//    public List<PlayerViewResponse> getFilteredPlayers(PlayerFilterRequest filterRequest) {
//        String club = filterRequest.getClub();
//        String position = filterRequest.getPosition();
//
//        List<MyPlayer> filteredPlayers;
//
//        if (club != null && position != null) {
//            filteredPlayers = myPlayerRepository.findByClubAndPosition(club, position);
//        } else if (club != null) {
//            filteredPlayers = myPlayerRepository.findByClub(club);
//        } else if (position != null) {
//            filteredPlayers = myPlayerRepository.findByPosition(position);
//        } else {
//            filteredPlayers = myPlayerRepository.findAll();
//        }
//        return convertToPlayerViewResponseList(filteredPlayers);
//    }


//    private List<PlayerViewResponse> convertToPlayerViewResponseList(List<MyPlayer> players) {
//        List<PlayerViewResponse> playerViewResponses = new ArrayList<>();
//        for (MyPlayer player : players) {
//            playerViewResponses.add(new PlayerViewResponse(player.getPlayer()));
//        }
//        return playerViewResponses;
//    }
}
