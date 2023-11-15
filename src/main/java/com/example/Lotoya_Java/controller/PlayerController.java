package com.example.Lotoya_Java.controller;

import com.example.Lotoya_Java.dto.PlayerViewResponse;
import com.example.Lotoya_Java.entity.Player;
import com.example.Lotoya_Java.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class PlayerController {
    private final PlayerService playerService;

    public String showStoreForm(Model model) {
        model.addAttribute("player", new Player());
        return "store";
    }

    @PostMapping("/store")
    public String processStore(@ModelAttribute Player player) {
        playerService.createPlayer(player);
        return "redirect:/store";
    }


    @GetMapping("/")
    public ResponseEntity<List<PlayerViewResponse>>getAllPlayers() {
        List<PlayerViewResponse> players = playerService.getAllPlayers()
                .stream()
                .map(PlayerViewResponse::new)
                .toList();
        return ResponseEntity.ok().body(players);
    }
}
