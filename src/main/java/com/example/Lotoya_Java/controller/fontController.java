package com.example.Lotoya_Java.controller;

import com.example.Lotoya_Java.BoramPlayer;
import com.example.Lotoya_Java.entity.Player;
import com.example.Lotoya_Java.entity.User;
import com.example.Lotoya_Java.service.PlayerService;
import com.example.Lotoya_Java.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class fontController {
    private final BoramPlayer boramPlayer;
    private final UserService userService;
    private final User user;

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("imagePath1", "/images/logo.jpg");
        model.addAttribute("imagePath2", "/images/vs.jpg");
        model.addAttribute("imagePath3", "/images/club.jpg");
        model.addAttribute("imagePath4", "/images/FA.jpg");
        return "login";
    }

    @RequestMapping("/join")
    public String join(Model model) {
        model.addAttribute("imagePath1", "/images/logo.jpg");
        model.addAttribute("imagePath2", "/images/vs.jpg");
        model.addAttribute("imagePath3", "/images/club.jpg");
        model.addAttribute("imagePath4", "/images/FA.jpg");
        return "join";
    }

    @RequestMapping("/main")
    public String Main(Model model) {
        model.addAttribute("imagePath1", "/images/logo.jpg");
        model.addAttribute("imagePath2", "/images/vs.jpg");
        model.addAttribute("imagePath3", "/images/club.jpg");
        model.addAttribute("imagePath4", "/images/FA.jpg");
        model.addAttribute("imagePath5", "/images/coin.jpg");
        model.addAttribute("imagePath6", "/images/basket.jpg");
        return "main";
    }

    @RequestMapping("/buyPlayer")
    public String buyPlayer(Model model) {
        model.addAttribute("imagePath1", "/images/logo.jpg");
        model.addAttribute("imagePath2", "/images/vs.jpg");
        model.addAttribute("imagePath3", "/images/club.jpg");
        model.addAttribute("imagePath4", "/images/FA.jpg");
        model.addAttribute("imagePath5", "/images/coin.jpg");
        model.addAttribute("imagePath6", "/images/basket.jpg");
        return "buyPlayer";
    }

    @RequestMapping("/forecast")
    public String forecast(Model model) {
        model.addAttribute("imagePath1", "/images/logo.jpg");
        model.addAttribute("imagePath2", "/images/vs.jpg");
        model.addAttribute("imagePath3", "/images/club.jpg");
        model.addAttribute("imagePath4", "/images/FA.jpg");
        model.addAttribute("imagePath6", "/images/SSG.png");
        model.addAttribute("imagePath7", "/images/두산.png");
        model.addAttribute("imagePath8", "/images/KT.png");
        model.addAttribute("imagePath9", "/images/LG.png");
        model.addAttribute("imagePath10", "/images/NC.png");
        model.addAttribute("imagePath11", "/images/기아.png");
        model.addAttribute("imagePath12", "/images/롯데.png");
        model.addAttribute("imagePath13", "/images/삼성.png");
        model.addAttribute("imagePath14", "/images/키움.png");
        model.addAttribute("imagePath15", "/images/한화.png");

        Integer currentUserCoin = user.getCoin();
        model.addAttribute("currentUserCoin", currentUserCoin);

        return "forecast";
    }

    @RequestMapping("/ground")
    public String ground(Model model) {
        model.addAttribute("imagePath1", "/images/logo.jpg");
        model.addAttribute("imagePath2", "/images/vs.jpg");
        model.addAttribute("imagePath3", "/images/club.jpg");
        model.addAttribute("imagePath4", "/images/FA.jpg");
        model.addAttribute("imagePath6", "/images/playerteam.jpg");
        model.addAttribute("imagePath7", "/images/search.jpg");
        model.addAttribute("imagePath8", "/images/coin.jpg");
        model.addAttribute("imagePath9", "/images/ground.png");

        Integer currentUserCoin = user.getCoin();
        model.addAttribute("currentUserCoin", currentUserCoin);

        return "ground";
    }

    @RequestMapping("/store")
    public String store(Model model) {
        model.addAttribute("logo", "/images/logo.jpg");
        model.addAttribute("imagePath2", "/images/vs.jpg");
        model.addAttribute("imagePath3", "/images/club.jpg");
        model.addAttribute("imagePath4", "/images/FA.jpg");
        model.addAttribute("imagePath6", "/images/playerteam.jpg");
        model.addAttribute("imagePath7", "/images/search.jpg");
        model.addAttribute("imagePath8", "/images/coin.jpg");
        model.addAttribute("imagePath9", "/images/basket.jpg");
        model.addAttribute("imagePath10", "/images/wishlist.jpg");

        Integer currentUserCoin = user.getCoin();
        model.addAttribute("currentUserCoin", currentUserCoin);

        return "store";
    }

}