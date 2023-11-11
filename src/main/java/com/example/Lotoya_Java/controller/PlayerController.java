package com.example.Lotoya_Java.controller;

import com.example.Lotoya_Java.dto.PlayerViewResponse;
import com.example.Lotoya_Java.repository.PlayerRepository;
import com.example.Lotoya_Java.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/plyaers")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService){
        this.playerService = playerService;
    }

    @GetMapping("/")
    public ResponseEntity<List<PlayerViewResponse>>getAllPlayers(){
        List<PlayerViewResponse> players = playerService.getAllPlayers()
                .stream()
                .map(PlayerViewResponse::new)
                .toList();
        return ResponseEntity.ok().body(players);


    }
}
