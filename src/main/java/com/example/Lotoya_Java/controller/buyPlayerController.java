package com.example.Lotoya_Java.controller;

import com.example.Lotoya_Java.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
public class buyPlayerController {
    private final Player player;

    @RequestMapping("/buyPlayer")
    public String buyPlayer(Model model) {
        ArrayList<String> name = player.getPlayerName();

        model.addAttribute("playerName", name);
        model.addAttribute("imagePath1", "/static/images/logo.jpg");
        model.addAttribute("imagePath2", "/static/images/vs.jpg");
        model.addAttribute("imagePath3", "/static/images/club.jpg");
        model.addAttribute("imagePath4", "/static/images/FA.jpg");
        model.addAttribute("imagePath5", "/static/images/mypage.jpg");
        model.addAttribute("imagePath8", "/static/images/coin.jpg");
        model.addAttribute("imagePath9", "/static/images/basket.jpg");
        return "buyPlayer";
    }
}
