package com.example.Lotoya_Java.controller;

import com.example.Lotoya_Java.entity.Player;
import com.example.Lotoya_Java.entity.User;
import com.example.Lotoya_Java.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class fontController {
    private final User user;
    private final PlayerService playerService;
//    private final UserContextHolder userContextHolder;

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("logo", "/images/logo.jpg");
        model.addAttribute("vs", "/images/vs.jpg");
        model.addAttribute("club", "/images/club.jpg");
        model.addAttribute("FA", "/images/FA.jpg");
        return "login";
    }

    @RequestMapping("/join")
    public String join(Model model) {
        model.addAttribute("logo", "/images/logo.jpg");
        model.addAttribute("vs", "/images/vs.jpg");
        model.addAttribute("club", "/images/club.jpg");
        model.addAttribute("FA", "/images/FA.jpg");
        return "join";
    }

    @RequestMapping("/main")
    public String Main(Model model) {
        model.addAttribute("logo", "/images/logo.jpg");
        model.addAttribute("vs", "/images/vs.jpg");
        model.addAttribute("club", "/images/club.jpg");
        model.addAttribute("FA", "/images/FA.jpg");
        model.addAttribute("imagePath5", "/images/coin.jpg");
        model.addAttribute("imagePath6", "/images/basket.jpg");

        return "main";
    }

    @RequestMapping("/buyPlayer/{id}")
    public String buyPlayer(@PathVariable Long id, Model model) {
        Optional<Player> optionalPlayer = playerService.getPlayer(id);

        if (optionalPlayer.isPresent()) {
            Player player = optionalPlayer.get();

            model.addAttribute("logo", "/images/logo.jpg");
            model.addAttribute("vs", "/images/vs.jpg");
            model.addAttribute("club", "/images/club.jpg");
            model.addAttribute("FA", "/images/FA.jpg");
            model.addAttribute("coin", "/images/coin.jpg");
            model.addAttribute("back", "/images/back.jpg");

            Integer currentUserCoin = user.getCoin();
            model.addAttribute("currentUserCoin", currentUserCoin);

            model.addAttribute("player", player);
            return "buyPlayer";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping("/forecast")
    public String forecast(Model model) {
        model.addAttribute("logo", "/images/logo.jpg");
        model.addAttribute("vs", "/images/vs.jpg");
        model.addAttribute("club", "/images/club.jpg");
        model.addAttribute("FA", "/images/FA.jpg");
        model.addAttribute("coin", "/images/coin.jpg");
        model.addAttribute("SSG", "/images/SSG.png");
        model.addAttribute("Doosan", "/images/두산.png");
        model.addAttribute("KT", "/images/KT.png");
        model.addAttribute("LG", "/images/LG.png");
        model.addAttribute("NC", "/images/NC.png");
        model.addAttribute("KIA", "/images/기아.png");
        model.addAttribute("Lotte", "/images/롯데.png");
        model.addAttribute("Samsung", "/images/삼성.png");
        model.addAttribute("Kiwoom", "/images/키움.png");
        model.addAttribute("Hanwha", "/images/한화.png");

        Integer currentUserCoin = user.getCoin();
        model.addAttribute("currentUserCoin", currentUserCoin);

        return "forecast";
    }

    @RequestMapping("/ground")
    public String ground(Model model) {
        model.addAttribute("logo", "/images/logo.jpg");
        model.addAttribute("vs", "/images/vs.jpg");
        model.addAttribute("club", "/images/club.jpg");
        model.addAttribute("FA", "/images/FA.jpg");
        model.addAttribute("playerTeam", "/images/playerteam.jpg");
        model.addAttribute("search", "/images/search.jpg");
        model.addAttribute("coin", "/images/coin.jpg");
        model.addAttribute("ground", "/images/ground.png");

        Integer currentUserCoin = user.getCoin();
        model.addAttribute("currentUserCoin", currentUserCoin);

        return "ground";
    }

    @RequestMapping("/store")
    public String store(Model model) {
        model.addAttribute("logo", "/images/logo.jpg");
        model.addAttribute("vs", "/images/vs.jpg");
        model.addAttribute("club", "/images/club.jpg");
        model.addAttribute("FA", "/images/FA.jpg");
        model.addAttribute("playerTeam", "/images/playerteam.jpg");
        model.addAttribute("search", "/images/search.jpg");
        model.addAttribute("coin", "/images/coin.jpg");
        model.addAttribute("wishList", "/images/wishlist.jpg");

        Integer currentUserCoin = user.getCoin();
        model.addAttribute("currentUserCoin", currentUserCoin);

        List<Player> players = playerService.getAllPlayers();
        model.addAttribute("players", players);

        return "store";
    }

}