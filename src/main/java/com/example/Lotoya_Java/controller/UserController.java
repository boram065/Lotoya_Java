package com.example.Lotoya_Java.controller;

import com.example.Lotoya_Java.dto.UserContextHolder;
import com.example.Lotoya_Java.entity.User;
import com.example.Lotoya_Java.repository.UserRepository;
import com.example.Lotoya_Java.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final UserContextHolder userContextHolder;

    @GetMapping("/join")
    public String showJoinForm(Model model) {
        model.addAttribute("user", new User());
        return "join";
    }

    @PostMapping("/join")
    public String processJoin(@ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        HttpSession httpSession) {
        User user = userService.loginUser(email, password);

        if (user != null) {
            userContextHolder.setLoggedInUser(httpSession, user);
            return "redirect:/main";
        } else {
            return "redirect:/login";
        }
    }

    // 다른 메서드에서 세션 사용 예시
    @GetMapping("/profile")
    public String showProfile(HttpSession httpSession, Model model) {
        User loggedInUser = userContextHolder.getLoggedInUser(httpSession);

        if (loggedInUser != null) {
            model.addAttribute("currentUserCoin", loggedInUser.getCoin());
            model.addAttribute("loggedInUser", loggedInUser);
            return "profile";
        } else {
            return "redirect:/login";
        }
    }


    // 다른 메서드에서 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        userContextHolder.clearLoggedInUser(httpSession);
        return "redirect:/login";
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
    }

    @PostMapping("/forecast")
    public ResponseEntity<String> updateCoin(@RequestParam Integer newCoinValue, HttpSession httpSession) {
        userService.updateCoinValue(newCoinValue, httpSession);
        return ResponseEntity.ok("Coin updated successfully");
    }
}
