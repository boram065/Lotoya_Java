package com.example.Lotoya_Java.controller;

import com.example.Lotoya_Java.entity.Player;
import com.example.Lotoya_Java.entity.User;
import com.example.Lotoya_Java.repository.MyPlayerRepository;
import com.example.Lotoya_Java.repository.UserRepository;
import com.example.Lotoya_Java.service.PlayerService;
import com.example.Lotoya_Java.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class UserController{
    private final UserService userService;
    private final UserRepository userRepository;
    private final MyPlayerRepository myPlayerRepository;
    private final PlayerService playerService;
    private final JdbcTemplate jdbcTemplate;
    private static final String RESET_ID_SQL = "ALTER TABLE player AUTO_INCREMENT = 1";

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

    @GetMapping("/")
    public String showLoginPage(Model model) {
        model.addAttribute("user", new User());
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

    @PostMapping("/buyPlayer/{playerId}")
    public ResponseEntity<String> buyPlayer(@PathVariable Long playerId, HttpSession session) {
        User loggedInUser = getLoggedInUser(session);

        if (loggedInUser != null) {
            Optional<Player> playerOptional = playerService.getPlayer(playerId);

            if (playerOptional.isPresent()) {
                Player player = playerOptional.get();

                boolean isPlayerAlreadyBought = myPlayerRepository.existsByUserAndPlayer(loggedInUser, player);
                if (isPlayerAlreadyBought) {
                    return ResponseEntity.badRequest().body("alreadyBought");
                }

                if (loggedInUser.getCoin() < player.getPrice()) {
                    return ResponseEntity.badRequest().body("noMoney");
                }

                String insertMyPlayerQuery = "INSERT INTO my_player (user_id, player_id) VALUES (?, ?)";
                jdbcTemplate.update(insertMyPlayerQuery, loggedInUser.getId(), player.getId());
                jdbcTemplate.execute(RESET_ID_SQL);

                Integer newCoinValue = loggedInUser.getCoin() - player.getPrice();
                loggedInUser.setCoin(newCoinValue);
                userRepository.save(loggedInUser);

                Integer updatedCoin = userRepository.findById(loggedInUser.getId()).map(User::getCoin).orElse(0);
                return ResponseEntity.ok(updatedCoin+"");
            }
        } else {
            return ResponseEntity.badRequest().body("선수 찾을 수 없습니다");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("사용자가 로그인하지 않았습니다");
    }

    @PostMapping("/forecast-coins")
    public ResponseEntity<String> updateCoins(@RequestParam Integer totalCoinsDifference, HttpSession session) {
        User loggedInUser = getLoggedInUser(session);

        if (loggedInUser != null) {
            loggedInUser.setCoin(totalCoinsDifference);
            userRepository.save(loggedInUser);
            return ResponseEntity.ok("코인이 업데이트되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("사용자가 로그인하지 않았습니다.");
        }
    }
}