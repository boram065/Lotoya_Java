package com.example.Lotoya_Java.controller;

import com.example.Lotoya_Java.entity.MyPlayer;
import com.example.Lotoya_Java.entity.Player;
import com.example.Lotoya_Java.entity.User;
import com.example.Lotoya_Java.repository.PlayerRepository;
import com.example.Lotoya_Java.repository.UserRepository;
import com.example.Lotoya_Java.service.MyPlayerService;
import com.example.Lotoya_Java.service.PlayerService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

import static com.example.Lotoya_Java.controller.UserController.getLoggedInUser;

@Controller
@RequiredArgsConstructor
public class fontController {
    private final PlayerService playerService;
    private final UserRepository userRepository;
    private final MyPlayerService myPlayerService;

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
        model.addAttribute("slide1", "/images/slide1.jpeg");
        model.addAttribute("slide2", "/images/slide2.jpg");
        model.addAttribute("slide3", "/images/slide3.jpg");
        model.addAttribute("slide4", "/images/slide4.jpg");
        model.addAttribute("slide5", "/images/slide5.jpg");

        return "main";
    }

    @RequestMapping("/buyPlayer/{id}")
    public String buyPlayer(@PathVariable Long id, Model model, HttpSession session) {
        Optional<Player> optionalPlayer = playerService.getPlayer(id);
        User loggedInUser = getLoggedInUser(session);

        if (optionalPlayer.isPresent()) {
            Player player = optionalPlayer.get();

            model.addAttribute("logo", "/images/logo.jpg");
            model.addAttribute("vs", "/images/vs.jpg");
            model.addAttribute("club", "/images/club.jpg");
            model.addAttribute("FA", "/images/FA.jpg");
            model.addAttribute("coin", "/images/coin.jpg");
            model.addAttribute("back", "/images/back.jpg");

            loggedInUser = userRepository.findById(loggedInUser.getId()).orElse(null);
            if (loggedInUser != null) {
                Integer currentUserCoin = loggedInUser.getCoin();
                model.addAttribute("currentUserCoin", currentUserCoin);
            }

            model.addAttribute("player", player);
            return "buyPlayer";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping("/forecast")
    public String forecast(Model model, HttpSession session) {
        User loggedInUser = getLoggedInUser(session);

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

        loggedInUser = userRepository.findById(loggedInUser.getId()).orElse(null);
        if (loggedInUser != null) {
            Integer currentUserCoin = loggedInUser.getCoin();
            model.addAttribute("currentUserCoin", currentUserCoin);
        }

        return "forecast";
    }

    @RequestMapping("/ground")
    public String ground(Model model, HttpSession session) {
        User loggedInUser = getLoggedInUser(session);

        model.addAttribute("logo", "/images/logo.jpg");
        model.addAttribute("vs", "/images/vs.jpg");
        model.addAttribute("club", "/images/club.jpg");
        model.addAttribute("FA", "/images/FA.jpg");
        model.addAttribute("playerTeam", "/images/playerteam.jpg");
        model.addAttribute("search", "/images/search.jpg");
        model.addAttribute("coin", "/images/coin.jpg");
        model.addAttribute("ground", "/images/ground.png");

        loggedInUser = userRepository.findById(loggedInUser.getId()).orElse(null);
        if (loggedInUser != null) {
            List<MyPlayer> myPlayers = myPlayerService.getAllPlayersByUser(loggedInUser);
            model.addAttribute("myPlayers", myPlayers);

            Integer currentUserCoin = loggedInUser.getCoin();
            model.addAttribute("currentUserCoin", currentUserCoin);
        }
        return "ground";
    }

    @RequestMapping("/store")
    public String store(Model model, HttpSession session) {
        User loggedInUser = getLoggedInUser(session);

        model.addAttribute("logo", "/images/logo.jpg");
        model.addAttribute("vs", "/images/vs.jpg");
        model.addAttribute("club", "/images/club.jpg");
        model.addAttribute("FA", "/images/FA.jpg");
        model.addAttribute("playerTeam", "/images/playerteam.jpg");
        model.addAttribute("search", "/images/search.jpg");
        model.addAttribute("coin", "/images/coin.jpg");
        model.addAttribute("wishList", "/images/wishlist.jpg");

        loggedInUser = userRepository.findById(loggedInUser.getId()).orElse(null);
        if (loggedInUser != null) {
            Integer currentUserCoin = loggedInUser.getCoin();
            model.addAttribute("currentUserCoin", currentUserCoin);
        }

        List<Player> players = playerService.getAllPlayers();
        model.addAttribute("players", players);

        return "store";
    }

}