package com.example.Lotoya_Java.controller;

//import com.example.Lotoya_Java.dto.UserContextHolder;
import com.example.Lotoya_Java.entity.MyPlayer;
import com.example.Lotoya_Java.entity.Player;
import com.example.Lotoya_Java.entity.User;
import com.example.Lotoya_Java.repository.MyPlayerRepository;
import com.example.Lotoya_Java.repository.UserRepository;
import com.example.Lotoya_Java.service.PlayerService;
import com.example.Lotoya_Java.service.UserService;
import jakarta.persistence.EntityNotFoundException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class UserController{
    private final UserService userService;
    private final UserRepository userRepository;
    private final MyPlayerRepository myPlayerRepository;
    private final PlayerService playerService;

    public static User getLoggedInUser(HttpSession session) {
        return (User) session.getAttribute("loggedInUser");
    }

    @GetMapping("/join")
    public String showJoinForm(Model model){
        model.addAttribute("user", new User());
        return "join";
    }

    @PostMapping("/join")
    public String processJoin(@ModelAttribute User user){
        userService.createUser(user);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session, Model model) {
        User user = userService.loginUser(email, password);

        if (user != null) {
            // 로그인 성공 시 세션에 사용자 정보 저장
            session.setAttribute("loggedInUser", user);
            return "redirect:/main";
        } else {
            model.addAttribute("error", "존재하지 않는 유저입니다.");
            return "redirect:/login";
        }
    }


    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
    }

    @PostMapping("/forecast")
    public ResponseEntity<String> updateCoin(@RequestParam Integer newCoinValue, HttpSession session) {
        User loggedInUser = getLoggedInUser(session);

        if (loggedInUser != null) {
            loggedInUser.setCoin(newCoinValue);
            userRepository.save(loggedInUser);
            return ResponseEntity.ok("Coin updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
        }
    }

    @PostMapping("/buyPlayer")
    public ResponseEntity<String> buyPlayer(@RequestParam Long playerId, HttpSession session) {
        User loggedInUser = getLoggedInUser(session);

        if (loggedInUser != null) {
            Player player = playerService.getPlayerId(playerId);
            MyPlayer myPlayer = new MyPlayer();
            myPlayer.setUser(loggedInUser);
            myPlayer.setPlayer(player);

            myPlayerRepository.save(myPlayer);

            Integer newCoinValue = loggedInUser.getCoin() - player.getPrice();
            loggedInUser.setCoin(newCoinValue);
            userRepository.save(loggedInUser);

            return ResponseEntity.ok("Purchase successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
        }
    }
}

// 커밋하자